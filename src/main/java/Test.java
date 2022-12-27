public class Test {
    

    public static void main (String[] args) {
        Table t = new Table(7);
        Sac s = new Sac(t);
        s.remplirSacDefaut();
        PartieCons p = new PartieCons(2, t, s);
        p.jouerPartieCons();



    }
}
