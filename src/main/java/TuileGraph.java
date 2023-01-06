import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TuileGraph extends JButton implements TuileType{
    Tuile tuile;

    int tailleTableau;
    public TuileGraph(Tuile t,int s){
        this.tuile=t;
        this.tailleTableau=s;
        this.setIcon(matchIcon(t));

    }

    public TuileGraph(){
        this.setEnabled(false);
    }

    public ImageIcon matchIcon(Tuile t){
        String path="ressources/tiles/";
        for(int i=0;i<t.bords.length;i++){
            path+=t.bords[i];
        }
        if(t instanceof TuileCarc) {
            if (((TuileCarc) t).abbaye) path += "(abbaye)";
            if (((TuileCarc) t).symbole) path += "(symbole)";
            if (((TuileCarc) t).separated) path += "(sep)";
        }
        ImageIcon icon=new ImageIcon(path + ".png");
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( (int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight()/tailleTableau,(int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight()/tailleTableau ,  Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon( newimg );
        return icon;
    }


    public void posePion(String bord,int couleur)  {
        if(tuile instanceof TuileCarc){
            ((TuileCarc) tuile).pion=true;
            ((TuileCarc) tuile).pBord=bord;
            switch (couleur) {
                case 1:
                    ((TuileCarc) tuile).pCouleur = "blue";
                    break;
                case 2:
                    ((TuileCarc) tuile).pCouleur = "red";
                    break;
                case 3:
                    ((TuileCarc) tuile).pCouleur = "green";
                    break;
                case 4:
                    ((TuileCarc) tuile).pCouleur = "violet";
                    break;
                default:
                    ((TuileCarc) tuile).pCouleur = "red";
                    break;

            }
        }
        affichePion();
    }

    public void affichePion()  {
        if(tuile instanceof TuileCarc) {
            System.out.println();
            String file = "ressources/pion/pion"+((TuileCarc) tuile).pBord + ((TuileCarc) tuile).pCouleur;

            BufferedImage pion;

            try {
                pion = ImageIO.read(new File(file+".png"));
            } catch (IOException e) {
                System.out.println("file not found : "+file+".png");
                pion = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
            }


            BufferedImage bi = new BufferedImage(
                    this.getIcon().getIconWidth(),
                    this.getIcon().getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);
            Graphics graph = bi.createGraphics();
            // paint the Icon to the BufferedImage.
            this.getIcon().paintIcon(null, graph, 0,0);
            graph.dispose();

            BufferedImage combined = new BufferedImage(this.getIcon().getIconWidth(), this.getIcon().getIconHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics g = combined.getGraphics();
            g.drawImage(bi, 0, 0, null);
            g.drawImage(pion, 0, 0, null);

            g.dispose();

            this.setIcon(new ImageIcon(combined));
            this.revalidate();
        }

    }

    public void tourneGauche(){
        tuile.tourneGauche();
        this.setIcon(matchIcon(tuile));
        this.revalidate();
    }

    public void tourneDroite(){
        tuile.tourneDroite();
        this.setIcon(matchIcon(tuile));
        this.revalidate();
    }

    public void removeActionListener() {
    }
}
