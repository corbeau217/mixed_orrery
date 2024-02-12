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
      
      double smaRadRelPlanet = Math.max((currPlanet.orbit.semiMajorAxis*currUniverse.pixelsPerSimmeter),1);
      double planetRad = Math.max((currPlanet.bodyRadius*currUniverse.pixelsPerSimmeter),1);
      // for curr planet, draw the orbit
      g.setColor(Color.CYAN);
      g.drawOval((int)(x-smaRadRelPlanet), (int)(y-smaRadRelPlanet), (int)(smaRadRelPlanet*2), (int)(smaRadRelPlanet*2));
      // get planet pos
      double planetX = x+smaRadRelPlanet, planetY = y;
      // now draw the planet
      g.setColor(currPlanet.fillColor);
      g.fillOval((int)(planetX-planetRad), (int)(planetY-planetRad), (int)(planetRad*2), (int)(planetRad*2));
      // for all that planets moons
      for(AstronomicalBody currMoon : currPlanet.satellites){
        //....
        double smaRadRelMoon = Math.max((currMoon.orbit.semiMajorAxis*currUniverse.pixelsPerSimmeter),1);
        double moonRad = Math.max((currMoon.bodyRadius*currUniverse.pixelsPerSimmeter),1);
        // for curr moon, draw the orbit
        g.setColor(Color.CYAN);
        g.drawOval((int)(planetX-smaRadRelMoon), (int)(planetY-smaRadRelMoon), (int)(smaRadRelMoon*2), (int)(smaRadRelMoon*2));
        // get moon pos
        double moonX = planetX, moonY = planetY-smaRadRelMoon;
        // now draw the moon
        g.setColor(currMoon.fillColor);
        g.fillOval((int)(moonX-moonRad), (int)(moonY-moonRad), (int)(moonRad*2), (int)(moonRad*2));
      }
    }
  }
  
  // ========================================================
  // ========================================================
  // ========================================================

}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

