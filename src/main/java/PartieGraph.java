import javax.swing.*;
import java.awt.*;

public class PartieGraph extends Partie {

    Affichage aff;

    public PartieGraph(int nombreJoueur, int nombreIA, Table table, Sac sac, Affichage aff) {
        super(nombreJoueur, nombreIA, table, sac);
        this.aff = aff;

    }

    public void jouerTourGraph() {
        Joueur actuel = joueurSuivant();
        piocher(actuel);
    }

    public void piocher(Joueur j) {
        TuileGraph tg = new TuileGraph(j.pieceMain);
        tg.setPreferredSize(new Dimension(80, 80));
        tg.setHorizontalAlignment(SwingConstants.CENTER);
        j.pieceMain = null;
        aff.pioché = tg;
        tg.setVisible(true);
        aff.main.remove(2);
        aff.main.add(tg, 2);
        int i = 0;
        for (Component b : aff.grid.getComponents()) {
            if (table.estPosable(aff.pioché.tuile, i / table.plateau.length, i % table.plateau.length)) {
                b.setEnabled(true);
            } else {
                if (table.estVide(i / table.plateau.length, i % table.plateau.length))
                    b.setEnabled(false);
            }
            i++;
        }
        aff.main.remove(1);
        aff.main.add(aff.tournes, 1);
        aff.revalidate();
        /*
         * MovementAnimation mv=new
         * MovementAnimation(tg,tg.getX(),window.getHeight()/2,main);
         * mv.start();
         * pg.add(tg,BorderLayout.CENTER);
         */

    }

    public void poser(int index) {
        
        JLabel info = new JLabel("Placement Impossible, réessayez");
        if (table.estPosable(aff.pioché.tuile, index / table.plateau.length, index % 11)) {
            table.pose(aff.pioché.tuile, index / table.plateau.length, index % 11, joueurs.get(indexJoueur));
            aff.grid.remove(index);
            aff.grid.add(aff.pioché, index);
            aff.main.add(Box.createGlue(), 2);
            for (Component b : aff.grid.getComponents()) {
                if (!(b instanceof TuileGraph))
                    b.setEnabled(false);
            }
            aff.main.remove(1);
            aff.main.add(Box.createGlue(), 1);
            aff.window.remove(info);
            if (!(this.table.plateau[0][0] instanceof TuileCarc)) {
                ajouteScore(joueurs.get(indexJoueur), index / table.plateau.length, index % 11);
                aff.updateScore();
            }
            aff.revalidate();

        } else {
            info.setVerticalAlignment(SwingConstants.BOTTOM);
            aff.window.add(info, BorderLayout.WEST);
            aff.window.revalidate();
        }

        if (suivantEstIA()) {
            Joueur actuel = joueurSuivant();
            aff.pioché = new TuileGraph(actuel.pieceMain);
            aff.pioché.setVisible(true);
            tourIA(actuel.pieceMain, actuel);
        }

    }

    public void tourneDroite() {
        aff.pioché.tourneDroite();
        int i = 0;
        for (Component b : aff.grid.getComponents()) {
            if (table.estPosable(aff.pioché.tuile, i / table.plateau.length, i % table.plateau.length)) {
                b.setEnabled(true);
            } else {
                if (table.estVide(i / table.plateau.length, i % table.plateau.length))
                    b.setEnabled(false);
            }
            i++;
        }
    }

    public void tourneGauche() {
        aff.pioché.tourneGauche();
        int i = 0;
        for (Component b : aff.grid.getComponents()) {
            if (table.estPosable(aff.pioché.tuile, i / table.plateau.length, i % table.plateau.length)) {
                b.setEnabled(true);
            } else {
                if (table.estVide(i / table.plateau.length, i % table.plateau.length))
                    b.setEnabled(false);
            }
            i++;
        }
    }

    public void tourIAGraph (Tuile piece, Joueur ai) {
        for (int i = 0; i<table.plateau.length; i++) {
            for (int j = 0; j<table.plateau.length; j++) {
                if (table.plateau[i][j]!=null) {
                    if (table.possedeBordValide(piece, i, j)) {
                        for (int x = 0; x<4; x++) {
                            if (i<table.plateau.length-1 && table.estPosable(piece, i+1, j)) {
                                poser(i*table.plateau.length+j);
                                ajouteScore(ai, i+1, j);                       
                                return;
                            }
                            if (i > 0 && table.estPosable(piece, i-1, j)) {
                                poser(i*table.plateau.length+j);
                                ajouteScore(ai, i-1, j);   
                                return;
                            }
                            if (j < table.plateau.length-1 && table.estPosable(piece, i, j+1)) {
                                poser(i*table.plateau.length+j);
                                ajouteScore(ai, i, j+1);   
                                return;
                            }
                            if (j > 0 && table.estPosable(piece, i, j-1)) {
                                poser(i*table.plateau.length+j);
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

}
