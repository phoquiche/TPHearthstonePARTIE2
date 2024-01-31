package org.HearthStone.personnages.monstres_classes;

import org.HearthStone.personnages.Monstre;

import java.util.Random;

import static org.HearthStone.personnages.ReferentielStats.*;

public class Eclaireur extends Monstre {

    public Eclaireur(int id,String nom, String type) {
        super(id,nom, genererPV(), genererFA(), type);
    }
    public Eclaireur(int id,String nom, int FA, int PV, String type) {
        super(id,nom, PV, FA,type);
    }
    private static int genererPV() {
        Random random = new Random();
        return PV_MIN_ECLAIREUR + random.nextInt(PV_MAX_ECLAIREUR - PV_MIN_ECLAIREUR + 1);
    }
    private static int genererFA() {
        Random random = new Random();
        return FA_MIN_ECLAIREUR + random.nextInt(FA_MAX_ECLAIREUR - FA_MIN_ECLAIREUR + 1);
    }

    @Override
    public void soignerPv(int pvGagne) {
        setPv(getPv() + pvGagne);
        if(getPv() > PV_MAX_ECLAIREUR){
            setPv(PV_MAX_ECLAIREUR);
        }
    }

}