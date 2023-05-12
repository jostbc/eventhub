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
		Sales sales2= new Sales();
		sales2.setCodClaveVenta(sales.getCodClaveVenta());
		sales2.setTipProductoBcaMinorista(sales.getTipProductoBcaMinorista());
		sales2.setCodMes(sales.getCodMes());
		sales2.setCodInternoComputacional(sales.getCodInternoComputacional());
		sales2.setCodClaveUnicoClidl(sales.getCodClaveUnicoClidl());
		sales2.setCodVendedor(sales.getCodVendedor());
		sales2.setFecVenta(sales.getFecVenta());
		sales2.setMtoVenta(sales.getMtoVenta());
		sales2.setCodMoneda(sales.getCodMoneda());
		sales2.setCodProducto(sales.getCodProducto());
		sales2.setCodSolicitud(sales.getCodSolicitud());
		sales2.setTipVtaProductoBcaMinorista(sales.getTipVtaProductoBcaMinorista());
		sales2.setCodProductoAplicativo(sales.getCodProductoAplicativo());
		sales2.setCodEstVtaProdBcaMinorista(sales.getCodEstVtaProdBcaMinorista());
		sales2.setCodSistVtaProdBcaMinorista(sales.getCodSistVtaProdBcaMinorista());
		sales2.setFlgRegEliminado(sales.getFlgRegEliminado());
		return ventasRepository.save(sales2);
	}
}
