package com.prueba.eventhub.controller;

import com.prueba.eventhub.model.Ruv;
import com.prueba.eventhub.servicios.ConsumerToEventHubService;
//import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux; @RestController

public class ReactiveController {

	@Autowired
	ConsumerToEventHubService consumerToEventHubService;

	@GetMapping("/ejemplo-flux")
	public Flux<String> ejemploFlux() {
		return Flux.just("Hola", "mundo", "reactivo");
	}

	@GetMapping(value= "/eventhub-flux", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public Flux<Ruv> findEventName(@RequestParam String eventHubName) {
		return Flux.just(consumerToEventHubService.consumerToEventHubNr(eventHubName));
	}
/*
	@GetMapping(value = "/eventhub-rxjava", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public Observable<Ruv> findEventName1(@RequestParam String eventHubName) {
		return Observable.just(consumerToEventHubService.consumerToEventHubNr(eventHubName));
	}
*/

}

