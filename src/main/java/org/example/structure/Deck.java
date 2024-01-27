package org.example.structure;

import org.example.personnages.ClasseMonstre;


import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Carte> cartes;

    public Deck() throws InterruptedException {
        this.cartes = new ArrayList<>();
        initialiserDeck();
    }


    public void initialiserDeck() throws InterruptedException {
        for (int i = 0; i<10; i++){
            Carte carte = new Carte("Test"+(i+1),ClasseMonstre.Eclaireur);
            cartes.add(carte);
            Thread.sleep(50);
        }
    }

    public Carte piocherCarte() {
        if (!cartes.isEmpty()) {
            return cartes.remove(0);
        } else {
            System.out.println("Le deck est vide");
            return null;
        }
    }
}