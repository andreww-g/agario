import java.awt.*;

public class Virus extends Thing {

    public Virus() {

        setRadius(30);
        setC(Color.green);
    }

    /**
     * Set countdown on object and change its position
     */
    public void respawn() {
        setP(position());
        setActive(false);
        setCountdown(1000);
    }
}
