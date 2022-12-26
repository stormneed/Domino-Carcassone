import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Partie {
    Sac sac;
    ArrayList<Joueur> joueurs;
    Table table;
    int indexJoueur;

    public Partie (int nombreJoueur, Table table, Sac sac) {
        this.joueurs=new ArrayList<Joueur>();
        for (int i=0; i<nombreJoueur; i++) {
            joueurs.add(new Joueur(this));
        }
        this.table=table;
        this.sac=sac;
    }

    public Joueur joueurSuivant () {
        System.out.println("Tour de joueur " + (indexJoueur+1));
        Joueur returnJoueur = joueurs.get(indexJoueur);
        if (indexJoueur==joueurs.size()-1) {
            indexJoueur=0;
        }
        else {indexJoueur+=1;}
        returnJoueur.piocher();
        return returnJoueur;
    }

    public void jouerPartieCons () {
        table.premierePose(sac.piocher());
        while (!estFini()) {
            System.out.println("------------------------------------------------------------");
            table.afficheCons();
            System.out.println();
            jouerTourCons();
        }
        System.out.println("Fin de la partie");
    }

    public void jouerTourCons() {
        Joueur joueurActuel = joueurSuivant();
        joueurActuel.pieceMain.afficheTuile();
        boolean tourTerminer = false;
        Scanner scan = new Scanner(System.in);
        int scanposX = -1;
        int scanposY = -1;
        while (!tourTerminer) {
            System.out.print("Veuillez choisir une action : p (pose) / d (defaussez) / a (abandon)/ l (tourne vers la gauche)/ r (tourne vers la droite)"  );
            String input = scan.nextLine();
            switch (input) {
                case "p" -> {  //pose
                    System.out.println("Entrez la coordonnée X : ");
                    try {
                        scanposX = scan.nextInt();
                        System.out.println("Entrez la coordonnée Y : ");
                        try {
                            scanposY = scan.nextInt();
                            tourTerminer = joueurActuel.poser(scanposY - 1, scanposX - 1);
                            input = scan.nextLine();
                        }
                        catch (InputMismatchException exception) {
                            System.out.println("Veuillez entrez un entier seulement");
                            input = scan.nextLine();
                        }
                    }
                    catch (InputMismatchException exception) {
                        System.out.println("Veuillez entrez un entier seulement");
                        input = scan.nextLine();
                    }
                }
                case "d" -> {  //defausse
                    joueurActuel.defausser();
                    tourTerminer = true;
                    System.out.println("defausse");
                }
                case "a" -> {  //abandon
                    joueurActuel.abandon();
                    tourTerminer = true;
                    System.out.println("Abandon de Joueur " + (indexJoueur + 1));
                }
                case "l" -> {
                    joueurActuel.pieceMain.tourneGauche();
                    joueurActuel.pieceMain.afficheTuile();
                    break;
                }
                case "r" -> {
                    joueurActuel.pieceMain.tourneDroite();
                    joueurActuel.pieceMain.afficheTuile();
                    break;
                }
                default -> System.out.println("Action non reconnu");
            }
        }


    }


    
    public boolean estFini () {
        if (sac.contenu.isEmpty()) {
            return true;
        }
        if (joueurs.size()<=1) {
            return true;
        }
        return false;
    }

    public void abandon (Joueur j) {
        joueurs.remove(j);
        if (indexJoueur!=0) {
            indexJoueur-=1;
        }
        else {
            indexJoueur=joueurs.size();
        }
    }

}
