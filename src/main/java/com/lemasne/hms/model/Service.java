package com.lemasne.hms.model;

public class Service {
    
    private int code;
    private String nom;
    private char batiment;
    private String directeur;

    public Service(int code, String nom, char batiment, String directeur) {
        this.code = code;
        this.nom = nom;
        this.batiment = batiment;
        this.directeur = directeur;
    }
    

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char getBatiment() {
        return batiment;
    }

    public void setBatiment(char batiment) {
        this.batiment = batiment;
    }

    public String getDirecteur() {
        return directeur;
    }

    public void setDirecteur(String directeur) {
        this.directeur = directeur;
    }
    
    
}
