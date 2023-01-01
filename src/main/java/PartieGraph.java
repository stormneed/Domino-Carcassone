import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PartieGraph extends JPanel {
    PartieGraphFonc p;

    JPanel grid;

    JLabel[] score;
    JPanel scores;
    JPanel main;

    JLabel iconSac;

    Window window;

    public PartieGraph(int nombreJoueur, int nombreIA, Table table, Sac sac,Window window){
        this.window=window;
        this.setLayout(new BorderLayout());
        p=new PartieGraphFonc(nombreJoueur, nombreIA, table, sac,this);
        grid=new JPanel();
        int test=(int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight();
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
            label.setEnabled(false);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            grid.add(label);
        }

        iconSac= new JLabel(new ImageIcon("ressources/icon/icons8-pouch-bag-58.png"));
        iconSac.setPreferredSize(new Dimension(130,130));
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

        this.add(grid,BorderLayout.CENTER);
        this.add(scores,BorderLayout.EAST);
        this.add(iconSac,BorderLayout.WEST);
        this.setVisible(true);
    }

    public void poser(int x, int y, TuileGraph t){
        grid.remove(y*p.table.plateau.length+x);
        grid.add(t,y*p.table.plateau.length+x);
        revalidate();

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
            System.out.println("pioche");
            TuileGraph tg=new TuileGraph(j.pieceMain);
            pg.poser(0,0,tg);
            tg.setVisible(true);
            //pg.add(tg,BorderLayout.EAST);
            //MovementAnimation mv=new MovementAnimation(tg,tg.getX(),window.getHeight(),window);
            //mv.start();
            //pg.add(tg,BorderLayout.CENTER);

        }
    }




}
