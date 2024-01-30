package org.HearthStone.structure;

import org.HearthStone.personnages.ClasseMonstre;
import org.HearthStone.personnages.Monstre;

import java.util.ArrayList;
import java.util.List;

public class PlateauV2 {
    private EspaceJoueur espaceJoueur1;
    private EspaceJoueur espaceJoueur2;

    public PlateauV2(EspaceJoueur espaceJoueur1, EspaceJoueur espaceJoueur2) {
        this.espaceJoueur1 = espaceJoueur1;
        this.espaceJoueur2 = espaceJoueur2;

    }

    public List<Monstre> getMonstresSurPlateau(Joueur joueur) {
        EspaceJoueur espaceJoueur = (joueur == espaceJoueur1.getJoueur()) ? espaceJoueur1 : espaceJoueur2;
        return new ArrayList<>(espaceJoueur.getMonstresIG());
    }




    public Monstre getMonstreParId(int id) {
        //Concaténation des dex esoaces joueurs pour avoir tous les monstres présent sur le plateau
        List<Monstre> monstres = getMonstresSurPlateau(espaceJoueur1.getJoueur());
        monstres.addAll(getMonstresSurPlateau(espaceJoueur2.getJoueur()));
        for (Monstre monstre : monstres) {
            if (monstre.getId() == id) {
                return monstre;
            }
        }
        return null;
    }

    public List<Monstre> getMonstresEnemieListSurPlateau(Joueur joueur) {
        EspaceJoueur espaceJoueur = (joueur == espaceJoueur1.getJoueur()) ? espaceJoueur1 : espaceJoueur2;
        List<Monstre> listMonstre = new ArrayList<>(espaceJoueur.getMonstresIG());
        boolean testProtecteur = false;
        for (Monstre monstre : listMonstre){
            if (monstre.getClasse() == ClasseMonstre.Protecteur) {
                testProtecteur = true;
                break;
            }
        }
        if (!testProtecteur){
            return listMonstre;
        }
        return listMonstre.stream()
                .filter(monstre -> monstre.getClasse() == ClasseMonstre.Protecteur)
                .toList();
    }

    public List<Monstre> getMonstresEnnemisSurPlateau(Joueur joueur){
        if(joueur.equals(espaceJoueur1.getJoueur())){
            return getMonstresEnemieListSurPlateau(espaceJoueur2.getJoueur());
        } else if (joueur.equals(espaceJoueur2.getJoueur())) {
            return getMonstresEnemieListSurPlateau(espaceJoueur1.getJoueur());

        }
        return null;
    }

    public void afficherMonstreEnJeu(Joueur joueur) {
            for (Monstre monstre : getMonstresSurPlateau(joueur)) {
                System.out.println("ID :" + monstre.getId() + ", Nom : " + monstre.getNom() + ", PV:" + monstre.getPv() + ", Force Adaptative:" + monstre.getForceAdaptative());
        }
    }

    public void invoquerMonstre(Joueur joueur, Monstre monstre){
        EspaceJoueur espaceJoueur = (joueur == espaceJoueur1.getJoueur()) ? espaceJoueur1 : espaceJoueur2;
        espaceJoueur.invoquerMonstre(monstre);
    }

    public void detruireMonstre(Monstre monstre, Joueur joueur){
        if (monstre.getPv() <= 0) {
            joueur.getCimetiere().ajouterMonsterDetruit(monstre);
            EspaceJoueur espaceJoueur = (joueur == espaceJoueur1.getJoueur()) ? espaceJoueur1 : espaceJoueur2;
            espaceJoueur.retirerMonstre(monstre);;
        }
    }
    public EspaceJoueur getEspaceJoueur1(){
        return espaceJoueur1;
    }
    public EspaceJoueur getEspaceJoueur2(){
        return espaceJoueur2;
    }
    public Joueur getJoueur1(){
        if (espaceJoueur1 !=null){
            return espaceJoueur1.getJoueur();
        } else {
            return null;
        }
    }
    public Joueur getJoueur2(){
        if (espaceJoueur2 !=null){
            return espaceJoueur2.getJoueur();
        } else {
            return null;
        }
    }
    public Joueur getJoueurEnnemi(Joueur joueur){
        if(joueur != null){
            if (joueur.equals(getJoueur1())){
                return getJoueur2();
            } else if (joueur.equals(getJoueur2())) {
                return getJoueur1();
            }
        }
        return null;
    }
}