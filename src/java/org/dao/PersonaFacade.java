/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.entidades.Persona;
import javax.persistence.TypedQuery;
/**
 *
 * @author David
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {

    @PersistenceContext(unitName = "fixed_up_prototipo_v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
       public Persona iniciarSesion(int documento, String password){
       Persona u = null;
       try {
           TypedQuery <Persona> q = getEntityManager().createNamedQuery("Persona.login", Persona.class);
           q.setParameter("documento", documento);
           q.setParameter("password", password);
           u = q.getSingleResult();
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return u;
   }

    
}
