/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.login;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.dao.PersonaFacadeLocal;
import org.entidades.Permiso;
import org.entidades.Persona;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Named(value = "controladorSesion")
@SessionScoped
public class ControladorSesion implements Serializable {

    @EJB
    private PersonaFacadeLocal pfl;
    private Persona p;

    private String password;
    private int documento;
    private int rol;
    private Rol rolSeleccionado;

    public ControladorSesion() {

    }

    @PostConstruct
    public void init() {

    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String iniciarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if ((documento != 0) && password != null && !password.equals("")) {
            p = pfl.iniciarSesion(documento, password);
            if (p != null) {
                List<Rol> rolesUsuario = p.getRoles();//esto lo hacemos para aprovechar el mapeo bidireccional y traer todos los roles
                if (rolesUsuario.size() > 0) { // se hace esta validacion para saber si el usuario tiene al menos un rol seleccionado para que no me retorne null
                    rolSeleccionado = rolesUsuario.get(0);
                    List<Permiso> permisosRol = rolSeleccionado.getPermisos();
                    for (Permiso permiso : permisosRol) {
                        System.out.println("permisos = " + permiso.getIdPermiso() + " - " + permiso.getNombrePermiso() + " - " + permiso.getUrl() + " - " + permiso.getPermisoPadre());

                    }
                    System.out.println("---------------------------------------------------");
                    for (Permiso permiso : permisosRol) {
                        if (permiso.getPermisoPadre() == null) {
                            //renderizar o pintar porque es mi primer nivel min 35.30 
                            System.out.println("permisos = " + permiso.getIdPermiso() + " - " + permiso.getNombrePermiso() + " - " + permiso.getUrl() + " - " + permiso.getPermisoPadre());
                            List<Permiso> subPermisos = permiso.getSubPermisos();
                            for (Permiso subPermiso : subPermisos) {
                                for (Permiso permiso1 : permisosRol) {
                                    if (subPermiso.equals(permiso1)) { // tener cuidado de lo que el metodo equal esta comparando, en esta situacion no compara objetos, sino llaves primarias, es bueno verificar  y sobreescribir el metodo equals
                                        System.out.println("sub-permisos = " + subPermiso.getIdPermiso() + " - " + subPermiso.getNombrePermiso() + " - " + subPermiso.getUrl() + " - " + subPermiso.getPermisoPadre());

                                    }
                                }

                            }

                        }

                    }

                    for (Rol rol1 : rolesUsuario) {
                        System.out.println("rol = " + rol1.getNombreRol());
                    }

                    System.out.println("area: " + p.getAreasIdArea().getIdArea() + " " + p.getAreasIdArea().getNombreArea());

                    return "admin/index.xhtml?faces-redirect=true";

                }

            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "contraseña o documento incorrecto", "intentelo de nuevo");
                fc.addMessage(null, fm);
            }

        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Todos los campos son obligatorios", "Diligencie todos los campos");
            fc.addMessage(null, fm);

        }

        return "";

    }

    public void cerrarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.invalidateSession();
        this.password = "";
        this.documento = 0;
        this.p = null;

        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml?faces-redirect=true");

        } catch (IOException ex) {
            Logger.getLogger(ControladorSesion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void validarSesion() {
        if (!isValidado()) {
            cerrarSesion();

        }

    }

    public boolean isValidado() {
        if (p != null) {
            return true;

        }
        return false;

    }

}
