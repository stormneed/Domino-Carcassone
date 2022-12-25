import java.util.ArrayList;

public class Partie {
    Sac sac;
    ArrayList<Joueur> joueurs;
    Table table;
    int indexJoueur;

    public Joueur joueurSuivant () {
        Joueur returnJoueur = joueurs.get(indexJoueur);
        if (indexJoueur==joueurs.size()-1) {
            indexJoueur=0;
        }  
        else {indexJoueur+=1;}
        returnJoueur.piocher();
        return returnJoueur;
    }

    public void jouerPartieCons () {
        while (!estFini()) {
            table.afficheCons();
            jouerTourCons();
        }
    }

    public void jouerTourCons() {
        Joueur joueurActuel = joueurSuivant();
        joueurActuel.pieceMain.afficheTuile();
        boolean tourTerminer = false;
        while (!tourTerminer) {

            String scan = ""; // Scanner
            if (scan.equals("p")) { //pose
                String scanposX = ""; // Scanner X
                String scanposY = ""; // Scanner Y
                tourTerminer = joueurActuel.poser(Integer.valueOf(scanposX), Integer.valueOf(scanposY));
            }
            if (scan.equals("d")) { //defausse
                joueurActuel.defausser();
                tourTerminer=true;
            }
            if (scan.equals("a")) { //abandon
                 joueurActuel.abandon();
                tourTerminer=true;
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
