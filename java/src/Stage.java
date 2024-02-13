import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

public class Stage {

  // ========================================================
  // ========================================================
  // ========================================================

  int screenWidth, screenHeight;

  // ========================================================
  // ========================================================
  // ========================================================

  SolarSystemPainter solarSystemPainter;
  
  // ========================================================
  // ========================================================
  // ========================================================

  public Stage(int screenWidth, int screenHeight){

    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;

    this.solarSystemPainter = new SolarSystemPainter(screenWidth, screenHeight);
  }

  // ========================================================
  // ========================================================
  // ========================================================

  public void paint(Graphics g) {
    // ----------------------------------------------
    // ----------------------------------------------
    // --- hand off for painting the solar system

    solarSystemPainter.paint(g);
    
    // ----------------------------------------------
    // ----------------------------------------------
    // --- paint other overlays afterwards

    // ...

    // ----------------------------------------------
    // ----------------------------------------------
  }
  
  // ========================================================
  // ========================================================
  // ========================================================

}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

