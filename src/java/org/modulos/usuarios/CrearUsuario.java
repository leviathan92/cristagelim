/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.PersonaFacadeLocal;
import org.entidades.Persona;
import javax.ejb.EJB;
import org.dao.AreaFacadeLocal;
import org.dao.PermisoFacadeLocal;
import org.dao.RolFacadeLocal;
import org.entidades.Area;
import org.entidades.Permiso;
import org.entidades.Rol;

/**
 *
 * @author David
 */
@Named(value = "crearUsuario")
@ViewScoped
public class CrearUsuario implements Serializable {

    @EJB
    private PersonaFacadeLocal pfl;
    @EJB
    private RolFacadeLocal rfl;
    @EJB
    private AreaFacadeLocal afl;
    @EJB
    private PermisoFacadeLocal permisoFacadeLocal;

    private int idPersona;
    private int documento;
    private String nombre;
    private String apellido;
    private String password;
    private int telefono;
    private String direccion;
    private String email;
    private int idRol;
    private Persona p;
    private Rol r;
    private Area area; 

    @PostConstruct
    public void init() {
        p = new Persona();
        r= new Rol();
        area = new Area();

    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    

    public Persona getP() {
        return p;
    }

    public void setP(Persona p) {
        this.p = p;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonaFacadeLocal getPfl() {
        return pfl;
    }

    public void setPfl(PersonaFacadeLocal pfl) {
        this.pfl = pfl;
    }

    public RolFacadeLocal getRfl() {
        return rfl;
    }

    public void setRfl(RolFacadeLocal rfl) {
        this.rfl = rfl;
    }

    public Rol getR() {
        return r;
    }

    public void setR(Rol r) {
        this.r = r;
    }

    public String crearUsuario() {
        p = new Persona(null, documento, nombre, apellido, password, telefono, direccion, email);
        //Area area = afl.find(1);
        Rol rol = rfl.find(1);
        p.setAreasIdArea(afl.find(area.getIdArea()));
        System.out.println(area);
        List<Rol> roles = new ArrayList();
        roles.add(rol);
        p.setRoles(roles);
        pfl.create(p);
        return "/admin/usuarios/listarUsuarios.xhtml?faces-redirect=true";
        
        
    }

    public String cancelar() {
        return "/admin/usuarios/listarUsuarios.xhtml?faces-reditect=true";

    }

}
