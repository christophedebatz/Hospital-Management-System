package com.lemasne.hms.model.entities;


public class Docteur {
    
    private int numero;
    private String specialite;

    
    public Docteur(int numero, String specialite) {
        this.numero = numero;
        this.specialite = specialite;
    }

       
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialty(String specialite) {
        this.specialite = specialite;
    }
    
    
}
