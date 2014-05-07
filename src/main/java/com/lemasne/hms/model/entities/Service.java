package com.lemasne.hms.model.entities;

public class Service {
    
    private String code;
    private String nom;
    private String batiment;
    private String directeur;

       
    public Service(String code, String nom, String batiment, String directeur) {
        this.code = code;
        this.nom = nom;
        this.batiment = batiment;
        this.directeur = directeur;
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public String getDirecteur() {
        return directeur;
    }

    public void setDirecteur(String directeur) {
        this.directeur = directeur;
    }
    
    @Override
    public String toString() {
        return this.nom;
    }
}
