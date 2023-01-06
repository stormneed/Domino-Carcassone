import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PartieGraph extends Partie {

    Affichage aff;

    TuileGraph lastput;
    public PartieGraph(int nombreJoueur, int nombreIA, Table table, Sac sac, Affichage aff) {
        super(nombreJoueur, nombreIA, table, sac);
        this.aff = aff;

    }

    public void jouerTourGraph() {
        Joueur actuel = joueurSuivant();
        piocher(actuel);
    }

    public void piocher(Joueur j) {
        TuileGraph tg = new TuileGraph(j.pieceMain,11);
        tg.setPreferredSize(new Dimension(80, 80));
        tg.setHorizontalAlignment(SwingConstants.CENTER);
        j.pieceMain = null;
        aff.pioché = tg;
        aff.pioché.setPreferredSize(new Dimension(tg.getPreferredSize()));
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

    }

    public void poser(int index) {
        table.pose(aff.pioché.tuile, index / table.plateau.length, index % table.plateau.length, actuel);
        aff.grid.remove(index);
        aff.main.remove(2);
        lastput=new TuileGraph(aff.pioché.tuile,table.plateau.length);
        aff.grid.add(lastput, index);
        lastput.removeActionListener((e) -> {
            String[] optionsToChoose = {"nord", "sud", "est", "west"};
            String getBord = (String) JOptionPane.showInputDialog(
                    null,
                    "Sur quels bord voulez vous votre pion? ",
                    "Choisissez un bord",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsToChoose,
                    optionsToChoose[3]);

            lastput.posePion(getBord, actuel.numero);
            aff.revalidate();
        });
        if(lastput.tuile instanceof TuileCarc) {
            lastput.addActionListener( (e) -> {
                    String[] optionsToChoose = {"nord", "sud", "est", "west"};
                    String getBord = (String) JOptionPane.showInputDialog(
                            null,
                            "Sur quels bord voulez vous votre pion? ",
                            "Choisissez un bord",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            optionsToChoose,
                            optionsToChoose[3]);
                    lastput.posePion(getBord, actuel.numero);
                    aff.revalidate();
                });
            }
        for (Component b : aff.grid.getComponents()) {
            if (!(b instanceof TuileGraph))
                b.setEnabled(false);
        }

        aff.main.remove(1);
        aff.main.add(Box.createGlue(), 1);
        aff.main.add(Box.createGlue(),2);

        if (!(this.table.plateau[0][0] instanceof TuileCarc)) {
            ajouteScore(actuel, index / table.plateau.length, index % table.plateau.length);
            aff.updateScore();
        }
        aff.revalidate();

        if(sac.estVide()){
            aff.removeAll();
            JLabel victory=new JLabel("Game Over");
            victory.setFont(new Font("Impact",Font.BOLD,80));
            aff.add(victory);
            if(!(lastput.tuile instanceof TuileCarc)){
                if(vainqueur()!=null) {
                    JLabel winner = new JLabel("Joueur " + vainqueur().numero + " a gagné");
                    aff.add(winner);
                }
                else{
                    JLabel winner = new JLabel("Egalité");
                    aff.add(winner);
                }

            }
            aff.revalidate();

        }

        if (suivantEstIA()) {
            Joueur tmp = joueurSuivant();
            aff.pioché = new TuileGraph(tmp.pieceMain,table.plateau.length);
            aff.pioché.setVisible(true);
            tourIAGraph(tmp.pieceMain, tmp);
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

                                poser((i+1)*table.plateau.length+j);
                                aff.refresh();
                                return;
                            }
                            if (i > 0 && table.estPosable(piece, i-1, j)) {
                                poser((i-1)*table.plateau.length+j);
                                aff.refresh();
                                return;
                            }
                            if (j < table.plateau.length-1 && table.estPosable(piece, i, j+1)) {
                                poser(i*table.plateau.length+(j+1));
                                aff.refresh();

                                return;
                            }
                            if (j > 0 && table.estPosable(piece, i, j-1)) {
                                poser(i*table.plateau.length+(j-1));
                                aff.refresh();
                                return;
                            }
                            aff.pioché.tourneDroite();
                        }
                    }
                }
            }
        }
        ai.defausser();
    }

}
