package com.prueba.eventhub.servicios;


import com.prueba.eventhub.model.Ruv;
import com.prueba.eventhub.model.VentaResponse;

public interface ConsumerToEventHubService {

    Ruv consumerToEventHub(String eventHubName);

    Ruv consumerToEventHubNr(String eventHubName);

    String connectEventHub();

    VentaResponse consumerEventHubToSql(String eventHubName);

}
