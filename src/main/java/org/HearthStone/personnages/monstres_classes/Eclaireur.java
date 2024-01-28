package org.HearthStone.personnages.monstres_classes;

import org.HearthStone.personnages.Monstre;

import java.util.Random;

import static org.HearthStone.personnages.ReferentielStats.*;

public class Eclaireur extends Monstre {

    public Eclaireur(int id,String nom) {
        super(id,nom, genererPV(), genererFA());
    }
    private static int genererPV() {
        Random random = new Random();
        return PV_MIN_ECLAIREUR + random.nextInt(PV_MAX_ECLAIREUR - PV_MIN_ECLAIREUR + 1);
    }
    private static int genererFA() {
        Random random = new Random();
        return FA_MIN_ECLAIREUR + random.nextInt(FA_MAX_ECLAIREUR - FA_MIN_ECLAIREUR + 1);
    }

}