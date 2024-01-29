package org.HearthStone.structure;

import org.HearthStone.personnages.ClasseMonstre;
import org.HearthStone.personnages.Monstre;
import org.HearthStone.personnages.monstres_classes.Healeur;
import org.HearthStone.personnages.monstres_classes.Eclaireur;

public class Carte {
    private static int compteurIDS = 0;
    private int id;
    private String nom;
    private ClasseMonstre classe;

    public Carte(String nom, ClasseMonstre classe){
        this.id = ++compteurIDS;
        this.nom = nom;
        this.classe = classe;
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
            case Eclaireur -> new Eclaireur(id, nom);
            case Healeur -> new Healeur(id, nom);
            default -> throw new IllegalArgumentException("Classe de Monstre non reconnue : " + classe);
        };
    }
}