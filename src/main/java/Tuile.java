package main.java;

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
}
