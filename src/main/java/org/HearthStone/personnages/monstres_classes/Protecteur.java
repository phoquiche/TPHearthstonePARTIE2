package org.HearthStone.personnages.monstres_classes;

import org.HearthStone.personnages.Monstre;


import java.util.Random;

import static org.HearthStone.personnages.ReferentielStats.*;

public class Protecteur extends Monstre {
    public Protecteur(int id, String nom, String type) {
        super(id, nom, genererPV(), 0, type);
    }

    private static int genererPV() {
        Random random = new Random();
        return PV_MIN_HEALEUR + random.nextInt(PV_MAX_PROTECTEUR - PV_MIN_PROTECTEUR + 1);
    }


    @Override
    public void soignerPv(int pvGagne) {
        setPv(getPv() + pvGagne);
        if(getPv() > 100){
            setPv(100);
        }
    }
}
