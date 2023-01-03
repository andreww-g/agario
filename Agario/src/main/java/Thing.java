import java.awt.*;
import java.util.Random;

public class Thing {

    private Point p;
    // object is moved in this direction
    private Point dir;
    private Color c;
    private double radius;
    private double mass;
    private int countdown;
    private boolean active;
    private final Random r = new Random();
    // width and height of the screen
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

    public Thing() {
        p = position();
        c = color();
    }

    public Dimension getD() {
        return d;
    }

    public Random getR() {
        return r;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public Point getDir() {
        return dir;
    }

    public void setDir(Point dir) {
        this.dir = dir;
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Chooses right x coordinate for circles representing objects
     * @return x coordinate
     */
    public int x() {
        return getP().x - (int) getRadius();
    }

    /**
     * Chooses right y coordinate for circles representing objects
     * @return y coordinate
     */
    public int y() {
        return getP().y - (int) getRadius();
    }

    /**
     * Multiplies radius to get diameter
     * @return diameter
     */
    public int diameter() {
        return (int) getRadius() * 2;
    }

    /**
     * Chooses objects starting position
     * @return position
     */
    public Point position() {
        Point p = new Point();
        p.x = r.nextInt(d.width - 80) + 40;
        p.y = r.nextInt(d.height - 130) + 40;
        return p;
    }

    /**
     * Chooses random color
     * @return new color
     */
    public Color color() {
        return new Color(r.nextInt(150) + 100, r.nextInt(150) + 100, r.nextInt(150) + 100);
    }

    /**
     * Moves an object in random direction each time
     */
    public void move() {
        Point newP = new Point();
        Point dir = dirChange();
        if (getP().x == 30)
            dir.x = 1;
        else if (getP().x == getD().width - 40)
            dir.x = -1;
        if (getP().y == 30)
            dir.y = 1;
        else if (getP().y == getD().height - 90)
            dir.y = -1;
        setDir(dir);
        newP.x = getP().x + getDir().x;
        newP.y = getP().y + getDir().y;
        setP(newP);

    }

    /**
     * Changes direction of movement
     * @return new direction
     */
    public Point dirChange() {
        Point newDir = new Point();
        int[] vertDir = {-1, 1};
        newDir.y = getR().nextInt(3) - 1;
        if (newDir.y == 0) {
            int i = getR().nextInt(2);
            newDir.x = vertDir[i];
        }
        else
            newDir.x = getR().nextInt(3) - 1;
        return newDir;
    }

    /**
     * Counts to 0, then activates object (make it appear)
     */
    public void countdown() {
        if (getCountdown() > 0)
            setCountdown(getCountdown() - 1);
        else if (getCountdown() == 0)
            setActive(true);
    }

    /**
     * Changes object position
     */
    public void respawn() {
        p = position();
    }
}
