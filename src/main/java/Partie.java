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
            else if (joueurs.get(i).score==scoreMax) {
                returnJoueur = null;
            }
        }
        return returnJoueur;
    }





    public void ajouteScore (Joueur j, int coordX, int coordY) {
        Tuile current = table.plateau[coordX][coordY];
        if (current!=null) {
            if (table.estAdjacentNord(coordX, coordY)) {
                j.score +=
                        current.bords[0].charAt(0) +
                                current.bords[0].charAt(1) +
                                current.bords[0].charAt(2) - 48 * 3;
            }
            if (table.estAdjacentSud(coordX, coordY)) {
                j.score +=
                        current.bords[2].charAt(0) +
                                current.bords[2].charAt(1) +
                                current.bords[2].charAt(2) - 48 * 3;
            }
            if (table.estAdjacentOuest(coordX, coordY)) {
                j.score +=
                        current.bords[3].charAt(0) +
                                current.bords[3].charAt(1) +
                                current.bords[3].charAt(2) - 48 * 3;
            }
            if (table.estAdjacentEst(coordX, coordY)) {
                j.score +=
                        current.bords[1].charAt(0) +
                                current.bords[1].charAt(1) +
                                current.bords[1].charAt(2) - 48 * 3;
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
