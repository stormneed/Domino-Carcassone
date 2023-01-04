import java.awt.*;
import javax.swing.*;

public class GenDomino extends JPanel{


    JPanel colonne;
    JPanel Buttons;

    JButton tourneGauche,tourneDroite;
    JButton change;
    Tuile domino;

    JPanel affichage;

    JPanel n,e,s,w;

    Sac sac;

    public GenDomino(Sac sac){
        this.sac=sac;
        this.setLayout(new BorderLayout());
        affichage=new JPanel();
        affichage.setPreferredSize(new Dimension(200,200));
        colonne=new JPanel();
        colonne.setLayout(new BoxLayout(colonne,BoxLayout.Y_AXIS));
        Buttons=new JPanel();
        Buttons.setLayout(new BoxLayout(Buttons,BoxLayout.X_AXIS));
        tourneGauche=new JButton(new ImageIcon("ressources/icon/tourneGauche.png"));
        tourneDroite=new JButton(new ImageIcon("ressources/icon/tourneDroite.png"));

        tourneGauche.addActionListener(e-> {domino.tourneGauche();affichage.removeAll();
            affichage.add(afficheDomino());revalidate();});
        tourneDroite.addActionListener(e-> {domino.tourneDroite();affichage.removeAll();
           affichage.add(afficheDomino());revalidate();});
        Buttons.add(Box.createHorizontalGlue());
        Buttons.add(tourneGauche);
        Buttons.add(tourneDroite);

        change=new JButton("New Tile");
        change.addActionListener(e-> {domino=sac.piocher();affichage.removeAll();
            affichage.add(afficheDomino());revalidate();});

        this.add(affichage);
        JLabel j=new JLabel("Pour les noms, n'oublie pas que le bord sud et ouest(gauche et droite) sont inversé");
        j.setFont(new Font("Impact",Font.PLAIN,20));
        this.add(j,BorderLayout.SOUTH);
        this.add(Buttons,BorderLayout.WEST);
        this.add(change,BorderLayout.EAST);




    }

    public JPanel afficheDomino(){
        JPanel piece=new JPanel(new BorderLayout());
        JPanel[] bord=new JPanel[4];
        for(int i=0;i<4;i++){
            bord[i]=new JPanel();
            if(i%2==0){
                bord[i].setLayout(new BoxLayout(bord[i],BoxLayout.X_AXIS));
                bord[i].add(Box.createHorizontalGlue());
                if(i==0) {
                    bord[i].add(new JLabel("    " + domino.bords[i].charAt(0)));
                    bord[i].add(new JLabel("   " + domino.bords[i].charAt(1)));
                    bord[i].add(new JLabel("   " + domino.bords[i].charAt(2) + "     "));
                }
                else{
                    bord[i].add(new JLabel("    " + domino.bords[i].charAt(2)));
                    bord[i].add(new JLabel("   " + domino.bords[i].charAt(1)));
                    bord[i].add(new JLabel("   " + domino.bords[i].charAt(0) + "     "));
                }
                bord[i].add(Box.createHorizontalGlue());
            }
            else{
                bord[i].setLayout(new BoxLayout(bord[i],BoxLayout.Y_AXIS));

                if(i==1) {
                    bord[i].add(new JLabel(domino.bords[i].charAt(0)+" "));
                    bord[i].add(new JLabel(domino.bords[i].charAt(1)+" "));
                    bord[i].add(new JLabel(domino.bords[i].charAt(2)+" "));
                }
                else{
                    bord[i].add(new JLabel(" " + domino.bords[i].charAt(2)));
                    bord[i].add(new JLabel(" " + domino.bords[i].charAt(1)));
                    bord[i].add(new JLabel(" " + domino.bords[i].charAt(0)));
                }


            }


        }
        piece.add(bord[0],BorderLayout.NORTH);
        piece.add(bord[1],BorderLayout.EAST);
        piece.add(bord[2],BorderLayout.SOUTH);
        piece.add(bord[3],BorderLayout.WEST);

        piece.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));


        return piece;
    }

}
