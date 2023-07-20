package com.dansmultipro.test.jobwebservices.controller;

import com.dansmultipro.test.jobwebservices.model.BaseResponse;
import com.dansmultipro.test.jobwebservices.model.response.JobPositionResponse;
import com.dansmultipro.test.jobwebservices.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping(value = "/job-positions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<List<JobPositionResponse>>> getAllJobPositions() {
        List<JobPositionResponse> allJobPositions = jobService.getAllJobPosition();
        BaseResponse<List<JobPositionResponse>> jobPositionResponses = BaseResponse.<List<JobPositionResponse>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(allJobPositions)
                .build();
        return new ResponseEntity<>(jobPositionResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/job-positions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<JobPositionResponse>> getJobPositionById(@PathVariable String id) {
        JobPositionResponse jobPosition = jobService.getJobPositionById(id);
        BaseResponse<JobPositionResponse> jobPositionResponse = BaseResponse.<JobPositionResponse>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(jobPosition)
                .build();
        return new ResponseEntity<>(jobPositionResponse, HttpStatus.OK);
    }

}
