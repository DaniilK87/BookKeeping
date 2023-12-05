package com.example.bookkeeping.controller;

import com.example.bookkeeping.dto.StudentGrantDto;
import com.example.bookkeeping.dto.TeacherSalaryDto;
import com.example.bookkeeping.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Операции по счетам", description = "REST API операций по счету")
public class OperationController {
    private final OperationService operationService;
    @PostMapping("grants/{balanceId}/{studentId}")
    @Operation(summary = "Стипендия", description = "Начитсление стипендии студенту")
    public void grant(@RequestBody StudentGrantDto studentGrantDto,
                              @PathVariable Integer balanceId,@PathVariable Integer studentId) {
        operationService.sendGrant(studentGrantDto,balanceId,studentId);
    }
    @PostMapping("salary/{balanceId}/{teacherId}")
    @Operation(summary = "Зарплата", description = "Начитсление зарплаты преподавателю")
    public void salary(@RequestBody TeacherSalaryDto teacherSalaryDto,
                              @PathVariable Integer balanceId,@PathVariable Integer teacherId) {
        operationService.sendSalary(teacherSalaryDto,balanceId,teacherId);
    }
}
