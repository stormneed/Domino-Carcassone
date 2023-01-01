import javax.swing.*;
import java.awt.*;


public class TuileGraph extends JButton implements TuileType{
    Tuile tuile;

    public TuileGraph(Tuile t){
        System.out.println("crée tuile");
        this.tuile=t;
        this.setIcon(matchIcon(t));

    }

    public ImageIcon matchIcon(Tuile t){
        String path="ressources/tiles/";
        for(int i=0;i<t.bords.length;i++){
            path+=t.bords[i];
        }
        if (((TuileCarc) t).abbaye) path += "(abbaye)";
        if (((TuileCarc) t).symbole) path += "(symbole)";
        if (((TuileCarc) t).separated) path+="(sep)";
        ImageIcon icon=new ImageIcon(path + ".png");
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( (int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight()/10,(int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight()/10 ,  Image.SCALE_DEFAULT ) ;
        icon = new ImageIcon( newimg );
        return icon;
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
}
