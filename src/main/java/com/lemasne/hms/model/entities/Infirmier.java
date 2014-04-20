/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lemasne.hms.model.entities;


public class Infirmier {
    
    private int numero;
    private String code_service;
    private String rotation;
    private float salaire;

    public Infirmier(int numero, String code_service, String rotation, float salaire) {
        this.numero = numero;
        this.code_service = code_service;
        this.rotation = rotation;
        this.salaire = salaire;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCode_service() {
        return code_service;
    }

    public void setCode_service(String code_service) {
        this.code_service = code_service;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }
    
    
    
}
