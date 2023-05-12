package com.prueba.eventhub.servicios;


import com.prueba.eventhub.model.Ruv;
import com.prueba.eventhub.model.VentaReponse;

public interface ConsumerToEventHubService {

    Ruv consumerToEventHub(String eventHubName);

    Ruv consumerToEventHubNr(String eventHubName);

    String connectEventHub();

    VentaReponse consumerEventHubToSql(String eventHubName);

}
