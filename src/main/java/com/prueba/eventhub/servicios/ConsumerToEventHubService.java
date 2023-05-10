package com.prueba.eventhub.servicios;


import com.prueba.eventhub.model.Ruv;

public interface ConsumerToEventHubService {

    Ruv consumerToEventHub(String eventHubName);

    Ruv consumerToEventHubNr(String eventHubName);

    String connectEventHub();

}
