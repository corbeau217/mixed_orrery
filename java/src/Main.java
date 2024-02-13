
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFrame;
import javax.swing.JPanel;

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

class Main extends JFrame implements KeyListener {

  // ========================================================
  // ========================================================
  // ========================================================

  public static boolean DEBUG_MODE = false;

  // ========================================================
  // ========================================================
  // ========================================================

  // final vars for window sizing
  public final int START_WIDTH = 800, START_HEIGHT = 600;
  
  // ========================================================
  // ========================================================
  // ========================================================

  private App canvas;

  // ========================================================
  // ========================================================
  // ========================================================

  public static void main(String[] args) throws Exception {
    System.out.println("===========================\nbegin\n===========================");

    if(args.length > 0){
      for(String arguer:args){
        System.out.println("arguement: "+arguer);
      }
    }
    
    Main window = new Main();

    window.run();

    System.out.println("===========================\nend\n===========================");
  }

  // ========================================================
  // ========================================================
  // ========================================================

  private Main() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas = new App();
    this.setContentPane(canvas);
    this.pack();
    this.setVisible(true);
  }

  // ========================================================
  // ========================================================
  // ========================================================

  @Override
  public void keyTyped(KeyEvent e) {
    canvas.stage.keyTyped(e);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    canvas.stage.keyPressed(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    canvas.stage.keyReleased(e);
  }

  // ========================================================
  // ========================================================
  // ========================================================

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

  // ========================================================
  // ========================================================
  // ========================================================

  class App extends JPanel {
    // =======================================
    // =======================================
    // =======================================

    Stage stage;

    // =======================================
    // =======================================
    // =======================================

    public App() {
      setPreferredSize(new Dimension(START_WIDTH, START_HEIGHT));
      stage = new Stage(START_WIDTH,START_HEIGHT);
    }

    @Override
      public void paint(Graphics g) {
      if (isVisible()) {
        stage.paint(g);
      }
    }

    // =======================================
    // =======================================
    // =======================================

  }

  // ========================================================
  // ========================================================
  // ========================================================

}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

