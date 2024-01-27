package org.example.structure;

import org.example.personnages.Champion;
import org.example.personnages.Monstre;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String nom;
    private Champion champion;
    private List<Carte> main;
    private Deck deck;
    private Cimetiere cimetiere;

    public Joueur(String nom, Champion champion, Deck deck) throws InterruptedException {
        this.nom = nom;
        this.champion = champion;
        this.main = new ArrayList<>();
        this.deck = deck;
        this.cimetiere = new Cimetiere();
        piocherMainInitiale();
    }
    private void piocherMainInitiale() throws InterruptedException {
        for (int i = 0; i<5; i++){
            piocherCarte();
            Thread.sleep(100);
        }
    }
    public void piocherCarte(){
        Carte cartePiochee = deck.piocherCarte();
        if (cartePiochee!= null){
            main.add(cartePiochee);
            System.out.println(nom+" a pioché une carte");
        }
    }


    public void invoquerCarte(Carte carte, PlateauV2 plateau){
        if (main.contains(carte)){
            Monstre monstre = carte.convertirEnMonstre();
            plateau.invoquerMonstre(this, monstre);
            main.remove(carte);
            System.out.println("Invocation réussie");
        }else {
            System.out.println("Impossible d'invoquer");
        }
    }

    //getter
    public Champion getChampion(){
        return champion;
    }
    public List<Carte> getMain(){
        return main;
    }
    public Deck getDeck(){
        return deck;
    }
    public String getNom(){
        return nom;
    }
    public Cimetiere getCimetiere(){
        return cimetiere;
    }
}