package org.HearthStone.personnages.monstres_classes;

import org.HearthStone.personnages.Monstre;
import java.util.Random;

import static org.HearthStone.personnages.ReferentielStats.*;

public class Healeur extends Monstre{
    public Healeur(int id,String nom) {
        super(id,nom, genererPV(), genererFA());
    }

    private static int genererPV() {
        Random random = new Random();
        return PV_MIN_HEALEUR + random.nextInt(PV_MAX_HEALEUR - PV_MIN_HEALEUR + 1);
    }
    private static int genererFA() {
        Random random = new Random();
        return FA_MIN_HEALEUR + random.nextInt(FA_MAX_HEALEUR - FA_MIN_HEALEUR + 1);
    }


    @Override
    public void soignerPv(int pvGagne) {
        setPv(getPv() + pvGagne);
        if(getPv() > PV_MAX_HEALEUR){
            setPv(PV_MAX_HEALEUR);
        }
    }
}