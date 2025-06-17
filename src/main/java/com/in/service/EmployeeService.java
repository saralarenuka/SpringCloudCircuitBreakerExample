package com.in.service;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmployeeService {

	// Circuit Breaker method
	 @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
	@CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackMethod")
	public String riskyApiCall() {
		System.out.println("Calling risky external service...");

		// Simulate random failure
		if (Math.random() > 0.5) {
			System.out.println(Math.random());
			throw new RuntimeException("Service failure");
		}
		return "YAAHO! SUCCESS FROM EMPLOYEE SERVICE";
	}

	// Fallback method
	public String fallbackMethod(Throwable t) {
		return "Fallback Response due to ERROR: " + t.getMessage();
	}
}





