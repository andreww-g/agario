import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Board extends JPanel {

    // width and height of the screen
    private final Dimension d;
    private LinkedList<Blob> blobs;
    private LinkedList<Food> foods;
    private LinkedList<Virus> viruses;


    public Board(LinkedList<Blob> blobs, LinkedList<Food> foods, LinkedList<Virus> viruses, Dimension d) {
        this.d = d;
        this.blobs = blobs;
        this.foods = foods;
        this.viruses = viruses;
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Draws different things on board 
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(new Color(220,220,220));
        int i = 1;
        // space between gray lines that appear on the board (in pixels)
        int space = 60;
        // draw vertical lines
        while (i * space < d.width) {
            g.drawLine(i * space, 0, i * space, d.height);
            i++;
        }
        i = 1;
        // draw horizontal lines
        while (i * space < d.height) {
            g.drawLine(0, i * space, d.width, i * space);
            i++;
        }
        // draw food objects
        for (Food food : foods) {
            drawCircle(g, food);
        }
        // draw blob objects
        for (Blob blob : blobs) {
            drawCircle(g, blob);
        }
        // draw virus objects according to their position
        for (Virus virus : viruses) {
            if (virus.isActive()) {
                drawCircle(g, virus);
            }
        }
    }

    /**
     * Draws circle with slightly darker edge
     * @param g
     * @param thing meaning blob, food, or virus
     */
    public void drawCircle(Graphics g, Thing thing) {
        g.setColor(thing.getC());
        g.fillOval(thing.x(), thing.y(), thing.diameter(), thing.diameter());
        g.setColor(thing.getC().darker());
        g.drawOval(thing.x(), thing.y(), thing.diameter(), thing.diameter());
    }
}
