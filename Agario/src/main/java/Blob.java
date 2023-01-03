import java.awt.*;

public class Blob extends Thing {

    public Blob() {
        setMass(12);
        setRadius(getMass()/2);
        setDir(dirChange());
    }

    /**
     * Moves object in random direction for a while before changing it
     */
    public void move() {
        Point newP = new Point();
        Point dir = getDir();
        if (getR().nextInt(100) == 0)
            dir = dirChange();
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
     * Increases object mass
     * @param t meaning food or blob
     */
    public void eat(Thing t) {
        if (t.getMass() == 1)
            setMass(getMass() + 1);
        else
            setMass(getMass() + t.getMass()/4);
    }

    /**
     * Makes object radius relative to mass up to date
     */
    public void updateRadius() {
        setRadius(getMass()/2);
    }

    /**
     * Decreases object mass
     */
    public void shrink() {
        setMass(getMass()/2);
    }

    /**
     * Resets object mass and sets random position
     */
    public void respawn() {
        setP(position());
        setMass(12);
    }
}
