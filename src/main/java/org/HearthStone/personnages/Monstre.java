package org.HearthStone.personnages;

import org.HearthStone.structure.*;

public abstract class Monstre extends Carte implements Vie{
    private int pv;

    private int forceAdaptative;

    private boolean sort = true;


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
    
    public void setForceAdaptative(int forceAdaptative) {
        this.forceAdaptative = forceAdaptative;
    }

    public boolean sortDisponible() {
        return sort;
    }

    public void sortUtilise() {
        sort = false;
    }

    public void resetSort() {
        sort = true;
    }





    @Override
    public void subirDegats(int pvPerdu, PlateauV2 plateau, Joueur joueur) {
        pv -= pvPerdu;
        if(pv <= 0){
            System.out.println("Le monstre " + getNom() + " est mort");
            plateau.detruireMonstre(this, joueur);
        }
        else System.out.println("Le monstre " + getNom() + " a subi " + pvPerdu + " dégâts. PV restants : " + pv);
    }

    @Override
    public void subirDegats(int pvPerdu) {}

    public void regenererHp(int soin) {
        pv += soin;
    }
}