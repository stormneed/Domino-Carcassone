import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PartieGraph extends JPanel {
    PartieGraphFonc p;

    TuileGraph pioché;

    JPanel grid;

    JPanel tournes;
    JLabel[] score;
    JPanel scores;
    JPanel main;

    JLabel iconSac;

    JButton tourneGauche,tourneDroite;

    Window window;

    public PartieGraph(int nombreJoueur, int nombreIA, Table table, Sac sac,Window window){
        this.window=window;
        this.setLayout(new BorderLayout());
        p=new PartieGraphFonc(nombreJoueur, nombreIA, table, sac,this);
        grid=new JPanel();
        grid.setPreferredSize(new Dimension((int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight(),(int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight()));
        grid.setLayout(new GridLayout(p.table.plateau.length,p.table.plateau.length));
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
            int finalI = i;
            label.addActionListener(e->p.poser(finalI));
            label.setEnabled(false);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            grid.add(label);
        }
        main=new JPanel();
        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));

        tournes=new JPanel();
        tournes.setLayout(new BoxLayout(tournes,BoxLayout.X_AXIS));
        tourneGauche=new JButton(new ImageIcon("ressources/icon/tourneGauche.png"));
        tourneDroite=new JButton(new ImageIcon("ressources/icon/tourneDroite.png"));

        tourneGauche.addActionListener(e-> pioché.tourneGauche());
        tourneDroite.addActionListener(e-> pioché.tourneDroite());
        tournes.add(Box.createHorizontalGlue());
        tournes.add(tourneGauche);
        tournes.add(tourneDroite);


        iconSac= new JLabel(new ImageIcon("ressources/icon/icons8-pouch-bag-58.png"));
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

        TuileGraph start=new TuileGraph(sac.contenu.getFirst());
        sac.contenu.removeFirst();
        grid.remove(60);
        grid.add(start,60);
        table.premierePose(start.tuile);

        pioché=new TuileGraph();
        pioché.setPreferredSize(new Dimension(60,60));
        main.add(Box.createGlue());
        main.add(Box.createGlue());
        main.add(Box.createGlue());
        main.add(Box.createGlue());
        main.add(iconSac);
        this.add(grid,BorderLayout.CENTER);
        this.add(scores,BorderLayout.EAST);
        this.add(main,BorderLayout.WEST);
        this.setVisible(true);
    }

    public void poser(int index){
        if(pioché!=null) {
            grid.remove(index);
            grid.add(pioché, index);
            main.add(Box.createGlue(),2);
            for (Component b: this.grid.getComponents()) {
                if(!(b instanceof TuileGraph)) b.setEnabled(false);
            }
            main.remove(1);
            main.add(Box.createGlue(),1);
            revalidate();
        }
        else{
            JLabel info=new JLabel("Cliquez ici pour piocher ->");
            info.setVerticalAlignment(SwingConstants.BOTTOM);
            window.add(info,BorderLayout.WEST);
        }

    }

    public class PartieGraphFonc extends Partie{

        PartieGraph pg;

        public PartieGraphFonc(int nombreJoueur, int nombreIA, Table table, Sac sac,PartieGraph pg) {
            super(nombreJoueur, nombreIA, table, sac);
            this.pg=pg;
        }

        public void jouerTourGraph(){
            Joueur actuel=joueurSuivant();
            piocher(actuel);

        }

        public void piocher(Joueur j){
            TuileGraph tg = new TuileGraph(j.pieceMain);
            tg.setPreferredSize(new Dimension(80,80));
            tg.setHorizontalAlignment(SwingConstants.CENTER);
            j.pieceMain = null;
            pioché=tg;
            tg.setVisible(true);
            main.remove(2);
            main.add(tg,2);
            for (Component b:
                 pg.grid.getComponents()) {
                 b.setEnabled(true);
            }
            main.remove(1);
            main.add(tournes,1);
            pg.revalidate();
            /*
            MovementAnimation mv=new MovementAnimation(tg,tg.getX(),window.getHeight()/2,main);
            mv.start();
            pg.add(tg,BorderLayout.CENTER);
            */


        }

        public void poser(int index){
            JLabel info=new JLabel("Placement Impossible, réessayez");
            if(table.estPosable(pioché.tuile,index/11,index%11)) {
                table.pose(pioché.tuile,index/11,index%11,joueurs.get(indexJoueur));
                grid.remove(index);
                grid.add(pioché, index);
                main.add(Box.createGlue(),2);
                for (Component b: pg.grid.getComponents()) {
                    if(!(b instanceof TuileGraph)) b.setEnabled(false);
                }
                main.remove(1);
                main.add(Box.createGlue(),1);
                window.remove(info);
                revalidate();
            }
            else{
                info.setVerticalAlignment(SwingConstants.BOTTOM);
                window.add(info,BorderLayout.WEST);
                window.revalidate();
            }

        }
    }




}
