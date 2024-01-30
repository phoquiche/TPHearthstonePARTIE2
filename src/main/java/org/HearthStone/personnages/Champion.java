package org.HearthStone.personnages;

import org.HearthStone.personnages.capacites.CapaciteSpeciale;
import org.HearthStone.structure.Joueur;
import org.HearthStone.structure.PlateauV2;

public class Champion implements Vie{
    private static int compteurChampionID = 1;
    private int id;
    private String nom;
    private int pv;
    private CapaciteSpeciale capaciteSpeciale;

    private int cooldownCapa;

    

    public Champion(String nom, CapaciteSpeciale capaciteSpeciale){
        this.id = compteurChampionID++;
        this.nom = nom;
        this.pv = ReferentielStats.PV_CHAMPION;
        this.capaciteSpeciale = capaciteSpeciale;
        this.cooldownCapa = ReferentielStats.CD_CAPA;
    }

    public void utiliserCapaciteSpeciale(Monstre cible){
        if(capaciteSpeciale != null) {
            capaciteSpeciale.utiliserCapacite(this, cible);
            setCooldownCapa(ReferentielStats.CD_CAPA);
        }
    }

    public static int getCompteurChampionID() {
        return compteurChampionID;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    @Override
    public void subirDegats(int degats) {
        pv -= degats;
        if(pv <= 0){
            pv = 0;
            System.out.println("Le champion "+nom+" est mort");
        }
    }

    @Override
    public void subirDegats(int pvPerdu, PlateauV2 plateau, Joueur joueur) {    }

    @Override
    public void soignerPv(int pvGagne) {
        pv += pvGagne;
        if(pv > ReferentielStats.PV_CHAMPION){
            pv = ReferentielStats.PV_CHAMPION;
        }
    }

    public CapaciteSpeciale getCapaciteSpeciale() {
        return capaciteSpeciale;
    }
    public boolean isCapaciteSpecialeDisponible() {
        return getCooldownCapa() == 0;
    }
    public int getCooldownCapa(){
        return cooldownCapa;
    }
    public void setCooldownCapa(int cooldownCapa) {
        this.cooldownCapa = cooldownCapa;
    }


}