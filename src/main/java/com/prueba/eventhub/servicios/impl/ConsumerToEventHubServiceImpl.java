package com.prueba.eventhub.servicios.impl;

import com.azure.core.util.IterableStream;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerClient;
import com.azure.messaging.eventhubs.models.EventPosition;
import com.azure.messaging.eventhubs.models.PartitionEvent;
import com.google.gson.Gson;
import com.prueba.eventhub.model.Sales;
import com.prueba.eventhub.model.VentaReponse;
import com.prueba.eventhub.repository.VentasRepository;
import com.prueba.eventhub.servicios.ConsumerToEventHubService;
import com.prueba.eventhub.servicios.DatabaseService;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.prueba.eventhub.model.Ruv;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ConsumerToEventHubServiceImpl implements ConsumerToEventHubService {

    private static final String SHARED_ACCESS_KEY_NAME = "RootManageSharedAccessKey";
    private static final String EVENT_HUB_NAMESPACE = "evh25261029";
    private static final String EVENT_HUB_NAME = "VentasEvent";
    private static final String SHARED_ACCESS_KEY = "JkG4ZL3xMRSL3Qvxj5+3PBndchWUWl6Jn+AEhCux8Fk=";
    private static final String EVENT_HUB_CONNECTION_STRING = "Endpoint=sb://" + EVENT_HUB_NAMESPACE + ".servicebus.windows.net:443/;SharedAccessKeyName=" + SHARED_ACCESS_KEY_NAME + ";SharedAccessKey=" + SHARED_ACCESS_KEY;
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DatabaseService databaseService;

    @Override
    public Ruv consumerToEventHub(String eventHubName) {
        // Configurar la conexión con el Event Hub
        try{
            EventHubConsumerClient consumerClient = new EventHubClientBuilder()
                    .connectionString(EVENT_HUB_CONNECTION_STRING, eventHubName)
                    .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                    .buildConsumerClient();
                System.out.println("conecto"+" - "+consumerClient.getFullyQualifiedNamespace());
                List<Ruv.RUVData> ventasList = new ArrayList<>();
                Ruv ventaRuv= new Ruv();


                Flux.fromIterable(consumerClient.receiveFromPartition("1", 300, EventPosition.earliest()))
                        .flatMap(event -> {
                            byte[] eventDataBytes = event.getData().getBody();
                            String eventDataString = new String(eventDataBytes, StandardCharsets.UTF_8);
                            Ruv.RUVData venta = new Gson().fromJson(eventDataString, Ruv.RUVData.class);
                            ventasList.add(venta);
                            return processEvent(event);
                        })
                        .doOnNext(result -> log.info("Event processed successfully: {}", result))
                        .doOnError(error -> log.error("Error processing event: {}", error.getMessage()))
                        .subscribe();

            consumerClient.close();

                ventaRuv.setData(ventasList);
                ventaRuv.setBatchId("123");
                ventaRuv.setTimestamp("08-05-2023");


                return ventaRuv;

        }catch (Exception e) {
            System.out.println("Error al establecer la conexión con el Event Hub: " + e.getMessage());
            return null;
        }

    }

    public Mono<String> processEvent(PartitionEvent event) {
        String messageBody = new String(event.getData().getBody());
        System.out.println("Received event: " + messageBody);
        return Mono.just("Event processed successfully");
    }

    @Override
    public String connectEventHub() {
        try {
            EventHubConsumerClient consumerClient = new EventHubClientBuilder()
                    .connectionString(EVENT_HUB_CONNECTION_STRING, EVENT_HUB_NAME)
                    .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                    .buildConsumerClient();
            return "Conexion satisfactoria.";
        }catch (Exception e){
            return "Error al conectar";
        }
    }

    @Override
    public Ruv consumerToEventHubNr(String eventHubName) {
        // Configurar la conexión con el Event Hub
        try{
            EventHubConsumerClient consumerClient = new EventHubClientBuilder()
                    .connectionString(EVENT_HUB_CONNECTION_STRING, eventHubName)
                    .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                    .buildConsumerClient();
            System.out.println("conecto"+" - "+consumerClient.getFullyQualifiedNamespace());

            Ruv ventaRuv= new Ruv();

            IterableStream<PartitionEvent> events = consumerClient.receiveFromPartition("1",5,EventPosition.earliest());

            // Almacenar los eventos en objetos Ventas
            List<Ruv.RUVData> ventasList = new ArrayList<>();
            events.forEach(event -> {
                byte[] eventDataBytes = event.getData().getBody();
                String eventDataString = new String(eventDataBytes, StandardCharsets.UTF_8);
                Ruv.RUVData venta = new Gson().fromJson(eventDataString, Ruv.RUVData.class);
                ventasList.add(venta);
            });

            consumerClient.close();

            ventaRuv.setData(ventasList);
            ventaRuv.setBatchId("123");
            ventaRuv.setTimestamp("08-05-2023");


            return ventaRuv;

        }catch (Exception e) {
            System.out.println("Error al establecer la conexión con el Event Hub: " + e.getMessage());
            return null;
        }
    }

    @Override
    public VentaReponse consumerEventHubToSql(String eventHubName) {

        try{
            EventHubConsumerClient consumerClient = new EventHubClientBuilder()
                    .connectionString(EVENT_HUB_CONNECTION_STRING, eventHubName)
                    .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                    .buildConsumerClient();
            System.out.println("conecto"+" - "+consumerClient.getFullyQualifiedNamespace());
            List<Sales> ventasList = new ArrayList<>();
            VentaReponse ventaResponse= new VentaReponse();

            Flux.fromIterable(consumerClient.receiveFromPartition("1", 300, EventPosition.earliest()))
                    .flatMap(event -> {
                        byte[] eventDataBytes = event.getData().getBody();
                        String eventDataString = new String(eventDataBytes, StandardCharsets.UTF_8);
                        Sales venta = new Gson().fromJson(eventDataString, Sales.class);
                        System.out.println("Hola");
                        ventasList.add(venta);
                        //Insertar a base de datos

                        return processEvent(event);
                    })
                    .doOnNext(result -> log.info("Event processed successfully: {}", result))
                    .doOnError(error -> log.error("Error processing event: {}", error.getMessage()))
                    .subscribe();

            consumerClient.close();
            ventaResponse.setData(ventasList);
            ventaResponse.setMessage(!ventasList.isEmpty()?"Operación exitosa.":"Sin datos.");


            return ventaResponse;

        }catch (Exception e) {
            System.out.println("Error al establecer la conexión con el Event Hub: " + e.getMessage());
            return null;
        }

    }
}
