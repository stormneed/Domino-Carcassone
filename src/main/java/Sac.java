import java.util.Collections;
import java.util.LinkedList;

public class Sac {
    LinkedList<Tuile> contenu;
    int nombrePiece;

    Table table;

    public Sac (Table table) {
        this.table=table;
        contenu = new LinkedList<Tuile>();
    }

    public void ajouter (Tuile piece) {
        contenu.add(piece);
    }

    public void remplirSacDefaut () {
        Tuile tc = new Tuile("212","222","303","040");
        Tuile t1 = new Tuile("104","032","122","212");
        Tuile t2 = new Tuile("401","032","040","032");
        Tuile t3 = new Tuile("212","312","312","230");
        Tuile t4 = new Tuile("123","032","212","213");
        Tuile t5 = new Tuile("104","212","321","230");
        Tuile t6 = new Tuile("221","140","213","040");
        Tuile t7 = new Tuile("040","032","104","041");
        Tuile t8 = new Tuile("213","140","212","230");
        Tuile t9 = new Tuile("212","211","213","041");
        Tuile t10 = new Tuile("123","040","140","112");
        Tuile t11 = new Tuile("312","230","211","222");
        Tuile t12 = new Tuile("401","040","321","032");
        Tuile t13 = new Tuile("312","211","032","222");
        Tuile t14 = new Tuile("041","222","321","112");
        Tuile t15 = new Tuile("112","312","321","040");
        Tuile t16 = new Tuile("123","104","303","213");
        Tuile t17 = new Tuile("303","123","140","401");
        Tuile t18 = new Tuile("230","401","303","321");
        Tuile t19 = new Tuile("123","040","312","104");
        Tuile t20 = new Tuile("123","140","401","222");
        Tuile t21 = new Tuile("303","211","104","041");
        Tuile t22 = new Tuile("041","041","212","112");
        Tuile t23 = new Tuile("303","211","321","140");
        Tuile t24 = new Tuile("213","222","401","112");
        contenu.add(tc);
        contenu.add(t1);
        contenu.add(t2);
        contenu.add(t3);
        contenu.add(t4);
        contenu.add(t5);
        contenu.add(t6);
        contenu.add(t7);
        contenu.add(t8);
        contenu.add(t9);
        contenu.add(t10);
        contenu.add(t11);
        contenu.add(t12);
        contenu.add(t13);
        contenu.add(t14);
        contenu.add(t15);
        contenu.add(t16);
        contenu.add(t17);
        contenu.add(t18);
        contenu.add(t19);
        contenu.add(t20);
        contenu.add(t21);
        contenu.add(t22);
        contenu.add(t23);
        contenu.add(t24);





    }
    private void genereSacRec(int nbr){
        Tuile tmp=contenu.getLast();
        for (int i = 0; i < 4; i++) {
            if (nombrePiece < nbr) {
                contenu.add(Tuile.genereTuile(tmp.bords[i]));
                nombrePiece++;
                if (Math.random() < 0.10*i) genereSacRec(nbr);
            }
        }
    }

    public void genereSac(int nbr){
        Tuile tmp=Tuile.genereTuile(""+(int)(Math.random()*5)+(int)(Math.random()*5)+(int)(Math.random()*5));
        contenu.add(tmp);
        nombrePiece++;
      while(nombrePiece<nbr) {
          genereSacRec(nbr);
      }
       // melanger();
    }



    public Tuile piocher () {
        Tuile t = contenu.getFirst();
        contenu.removeFirst();
        melanger();
        return t;
    }

    public void melanger () {
        Collections.shuffle(contenu);
    }


    




}
