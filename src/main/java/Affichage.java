import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Affichage extends JPanel {
    PartieGraph p;

    TuileGraph pioché;

    JPanel grid;

    JPanel tournes;
    JLabel[] score;
    JPanel scores;
    JPanel main;

    JLabel iconSac;

    JButton tourneGauche, tourneDroite;

    Window window;

    public Affichage(int nombreJoueur, int nombreIA, Table table, Sac sac, Window window) {
        this.window = window;
        this.setLayout(new BorderLayout());
        p = new PartieGraph(nombreJoueur, nombreIA, table, sac, this);
        grid = new JPanel();
        grid.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(),
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        grid.setLayout(new GridLayout(p.table.plateau.length, p.table.plateau.length));
        grid.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        scores = new JPanel();
        scores.setLayout(new BoxLayout(scores, BoxLayout.Y_AXIS));
        score = new JLabel[nombreJoueur];

        for (int i = 0; i < nombreJoueur; i++) {
            score[i] = new JLabel();
            if(sac.contenu.getFirst() instanceof TuileCarc){
                score[i].setIcon(new ImageIcon("ressources/pion/pion"+(i+1)+".png"));
                score[i].setText("Joueur" + (i + 1));
            }
            else {
                score[i].setText("Joueur" + (i + 1) + "=0");
            }
            score[i].setFont(new Font("Impact", Font.PLAIN, 50));
            score[i].setAlignmentY(CENTER_ALIGNMENT);
            scores.add(score[i]);
        }

        for (int i = 0; i < (p.table.plateau.length * p.table.plateau.length); i++) {
            final JButton label = new JButton();
            int finalI = i;
            label.addActionListener(e -> p.poser(finalI));
            label.setEnabled(false);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            grid.add(label);
        }
        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        tournes = new JPanel();
        tournes.setLayout(new BoxLayout(tournes, BoxLayout.X_AXIS));
        tourneGauche = new JButton(new ImageIcon("ressources/icon/tourneGauche.png"));
        tourneDroite = new JButton(new ImageIcon("ressources/icon/tourneDroite.png"));

        tourneGauche.addActionListener(e -> p.tourneGauche());
        tourneDroite.addActionListener(e -> p.tourneDroite());
        tournes.add(Box.createHorizontalGlue());
        tournes.add(tourneGauche);
        tournes.add(tourneDroite);

        iconSac = new JLabel(new ImageIcon("ressources/icon/icons8-pouch-bag-58.png"));
        iconSac.setVerticalAlignment(SwingConstants.BOTTOM);
        iconSac.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                p.jouerTourGraph();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                iconSac.setIcon(new ImageIcon("ressources/icon/icons8-pouch-bag-58-hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                iconSac.setIcon(new ImageIcon("ressources/icon/icons8-pouch-bag-58.png"));
            }
        });

        TuileGraph start = new TuileGraph(sac.contenu.getFirst(),table.plateau.length);
        sac.contenu.removeFirst();
        grid.remove(grid.getComponentCount()/2);
        grid.add(start, grid.getComponentCount()/2);
        table.premierePose(start.tuile);

        pioché = new TuileGraph();
        pioché.setPreferredSize(new Dimension(start.getWidth(), start.getHeight()));
        main.add(Box.createGlue());
        main.add(Box.createGlue());
        main.add(Box.createGlue());
        main.add(Box.createGlue());
        main.add(iconSac);
        this.add(grid, BorderLayout.CENTER);
        this.add(scores, BorderLayout.EAST);
        this.add(main, BorderLayout.WEST);
        this.setVisible(true);
    }

    public void updateScore () {
        if(!(pioché.tuile instanceof TuileCarc)){
            for (int i = 0; i < p.joueurs.size(); i++) {
                scores.remove(i);
                score[i] = new JLabel();
                score[i].setText("Joueur" + (p.joueurs.get(i).numero) + "=" + p.joueurs.get(i).score);
                score[i].setFont(new Font("Impact", Font.PLAIN, 50));
                score[i].setAlignmentY(CENTER_ALIGNMENT);
                scores.add(score[i], i);
            }
        }
    }

    public void refresh(){
        for(int i=0;i<p.table.plateau.length;i++){
            for(int j=0;i<p.table.plateau.length;i++){
                if(p.table.plateau[i][j]!=null && !(grid.getComponent(i*p.table.plateau.length+j) instanceof TuileGraph)){
                    grid.remove(i*p.table.plateau.length+j);
                    grid.add(new TuileGraph(p.table.plateau[i][j],p.table.plateau.length));
                    grid.revalidate();
                }
            }
        }
    }

}
