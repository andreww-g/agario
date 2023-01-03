import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel {

    private boolean started = false;
    JButton b = new JButton("Start");

    public Buttons() {
        this.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                started = !started;
                if (started)
                    b.setText("Stop");
                else
                    b.setText("Start");
            }
        });
    }

    public boolean isStarted() {
        return started;
    }
}
