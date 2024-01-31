package org.HearthStone.structure;

import org.HearthStone.personnages.ClasseMonstre;
import org.HearthStone.personnages.Monstre;
import org.HearthStone.personnages.monstres_classes.Healeur;
import org.HearthStone.personnages.monstres_classes.Eclaireur;
import org.HearthStone.personnages.monstres_classes.Protecteur;

public class Carte {
    private static int compteurIDS = 0;
    private int id;
    private String nom;
    private ClasseMonstre classe;

    protected String type;

    public Carte(String nom, ClasseMonstre classe, String type){
        this.id = ++compteurIDS;
        this.nom = nom;
        this.classe = classe;
        this.type = type;


    }

    //getter

    public int getId(){
        return id;
    }
    public String getNom(){
        return nom;
    }
    public ClasseMonstre getClasse(){
        return classe;
    }

    public Monstre convertirEnMonstre(){
        return switch (classe) {
            case Eclaireur -> new Eclaireur(id, nom,type);
            case Healeur -> new Healeur(id, nom, type);
            case Protecteur -> new Protecteur(id, nom, type);
            default -> throw new IllegalArgumentException("Classe de Monstre non reconnue : " + classe);
        };
    }

    public String getType() {
        return type;
    }
}