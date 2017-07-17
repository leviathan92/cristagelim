/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.usuarios;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.dao.PersonaFacadeLocal;
import org.dao.RolFacadeLocal;
import org.entidades.Persona;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Named(value = "listarUsuarios")
@ConversationScoped
public class ListarUsuarios implements Serializable {

    @EJB
    private PersonaFacadeLocal pfl;
    @EJB
    private RolFacadeLocal rfl;
    @Inject
    private Conversation conversacion;
    private Persona personaSeleccionado;
    private List<Persona> persona;
    private List<Rol> rol;

    public ListarUsuarios() {

    }

    @PostConstruct
    public void init() {
        persona = pfl.findAll();
        rol = rfl.findAll();
        
    }

    public List<Persona> getPersona() {
        return persona;

    }

    public Persona getPersonaSeleccionado() {
        return personaSeleccionado;
    }

    public void setPersonaSeleccionado(Persona personaSeleccionado) {
        this.personaSeleccionado = personaSeleccionado;
    }

    public List<Rol> getRol() {
        return rol;
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
        return "/admin/usuarios/listarUsuarios.xhtml?faces-redirect=true";

    }

    public String preparacionEditar(Persona p) {
        iniciarConversacion();
        personaSeleccionado = p;

        return "/admin/usuarios/editar.xhtml?faces-redirect=true";
    }

    public String actualizarUsuario() {
        pfl.edit(personaSeleccionado);
        return cancelar();

    }

    public void preparacionEliminar(Persona p) {
        iniciarConversacion();
        personaSeleccionado = p;
        System.out.printf("se elimini: %s", personaSeleccionado.getNombre());

    }

    public void eliminarUsuario() {
        try {
            System.out.println("entro a eliminar");
            pfl.remove(personaSeleccionado);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al emiminar el usuario", "intentelo de nuevo");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        }

    }

}
