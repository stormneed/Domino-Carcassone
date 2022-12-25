import java.util.ArrayList;
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
        while (!tourTerminer) {
            System.out.print("Veuillez choisir une action : p (pose) / d (defaussez) / a (abandon) : "  );
            String input = scan.nextLine();
            if (input.equals("p")) { //pose
                System.out.println("Entrez la coordonnée X : ");
                String scanposX = scan.nextLine() ; // Scanner X
                System.out.println("Entrez la coordonnée Y : ");
                String scanposY = scan.nextLine(); // Scanner Y
                tourTerminer = joueurActuel.poser(Integer.valueOf(scanposX)-1, Integer.valueOf(scanposY)-1);
            }
            else if (input.equals("d")) { //defausse
                joueurActuel.defausser();
                tourTerminer=true;
                System.out.println("defausse");
            }
            else if (input.equals("a")) { //abandon
                joueurActuel.abandon();
                tourTerminer=true;
                System.out.println("Abandon de Joueur " + (indexJoueur+1));
            }
            else {
                System.out.println("Action non reconnu");
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
