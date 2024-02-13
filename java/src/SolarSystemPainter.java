import java.awt.Color;
import java.awt.Graphics;

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

public class SolarSystemPainter {

  // ========================================================
  // ========================================================
  // ========================================================
  // === screen sizing

  int screenWidth, screenHeight;

  // ========================================================
  // ========================================================
  // ========================================================
  // === screen middle position

  int screenOffsetX, screenOffsetY;

  // ========================================================
  // ========================================================
  // ========================================================
  // === painting colours

  Color spaceBackgroundColor;
  Color orbitBaseColor;

  // ========================================================
  // ========================================================
  // ========================================================

  public SolarSystemPainter(int screenWidth, int screenHeight){
    // ---------------------------------------
    // ---------------------------------------
    // -------- sceen data
    
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.screenOffsetX = screenWidth/2;
    this.screenOffsetY = screenHeight/2;

    // ---------------------------------------
    // ---------------------------------------
    // -------- choosing colors
    
    this.spaceBackgroundColor = Color.BLACK;
    this.orbitBaseColor = Color.CYAN;

    // ---------------------------------------
    // ---------------------------------------
  }
  
  // ========================================================
  // ========================================================
  // ========================================================

  public void paint(Graphics g) {
    // ---------------------------------------
    // ---------------------------------------
    // -------- do initial painting
    
    // handoff for bg paint
    paintBackground(g);
    
    // ---------------------------------------
    // ---------------------------------------
    // -------- prepare drawing stuff

    // relative position on the screen of the universe
    double simSystemCenterPosX = SolarSystem.getSolarSystem().offsetX;
    // relative position on the screen of the universe
    double simSystemCenterPosY = SolarSystem.getSolarSystem().offsetY;

    // ---------------------------------------
    // ---------------------------------------
    // -------- draw currOrbitals in system

    for(Orbit currOrbital : SolarSystem.getSolarSystem().sun.satelliteOrbitList){
      // handoff orbital drawing each
      paintOrbital(g, currOrbital, simSystemCenterPosX, simSystemCenterPosY);
    }

    // ---------------------------------------
    // ---------------------------------------
    // ---------------------------------------
    // ---------------------------------------
    // -------- draw currOrbitals in system

    // handoff for sun paint
    paintStar(g, simSystemCenterPosX, simSystemCenterPosY);

    // ---------------------------------------
    // ---------------------------------------
  }
  



  // ========================================================
  // ========================================================
  // ========================================================

  /**
   * @brief delegation of painting the space background
   * @param g graphics obj to paint with
   */
  void paintBackground(Graphics g){
    // ---------------------------------------
    // ---------------------------------------
    // -------- simple background
    
    g.setColor( spaceBackgroundColor );
    g.fillRect(
      0, 0,
      screenWidth, screenHeight
    );

    // ---------------------------------------
    // ---------------------------------------
  }

  // ========================================================
  // ========================================================
  // ========================================================

  /**
   * @brief delegation of painting the sun
   * @param g graphics obj to paint with
   * @param simRelativePosX x offset to paint with
   * @param simRelativePosY y offset to paint with
   */
  void paintStar(Graphics g, double simRelativePosX, double simRelativePosY){
    // ---------------------------------------
    // ---------------------------------------
    // -------- simple star paint it

    g.setColor( SolarSystem.getSolarSystem().sun.fillColor );
    g.fillOval(
      screenOffsetX + (int)( ( simRelativePosX - SolarSystem.getSolarSystem().sun.bodyRadius ) * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      screenOffsetY + (int)( ( simRelativePosY - SolarSystem.getSolarSystem().sun.bodyRadius ) * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      (int)( ( SolarSystem.getSolarSystem().sun.bodyRadius * 2 ) * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      (int)( ( SolarSystem.getSolarSystem().sun.bodyRadius * 2 ) * SolarSystem.getSolarSystem().pixelsPerSimmeter )
    );

    // ---------------------------------------
    // ---------------------------------------
  }

  // ========================================================
  // ========================================================
  // ========================================================

  /**
   * @brief delegation of painting orbital, based on a provided position for center of orbit
   * @param g graphics obj to paint with
   * @param orbitalBody the orbit data to use for painting
   * @param simRelativePosX x offset to paint with
   * @param simRelativePosY y offset to paint with
   */
  void paintOrbital(Graphics g, Orbit orbitalBody, double simRelativePosX, double simRelativePosY){
    // ---------------------------------------
    // ---------------------------------------
    // -------- base case when not given orbit

    // junkable?
    if(null==orbitalBody){
      // junkable.
      return; // throw out the code branch
    }
    
    // ---------------------------------------
    // ---------------------------------------
    // -------- recursive case, have paintable

    // ---------------------
    // ---------------------
    // - draw the orbit line

    g.setColor( orbitBaseColor );
    g.drawOval(
      // circle for now, want the middle to be relative x/y
      screenOffsetX + (int)( ( simRelativePosX - orbitalBody.semiMajorAxis ) * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      screenOffsetY + (int)( ( simRelativePosY - orbitalBody.semiMajorAxis ) * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      // use semi major axis as radius
      (int)( ( orbitalBody.semiMajorAxis * 2 ) * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      (int)( ( orbitalBody.semiMajorAxis * 2 ) * SolarSystem.getSolarSystem().pixelsPerSimmeter )
    );

    // ---------------------
    // ---------------------
    // - prepare position

    double simOrbitalPosX = simRelativePosX + orbitalBody.semiMajorAxis;
    double simOrbitalPosY = simRelativePosY;

    // ---------------------
    // ---------------------
    // - drawing soi

    // when not "infinite"
    if(Double.MAX_VALUE!=orbitalBody.refBody.soiRadius){
      // prepare color
      // todo
      // do draw
      // todo
    }

    // ---------------------
    // ---------------------
    // - drawing orbiters

    // for all that planet's moons/moon's satellites (if exist)
    if(null!=orbitalBody.refBody.satelliteOrbitList)
    for(Orbit currOrbital : orbitalBody.refBody.satelliteOrbitList){
      // handoff for moon painting
      paintOrbital(g, currOrbital, simOrbitalPosX, simOrbitalPosY);

      ////////// old method //////////////
      // double smaRadRelMoon = Math.max((currMoon.orbit.semiMajorAxis*currUniverse.pixelsPerSimmeter),1);
      // double moonRad = Math.max((currMoon.bodyRadius*currUniverse.pixelsPerSimmeter),1);
      // // for curr moon, draw the orbit
      // g.setColor(Color.CYAN);
      // g.drawOval((int)(planetX-smaRadRelMoon), (int)(planetY-smaRadRelMoon), (int)(smaRadRelMoon*2), (int)(smaRadRelMoon*2));
      // // get moon pos
      // double moonX = planetX, moonY = planetY-smaRadRelMoon;
      // // now draw the moon
      // g.setColor(currMoon.fillColor);
      // g.fillOval((int)(moonX-moonRad), (int)(moonY-moonRad), (int)(moonRad*2), (int)(moonRad*2));
    }

    // ---------------------
    // ---------------------
    // - drawing planet
    g.setColor( orbitalBody.refBody.fillColor );
    g.fillOval(
      screenOffsetX + (int)( simOrbitalPosX * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      screenOffsetY + (int)( simOrbitalPosX * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      (int)( ( orbitalBody.refBody.bodyRadius * 2 ) * SolarSystem.getSolarSystem().pixelsPerSimmeter ),
      (int)( ( orbitalBody.refBody.bodyRadius * 2 ) * SolarSystem.getSolarSystem().pixelsPerSimmeter )
    );

    // ---------------------------------------
    // ---------------------------------------
    // 
  }

  // ========================================================
  // ========================================================
  // ========================================================

}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

