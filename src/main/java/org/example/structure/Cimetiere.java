package org.example.structure;

import org.example.personnages.Monstre;

import java.util.ArrayList;
import java.util.List;

public class Cimetiere {
    private List<Monstre> monstresDetruits;
    public Cimetiere (){
        this.monstresDetruits = new ArrayList<>();
    }
    public void ajouterMonsterDetruit(Monstre monstre){
        monstresDetruits.add(monstre);
    }
}