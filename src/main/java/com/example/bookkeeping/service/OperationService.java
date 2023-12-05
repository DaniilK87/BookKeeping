package com.example.bookkeeping.service;

import com.example.bookkeeping.dto.StudentGrantDto;
import com.example.bookkeeping.dto.TeacherSalaryDto;

public interface OperationService {
//    RatingDto getStudentRating(Integer studentId);
    void sendGrant(StudentGrantDto grantDto, Integer balanceId, Integer studentId);
    void sendSalary(TeacherSalaryDto salaryDto, Integer balanceId, Integer teacherId);
}
