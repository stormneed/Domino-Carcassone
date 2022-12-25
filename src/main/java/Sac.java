import java.util.Collections;
import java.util.LinkedList;

public class Sac {
    LinkedList<Tuile> contenu;

    public Sac () {
        contenu = new LinkedList<Tuile>();
    }

    public void ajouter (Tuile piece) {
        contenu.add(piece);
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
