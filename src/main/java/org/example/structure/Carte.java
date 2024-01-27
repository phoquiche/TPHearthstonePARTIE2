package org.example.structure;

import org.example.personnages.ClasseMonstre;
import org.example.personnages.Monstre;
import org.example.personnages.Monstre_classes.Healeur;
import org.example.personnages.monstres_classes.Eclaireur;

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
        switch (classe){
            case Eclaireur:
                return new Eclaireur(id,nom,20,20);
            case Healeur:
                return new Healeur(id,nom,20,20);
            default:
                throw new IllegalArgumentException("Classe de Monstre non reconnue : "+ classe);
        }
    }
}