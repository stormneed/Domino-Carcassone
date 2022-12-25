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

    public boolean estAdjacent (int coordX, int coordY) {
        if (coordX>0 && plateau[coordX-1][coordY]!=null) {
            return true;
        }
        if (coordX<plateau.length-1 && plateau[coordX+1][coordY]!=null) {
            return true;
        }
        if (coordY>0 && plateau[coordX][coordY-1]!=null) {
            return true;
        }
        if (coordY<plateau.length-1 && plateau[coordX][coordY+1]!=null) {
            return true;
        }
        return false;
    }

    public void premierePose(Tuile piece){
        this.plateau[this.plateau.length/2][this.plateau.length/2]=piece;
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
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                System.out.print((char)27+"[0m ");
                if(plateau[i][j]==null){
                    System.out.print("*** |");
                }
                else {
                    System.out.print(plateau[i][j].bords[0] + " |");
                }
            }
            for (int j = 0; j < 3; j++) {
                System.out.println();
                for (int k = 0; k < plateau[i].length; k++) {
                    if(plateau[i][k]==null){
                        System.out.print("*   *|");
                    }
                    else {
                        System.out.print(plateau[i][k].bords[3].charAt(j) + "   " +plateau[i][k].bords[1].charAt(j)+"|");
                    }
                }
            }

            System.out.println();
            for (int j = 0; j < plateau[i].length; j++) {
                System.out.print((char)27+"[4m ");
                if(plateau[i][j]==null){
                    System.out.print("*** |");
                }
                else {
                    System.out.print(plateau[i][j].bords[2] + " |");
                }
            }
            System.out.println();

        }
    }

}