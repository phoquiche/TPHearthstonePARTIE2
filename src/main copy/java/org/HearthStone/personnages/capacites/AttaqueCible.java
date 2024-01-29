package org.HearthStone.personnages.capacites;

import org.HearthStone.personnages.Champion;
import org.HearthStone.personnages.Monstre;

import org.HearthStone.personnages.ReferentielStats;

public class AttaqueCible extends CapaciteSpeciale{
    @Override
    public void utiliserCapacite(Champion source, Monstre cible){
        int degats = ReferentielStats.DEGATS_CAPA_ATTAQUE;
        System.out.println("Le Champion " +source.getNom()+" attaque "+ cible.getNom());
        System.out.println("Pv de la cible :"+cible.getPv());
        cible.subirDegats(degats);
        System.out.println("Pv de la cible après l'attaque :"+cible.getPv());
    }
}