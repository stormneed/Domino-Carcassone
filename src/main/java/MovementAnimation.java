import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class MovementAnimation {

    //the window within the animation takes places
    Window window;

    // The component we want to animate
    private TuileGraph label;

    // The Timer that drives the animation
    private Timer timer;

    // The interval between updates (in milliseconds)
    private int interval = 20;

    // The amount to move the component per update (in pixels)
    private int step = 1;

    // The current position of the component
    private int x,y,x1,y1;

    public MovementAnimation(TuileGraph label,int x1,int y1,Window window) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.label = label;
        this.x=label.getX();
        this.y=label.getY();
        this.x1=x1;
        this.y1=y1;
        this.window=window;
        timer = new Timer(interval, new TimerListener());
    }

    public void start() {
        timer.start();
        System.out.println("lance anim");
    }

    public void stop() {
        timer.stop();
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Update the position of the component
            if(x<x1) x += step;
            if(x>x1) x -= step;
            if(y<y1) y +=step;
            if(y>y1) y -= step;

            label.setLocation(x, y);
            if(x==x1 && y==y1) timer.stop();

            // If the component has reached the edge of the container, reverse the direction
            if (x + label.getWidth() > window.getWidth() || x < 0) {
                step = -step;
            }
            if (y + label.getHeight() > window.getHeight() || y < 0) {
                step = -step;
            }
        }
    }
}
