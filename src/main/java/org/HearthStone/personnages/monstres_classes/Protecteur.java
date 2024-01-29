package org.HearthStone.personnages.monstres_classes;

import org.HearthStone.personnages.Monstre;
public class Protecteur extends Monstre {
    public Protecteur(int id, String nom, int pv, int forceAdaptative) {
        super(id, nom, pv, forceAdaptative);
    }


    @Override
    public void soignerPv(int pvGagne) {
        setPv(getPv() + pvGagne);
        if(getPv() > 100){
            setPv(100);
        }
    }
}
