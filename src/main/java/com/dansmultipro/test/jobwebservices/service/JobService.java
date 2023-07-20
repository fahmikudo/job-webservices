package com.dansmultipro.test.jobwebservices.service;

import com.dansmultipro.test.jobwebservices.model.response.JobPositionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface JobService {

    List<JobPositionResponse> getAllJobPosition();

    JobPositionResponse getJobPositionById(String id);

}
