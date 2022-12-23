package main.java;

public class Table {
    Tuile[][] plateau;

    public Table (int x) {
        plateau = new Tuile[x][x];

    }

    public Tuile getTuile (int coordX, int coordY) {
        return this.plateau[coordX][coordY];
    }


    public boolean estPosable (Tuile piece, int coordX, int coordY) {
        if (coordX>0 && plateau[coordX-1][coordY]!=null &&!(plateau[coordX-1][coordY].bords[2]==piece.bords[0])) {
            return false;
        }
        if (coordX<plateau.length-1 && plateau[coordX+1][coordY]!=null && !(plateau[coordX+1][coordY].bords[0]==piece.bords[2])){
            return false;
        }
        if (coordY>0 && plateau[coordX][coordY-1]!=null && !(plateau[coordX][coordY-1].bords[1]==piece.bords[3])) {
            return false;
        }
        if (coordY<plateau.length-1 && plateau[coordX][coordY+1]!=null &&  !(plateau[coordX][coordY+1].bords[3]==piece.bords[1])) {
            return false;
        }
        return true;
    }

}
