public class Test {
    

    public static void main (String[] args) {
        System.out.println('5' + 2);
        Table t = new Table(5);
        Sac s = new Sac(t);
        s.genereSac(10);
        Partie p = new Partie(2, t, s);
        p.jouerPartieCons();



    }
}
