package com.prueba.eventhub.repository;

import com.prueba.eventhub.model.Sales;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VentasRepository extends ReactiveCrudRepository<Sales, String> {

}