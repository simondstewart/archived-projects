package com.deltek.integration.trafficlive.service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.deltek.integration.trafficlive.domain.BaseTO;
import com.deltek.integration.trafficlive.domain.EmployeePagedResultsTO;
import com.deltek.integration.trafficlive.domain.JobPagedResultsTO;
import com.deltek.integration.trafficlive.domain.JobTO;
import com.deltek.integration.trafficlive.domain.JobTaskTimeEntryPagedResultsTO;
import com.deltek.integration.trafficlive.domain.PagedResultsTO;
import com.deltek.integration.trafficlive.domain.TrafficEmployeeTO;

@Component
public class TrafficLiveRestClient {

	private final String apiBasePath;
	
	private static final Map<String, String> EMPTY_MAP = Collections.unmodifiableMap(new HashMap<String, String>());
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public TrafficLiveRestClient(RestTemplate restTemplate, String apiBasePath) {
		super();
		this.restTemplate = restTemplate;
		this.apiBasePath = apiBasePath;
	}

	public JobPagedResultsTO getJobs(Integer currentPage, Integer windowSize) {
		return getPagedResults(new ParameterizedTypeReference<JobPagedResultsTO>() {}, "/job", currentPage, windowSize);
	}

	public EmployeePagedResultsTO getEmployees(Integer currentPage, Integer windowSize) {
		return getPagedResults(new ParameterizedTypeReference<EmployeePagedResultsTO>() {}, "/staff/employee", currentPage, windowSize);
	}
	
	public JobTaskTimeEntryPagedResultsTO getJobTaskTimeEntries(Integer currentPage, Integer windowSize) {
		return getPagedResults(new ParameterizedTypeReference<JobTaskTimeEntryPagedResultsTO>() {}, "/timeentries", currentPage, windowSize, EMPTY_MAP);
	}

	public JobTaskTimeEntryPagedResultsTO getJobTaskTimeEntries(Integer currentPage, Integer windowSize, Map<String, String> parameters) {
		return getPagedResults(new ParameterizedTypeReference<JobTaskTimeEntryPagedResultsTO>() {}, "/timeentries", currentPage, windowSize);
	}
	
	public <TO extends BaseTO, PAGE extends PagedResultsTO<TO>> PAGE getPagedResults(ParameterizedTypeReference<PAGE> parameterizedTypeReference, 
			String relativePath, Integer currentPage, Integer windowSize) {
		return getPagedResults(parameterizedTypeReference, relativePath, currentPage, windowSize, EMPTY_MAP);
	}
	
	public <TO extends BaseTO, PAGE extends PagedResultsTO<TO>> PAGE getPagedResults(ParameterizedTypeReference<PAGE> parameterizedTypeReference, 
																				String relativePath, Integer currentPage, Integer windowSize,
																				Map<String, String> parameterMap) {
		ResponseEntity<PAGE> page = getPage(parameterizedTypeReference, 
											buildUrl(apiBasePath, relativePath, currentPage, windowSize, parameterMap), 
											createHttpEntity());
		return page.getBody();
	}
	
	public <TO extends BaseTO> TO getById(String basePath, Long id) {
		return null;
	}
	
	public <TO extends BaseTO> TO update(String basePath, TO toUpdate) {
		return null;
	}
	
	public <TO extends BaseTO> TO create(String basePath, TO toCreate) {
		return null;
	}
	
	private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}

	private URI buildUrl(String baseUrl, String relativePath, Integer currentPage, Integer windowSize, Map<String, String> parameterMap) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + relativePath).queryParam("windowSize", windowSize);
        builder.queryParam("currentPage", currentPage);
        parameterMap.forEach((String key, String value) -> builder.queryParam(key, value));
        return builder.build().encode().toUri();
	}
	
	private <TO extends BaseTO, PAGE extends PagedResultsTO<TO>> ResponseEntity<PAGE> 
												getPage(ParameterizedTypeReference<PAGE> pageType, URI uri, HttpEntity<String> entity) {
		ResponseEntity<PAGE> response = null;

		try {
			response = restTemplate.exchange(uri, HttpMethod.GET, entity, pageType);
		} catch (HttpStatusCodeException ex) {
			throw new RuntimeException(ex);
		}
		return response;
	}
	
}
