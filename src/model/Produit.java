package model;

import jade.core.Agent;

import java.io.Serializable;

public class Produit implements Serializable {
    private String nom;
    private String desc;
    private float prix;
    private Agent agent;

    public Produit() {
    }

    public Produit(String nom, String desc, float prix) {
        this.nom = nom;
        this.desc = desc;
        this.prix = prix;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "nom='" + nom + '\'' +
                ", desc='" + desc + '\'' +
                ", prix=" + prix +
                '}';
    }
}
