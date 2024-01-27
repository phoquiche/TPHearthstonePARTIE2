package org.example.personnages;

import org.example.personnages.capacites.CapaciteSpeciale;
import org.example.personnages.capacites.Cible;

public class Champion {
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