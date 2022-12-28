import javax.swing.*;
import java.awt.*;

public class PartieGraph extends JPanel {
    Partie p;

    JPanel grid;

    JLabel[] score;
    JPanel scores;
    JPanel main;

    public PartieGraph(int nombreJoueur, int nombreIA, Table table, Sac sac){
        this.setLayout(new BorderLayout());
        p=new Partie(nombreJoueur, nombreIA, table, sac);
        grid=new JPanel();
        grid.setLayout(new GridLayout(p.table.plateau.length,p.table.plateau.length));
        grid.setSize((int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight(),(int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight());
        grid.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        scores=new JPanel();
        scores.setLayout(new BoxLayout(scores,BoxLayout.Y_AXIS));
        score=new JLabel[nombreJoueur];
        for(int i=0;i<nombreJoueur;i++){
            score[i]=new JLabel();
            score[i].setText("Joueur"+ (i+1) +"=0");
            score[i].setFont(new Font("Impact",Font.PLAIN,50));
            score[i].setAlignmentY(CENTER_ALIGNMENT);
            scores.add(score[i]);
        }


        for (int i =0; i<(p.table.plateau.length*p.table.plateau.length); i++){
            final JButton label = new JButton();
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            grid.add(label);
        }

        this.add(grid,BorderLayout.CENTER);
        this.add(scores,BorderLayout.EAST);
        this.setVisible(true);
    }

    public void poser(int x, int y, TuileGraph t){
        grid.remove(y*p.table.plateau.length+x);
        grid.add(t,y*p.table.plateau.length+x);
        revalidate();

    }


    public class PartieFonc extends Partie{

        public PartieFonc(int nombreJoueur, int nombreIA, Table table, Sac sac) {
            super(nombreJoueur, nombreIA, table, sac);
        }
    }


}
