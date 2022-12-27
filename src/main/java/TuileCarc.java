public class TuileCarc extends Tuile{

    boolean pion;
    String pCouleur;
    String pBord;
    boolean abbaye;
    boolean symbole;
    boolean separated;

    public TuileCarc(String nord,String ouest, String sud, String est,boolean abbaye, boolean symbole, boolean separated){
        super(nord,ouest,sud,est);
        this.abbaye=abbaye;
        this.symbole=symbole;
        this.separated=separated;
    }
}
