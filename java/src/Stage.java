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

    // relative position on the screen of the universe
    double x = screenCenterX+(currUniverse.offsetX*currUniverse.pixelsPerSimmeter),
        y = screenCenterY+(currUniverse.offsetY*currUniverse.pixelsPerSimmeter);
    // radius relative
    double rad = (curr.bodyRadius*currUniverse.pixelsPerSimmeter);
    // relative diam of the body
    double diam = rad*2;

    // ---------------------------------------
    // ---------------------------------------
    // -------- draw sun

    g.setColor(curr.fillColor);
    g.fillOval((int)(x-rad), (int)(y-rad), (int)(diam), (int)(diam));

    // now for each of the satellites, draw them


    // ---------------------------------------
    // ---------------------------------------
    for(AstronomicalBody currPlanet : currUniverse.sun.satellites){
      double smaRadRel = (currPlanet.orbit.semiMajorAxis*currUniverse.pixelsPerSimmeter);
      double planetRad = (currPlanet.bodyRadius*currUniverse.pixelsPerSimmeter);
      // for curr planet, draw the orbit
      g.setColor(Color.CYAN);
      g.drawOval((int)(x-smaRadRel), (int)(y-smaRadRel), (int)(smaRadRel*2), (int)(smaRadRel*2));
      // now draw the planet
      g.setColor(currPlanet.fillColor);
      g.fillOval((int)(x+smaRadRel-planetRad), (int)(y-planetRad), (int)(planetRad*2), (int)(planetRad*2));
    }
  }
  
  // ========================================================
  // ========================================================
  // ========================================================

}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

