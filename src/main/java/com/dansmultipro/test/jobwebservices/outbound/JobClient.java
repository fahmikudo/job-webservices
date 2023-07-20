package com.dansmultipro.test.jobwebservices.outbound;

import com.dansmultipro.test.jobwebservices.model.ClientResponse;
import com.dansmultipro.test.jobwebservices.model.response.JobPositionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
@Log4j2
public class JobClient {

    @Value("${job.dans.multipro.url}")
    private String dansMultiProUrl;

    @Value("${job.dans.multipro.list.jobs.uri}")
    private String listJobUri;

    @Value("${job.dans.multipro.position.id.uri}")
    private String jobPositionByIdUri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public ClientResponse<String> getListJob() {
        ClientResponse<String> clientResponse = new ClientResponse<>();
        try {
            final String URL = dansMultiProUrl.concat(listJobUri);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<List<JobPositionResponse>> response = restTemplate.exchange(
                    URL,
                    HttpMethod.GET,
                    new HttpEntity<>(httpHeaders),
                    new ParameterizedTypeReference<List<JobPositionResponse>>() {
                    }
            );

            clientResponse.setHttpStatus(response.getStatusCode());
            clientResponse.setContent(Objects.requireNonNull(objectMapper.writeValueAsString(response.getBody())));
            return clientResponse;
        } catch (HttpClientErrorException ex) {
            String responseError = ex.getResponseBodyAsString();
            clientResponse.setContent(responseError);
            clientResponse.setHttpStatus(ex.getStatusCode());
            return clientResponse;
        } catch (Exception e) {
            clientResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            clientResponse.setContent(e.getLocalizedMessage());
            return clientResponse;
        }
    }

    public ClientResponse<String> getJobById(String id) {
        ClientResponse<String> clientResponse = new ClientResponse<>();
        try {
            String uri = jobPositionByIdUri.replace("{id}", id);
            final String URL = dansMultiProUrl.concat(uri);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<JobPositionResponse> response = restTemplate.exchange(
                    URL,
                    HttpMethod.GET,
                    new HttpEntity<>(httpHeaders),
                    JobPositionResponse.class
            );

            clientResponse.setHttpStatus(response.getStatusCode());
            clientResponse.setContent(Objects.requireNonNull(objectMapper.writeValueAsString(response.getBody())));
            return clientResponse;
        } catch (HttpClientErrorException ex) {
            String responseError = ex.getResponseBodyAsString();
            clientResponse.setContent(responseError);
            clientResponse.setHttpStatus(ex.getStatusCode());
            return clientResponse;
        } catch (Exception e) {
            clientResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            clientResponse.setContent(e.getLocalizedMessage());
            return clientResponse;
        }
    }


}
