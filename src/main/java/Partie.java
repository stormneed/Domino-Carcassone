import java.util.ArrayList;

public class Partie {
    Sac sac;
    ArrayList<Joueur> joueurs;

    
    
    public boolean estFini () {
        if (sac.contenu.isEmpty()) {
            return true;
        }
        if (joueurs.size()<=0) {
            return true;
        }
        return false;
    }

}
