import javax.swing.*;
import java.awt.*;

public class Tuile {
    String[] bords;

    public Tuile () {
        bords=new String[4];// Tuile vide
    }

    public Tuile (String bordNord, String bordEst, String bordSud, String bordOuest) {
        bords = new String[4];
        bords[0] = bordNord;
        bords[1] = bordEst;
        bords[2] = bordSud;
        bords[3] = bordOuest;
    }

    public void tourneDroite () {
        String temp = bords[3];
        bords[3] = bords[2];
        bords[2] = bords[1];
        bords[1] = bords[0];
        bords[0] = temp;
    }

    public void tourneGauche () {
        String temp = bords[0];
        bords[0] = bords[1];
        bords[1] = bords[2];
        bords[2] = bords[3];
        bords[3] = temp;
    }


    public void afficheTuile() {
        System.out.println("Piece actuelle : ");
        System.out.println((char)27+"[4m       "+(char)27+"[0m");
        System.out.println("| " + bords[0] + " |");
        System.out.println("|" + bords[3].charAt(2) + "   " + bords[1].charAt(0) + "|");
        System.out.println("|" + bords[3].charAt(1) + "   " + bords[1].charAt(1) + "|");
        System.out.println("|" + bords[3].charAt(0) + "   " + bords[1].charAt(2) + "|");
        System.out.println((char)27+"[4m| " + bords[2].charAt(2) + bords[2].charAt(1) + bords[2].charAt(0) + " |"+(char)27+"[0m");
    }

    public static Tuile genereTuile(String bord){
        String s=""+bord.charAt(2)+bord.charAt(1)+bord.charAt(0);
        Tuile t=new Tuile();
        t.bords[(int)(Math.random()*4)]=s;
        for (int i = 0; i < 4; i++) {
            if(t.bords[i]==null){
                t.bords[i]=""+(int)(Math.random()*5)+(int)(Math.random()*5)+(int)(Math.random()*5);
            }
        }
        return t;
    }


    public class TuileGraph extends JButton {
        Tuile tuile;

        public TuileGraph(Tuile t) {
            this.tuile = t;
            this.setIcon(matchIcon(t));
        }

        public ImageIcon matchIcon(Tuile t) {
            String path = "ressources/tiles/";
            for (int i = 0; i < t.bords.length; i++) {
                path += t.bords[i];
            }
            if (((TuileCarc) t).abbaye) path += "(abbaye)";
            if (((TuileCarc) t).symbole) path += "(symbole)";
            if (((TuileCarc) t).separated) path += "(sep)";
            ImageIcon icon = new ImageIcon(path + ".png");
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(190, 125, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newimg);
            return icon;
        }

        public void tourneGauche() {
            tuile.tourneGauche();
            matchIcon(tuile);
            this.revalidate();
        }
    }






}