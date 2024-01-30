package org.HearthStone.structure;

import org.HearthStone.personnages.Monstre;

import java.util.ArrayList;
import java.util.List;

public class EspaceJoueur {
    private Joueur joueur;

    private List<Monstre> monstresIG;

    public EspaceJoueur (Joueur joueur){
        this.joueur = joueur;
        this.monstresIG= new ArrayList<>();
    }
    public void invoquerMonstre(Monstre monstre){
        monstresIG.add(monstre);
    }
    public List<Monstre> getMonstresIG(){
        return monstresIG;
    }
    public Joueur getJoueur() {
        return joueur;
    }

    public void resetAllSorts() {
        for (Monstre monstre : monstresIG) {
            monstre.resetSort();
        }
    }

    public void retirerMonstre(Monstre monstre) {
        monstresIG.remove(monstre);
        System.out.println("Il reste "+monstresIG.size()+" monstres adverse en jeu");
    }

}