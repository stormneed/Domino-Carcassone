public class Joueur {
    Tuile pieceMain;
    Partie partie;

    public int piocher () {
        if (pieceMain==null) {
            pieceMain = partie.sac.piocher();
            return 1;
        }
        return 0;
    }

    public void defausser () {
        pieceMain = null;
    }

    public int poser (int coordX, int coordY) {
        return partie.table.pose(pieceMain, coordX, coordY);
    }


}
