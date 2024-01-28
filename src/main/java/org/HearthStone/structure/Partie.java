package org.HearthStone.structure;

import org.HearthStone.HearthStone;
import org.HearthStone.personnages.Champion;
import org.HearthStone.personnages.Monstre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Partie {
    private Joueur joueur1;
    private Joueur joueur2;
    private PlateauV2 plateau;

    public Partie(Joueur joueur1, Joueur joueur2){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.plateau = new PlateauV2(new EspaceJoueur(joueur1), new EspaceJoueur(joueur2));
    }
    private Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Partie.class);
    public void demarrerPartie() throws InterruptedException {
        while(!partieTerminee()){
            effectuerTour(joueur1);
            if(!partieTerminee()){
                effectuerTour(joueur2);
            }
        }
    }

    private void effectuerTour(Joueur joueur) throws InterruptedException {
        System.out.println("Tour de "+joueur.getNom());
        Thread.sleep(250);
        //phase de pioche
        joueur.piocherCarte();
        Thread.sleep(1000);
        //phase d'invocations
        phaseInvocations(joueur);

        //phase d'attaque
        phaseAttaque(joueur);

        //fin du tour
    }

    private void phaseInvocations(Joueur joueur) throws InterruptedException {
        System.out.println("Début de la phase d'invocation pour "+joueur.getNom());

        while(true){
            afficherMain(joueur);

            System.out.println("Choisissez un monstre à invoquer ou tapez 'fin' pour passer à la phase suivante :");
            String choix = scanner.next();

            if (choix.equalsIgnoreCase("fin")){
                break;
            }
            Carte carteChoisie = trouverCarteDansMain(joueur, choix);
            if(carteChoisie != null){
                joueur.invoquerCarte(carteChoisie,plateau);
                logger.info("Monstre invoqué par "+joueur.getNom()+" : "+carteChoisie.getNom());
            }else {
                System.out.println("Carte non trouvée, veuillez choisir à nouveau");
                logger.error("Carte choisie par le joueur "+joueur.getNom()+" est incorrecte");
            }
        }
        System.out.println("Phase d'invocation terminée");
    }

    private void phaseAttaque(Joueur joueur){
        System.out.println("Début de la phase d'attaque pour "+joueur.getNom());
        Champion champion = joueur.getChampion();
        if (champion.isCapaciteSpecialeDisponible()) {
            System.out.println("Voulez-vous utiliser la capacité de votre champion ?");
            String choixCapacite = scanner.next();


            if(choixCapacite.equalsIgnoreCase("oui")) {

                List<Monstre> monstresEnnemis = plateau.getMonstresEnnemisSurPlateau(joueur);
                System.out.println("Monstres ennemies disponible : ");
                for (int i = 0; i<monstresEnnemis.size(); i++){
                    System.out.println((i+1)+". ID :"+monstresEnnemis.get(i).getId()+" Nom :"+monstresEnnemis.get(i).getNom());
                }
                System.out.println("Choisissez l'ID du monstre à cibler ");
                int idMonstre = scanner.nextInt();
                Monstre monstrecible = trouverMonstreParID(monstresEnnemis, idMonstre);
                if(monstrecible != null){
                    champion.utiliserCapaciteSpeciale(monstrecible);
                    logger.info("Capacité effectuée par "+joueur.getNom());
                    if (monstrecible.getPv() == 0){
                        plateau.detruireMonstre(monstrecible,plateau.getJoueurEnnemi(joueur));
                    }
                }

                System.out.println("Capacitée utilisée");
            }

        }else {
            System.out.println("La capacitée du champion est inutilisable pendant ce tour !");
        }

        int cdRestantCapacite = champion.getCooldownCapa();
        if (cdRestantCapacite !=0){
            champion.setCooldownCapa(cdRestantCapacite-1);
        }


        while(true){
            if (!plateau.getMonstresSurPlateau(joueur).isEmpty()){
                System.out.println("Voulez vous lancer une attaque avec vos monstres ? (oui/non)");
                String choixAttaque = scanner.next();
                if (choixAttaque.equalsIgnoreCase("non")){
                    break;
                }
                if (choixAttaque.equalsIgnoreCase("oui")){
                    plateau.afficherMonstreEnJeu(joueur);
                    System.out.println("Choisissez l'ID du monstre à faire attaquer ");
                    int idMonstre = scanner.nextInt();
                    System.out.println("Monstre choisi" +" ");
                }
            } else {
                break;
            }
        }
        System.out.println("Fin de la phase d'attaque :");
    }

    private void afficherMain(Joueur joueur) throws InterruptedException {
        System.out.println("Main de "+joueur.getNom()+": ");
        for (Carte carte : joueur.getMain()){
            System.out.println(carte.getNom());
            Thread.sleep(250);
        }
    }

    private Carte trouverCarteDansMain(Joueur joueur, String nomCarte){
        for (Carte carte : joueur.getMain()){
            if(carte.getNom().equalsIgnoreCase(nomCarte)){
                return carte;
            }
        }
        return null;
    }

    private Monstre trouverMonstreParID(List<Monstre> monstres, int id){
        for (Monstre monstre : monstres){
            if (monstre.getId() == id){
                return monstre;
            }
        }
        return null;
    }
    private boolean partieTerminee(){
        return false;
    }
}