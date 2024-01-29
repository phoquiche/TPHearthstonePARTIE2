package org.HearthStone.personnages;

import org.HearthStone.structure.*;

public abstract class Monstre extends Carte implements Vie{
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



    @Override
    public void subirDegats(int pvPerdu, PlateauV2 plateau, Joueur joueur) {
        pv -= pvPerdu;
        System.out.println("Le monstre " + getNom() + " a subi " + pvPerdu + " dégâts. PV restants : " + pv);
        if(pv <= 0){
            System.out.println("Le monstre " + getNom() + " est mort");
            plateau.detruireMonstre(this, joueur);
        }
    }

    


}