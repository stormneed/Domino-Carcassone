import java.util.InputMismatchException;
import java.util.Scanner;

public class PartieCons extends Partie{
    public PartieCons(int nombreJoueur, int nombreIA, Table table, Sac sac) {
        super(nombreJoueur, nombreIA, table, sac);
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
        if (joueurActuel.estIA) {
            tourIA((joueurActuel.pieceMain), joueurActuel);
            
        }
        else {
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
    }
    public void afficheScoreCons () {
        for (int i = 0; i<joueurs.size(); i++) {
            System.out.println("Score de Joueur " + joueurs.get(i).numero + " : " + joueurs.get(i).score);
        }
    }

}
