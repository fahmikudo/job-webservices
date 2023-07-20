package com.dansmultipro.test.jobwebservices.service.impl;

import com.dansmultipro.test.jobwebservices.model.ClientResponse;
import com.dansmultipro.test.jobwebservices.model.response.JobPositionResponse;
import com.dansmultipro.test.jobwebservices.outbound.JobClient;
import com.dansmultipro.test.jobwebservices.service.JobService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobClient jobClient;

    private final ObjectMapper objectMapper;

    @Override
    public List<JobPositionResponse> getAllJobPosition() {
        ClientResponse<String> clientResponse = jobClient.getListJob();
        if (!clientResponse.getHttpStatus().is2xxSuccessful()) {
            throw new ResponseStatusException(clientResponse.getHttpStatus(), clientResponse.getContent());
        }
        List<JobPositionResponse> jobPositionResponses;
        try {
            jobPositionResponses = objectMapper.readValue(clientResponse.getContent(), new TypeReference<List<JobPositionResponse>>(){});
        } catch (JsonProcessingException er) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, er.getMessage());
        }
        return jobPositionResponses;
    }

    @Override
    public JobPositionResponse getJobPositionById(String id) {
        ClientResponse<String> clientResponse = jobClient.getJobById(id);
        if (!clientResponse.getHttpStatus().is2xxSuccessful()) {
            throw new ResponseStatusException(clientResponse.getHttpStatus(), clientResponse.getContent());
        }
        JobPositionResponse jobPositionResponse;
        try {
            jobPositionResponse = objectMapper.readValue(clientResponse.getContent(), JobPositionResponse.class);
        } catch (JsonProcessingException er) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, er.getMessage());
        }
        return jobPositionResponse;
    }
}
