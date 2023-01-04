import javax.swing.*;
import java.awt.*;

public class Choose extends JPanel {


    JPanel middle;

    JPanel outline;
    JPanel boutons;

    JPanel ajouter;

    JPanel joueurs;
    public Choose() {
        middle = new JPanel(new BorderLayout());
        middle.setBackground(new Color(171, 171, 171, 255));
        middle.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50));
        outline.add(middle, CENTER_ALIGNMENT);
        this.add(outline, BorderLayout.CENTER);
        this.setVisible(true);
    }

}
