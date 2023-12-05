package com.example.bookkeeping.service;

import com.example.bookkeeping.dto.StudentGrantDto;
import com.example.bookkeeping.dto.RatingDto;
import com.example.bookkeeping.dto.TeacherSalaryDto;
import com.example.bookkeeping.entity.Balance;
import com.example.bookkeeping.entity.Operation;
import com.example.bookkeeping.entity.TransactionType;
import com.example.bookkeeping.handling_exception.TransferMoneyException;
import com.example.bookkeeping.repo.BalanceRepository;
import com.example.bookkeeping.repo.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {
    private final RestTemplate restTemplate;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private BalanceRepository balanceRepository;
    private LocalDate date = LocalDate.now();
    public OperationServiceImpl(RestTemplateBuilder restTemplateBuilder, OperationRepository operationRepository) {
        restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        this.operationRepository = operationRepository;
    }
    private RatingDto getStudentRating(Integer studentId) {
        String url = "http://localhost:8088/api/students/rating/" + studentId;
        ResponseEntity<RatingDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RatingDto>() {});
        return response.getBody();
    }
    private RatingDto getTeacherRating(Integer teacherId) {
        String url = "http://localhost:8088/api/teachers/rating/" + teacherId;
        ResponseEntity<RatingDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RatingDto>() {});
        return response.getBody();
    }
    @Override
    public void sendGrant(StudentGrantDto grantDto, Integer balanceId, Integer studentId) {
        RatingDto rating = getStudentRating(studentId);
        Balance balance = balanceRepository.getReferenceById(balanceId);
        if (balance.getTransactionType() != TransactionType.GRANT)
            throw new TransferMoneyException("для проведения транзакции выберите счет с типом "
                + TransactionType.SALARY.getType());
        String url = "http://localhost:8088/api/students/grants/" + studentId;
        if (rating.getRating() >= 4) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Operation operation = new Operation();
            operation.setSum(grantDto.getSum());
            operation.setDate(date.toString());
            operation.setType(balance.getTransactionType().getType());
            operation.setBalance(balance);
            HttpEntity<StudentGrantDto> httpEntity = new HttpEntity<>(grantDto, headers);
            restTemplate.postForObject(url,httpEntity, StudentGrantDto.class);
            log.info("Деньги отправлены" + " " + studentId + " " + grantDto.getSum());
            balance.setBalance(balance.getBalance() - operation.getSum());
            operationRepository.save(operation);
            balanceRepository.save(balance);
        } else {
            log.info("Деньги не отправлены, средний бал ниже 4");
            throw new TransferMoneyException("Деньги не отправлены, средний бал ниже 4");
        }
    }
    @Override
    public void sendSalary(TeacherSalaryDto salaryDto, Integer balanceId, Integer teacherId) {
        RatingDto rating = getTeacherRating(teacherId);
        Balance balance = balanceRepository.getReferenceById(balanceId);
        if (balance.getTransactionType() != TransactionType.SALARY)
            throw new TransferMoneyException("для проведения транзакции выберите счет с типом "
                    + TransactionType.SALARY.getType());
        String url = "http://localhost:8088/api/teachers/salary/" + teacherId;
        HttpHeaders headers = new HttpHeaders();
        Operation operation = new Operation();
        operation.setDate(date.toString());
        operation.setType(balance.getTransactionType().getType());
        switch (rating.getRating()) {
            case 1:
                operation.setSum(salaryDto.getSum());
                balance.setBalance(balance.getBalance() - operation.getSum());
                log.info("зп начисленна, сумма: " + operation.getSum());
                break;
            case 2:
                salaryDto.setSum(salaryDto.getSum() * 2);
                operation.setSum(salaryDto.getSum());
                balance.setBalance(balance.getBalance() - operation.getSum());
                log.info("зп начисленна, сумма: " + operation.getSum());
                break;
            case 3:
                salaryDto.setSum(salaryDto.getSum() * 3);
                operation.setSum(salaryDto.getSum());
                log.info("зп начисленна, сумма: " + operation.getSum());
                break;
            default:
                throw new TransferMoneyException("зп не начислена, ставка "
                        + rating.getRating() + " не заведена в систему");
        }
        operation.setBalance(balance);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TeacherSalaryDto> httpEntity = new HttpEntity<>(salaryDto, headers);
        restTemplate.postForObject(url,httpEntity, StudentGrantDto.class);
        operationRepository.save(operation);
        balanceRepository.save(balance);
    }
}
