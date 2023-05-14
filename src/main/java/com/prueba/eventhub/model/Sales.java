package com.prueba.eventhub.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Sales {
    @JsonAlias("codClaveVenta")
    @Column("codClaveVenta")
    private String codClaveVenta;
    @JsonAlias("tipProductoBcaMinorista")
    @Column("tipProductoBcaMinorista")
    private String tipProductoBcaMinorista;
    @JsonAlias("codMes")
    @Column("codMes")
    private String codMes;
    @JsonAlias("codInternoComputacional")
    @Column("codInternoComputacional")
    private String codInternoComputacional;
    @JsonAlias("codClaveUnicoClidl")
    @Column("codClaveUnicoClidl")
    private String codClaveUnicoClidl;
    @JsonAlias("codVendedor")
    @Column("codVendedor")
    private String codVendedor;
    @JsonAlias("fecVenta")
    @Column("fecVenta")
    private String fecVenta;
    @JsonAlias("mtoVenta")
    @Column("mtoVenta")
    private String mtoVenta;
    @JsonAlias("codMoneda")
    @Column("codMoneda")
    private String codMoneda;
    @JsonAlias("codProducto")
    @Column("codProducto")
    private String codProducto;
    @JsonAlias("codSolicitud")
    @Column("codSolicitud")
    private String codSolicitud;
    @JsonAlias("tipVtaProductoBcaMinorista")
    @Column("tipVtaProductoBcaMinorista")
    private String tipVtaProductoBcaMinorista;
    @JsonAlias("codProductoAplicativo")
    @Column("codProductoAplicativo")
    private String codProductoAplicativo;
    @JsonAlias("codEstVtaProdBcaMinorista")
    @Column("codEstVtaProdBcaMinorista")
    private String codEstVtaProdBcaMinorista;
    @JsonAlias("codSistVtaProdBcaMinorista")
    @Column("codSistVtaProdBcaMinorista")
    private String codSistVtaProdBcaMinorista;
    @JsonAlias("flgRegEliminado")
    @Column("flgRegEliminado")
    private String flgRegEliminado;

}