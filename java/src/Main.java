
import java.awt.Dimension;
import java.awt.Graphics;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Main extends JFrame {
    // our window setup variables
    public final int START_WIDTH = 640;
    public final int START_HEIGHT = 480;

    class App extends JPanel {
        Stage stage;

        public App() {
            setPreferredSize(new Dimension(START_WIDTH, START_HEIGHT));
            stage = new Stage();
        }

        @Override
        public void paint(Graphics g) {
            if (isVisible()) {
                stage.paint(g);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            Instant startTime = Instant.now();
            this.repaint();
            Instant endTime = Instant.now();
            long howLong = Duration.between(startTime, endTime).toMillis();
            try {
                Thread.sleep(20L - howLong);
            } catch (InterruptedException e) {
                System.out.println("thread was interrupted, but who cares?");
            } catch (IllegalArgumentException e) {
                System.out.println("application can't keep up with framerate");
            }
        }
    }
}