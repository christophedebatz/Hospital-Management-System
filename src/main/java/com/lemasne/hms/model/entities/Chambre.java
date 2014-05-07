package com.lemasne.hms.model.entities;


public class Chambre {
    
    private int no_chambre;
    private String code_service;
    private int surveillant;
    private int nb_lits;

    public Chambre(int no_chambre, String code_service, int surveillant, int nb_lits) {
        this.no_chambre = no_chambre;
        this.code_service = code_service;
        this.surveillant = surveillant;
        this.nb_lits = nb_lits;
    }

    
    public String getCode_service() {
        return code_service;
    }

    public void setCode_service(String code_service) {
        this.code_service = code_service;
    }

    public int getSurveillant() {
        return surveillant;
    }

    public void setSurveillant(int surveillant) {
        this.surveillant = surveillant;
    }

    public int getNo_chambre() {
        return no_chambre;
    }

    public void setNo_chambre(int no_chambre) {
        this.no_chambre = no_chambre;
    }

    public int getNb_lits() {
        return nb_lits;
    }

    public void setNb_lits(int nb_lits) {
        this.nb_lits = nb_lits;
    }
    
    @Override
    public String toString() {
        return "Chambre #" + this.no_chambre;
    }
}
