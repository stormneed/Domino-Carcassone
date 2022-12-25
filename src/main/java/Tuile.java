public class Tuile {
    String[] bords;

    public Tuile () { // Tuile vide      
    }

    public Tuile (String bordNord, String bordEst, String bordSud, String bordOuest) {
        bords = new String[4];
        bords[0] = bordNord;
        bords[1] = bordEst;
        bords[2] = bordSud;
        bords[3] = bordOuest;
    }

    public void tourneDroite () {
        String temp = bords[3];
        bords[3] = bords[2];
        bords[2] = bords[1];
        bords[1] = bords[0];
        bords[0] = temp;
    }

    public void tourneGauche () {
        String temp = bords[0];
        bords[0] = bords[1];
        bords[1] = bords[2];
        bords[2] = bords[3];
        bords[3] = temp;
    }

    public void afficheTuile() {
        System.out.println("Piece actuelle : ");
        System.out.println(" ----- ");
        System.out.println("| " + bords[0] + " |");
        System.out.println("|" + bords[3].charAt(2) + "   " + bords[1].charAt(0) + "|");
        System.out.println("|" + bords[3].charAt(1) + "   " + bords[1].charAt(1) + "|");
        System.out.println("|" + bords[3].charAt(0) + "   " + bords[1].charAt(2) + "|");
        System.out.println("| " + bords[2].charAt(2) + bords[2].charAt(1) + bords[2].charAt(0) + " |");
        System.out.println(" ----- ");
    }

    

    
    






}