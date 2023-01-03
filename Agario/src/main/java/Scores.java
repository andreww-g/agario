import java.io.*;
import java.util.LinkedList;

public class Scores {

    /**
     * Write scores of every blob
     * @param blobs is a list containing blob objects
     * @param scoreToWin meaning score that makes blob win
     */
    static public void listBlobs(LinkedList<Blob> blobs, int scoreToWin) {
        String path = "scoreboard.txt";
        String winner = "";
        int score = 0;
        try {
            FileWriter f = new FileWriter(path, false);
            BufferedWriter b = new BufferedWriter(f);
            PrintWriter p = new PrintWriter(b);

            p.println(" Player  " + "Score\n");
            for (int i = 0; i < blobs.size(); i++) {
                if (blobs.get(i).getMass() >= scoreToWin) {
                    winner = "Blob " + (i + 1);
                    score = (int) blobs.get(i).getMass();
                }
                p.println(" Blob " + (i + 1) + " :  " + (int) blobs.get(i).getMass());
            }
            p.println("\n Winner : " + winner);
            p.println(" Score : " + score );
            p.flush();
            p.close();
        }
        catch (IOException e) {
            System.out.println("Failed to create scoreboard");
        }
    }
}
