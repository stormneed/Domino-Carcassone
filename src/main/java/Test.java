public class Test {
    

    public static void main (String[] args) {
        Table t = new Table(5);
        Sac s = new Sac();
        Tuile t1 = new Tuile("111","111","111","111");
        Tuile t2 = new Tuile("111","111","111","111");
        Tuile t3 = new Tuile("111","111","111","111");
        Tuile t4 = new Tuile("111","111","111","111");
        Tuile t5 = new Tuile("111","111","111","111");
        s.ajouter(t1);
        s.ajouter(t2);
        s.ajouter(t3);
        s.ajouter(t4);
        s.ajouter(t5);
        Partie p = new Partie(2, t, s);
        p.jouerPartieCons();

        

    }
}
