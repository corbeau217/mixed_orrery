
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

  public static long MINIMUM_FRAME_COOLDOWN = 20L;

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


    // SolarSystem.getSolarSystem()

    window.run();

    System.out.println("===========================\nend\n===========================");
  }

  // ========================================================
  // ========================================================
  // ========================================================

  private Main() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas = new App();
    this.addKeyListener(this);
    this.setContentPane(canvas);
    this.pack();
    this.setVisible(true);
  }

  // ========================================================
  // ========================================================
  // ========================================================

  @Override
  public void keyTyped(KeyEvent e) {
    // System.out.println("typer");
    canvas.keyTyped(e);
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    // System.out.println("presser");
    canvas.keyPressed(e);
  }
  
  @Override
  public void keyReleased(KeyEvent e) {
    // System.out.println("releaser");
    canvas.keyReleased(e);
  }

  // ========================================================
  // ========================================================
  // ========================================================

  public void run() {
    while (true) {
      Instant updateStartTime = Instant.now();
      // ------------------------------------
      // ------------------------------------
      SolarSystem.getSolarSystem().progressTime( updateStartTime );
      // ------------------
      this.repaint();
      // ------------------------------------
      // ------------------------------------
      Instant updateEndTime = Instant.now();
      long howLong = Duration.between(updateStartTime, updateEndTime).toMillis();
      try {
        Thread.sleep(MINIMUM_FRAME_COOLDOWN - howLong);
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
    
    private static final int CYCLE_FOCUS_BINDING = KeyEvent.VK_F;
    private static final int ZOOM_IN_BINDING = KeyEvent.VK_W;
    private static final int ZOOM_OUT_BINDING = KeyEvent.VK_S;
    private static final int SPEED_UP_BINDING = KeyEvent.VK_D;
    private static final int SPEED_DOWN_BINDING = KeyEvent.VK_A;
    
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

    public void keyTyped(KeyEvent e) {
      // ....
    }
  
    public void keyPressed(KeyEvent e) {
      // ....
      switch (e.getKeyCode()) {
        // ---------------------------------
        case CYCLE_FOCUS_BINDING:
          System.out.println("CYCLING FOCUS");
          SolarSystem.getSolarSystem().cycleForwardFocus();
          break;
        // ---------------------------------
        case ZOOM_IN_BINDING:
          System.out.println("ZOOM IN");
          SolarSystem.getSolarSystem().zoomIn();
          break;
        // ---------------------------------
        case ZOOM_OUT_BINDING:
          System.out.println("ZOOM OUT");
          SolarSystem.getSolarSystem().zoomOut();
          break;
        // ---------------------------------
        case SPEED_UP_BINDING:
          System.out.println("SPEED UP");
          SolarSystem.getSolarSystem().speedUp();
          break;
        // ---------------------------------
        case SPEED_DOWN_BINDING:
          System.out.println("SPEED DOWN");
          SolarSystem.getSolarSystem().speedDown();
          break;
        // ---------------------------------
        default:
        // ---------------------------------
      }
    }
  
    public void keyReleased(KeyEvent e) {
      // ....
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

