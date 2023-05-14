package com.prueba.eventhub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VentaResponse {

	private String batchId;
	private String fileName;
	private Date date;
	private String status;
	private List<Sales> data;

}
