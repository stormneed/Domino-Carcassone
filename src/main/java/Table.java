
import java.util.Objects;

public class Table {
    Tuile[][] plateau;

    public Table (int x) {
        plateau = new Tuile[x][x];

    }

    public Tuile getTuile (int coordX, int coordY) {
        return this.plateau[coordX][coordY];
    }


    public boolean estPosable (Tuile piece, int coordX, int coordY) {
        if (coordX>0 && plateau[coordX-1][coordY]!=null &&!(Objects.equals(plateau[coordX - 1][coordY].bords[2], piece.bords[0]))) {
            return false;
        }
        if (coordX<plateau.length-1 && plateau[coordX+1][coordY]!=null && !(Objects.equals(plateau[coordX + 1][coordY].bords[0], piece.bords[2]))){
            return false;
        }
        if (coordY>0 && plateau[coordX][coordY-1]!=null && !(Objects.equals(plateau[coordX][coordY - 1].bords[1], piece.bords[3]))) {
            return false;
        }
        if (coordY<plateau.length-1 && plateau[coordX][coordY+1]!=null &&  !(Objects.equals(plateau[coordX][coordY + 1].bords[3], piece.bords[1]))) {
            return false;
        }
        return plateau[coordX][coordY]==null;
    }

    public void pose(Tuile piece, int coordX, int coordY){
        if(estPosable(piece,coordX,coordY)){
            this.plateau[coordX][coordY]=piece;
        }
        else{
            System.out.println("Placement impossible");
        }
    }

    public void afficheCons(){
        for (Tuile[] tuiles : plateau) {
            for (Tuile tuile : tuiles) {
                System.out.print((char) 27 + "[0m ");
                if (tuile == null) {
                    System.out.print("*** |");
                } else {
                    System.out.print(tuile.bords[0] + " |");
                }
            }
            for (int j = 0; j < 3; j++) {
                System.out.println();
                for (Tuile tuile : tuiles) {
                    if (tuile == null) {
                        System.out.print("*   *|");
                    } else {
                        System.out.print(tuile.bords[3].charAt(2-j) + "   " + tuile.bords[1].charAt(j) + "|");
                    }
                }
            }

            System.out.println();
            for (Tuile tuile : tuiles) {
                System.out.print((char) 27 + "[4m ");
                if (tuile == null) {
                    System.out.print("*** |");
                } else {
                    System.out.print(""+tuile.bords[2].charAt(2) +""+ tuile.bords[2].charAt(1)+""+ tuile.bords[2].charAt(0)+" |");
                }
            }
            System.out.println();

        }
    }

}
