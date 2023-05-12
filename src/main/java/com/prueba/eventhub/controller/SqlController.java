package com.prueba.eventhub.controller;

import com.prueba.eventhub.model.Ruv;
import com.prueba.eventhub.model.Sales;
import com.prueba.eventhub.model.VentaReponse;
import com.prueba.eventhub.repository.VentasRepository;
import com.prueba.eventhub.servicios.ConsumerToEventHubService;
import com.prueba.eventhub.servicios.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class SqlController {

	@Autowired
	ConsumerToEventHubService consumerToEventHubService;
	@Autowired
	DatabaseService databaseService;

	private final VentasRepository ventasRepository;

	public SqlController(VentasRepository ventasRepository) {
		this.ventasRepository = ventasRepository;
	}

	@GetMapping("/ventas")
	public Flux<Sales> getVentas() {
		return ventasRepository.findAll();
	}

	@PostMapping("/ventas")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Sales> createVentas(@RequestBody Sales salesOperation) {
		return ventasRepository.save(salesOperation);
	}

	@PostMapping("/ventas1")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Sales> createVentas1(@RequestBody Sales salesOperation) {
		return databaseService.insertSales(salesOperation);
	}

	@GetMapping(value = "/eventhubsql", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public VentaReponse findEventNameSql(@RequestParam String eventHubName) {
		return consumerToEventHubService.consumerEventHubToSql(eventHubName);
	}

	@GetMapping(value = "/eventhub", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public Ruv findEventName(@RequestParam String eventHubName) {
		return consumerToEventHubService.consumerToEventHub(eventHubName);
	}
}
