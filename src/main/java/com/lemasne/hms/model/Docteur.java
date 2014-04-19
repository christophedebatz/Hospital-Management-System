package com.lemasne.hms.model;


public class Docteur {
    
    private int numero;
    private String specialite;

    
    public Docteur(int numero, String specialite) {
        this.numero = numero;
        this.specialite = specialite;
    }

       
    public int getId() {
        return numero;
    }

    public void setId(int numero) {
        this.numero = numero;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialty(String specialite) {
        this.specialite = specialite;
    }
    
    
}
