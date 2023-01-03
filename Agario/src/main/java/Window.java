import javax.swing.*;
import java.awt.*;

public class Window {

    JFrame f = new JFrame();

    public Window(Board b, Buttons but) {
        f.setTitle("Agar.io");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setResizable(false);
        f.setVisible(true);
        f.add(but, BorderLayout.PAGE_END);
        f.add(b);
    }
}
