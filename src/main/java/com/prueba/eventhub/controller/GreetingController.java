package com.prueba.eventhub.controller;

import com.prueba.eventhub.model.Ruv;
import com.prueba.eventhub.servicios.ConsumerToEventHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

//@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	ConsumerToEventHubService consumerToEventHubService;

	@GetMapping("/hola/{nombre}")
	public String holaMundo(@PathVariable String nombre) {
		return "¡Hola, " + nombre + "!";
	}

	@GetMapping(value = "/eventhub", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public Ruv findEventName(@RequestParam String eventHubName) {
		return consumerToEventHubService.consumerToEventHubNr(eventHubName);
	}

	@GetMapping(value = "/connect", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public String findEventName() {
		return consumerToEventHubService.connectEventHub();
	}



}
