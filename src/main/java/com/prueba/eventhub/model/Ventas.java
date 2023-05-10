package com.prueba.eventhub.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ventas {

    private String codClaveVenta;
    private String tipProductoBcaMinorista;
    private String codMes;
    private String codInternoComputacional;
    private String codClaveUnicoClidl;
    private String codVendedor;
    private String fecVenta;
    private String mtoVenta;
    private String codMoneda;
    private String codProducto;
    private String codSolicitud;
    private String tipVtaProductoBcaMinorista;
    private String codProductoAplicativo;
    private String codEstVtaProdBcaMinorista;
    private String codSistVtaProdBcaMinorista;
    private String flgRegEliminado;
}