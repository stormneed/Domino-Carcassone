public class TuileCarc extends Tuile{

    boolean pion;
    String pCouleur;
    String pBord;
    boolean abbaye;
    boolean symbole;
    boolean separated;

    public TuileCarc(String nord,String est, String sud, String ouest,boolean abbaye, boolean symbole, boolean separated){
        super(nord,est,sud,ouest);
        this.abbaye=abbaye;
        this.symbole=symbole;
        this.separated=separated;
    }
}
