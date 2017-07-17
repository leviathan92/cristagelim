/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.materia_prima;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.MateriaPrimaFacadeLocal;
import org.entidades.MateriaPrima;

/**
 *
 * @author David
 */
@Named(value = "crearMateriaPrima")
@ViewScoped
public class CrearMateriaPrima implements Serializable {

    @EJB
    private MateriaPrimaFacadeLocal materiaPrimaFacadeLocal;
    private MateriaPrima materiaPrima;
    private String referencia,
            tipoMateriaPrima;
    private int calibre,
            dimensionLargo,
            dimensionAlto;

    public CrearMateriaPrima() {

    }

    @PostConstruct
    public void init() {

    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipoMateriaPrima() {
        return tipoMateriaPrima;
    }

    public void setTipoMateriaPrima(String tipoMateriaPrima) {
        this.tipoMateriaPrima = tipoMateriaPrima;
    }

    public int getCalibre() {
        return calibre;
    }

    public void setCalibre(int calibre) {
        this.calibre = calibre;
    }

    public int getDimensionLargo() {
        return dimensionLargo;
    }

    public void setDimensionLargo(int dimensionLargo) {
        this.dimensionLargo = dimensionLargo;
    }

    public int getDimensionAlto() {
        return dimensionAlto;
    }

    public void setDimensionAlto(int dimensionAlto) {
        this.dimensionAlto = dimensionAlto;
    }

    public String crear() {
        try {
            materiaPrima = new MateriaPrima(null, referencia, tipoMateriaPrima, calibre, dimensionLargo, dimensionAlto);
            materiaPrimaFacadeLocal.create(materiaPrima);
            return "/admin/materiaPrima/ListarMateriaPrima.xhtml?faces-redirect=true";

        } catch (Exception e) {
            return "";

        }

    }

    public String cancelar() {
        return "/admin/materiaPrima/ListarMateriaPrima.xhtml?faces-redirect=true";

    }

    public String prueba() {
        return "/admin/materiaPrima/ListarMateriaPrima.xhtml?faces-redirect=true";

    }

}
