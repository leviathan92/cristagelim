/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.proyectos;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.dao.ProyectoFacadeLocal;
import org.entidades.Proyecto;

/**
 *
 * @author David
 */
@Named(value = "listarProyectos")
@ConversationScoped
public class ListarProyectos implements Serializable {

    @EJB
    private ProyectoFacadeLocal pfl;
    @Inject
    private Conversation conversacion;
    private Proyecto proyectoSeleccionado;
    private List<Proyecto> proyecto;

    public ListarProyectos() {
        
    }

    @PostConstruct
    public void init() {
        proyecto = pfl.findAll();

    }

    public List<Proyecto> getProyecto() {
        return proyecto;
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    private void iniciarConversacion() {
        if (conversacion.isTransient()) {
            conversacion.begin();

        }

    }

    private void terminarConversacion() {
        if (!conversacion.isTransient()) {
            conversacion.end();

        }

    }

    public String cancelar() {
        terminarConversacion();
        return "/admin/proyectos/listarProyectos.xhtml?faces-redirect=true";

    }
    public String prueba(){
        terminarConversacion();
        return "/admin/proyectos/listarProyectos.xhtml?faces-redirect=true";
    }

    public String preparacionEditar(Proyecto p) {
        iniciarConversacion();
        proyectoSeleccionado = p;
        return "/admin/proyectos/editarProyecto.xhtml?faces-redirect=true";

    }

    public String actualizarProyecto() {
        pfl.edit(proyectoSeleccionado);
        return cancelar();

    }

    public void eliminarProyecto() {
        try {
            System.out.println("entro a eliminar");
            pfl.remove(proyectoSeleccionado);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "proyecto eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al eliminar el usuario", "intentelo de nuevo");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        }

    }

}
