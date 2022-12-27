public class Test {
    

    public static void main (String[] args) {
        Table t = new Table(5);
        Sac s = new Sac(t);
        s.remplirSacDefaut();
        PartieCons p = new PartieCons(2,1, t, s);
        p.jouerPartieCons();



    }
}
