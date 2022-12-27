import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TuileGraph extends JButton {
    Tuile tuile;
    boolean type; //True=Carcassonne, false=domino;





    public TuileGraph(Tuile t){
        this.tuile=t;
        type=t instanceof TuileCarc;
        File f=new File("ressources/tiles/PPPP.png");

        System.out.println(f.canRead());
        if(type){
            this.setIcon(new ImageIcon("ressources/tiles/PPPP.png"));
        }
        else{
            this.setIcon(new ImageIcon(""));
        }
    }

}
