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
        return returnJoueur;
    }

    public void jouerTourCons() {
        if (!estFini()) {
            // Scanner
            Joueur joueurActuel = joueurSuivant();
            String scan = "";
            if (scan.equals("s")) {

            }

        }
    }
    
    public boolean estFini () {
        if (sac.contenu.isEmpty()) {
            return true;
        }
        if (joueurs.size()<=0) {
            return true;
        }
        return false;
    }

}
