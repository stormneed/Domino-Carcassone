public class Table {
    Tuile[][] plateau;

    public Table (int x) {
        plateau = new Tuile[x][x];

    }

    public Tuile getTuile (int coordX, int coordY) {
        return this.plateau[coordX][coordY];
    }

    public static String reverseString(String str){
        StringBuilder sb=new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    public boolean estPosable (Tuile piece, int coordX, int coordY) {
        if (coordX>0 && plateau[coordX-1][coordY]!=null &&!(plateau[coordX-1][coordY].bords[2].equals(reverseString(piece.bords[0])))) {
            return false;
        }
        if (coordX<plateau.length-1 && plateau[coordX+1][coordY]!=null && !(plateau[coordX+1][coordY].bords[0].equals(reverseString(piece.bords[2])))){
            return false;
        }
        if (coordY>0 && plateau[coordX][coordY-1]!=null && !(plateau[coordX][coordY-1].bords[1].equals(reverseString(piece.bords[3])))) {
            return false;
        }
        if (coordY<plateau.length-1 && plateau[coordX][coordY+1]!=null && !(plateau[coordX][coordY+1].bords[3].equals(reverseString(piece.bords[1])))) {
            return false;
        }
        if (estAdjacent(coordX, coordY)) {
            return plateau[coordX][coordY]==null;
        }
        return false;

    }

    public boolean estAdjacentNord (int coordX, int coordY) {
        return coordX>0 && plateau[coordX-1][coordY]!=null;
    }

    public boolean estAdjacentSud (int coordX, int coordY) {
        return coordX<plateau.length-1 && plateau[coordX+1][coordY]!=null;
    }

    public boolean estAdjacentOuest (int coordX, int coordY) {
        return coordY>0 && plateau[coordX][coordY-1]!=null;
    }

    public boolean estAdjacentEst (int coordX, int coordY) {
        return coordY<plateau.length-1 && plateau[coordX][coordY+1]!=null;
    }

    public boolean estAdjacent (int coordX, int coordY) {
        if (estAdjacentNord(coordX, coordY)) {
            return true;
        }
        if (estAdjacentSud(coordX, coordY)) {
            return true;
        }
        if (estAdjacentOuest(coordX, coordY)) {
            return true;
        }
        if (estAdjacentSud(coordX, coordY)) {
            return true;
        }
        return false;
    }

    public void premierePose(Tuile piece){
        this.plateau[this.plateau.length/2][this.plateau.length/2]=piece;
    }

    public boolean pose(Tuile piece, int coordX, int coordY){
        if(estPosable(piece,coordX,coordY)){
            this.plateau[coordX][coordY]=piece;
            return true;
        }
        else{
            System.out.println("Placement impossible");
            return false;
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
            System.out.println((char)27+"[0m");

        }
    }

}