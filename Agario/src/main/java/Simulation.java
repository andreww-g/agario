import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Simulation {

    // width and height of the screen
    private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private final LinkedList<Blob> blobs = new LinkedList<>();
    private final LinkedList<Food> foods = new LinkedList<>();
    private final LinkedList<Virus> viruses = new LinkedList<>();
    // number of blobs
    private final int nBlobs;
    // number of foods
    private final int nFoods;
    // number of viruses
    private final int nViruses;
    // reaching this score ends simulation
    private final int scoreToWin;

    public Simulation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of blobs/players: ");
        nBlobs = sc.nextInt();
        System.out.println("Number of foods: ");
        nFoods = sc.nextInt();
        System.out.println("Number of viruses: ");
        nViruses = sc.nextInt();
        System.out.println("Score needed for player to win (100 - 500): ");
        scoreToWin = sc.nextInt();
        createThings();
        Board b = new Board(blobs, foods, viruses, d);
        Buttons but = new Buttons();
        new Window(b, but);
        while (!but.isStarted()) {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException ignored) {
            }
        }
        do {
            while (!but.isStarted()) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ignored) {
                }
            }
            try {
                Thread.sleep(2);
            }
            catch (Exception ignored) {
            }
            feedBlobs();
            moveThings();
            countdownAll();
            b.repaint();
        } while (!winnerExists());
        Scores.listBlobs(blobs, scoreToWin);
        end();
    }

    /**
     * Create different thing objects and add them to respective lists
     */
    public void createThings() {
        for (int i = 0; i < nBlobs; i++) {
            blobs.add(new Blob());
        }
        for (int i = 0; i < nFoods; i++) {
            foods.add(new Food());
        }
        for (int i = 0; i < nViruses; i++) {
            viruses.add(new Virus());
        }
    }

    /**
     * Moves all blobs
     */
    public void moveThings() {
        for (Blob blob : blobs) {
            blob.move();
        }
    }

    /**
     * Makes blobs eat everything in range
     */
    public void feedBlobs() {
        eatBlobs();
        eatFoods();
        growBlobs();
        eatViruses();
    }

    /**
     * Counts time until virus reappears
     */
    public void countdownAll() {
        for (Virus virus : viruses)
            virus.countdown();
    }

    /**
     * Makes blob eat smaller blobs if they are in range
     */
    public void eatBlobs() {
        for (int i = 0; i < blobs.size(); i++)
            for (int j = 0; j < blobs.size(); j++) {
                if (i == j)
                    continue;
                if (Calculator.isGreater(blobs.get(i), blobs.get(j)))
                    if (Calculator.inRange(blobs.get(i), blobs.get(j))) {
                        blobs.get(i).eat(blobs.get(j));
                        blobs.get(j).respawn();
                    }
            }
    }

    /**
     * Makes blob eat foods if they are in range
     */
    public void eatFoods() {
        for (Food food : foods)
            for (Blob blob : blobs)
                if (Calculator.inRange(blob, food)) {
                    blob.eat(food);
                    food.respawn();
                    break;
                }
    }

    /**
     * Makes blob eat viruses if they are smaller and in range
     */
    public void eatViruses() {
        for (Virus virus : viruses)
            for (Blob blob : blobs)
                if (Calculator.isGreater(blob, virus))
                    if (Calculator.inRange(blob, virus)) {
                        blob.shrink();
                        virus.respawn();
                        break;
                    }
    }

    /**
     * Updates radius of every blob
     */
    public void growBlobs() {
        for (Blob blob : blobs) {
            blob.updateRadius();
        }
    }

    /**
     * Checks condition that leads to simulation end
     * @return boolean value
     */
    public boolean winnerExists() {
        for (Blob blob : blobs) {
            if (blob.getMass() >= scoreToWin)
                return true;
        }
        return false;
    }

    /**
     * Displays message and exits program
     */
    public void end() {
        JOptionPane.showMessageDialog(null, "Simulation has been finished");
        System.exit(0);
    }
}