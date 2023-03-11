package model;

public class Produit {
    private String nom;
    private String desc;
    private float prix;

    public Produit() {
    }

    public Produit(String nom, String desc, float prix) {
        this.nom = nom;
        this.desc = desc;
        this.prix = prix;
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
