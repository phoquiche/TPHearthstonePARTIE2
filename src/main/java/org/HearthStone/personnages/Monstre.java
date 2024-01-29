package org.HearthStone.personnages;

import org.HearthStone.structure.Carte;

public abstract class Monstre  extends Carte {
    private int pv;

    private int forceAdaptative;


    public Monstre(int id, String nom, int pv, int forceAdaptative){
        super(nom, ClasseMonstre.Eclaireur);
        this.pv = pv;
        this.forceAdaptative = forceAdaptative;
    }

    public int getPv() {
        return pv;
    }

    public int getForceAdaptative() {
        return forceAdaptative;
    }
    public void setPv(int pv) {
        this.pv = pv;
    }


    public void subirDegats(int degats) {
        pv -= degats;
        if(pv <= 0){
            pv = 0;
        }
    }

    public void regenererHp(int soin) {
        pv += soin;
    }
}