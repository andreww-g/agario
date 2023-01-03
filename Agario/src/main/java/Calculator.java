import java.lang.Math;

public class Calculator {

    /**
     * Check if first things mass is greater than second things
     * @param thing1 meaning blob, food or virus
     * @param thing2 meaning blob, food or virus
     * @return boolean value
     */
    static public boolean isGreater(Thing thing1 , Thing thing2) {
        return thing1.getRadius() > thing2.getRadius();
    }

    /**
     * Check if circle representing first thing has circle representing second inside
     * @param thing1 meaning blob, food or virus
     * @param thing2 meaning blob, food or virus
     * @return boolean value
     */
    static public boolean inRange(Thing thing1, Thing thing2) {
        return distance(thing1, thing2) <= radiusDiff(thing1, thing2);
    }

    /**
     * Calculate distance between things represented as circles
     * @param thing1 meaning blob, food or virus
     * @param thing2 meaning blob, food or virus
     * @return distance between things
     */
    static public double distance(Thing thing1, Thing thing2) {
        return Math.sqrt(Math.pow(thing1.getP().x - thing2.getP().x, 2) + Math.pow(thing1.getP().y - thing2.getP().y, 2));
    }

    /**
     * Calculate difference between circles radius
     * @param thing1 meaning blob, food or virus
     * @param thing2 meaning blob, food or virus
     * @return difference between things radius
     */
    static public double radiusDiff(Thing thing1, Thing thing2) {
        return thing1.getRadius() - thing2.getRadius();
    }
}
