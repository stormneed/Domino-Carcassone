public class Test {
    

    public static void main (String[] args) {
        Table t = new Table(9);
        Sac s = new Sac(t);
        /*s.remplirSacDefautDomino();
        PartieCons p = new PartieCons(3, 2, t, s);
        p.jouerPartieCons();*/
        Window p=new Window(2, 0, t, s);
        TuileCarc tuile=new TuileCarc("P","R","R","R",true, false,false);
        TuileGraph tg=new TuileGraph(tuile);

        p.content.poser(2,2,tg);





    }
}
