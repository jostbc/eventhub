package com.prueba.eventhub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VentaReponse {

	private String message;

	private List<Sales> data;

}
