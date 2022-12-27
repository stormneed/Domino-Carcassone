import java.util.ArrayList;

public class Partie {
    Sac sac;
    ArrayList<Joueur> joueurs;
    Table table;
    int indexJoueur;

    public Partie (int nombreJoueur, int nombreIA, Table table, Sac sac) {
        this.joueurs=new ArrayList<Joueur>();
        for (int i=1; i<=nombreJoueur-nombreIA; i++) {
            joueurs.add(new Joueur(this,i,false));
        }
        for (int j=1; j<=nombreIA; j++) {
            joueurs.add(new Joueur(this,joueurs.size()+j-1,true));
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

    public void tourIA (Tuile piece, Joueur ai) {
        for (int i = 0; i<table.plateau.length; i++) {
            for (int j = 0; j<table.plateau.length; j++) {
                if (table.plateau[i][j]!=null) {
                    if (table.possedeBordValide(piece, i, j)) {
                        for (int x = 0; x<4; x++) {
                            if (i<table.plateau.length-1 && table.estPosable(piece, i+1, j)) {
                                table.pose(piece, i+1, j, ai);
                                ajouteScore(ai, i+1, j);
                                return;
                            }
                            if (i > 0 && table.estPosable(piece, i-1, j)) {
                                table.pose(piece, i-1, j, ai);
                                ajouteScore(ai, i-1, j);
                                return;
                            }
                            if (j < table.plateau.length-1 && table.estPosable(piece, i, j+1)) {
                                table.pose(piece, i, j+1, ai);
                                ajouteScore(ai, i, j+1);
                                return;
                            }
                            if (j > 0 && table.estPosable(piece, i, j-1)) {
                                table.pose(piece, i, j-1, ai);
                                ajouteScore(ai, i, j-1);
                                return;
                            }
                            piece.tourneDroite();
                        }
                    }
                }
            }
        }
        ai.defausser();
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
