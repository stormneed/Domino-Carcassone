public class Joueur {
    Tuile pieceMain;
    Partie partie;

    public void piocher () {
        if (pieceMain==null) {
            pieceMain = partie.sac.piocher();
        }
    }

    public void defausser () {
        pieceMain = null;
    }

    public boolean poser (int coordX, int coordY) {
        return partie.table.pose(pieceMain, coordX, coordY);
    }

    public void abandon () {
        partie.abandon(this);
    }


}
