/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.provedores;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.CiudadFacadeLocal;
import org.dao.ProveedorFacadeLocal;
import org.entidades.Ciudad;
import org.entidades.Proveedor;

/**
 *
 * @author David
 */
@Named(value = "crearProveedor")
@ViewScoped
public class CrearProveedor implements Serializable{
    
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    @EJB
    private CiudadFacadeLocal ciudadFacadeLocal;
    private Proveedor proveedor;
    private String nombreEmpresa,
            representanteLegal,
            direccion,
            nitProveedor;
    private int idProveedor,
            telefono,
            ciudadesIdCiudad;
    
    public CrearProveedor() {
        
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCiudadesIdCiudad() {
        return ciudadesIdCiudad;
    }

    public void setCiudadesIdCiudad(int ciudadesIdCiudad) {
        this.ciudadesIdCiudad = ciudadesIdCiudad;
    }
    
    public String crear(){
        proveedor = new Proveedor(null, nitProveedor, nombreEmpresa, direccion, telefono, representanteLegal);
        Ciudad ciudad  = ciudadFacadeLocal.find(1);
        proveedor.setCiudadesIdCiudad(ciudad);
        proveedorFacadeLocal.create(proveedor);
        return "/admin/provedores/listarProveedores.xhtml?faces-redirect=true";
        
    }
    
    public String cancelar() {
        return "/admin/provedores/listarProveedores.xhtml?faces-redirect=true";
        
    }
       
}