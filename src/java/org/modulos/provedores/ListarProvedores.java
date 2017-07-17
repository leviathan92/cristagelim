/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.provedores;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.dao.ProveedorFacadeLocal;
import org.entidades.Proveedor;

/**
 *
 * @author David
 */
@Named(value = "listarProvedores")
@ConversationScoped
public class ListarProvedores implements Serializable {

    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    @Inject
    private Conversation conversacion;
    private Proveedor proveedorSeleccionado;
    private List<Proveedor> listaProveedores;

    public ListarProvedores() {

    }

    @PostConstruct
    public void init() {
        System.out.println("sdasdsdasdsa");
        listaProveedores=proveedorFacadeLocal.findAll();
    }

    public List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public void iniciarConversacion() {
        if (conversacion.isTransient()) {
            conversacion.begin();

        }

    }
    
    public void terminarConversacion(){
        if(!conversacion.isTransient()){
            conversacion.end();
            
        }
        
    }
    
    public String cancelar(){
        terminarConversacion();
        return "/admin/provedores/listarProveedores.xhtml?faces-redirect=true";
                
    }
    
    public String prepararEditar(Proveedor p){
        iniciarConversacion();
        proveedorSeleccionado = p;
        return "/admin/provedores/editarProveedor.xhtml?faces-redirect=true";
        
    }
    
    public String actualizar(){
        proveedorFacadeLocal.edit(proveedorSeleccionado);
        return cancelar();
    
    }

}
