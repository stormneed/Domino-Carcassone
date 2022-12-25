import java.util.Collections;
import java.util.LinkedList;

public class Sac {
    LinkedList<Tuile> contenu;
    int nombrePiece;

    public Sac () {
        contenu = new LinkedList<Tuile>();
    }

    public void ajouter (Tuile piece) {
        contenu.add(piece);
    }
    private void genereSacRec(int nbr){
        Tuile tmp=contenu.getLast();
        for (int i = 0; i < 4; i++) {
            if (nombrePiece < nbr) {
                contenu.add(Tuile.genereTuile(tmp.bords[i]));
                nbr++;
                if (Math.random() < 0.5) genereSac(nbr);
            }
        }
    }

    public void genereSac(int nbr){
        Tuile tmp=Tuile.genereTuile(""+(int)(Math.random()*5)+(int)(Math.random()*5)+(int)(Math.random()*5));
        contenu.add(tmp);
        genereSacRec(nbr);
        melanger();
    }



    public Tuile piocher () {
        Tuile t = contenu.getFirst();
        contenu.removeFirst();
        return t;
    }

    public void melanger () {
        Collections.shuffle(contenu);
    }

    public void defausser (Tuile piece) {
        ajouter(piece);
        melanger();
    }

    




}
