public class Joueur {
    Tuile pieceMain;
    Partie partie;
    int score;
    int numero;
    boolean estIA;

    public Joueur (Partie partie, int i, boolean iA) {
        this.partie=partie;
        this.pieceMain=null;
        numero = i;
        estIA=iA;
    }

    public void piocher () {
        if (pieceMain==null) {
            pieceMain = partie.sac.piocher();
        }
    }

    public void defausser () {
        pieceMain = null;
    }

    public boolean poser (int coordX, int coordY) {
        return partie.table.pose((Tuile) pieceMain, coordX, coordY, this);
    }

    public void abandon () {
        partie.abandon(this);
    }

    public void afficheScore () {
        System.out.println("Score : " + score);
    }


}
