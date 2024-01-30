package org.HearthStone.structure;

import org.HearthStone.HearthStone;
import org.HearthStone.personnages.Champion;
import org.HearthStone.personnages.Monstre;
import org.HearthStone.personnages.capacites.AttaqueCible;
import org.HearthStone.personnages.capacites.CapaciteSpeciale;
import org.HearthStone.personnages.capacites.Guerison;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Partie {
    private Joueur joueur1;
    private Joueur joueur2;

    public PlateauV2 plateau;


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
        System.out.println("Partie terminée");
    }

    private void effectuerTour(Joueur joueur) throws InterruptedException {
        System.out.println("Tour de "+joueur.getNom());
        Thread.sleep(0);//250
        //phase de pioche
        joueur.piocherCarte();
        Thread.sleep(0);//1000
        //phase d'invocations
        phaseInvocations(joueur);
        System.out.println("Fin de la phase d'invocation");
        //phase d'attaque
        phaseAttaque(joueur);

        //fin du tour
      
    }

    private void phaseInvocations(Joueur joueur) throws InterruptedException {
        System.out.println("Début de la phase d'invocation pour "+joueur.getNom());
        plateau.resetAllSorts(joueur);

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
                break;
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
            System.out.println("Voulez-vous utiliser la capacité de votre champion ? (oui/non)");
            String choixCapacite = scanner.next();


            if(choixCapacite.equalsIgnoreCase("oui")) {


                if (Objects.equals(joueur.getChampion().getCapaciteSpeciale(), new AttaqueCible())){
                    
                  
                    if ( plateau.getMonstresSurPlateau(plateau.getJoueurEnnemi(joueur)).isEmpty()) {
                      System.out.println("Il n'y a pas de monstre ennemi sur le plateau, le joueur adverse est attaque");
                    } else {
                        List<Monstre> monstresEnnemis = plateau.getMonstresEnnemisSurPlateau(joueur);

                        System.out.println("Monstres ennemies disponible : ");
                        for (int i = 0; i < monstresEnnemis.size(); i++) {
                            System.out.println((i + 1) + ". ID :" + monstresEnnemis.get(i).getId() + " Nom :" + monstresEnnemis.get(i).getNom());
                        }
                        System.out.println("Choisissez l'ID du monstre à cibler ");
                        int idMonstre = scanner.nextInt();
                        Monstre monstrecible = trouverMonstreParID(monstresEnnemis, idMonstre);
                        if (monstrecible != null) {
                            champion.utiliserCapaciteSpeciale(monstrecible);
                            logger.info("Capacité effectuée par " + joueur.getNom());
                            if (monstrecible.getPv() == 0) {
                                plateau.detruireMonstre(monstrecible, plateau.getJoueurEnnemi(joueur));
                            }

                        }
                    }

                    System.out.println("Capacitée utilisée");

                } else {
                    List<Monstre> monstresAllies = plateau.getMonstresSurPlateau(joueur);
                    System.out.println("Monstres alliés disponible : ");
                    for (int i = 0; i<monstresAllies.size(); i++){
                        System.out.println((i+1)+". ID :"+monstresAllies.get(i).getId()+" Nom :"+monstresAllies.get(i).getNom());
                    }
                    System.out.println("Choisissez l'ID du monstre à cibler ");
                    int idMonstre = scanner.nextInt();
                    Monstre monstrecible = trouverMonstreParID(monstresAllies, idMonstre);
                    if(monstrecible != null){
                        champion.utiliserCapaciteSpeciale(monstrecible);
                        logger.info("Capacité effectuée par "+joueur.getNom());
                    }
                }



                System.out.println("Capacitée utilisée");
            }
              
        } else {
                System.out.println("La capacitée du champion est inutilisable pendant ce tour !");
            }

            int cdRestantCapacite = champion.getCooldownCapa();
            if (cdRestantCapacite !=0){
                champion.setCooldownCapa(cdRestantCapacite-1);
            }


            while(true){
                if (!plateau.getMonstresSurPlateau(joueur).isEmpty()){
                    System.out.println("Voulez vous utiliser la compétence d'un de vos monstres ? (oui/non)");
                    String choixAttaque = scanner.next();
                    if (choixAttaque.equalsIgnoreCase("non")){
                        break;
                    }
                    if (choixAttaque.equalsIgnoreCase("oui")) {
                        while (plateau.getMonstreEnJeuAvecSort(joueur).size()>0){
                            System.out.println("Monstre disponible sur votre plateau : ");
                            plateau.afficherMonstreEnJeuAvecSort(joueur);
                            System.out.println("Choisissez l'ID du monstre à faire attaquer ");
                            int idMonstre;
                            //vérifie que l'entrée est un entier pour pas faire planter le programme
                            if (scanner.hasNextInt()) {
                                idMonstre = scanner.nextInt();
                            } else {
                                System.out.println("Veuillez entrer un ID de monstre valide (entier).");
                                scanner.next();
                                continue; // Retourner au début de la boucle pour redemander une entrée.
                            }
                            System.out.println("Monstre choisi : " + idMonstre);

                            // Vérifiez si le monstre est bien présent sur le jeu et si son sort est disponible
                            if (plateau.getMonstreParId(idMonstre)== null) {
                                System.out.println("Merci de choisir un monstre valide");
                            } else if (plateau.getMonstreParId(idMonstre).sortDisponible() == false) {
                                System.out.println("Ce monstre a déjà utilisé son sort");
                            } else {
                                if ( plateau.getMonstresSurPlateau(plateau.getJoueurEnnemi(joueur)).isEmpty()) {
                                    System.out.println("Il n'y a pas de monstre ennemi sur le plateau, le joueur adverse est attaque");
                                    joueur.getEnnemie(plateau).getChampion().subirDegats(plateau.getMonstreParId(idMonstre).getForceAdaptative());
                                    plateau.getMonstreParId(idMonstre).sortUtilise();
                                    break;
                                }else {
                                    System.out.println("Monstre ennemi disponible : ");
                                    plateau.afficherMonstreEnJeu(plateau.getJoueurEnnemi(joueur));
                                    int idMonstreEnnemi = scanner.nextInt();
                    
                                    Monstre attaquant = plateau.getMonstreParId(idMonstre);
                                    Monstre ennemi = plateau.getMonstreParId(idMonstreEnnemi);
                    
                                    // Vérifiez si les deux monstres sont valides
                                    if (attaquant != null && ennemi != null) {
                                        // Attaque du monstre ennemi
                                        ennemi.subirDegats(attaquant.getForceAdaptative(), plateau, plateau.getJoueurEnnemi(joueur));
                                        plateau.getMonstreParId(idMonstre).sortUtilise();
                                        // Affiche les points de vie du monstre ennemi après l'attaque
                                        if (ennemi.getPv() > 0) 
                                            System.out.println("Points de vie du monstre ennemi : " + ennemi.getPv());
                                        else logger.info("Le monstre " + ennemi.getNom() + " est mort suite à l'attaque de " + attaquant.getNom()); 
                                    }
                                }
                            }
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            System.out.println("Fin de la phase d'attaque  ");
        }


    public static void afficherMain(Joueur joueur) throws InterruptedException {
        System.out.println("Main de "+joueur.getNom()+": ");
        for (Carte carte : joueur.getMain()){
            System.out.println("Nom : " + carte.getNom() + " Classe : " + carte.getClasse());
            Thread.sleep(0);//250
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


    public boolean partieTerminee() {
        // Vérifie si les points de vie du champion de joueur1 sont épuisés
        if (joueur1.getChampion().getPv() <= 0) {
            System.out.println("La partie est terminée. Le joueur " + joueur2.getNom() + " a gagné!");
            return true;
        }
    
        // Vérifie si les points de vie du champion de joueur2 sont épuisés
        if (joueur2.getChampion().getPv() <= 0) {
            System.out.println("La partie est terminée. Le joueur " + joueur1.getNom() + " a gagné!");
            return true;
        }
    
        // Si aucune des conditions ci-dessus n'est remplie, la partie n'est pas terminée
        return false;
    }

}

