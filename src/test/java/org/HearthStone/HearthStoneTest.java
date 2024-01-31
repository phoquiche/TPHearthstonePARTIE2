package org.HearthStone;

import org.HearthStone.personnages.Champion;
import org.HearthStone.personnages.ClasseMonstre;
import org.HearthStone.personnages.Monstre;
import org.HearthStone.personnages.capacites.AttaqueCible;
import org.HearthStone.personnages.capacites.CapaciteSpeciale;
import org.HearthStone.personnages.monstres_classes.Eclaireur;
import org.HearthStone.structure.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class HearthStoneTest {
    private List<Carte> cartes;
    private CapaciteSpeciale AttaqueCible = new AttaqueCible();
    private
    Champion champion1 = new Champion("Joueur1", AttaqueCible);
    Champion champion2 = new Champion("Joueur2", AttaqueCible);
    Joueur joueur1 = new Joueur("joueur1", champion1, creerDeck());
    Joueur joueur2 = new Joueur("joueur2", champion2, creerDeck());
    PlateauV2 plateauV2 = new PlateauV2(new EspaceJoueur(joueur1), new EspaceJoueur(joueur2));
    Partie partie = new Partie(joueur1,joueur2);


    HearthStoneTest() throws InterruptedException {
    }
    @Test
    public void testAttaqueMonstre() {
        Carte carte = new Carte("eclaireur", ClasseMonstre.Eclaireur,"feu");

        Monstre eclaireur1 = carte.convertirEnMonstre();
        Monstre eclaireur2 = carte.convertirEnMonstre();

        eclaireur1.setForceAdaptative(1);
        int pointDeVie2 = eclaireur2.getPv();

        eclaireur2.subirDegats(eclaireur1.getForceAdaptative(), plateauV2, joueur2);
        assertEquals(pointDeVie2 - 1, eclaireur2.getPv());
    }

    @Test
    public void testGuerisonMonstre() {
        Carte carte1 = new Carte("eclaireur", ClasseMonstre.Eclaireur,"feu");
        Carte carte2 = new Carte("healer", ClasseMonstre.Healeur,"eau");

        Monstre eclaireur = carte1.convertirEnMonstre();
        Monstre healer = carte2.convertirEnMonstre();

        healer.setForceAdaptative(1);
        int pointDeVie = eclaireur.getPv();

        eclaireur.regenererHp(healer.getForceAdaptative());
        assertEquals(pointDeVie + 1, eclaireur.getPv());
    }

    @Test
    public void testMortMonstre() {
        Carte carte = new Carte("eclaireur", ClasseMonstre.Eclaireur, "feu");
        Carte carte2 = new Carte("eclaireur1", ClasseMonstre.Eclaireur, "eau");

        Monstre eclaireur1 = carte.convertirEnMonstre();
        Monstre eclaireur2 = carte2.convertirEnMonstre();

        eclaireur2.setPv(30);
        eclaireur1.setForceAdaptative(30);



        eclaireur2.subirDegats(eclaireur1.getForceAdaptative()/2, plateauV2, joueur2);
        assertEquals(15, eclaireur2.getPv());
    }


    @Test
    public void degatChampion() {
        Carte carte = new Carte("eclaireur", ClasseMonstre.Eclaireur,"feu");

        Monstre eclaireur = carte.convertirEnMonstre();
        eclaireur.setForceAdaptative(1);

        joueur2.getChampion().subirDegats(eclaireur.getForceAdaptative());

        assertEquals(199, joueur2.getChampion().getPv());
    }

    @Test
    public void partieTermine() {
        Carte carte = new Carte("eclaireur", ClasseMonstre.Eclaireur, "Feu");

        Monstre eclaireur1 = carte.convertirEnMonstre();
        eclaireur1.setForceAdaptative(200);

        joueur2.getChampion().subirDegats(eclaireur1.getForceAdaptative());

        assertTrue(partie.partieTerminee());
    }




    private static Deck creerDeck() throws InterruptedException {
        Deck deck = new Deck();
        deck.initialiserDeck();
        return deck;
    }

}
