package com.prueba.eventhub.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Ruv {

  private String batchId;

  private String timestamp;

  private List<RUVData> data;


  @Getter
  @Setter
  public static class RUVData {

    private String codClaveVenta;

    private String tipProductoBcaMinorista;

    private int codMes;

    private String codInternoComputacional;

    private String codClaveUnicoClidl;

    private String codVendedor;

    private String fecVenta;

    private double mtoVenta;

    private String codMoneda;

    private String codProducto;

    private String codSolicitud;

    private String tipVtaProductoBcaMinorista;

    private String codProductoAplicativo;

    private int codEstVtaProdBcaMinorista;

    private String codSistVtaProdBcaMinorista;

    private String flgRegEliminado;


  }


}


