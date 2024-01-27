package org.example.personnages.Monstre_classes;

import org.example.personnages.ClasseMonstre;
import org.example.personnages.Monstre;

public class Healeur extends Monstre{
    public Healeur(int id,String nom, int pv, int forceAdaptative) {
        super(id,nom, pv, forceAdaptative);
    }

    @Override
    public void subirDegats(int degats) {

    }
}