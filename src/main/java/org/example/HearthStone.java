package org.example;

import org.example.personnages.Champion;
import org.example.personnages.capacites.AttaqueCible;
import org.example.personnages.capacites.CapaciteSpeciale;
import org.example.personnages.capacites.Guerison;
import org.example.structure.*;

import java.util.Scanner;

public class HearthStone {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        CapaciteSpeciale attaqueCible = new AttaqueCible();
        CapaciteSpeciale guerison = new Guerison();

        System.out.println("Création du deck aléatoire du Joueur 1");
        Deck deckJoueur1 = creerDeck();

        System.out.println("Création du champion 1 :");
        Champion champion1 = creerChampion();

        System.out.println("Création du deck aléatoire du Joueur 2");
        Deck deckJoueur2 = creerDeck();

        System.out.println("Création du champion 2 :");
        Champion champion2 = creerChampion();

        System.out.println("Champion 1 : ID:" +champion1.getId()+", Nom:"+ champion1.getNom()+", PV:"+champion1.getPv()+", Capacité:"+ champion1.getCapaciteSpeciale());
        System.out.println("Champion 2 : ID:" +champion2.getId()+", Nom:"+ champion2.getNom()+", PV:"+champion2.getPv()+", Capacité:"+ champion2.getCapaciteSpeciale());

        Joueur joueur1 = new Joueur(champion1.getNom(),champion1,deckJoueur1);
        Joueur joueur2 = new Joueur(champion2.getNom(),champion2,deckJoueur2);

        Partie partie = new Partie(joueur1, joueur2);

        partie.demarrerPartie();
    }


    private static Deck creerDeck() throws InterruptedException {
        Deck deck = new Deck();
        deck.initialiserDeck();
        return deck;
    }
    private static Champion creerChampion() {
        System.out.println("Entrez le nom de votre champion : ");
        String nomChampion = scanner.nextLine();

        System.out.println("Choisissez la capacité spéciale de votre champion :");
        System.out.println("1. Attaque sur une cible");
        System.out.println("2. Guérison");

        int choixCapacite = scanner.nextInt();
        scanner.nextLine();

        CapaciteSpeciale capaciteSpeciale;
        switch (choixCapacite) {
            case 1:
                capaciteSpeciale = new AttaqueCible();
                break;
            case 2:
                capaciteSpeciale = new Guerison();
                break;
            default:
                System.out.println("Choix non valide, la capacité par défaut sera Attaque sur une cible.");
                capaciteSpeciale = new AttaqueCible();
                break;
        }

        return new Champion(nomChampion, capaciteSpeciale);
    }
}
