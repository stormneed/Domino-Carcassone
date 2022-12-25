import java.util.Arrays;

public class Test {
    

    public static void main (String[] args) {
        Table t = new Table(5);
        Tuile p1 = new Tuile("123","000","000","000");
        Tuile p2 = new Tuile ("000","000","321","000");
        Sac s=new Sac();
        s.genereSac(10);

        for (Tuile tuile:
             s.contenu) {
            tuile.afficheTuile();
        }
        t.premierePose(p1);
        t.afficheCons();
        p2.afficheTuile();
        t.pose(p2, 1, 2);
        t.afficheCons();

    }
}
