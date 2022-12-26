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
            joueurs.add(new Joueur(this,i+1));
        }
        this.table=table;
        this.sac=sac;
    }

    public Joueur joueurSuivant () {
        Joueur returnJoueur = joueurs.get(indexJoueur);
        System.out.println("Tour de joueur " + returnJoueur.numero);
        if (indexJoueur==joueurs.size()-1) {
            indexJoueur=0;
        }
        else {indexJoueur+=1;}
        returnJoueur.piocher();
        return returnJoueur;
    }

    public Joueur vainqueur () {
        int scoreMax = 0;
        Joueur returnJoueur = null;
        for (int i = 0; i<joueurs.size(); i++) {
            if (joueurs.get(i).score>scoreMax) {
                returnJoueur=joueurs.get(i);
                scoreMax = joueurs.get(i).score;
            }
            else if (joueurs.get(i).score>scoreMax) {
                returnJoueur = null;
            }
        }
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
        if (vainqueur()!=null) {
            System.out.println("Le vainqueur est Joueur " + vainqueur().numero);
        }
        else {
            System.out.println("Il n'y a pas de vainqueur");
        }
        afficheScoreCons();
    }

    public void jouerTourCons() {
        Joueur joueurActuel = joueurSuivant();
        joueurActuel.afficheScore();
        joueurActuel.pieceMain.afficheTuile();
        boolean tourTerminer = false;
        Scanner scan = new Scanner(System.in);
        int scanposX = -1;
        int scanposY = -1;
        while (!tourTerminer) {
            System.out.print("Veuillez choisir une action : p (pose) / d (defaussez) / a (abandon) / l (tourne vers la gauche) / r (tourne vers la droite) : "  );
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
                            ajouteScore(joueurActuel, scanposY - 1, scanposX - 1);
                            input = scan.nextLine();
                        }
                        catch (InputMismatchException exception) {
                            System.out.println("Veuillez uniquement entrer un entier");
                            input = scan.nextLine();
                        }
                    }
                    catch (InputMismatchException exception) {
                        System.out.println("Veuillez uniquement entrer un entier");
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

    public void ajouteScore (Joueur j, int coordX, int coordY) {
        Tuile current = table.plateau[coordX][coordY];
        if (table.estAdjacentNord(coordX, coordY)) {
            j.score+=
            current.bords[0].charAt(0)+
            current.bords[0].charAt(1)+
            current.bords[0].charAt(2) - 48*3;
        }
        if (table.estAdjacentSud(coordX, coordY)) {
            j.score+=
            current.bords[2].charAt(0)+
            current.bords[2].charAt(1)+
            current.bords[2].charAt(2) - 48*3;
        }
        if (table.estAdjacentOuest(coordX, coordY)) {
            j.score+=
            current.bords[3].charAt(0)+
            current.bords[3].charAt(1)+
            current.bords[3].charAt(2) - 48*3;
        }
        if (table.estAdjacentEst(coordX, coordY)) {
            j.score+=
            current.bords[1].charAt(0)+
            current.bords[1].charAt(1)+
            current.bords[1].charAt(2) - 48*3;
        }
    }

    public void afficheScoreCons () {
        for (int i = 0; i<joueurs.size(); i++) {
            System.out.println("Score de Joueur " + joueurs.get(i).numero + " : " + joueurs.get(i).score);
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
