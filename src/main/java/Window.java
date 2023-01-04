import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    JPanel content;



    public Window(int nombreJoueur,int nombreIA){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setSize(MAXIMIZED_HORIZ,MAXIMIZED_VERT);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        Table t=new Table(11);
        Sac s=new Sac(t);
        s.remplirSacDefautDomino();
        content=new JPanel();
        content.setPreferredSize(new Dimension((int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight(),(int)Toolkit. getDefaultToolkit(). getScreenSize().getHeight()));
        content.add(/*new PartieGraph(3,0,t,s,this));*/ /*new Menu(this))*/ new GenDomino(s));
        this.add(content,BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }



    public class Menu extends JPanel{
        Window window;
        public Menu(Window window){
            this.window=window;
            BoxLayout layout=new BoxLayout(this,BoxLayout.Y_AXIS);
            this.setLayout(layout);

            JLabel domino= new JLabel("Domino");
            domino.setFocusable(true);
            domino.setFont(new Font("Impact",Font.BOLD,50));
            domino.setFocusCycleRoot(true);
            JLabel carc=new JLabel("Carcassonne");
            carc.setFocusable(true);
            carc.setFont(new Font("Impact",Font.BOLD,50));
            JLabel exit=new JLabel("Exit");
            exit.setFocusable(true);
            exit.setFont(new Font("Impact",Font.BOLD,50));
            this.add(Box.createGlue());
            ajouteListenersAlign(domino);;

            ajouteListenersAlign(carc);

            ajouteListenersAlign(exit);

            carc.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Table t=new Table(10);
                    Sac s=new Sac(t);
                    s.remplirSacDefautCarcasonne();

                    content.removeAll();
//                    content.add(new PartieGraph(2,0,t,s,window));
                    content.add(new Choose());
                    content.revalidate();
                }
            });

            this.add(domino);
            this.add(carc);
            this.add(exit);
            this.add(Box.createGlue());
            this.setVisible(true);

        }

        private void ajouteListenersAlign(JLabel lab) {
            lab.setAlignmentX(CENTER_ALIGNMENT);
            lab.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    lab.grabFocus();
                }
            });

            lab.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    lab.setFont(new Font("Impact",Font.BOLD,60));
                    lab.setForeground(Color.RED);

                }
                @Override
                public void focusLost(FocusEvent e) {
                    lab.setFont(new Font("Impact", Font.BOLD, 50));
                    lab.setForeground(Color.BLACK);
                }

            });
        }

    }


}