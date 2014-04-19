package com.lemasne.hms.model;


public class Soigne 
{
    private int no_docteur;
    private int no_malade;

    public Soigne(int no_docteur, int no_malade) {
        this.no_docteur = no_docteur;
        this.no_malade = no_malade;
    }

    public int getNo_docteur() {
        return no_docteur;
    }

    public void setNo_docteur(int no_docteur) {
        this.no_docteur = no_docteur;
    }

    public int getNo_malade() {
        return no_malade;
    }

    public void setNo_malade(int no_malade) {
        this.no_malade = no_malade;
    }
    
    
}
