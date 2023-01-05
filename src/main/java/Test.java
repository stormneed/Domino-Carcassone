import javax.swing.*;
import java.io.File;

public class Test {
    

    public static void main (String[] args) {
        Table t = new Table(7);
        Sac s = new Sac(t);
        s.remplirSacDefautCarcasonne();
        //PartieCons p = new PartieCons(3, 2, t, s);
        //p.jouerPartieCons();
        Window p=new Window(2,1);
        p.setVisible(true);
        //TuileCarc tuile=new TuileCarc("V","R","P","R",false, false,false);
        //TuileGraph tg=new TuileGraph(tuile);
        //if(p. content.getComponent(0) instanceof PartieGraph){
        //    ((PartieGraph)p. content.getComponent(0)).poser(3,3,tg);
        //}






    }
}
