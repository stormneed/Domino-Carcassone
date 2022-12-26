public class Joueur {
    Tuile pieceMain;
    Partie partie;
    int score;
    int numero;

    public Joueur (Partie partie, int i) {
        this.partie=partie;
        this.pieceMain=null;
        numero = i;
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
        return partie.table.pose(pieceMain, coordX, coordY, this);
    }

    public void abandon () {
        partie.abandon(this);
    }

    public void afficheScore () {
        System.out.println("Score : " + score);
    }


}
