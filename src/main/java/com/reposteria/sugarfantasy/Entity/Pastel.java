/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reposteria.sugarfantasy.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pastel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String relleno;
    private String bizcocho;
    private String cubierta;
    private String tamano;
    private int precioP; //precio torta pequeña
    private int precioM; //precio torta mediana
    
    @Temporal(TemporalType.DATE)
    private Date alta;
    @Temporal (TemporalType.DATE)
    private Date baja;
    
    @OneToOne
    @Lob
    private Foto foto;

    public Pastel() {
    }

    public Pastel(String nombre, String relleno, String bizcocho, String cubierta, String tamano) {
        this.nombre = nombre;
        this.relleno = relleno;
        this.bizcocho = bizcocho;
        this.cubierta = cubierta;
        this.tamano = tamano;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRelleno() {
        return relleno;
    }

    public void setRelleno(String relleno) {
        this.relleno = relleno;
    }

    public String getBizcocho() {
        return bizcocho;
    }

    public void setBizcocho(String bizcocho) {
        this.bizcocho = bizcocho;
    }

    public String getCubierta() {
        return cubierta;
    }

    public void setCubierta(String cubierta) {
        this.cubierta = cubierta;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public int getPrecioP() {
        return precioP;
    }

    public void setPrecioP(int precioP) {
        this.precioP = precioP;
    }

    public int getPrecioM() {
        return precioM;
    }

    public void setPrecioM(int precioM) {
        this.precioM = precioM;
    }
    
    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getBaja() {
        return baja;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pastel)) {
            return false;
        }
        Pastel other = (Pastel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reposteria.sugarfantasy.Entity.Pastel[ id=" + id + " ]";
    }

}
