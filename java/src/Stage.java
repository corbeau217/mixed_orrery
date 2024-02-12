import java.awt.Color;
import java.awt.Graphics;


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

  int screenCenterX, screenCenterY;

  // ========================================================
  // ========================================================
  // ========================================================

  Color bgColor;

  // ========================================================
  // ========================================================
  // ========================================================

  public Stage(int w, int h){
    //... uhhhh  it's done elsewhere now i guess?
    bgColor = Color.BLACK;
    screenWidth = w;
    screenHeight = h;
    screenCenterX = w/2;
    screenCenterY = h/2;
  }

  public void paint(Graphics g) {
    // ....
    // get data from universe singleton and einsteintalk for the makables

    // so we have shorter reference
    Universe currUniverse = Universe.getUniverse();

    // tbh stage paints for now
    // ---------------------------------------
    // ---------------------------------------
    // -------- background drawing
    
    g.setColor(bgColor);
    g.fillRect(0,0,screenWidth,screenHeight);
    
    // ---------------------------------------
    // ---------------------------------------
    // -------- prepare drawing stuff

    // body to draw
    AstronomicalBody curr = currUniverse.sun;

    // relative position of the body
    int x = screenCenterX+(int)(currUniverse.offsetX*currUniverse.pixelsPerSimmeter),
        y = screenCenterY+(int)(currUniverse.offsetY*currUniverse.pixelsPerSimmeter);
    // radius relative
    int rad = (int)(curr.bodyRadius*currUniverse.pixelsPerSimmeter);
    // relative diam of the body
    int diam = rad*2;

    // ---------------------------------------
    // ---------------------------------------
    // -------- draw sun

    g.setColor(curr.fillColor);
    g.fillOval(x-rad, y-rad, diam, diam);

    // now for each of the satellites, draw them


    // ---------------------------------------
    // ---------------------------------------
  }
  
  // ========================================================
  // ========================================================
  // ========================================================

}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

