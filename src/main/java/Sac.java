import java.util.Collections;
import java.util.LinkedList;

public class Sac {
    LinkedList<Tuile> contenu;
    int nombrePiece;

    Table table;

    public Sac(Table table) {
        this.table = table;
        contenu = new LinkedList<Tuile>();
    }



    public void remplirSacDefautDomino() {
        contenu.add(new Tuile("212", "222", "303", "040"));
        contenu.add(new Tuile("104", "032", "122", "212"));
        contenu.add(new Tuile("401", "032", "040", "032"));
        contenu.add(new Tuile("212", "312", "312", "230"));
        contenu.add(new Tuile("123", "032", "212", "213"));
        contenu.add(new Tuile("104", "212", "321", "230"));
        contenu.add(new Tuile("221", "140", "213", "040"));
        contenu.add(new Tuile("040", "032", "104", "041"));
        contenu.add(new Tuile("213", "140", "212", "230"));
        contenu.add(new Tuile("212", "211", "213", "041"));
        contenu.add(new Tuile("123", "040", "140", "112"));
        contenu.add(new Tuile("312", "230", "211", "222"));
        contenu.add(new Tuile("401", "040", "321", "032"));
        contenu.add(new Tuile("312", "211", "032", "222"));
        contenu.add(new Tuile("041", "222", "321", "112"));
        contenu.add(new Tuile("112", "312", "321", "040"));
        contenu.add(new Tuile("123", "104", "303", "213"));
        contenu.add(new Tuile("303", "123", "140", "401"));
        contenu.add(new Tuile("230", "401", "303", "321"));
        contenu.add(new Tuile("123", "040", "312", "104"));
        contenu.add(new Tuile("123", "140", "401", "222"));
        contenu.add(new Tuile("303", "211", "104", "041"));
        contenu.add(new Tuile("041", "041", "212", "112"));
        contenu.add(new Tuile("303", "211", "321", "140"));
        contenu.add(new Tuile("213", "222", "401", "112"));
    }

    public void remplirSacDefautCarcasonne() {
        contenu.add(new TuileCarc("V", "R", "P", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "P", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "P", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "P", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "V", false, true, false));
        contenu.add(new TuileCarc("V", "R", "R", "V", false, true, false));
        contenu.add(new TuileCarc("V", "V", "R", "V", false, false, false));
        contenu.add(new TuileCarc("V", "V", "P", "V", false, true, false));
        contenu.add(new TuileCarc("V", "R", "R", "V", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "V", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "V", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "R", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "R", false, false, false));
        contenu.add(new TuileCarc("V", "R", "R", "R", false, false, false));
        contenu.add(new TuileCarc("R", "P", "R", "P", false, false, false));
        contenu.add(new TuileCarc("R", "P", "R", "P", false, false, false));
        contenu.add(new TuileCarc("R", "P", "R", "P", false, false, false));
        contenu.add(new TuileCarc("R", "P", "R", "P", false, false, false));
        contenu.add(new TuileCarc("R", "P", "R", "P", false, false, false));
        contenu.add(new TuileCarc("R", "P", "R", "P", false, false, false));
        contenu.add(new TuileCarc("R", "P", "R", "P", false, false, false));
        contenu.add(new TuileCarc("R", "P", "R", "P", false, false, false));
        contenu.add(new TuileCarc("P", "R", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "R", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "R", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "R", "R", "R", false, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "P", false, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "P", false, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "P", false, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "P", false, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "P", false, false, false));
        contenu.add(new TuileCarc("V", "V", "P", "P", false, false, true));
        contenu.add(new TuileCarc("V", "V", "P", "P", false, false, true));
        contenu.add(new TuileCarc("V", "V", "P", "V", false, false, false));
        contenu.add(new TuileCarc("V", "V", "P", "V", false, false, false));
        contenu.add(new TuileCarc("V", "V", "P", "V", false, false, false));
        contenu.add(new TuileCarc("P", "P", "P", "P", true, false, false));
        contenu.add(new TuileCarc("P", "P", "P", "P", true, false, false));
        contenu.add(new TuileCarc("P", "P", "P", "P", true, false, false));
        contenu.add(new TuileCarc("P", "P", "P", "P", true, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "P", true, false, false));
        contenu.add(new TuileCarc("P", "P", "R", "P", true, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "V", false, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "V", false, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "V", false, false, false));
        contenu.add(new TuileCarc("P", "V", "P", "V", false, true, false));
        contenu.add(new TuileCarc("P", "V", "P", "V", false, true, false));
        contenu.add(new TuileCarc("V", "R", "P", "R", false, false, false));
        contenu.add(new TuileCarc("V", "R", "P", "R", false, false, false));
        contenu.add(new TuileCarc("V", "R", "P", "R", false, false, false));
        contenu.add(new TuileCarc("V", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("V", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("V", "P", "R", "R", false, false, false));
        contenu.add(new TuileCarc("P", "V", "P", "V", false, false, false));
        contenu.add(new TuileCarc("V", "V", "R", "V", false, true, false));
        contenu.add(new TuileCarc("V", "V", "R", "V", false, true, false));
        contenu.add(new TuileCarc("V", "V", "V", "V", false, true, false));
        contenu.add(new TuileCarc("R", "R", "R", "R", false, false, false));
        contenu.add(new TuileCarc("V", "P", "P", "V", false, true, false));
        contenu.add(new TuileCarc("V", "P", "P", "V", false, true, false));
        contenu.add(new TuileCarc("P", "V", "P", "V", false, false, true));
        contenu.add(new TuileCarc("P", "V", "P", "V", false, false, true));
    }



    public Tuile piocher() {
        Tuile t = contenu.getFirst();
        contenu.removeFirst();
        melanger();
        return t;
    }

    public void melanger() {
        Collections.shuffle(contenu);
    }

    public boolean estVide(){
        return this.contenu.isEmpty();
    }

}
