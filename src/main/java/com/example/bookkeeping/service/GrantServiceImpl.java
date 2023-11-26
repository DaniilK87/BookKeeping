package com.example.bookkeeping.service;

import com.example.bookkeeping.dto.BalanceResponseDto;
import com.example.bookkeeping.dto.MoneyRequestDto;
import com.example.bookkeeping.dto.RatingResponseDto;
import com.example.bookkeeping.entity.Grants;
import com.example.bookkeeping.repo.GrantRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GrantServiceImpl implements GrantService {

    private static final Logger log = Logger.getLogger(GrantServiceImpl.class.getName());
    private final RestTemplate restTemplate;
    private final GrantRepository grantRepository;
    private LocalDate date = LocalDate.now();


    public GrantServiceImpl(RestTemplateBuilder restTemplateBuilder, GrantRepository grantRepository) {
        restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        this.grantRepository = grantRepository;
    }
    @Override
    public RatingResponseDto getRating(Integer studentId) {
        String url = "http://localhost:8088/api/groups/student/rating/" + studentId;
        ResponseEntity<RatingResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RatingResponseDto>() {});
        return response.getBody();
    }
    @Override
    public void transferMoney(MoneyRequestDto moneyRequestDto, Integer balanceId, Integer studentId) {
        RatingResponseDto rating = getRating(studentId);
        Grants balance = grantRepository.getReferenceById(balanceId);
        String url = "http://localhost:8088/api/groups/student/grants/" + studentId;
        if (rating.getRating() >= 4) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Grants grants = new Grants();
            grants.setSum(moneyRequestDto.getGrant());
            grants.setTransactionType(moneyRequestDto.getTransactionType());
            balance.setBalance(balance.getBalance() - moneyRequestDto.getGrant());
            grants.setDate(date.toString());
            HttpEntity<MoneyRequestDto> httpEntity = new HttpEntity<>(moneyRequestDto, headers);
            restTemplate.postForObject(url,httpEntity,MoneyRequestDto.class);
            log.info("Деньги отправлены" + " " + studentId + " " + moneyRequestDto.getGrant());
            grantRepository.save(grants);
            grantRepository.save(balance);
        } else {
            log.info("Деньги не отправлены, рейтинг ниже 4");
        }
    }
    @Override
    public void changeBalance(BalanceResponseDto balanceResponseDto, Integer balanceId) {
        Grants balance = grantRepository.getReferenceById(balanceId);
        if (balance.getBalance() == null) {
            balance.setBalance(balanceResponseDto.getBalance());
        } else {
            balance.setBalance(balance.getBalance() + balanceResponseDto.getBalance());
            log.info("Счет пополнен на : " + balanceResponseDto.getBalance());
        }
        balance.setTransactionType(balanceResponseDto.getTransactionType());
        balance.setDate(date.toString());
        grantRepository.save(balance);
    }
    @Override
    public void createBalance(BalanceResponseDto balanceResponseDto) {
        Grants balance = new Grants();
        balance.setBalance(balanceResponseDto.getBalance());
        balance.setTransactionType(balanceResponseDto.getTransactionType());
        balance.setDate(date.toString());
        grantRepository.save(balance);
        log.info("Счет id: " + balance.getId() +  "успешно создан");
    }

    @Override
    public BalanceResponseDto getBalanceById(Integer balanceId) {
        Grants grants = grantRepository.getReferenceById(balanceId);
        BalanceResponseDto balanceResponseDto = new BalanceResponseDto();
        balanceResponseDto.setId(grants.getId());
        balanceResponseDto.setBalance(grants.getBalance());
        balanceResponseDto.setTransactionType(grants.getTransactionType());
        return balanceResponseDto;
    }

}
