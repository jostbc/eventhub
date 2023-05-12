package com.prueba.eventhub.servicios;

import com.prueba.eventhub.model.Sales;
import reactor.core.publisher.Mono;

public interface DatabaseService {
	Mono<Sales> insertSales(Sales sales);
}
