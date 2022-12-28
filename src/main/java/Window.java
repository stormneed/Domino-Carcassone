import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    PartieGraph content;



    public Window(int nombreJoueur, int nombreIA, Table table, Sac sac){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setSize(MAXIMIZED_HORIZ,MAXIMIZED_VERT);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        content=new PartieGraph(nombreJoueur, nombreIA, table, sac);
        this.add(content,BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    public class Menu extends JPanel{
        public Menu(){

        }

    }


}