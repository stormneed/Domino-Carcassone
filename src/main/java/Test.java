import javax.swing.*;
import java.io.File;

public class Test {
    

    public static void main (String[] args) {
        Table t = new Table(7);
        Sac s = new Sac(t);
        s.remplirSacDefaut();
        /*PartieCons p = new PartieCons(2, t, s);
        p.jouerPartieCons();*/
        Window p=new Window(2, t, s);
        TuileCarc tuile=new TuileCarc("V","R","P","R",false, false,false);
        TuileGraph tg=new TuileGraph(tuile);

        p.content.poser(2,2,tg);





    }
}
