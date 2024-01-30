package org.HearthStone.personnages;

import org.HearthStone.structure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Monstre extends Carte implements Vie{
    private int pv;

    private int forceAdaptative;

    private boolean sort = true;

    private static final Logger logger = LogManager.getLogger(Partie.class);

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
        System.out.println("Le monstre " + getNom() + " a subi " + pvPerdu + " dégâts. PV restants : " + pv);
        logger.info("Le monstre " + getNom() + " a subi " + pvPerdu + " dégâts. PV restants : " + pv);

        if(pv <= 0){
            System.out.println("Le monstre " + getNom() + " est mort");
            logger.info("Le monstre " + getNom() + " a subi " + pvPerdu + " dégâts. PV restants : " + pv);
            plateau.detruireMonstre(this, joueur);
        }
        else System.out.println("Le monstre " + getNom() + " a subi " + pvPerdu + " dégâts. PV restants : " + pv);
    }

    @Override
    public void subirDegats(int pvPerdu) {}

    public void regenererHp(int soin) {
        pv += soin;
        logger.info("Le monstre " + getNom() + " a été soigné de " + soin + ". PV actuels : " + pv);
    }
}