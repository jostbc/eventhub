package com.prueba.eventhub.servicios.impl;

import com.prueba.eventhub.model.Sales;
import com.prueba.eventhub.repository.VentasRepository;
import com.prueba.eventhub.servicios.DatabaseService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DatabaseServiceImpl implements DatabaseService {

	private final VentasRepository ventasRepository;

	public DatabaseServiceImpl(VentasRepository ventasRepository) {
		this.ventasRepository = ventasRepository;
	}

	@Override
	public Mono<Sales> insertSales(Sales sales) {
		return ventasRepository.save(sales);
	}
}
