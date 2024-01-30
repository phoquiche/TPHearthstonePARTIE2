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


    HearthStoneTest() throws InterruptedException {
    }
    @Test
    public void testMortMonstre() {
        Carte carte = new Carte("eclaireur2", ClasseMonstre.Eclaireur);

        Monstre eclaireur1 = carte.convertirEnMonstre();
        Monstre eclaireur2 = carte.convertirEnMonstre();

        eclaireur2.setPv(eclaireur1.getForceAdaptative());

        eclaireur2.subirDegats(eclaireur1.getForceAdaptative(), plateauV2, joueur2);
        assertEquals(0, eclaireur2.getPv());
    }

    private static Deck creerDeck() throws InterruptedException {
        Deck deck = new Deck();
        deck.initialiserDeck();
        return deck;
    }

}
