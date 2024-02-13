import java.awt.Color;
import java.awt.Graphics;

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

public class SolarSystemPainter {

  public static final double LARGER_PI = 3141592.65358979323846264338327;

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
    paintBackground( g );
    
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

    for(Orbit currOrbital : SolarSystem.getSolarSystem().systemCenter.satelliteOrbitList){
      // handoff orbital drawing each
      paintOrbital(
        g,
        currOrbital,
        simSystemCenterPosX,
        simSystemCenterPosY
      );
    }

    // ---------------------------------------
    // ---------------------------------------
    // ---------------------------------------
    // ---------------------------------------
    // -------- draw currOrbitals in system

    // handoff for sun paint
    paintStar(
      g,
      SolarSystem.getSolarSystem().offsetX,
      SolarSystem.getSolarSystem().offsetY
    );

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
   * @param simSystemCenterPosX x offset to paint with
   * @param simSystemCenterPosY y offset to paint with
   */
  void paintStar(Graphics g, double simSystemCenterPosX, double simSystemCenterPosY){
    // ---------------------------------------
    // ---------------------------------------
    // -------- simple star paint it

    g.setColor( SolarSystem.getSolarSystem().systemCenter.fillColor );
    g.fillOval(
      screenOffsetX + (int)( ( simSystemCenterPosX - SolarSystem.getSolarSystem().systemCenter.bodyRadiusM * 0.001 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ),
      screenOffsetY + (int)( ( simSystemCenterPosY - SolarSystem.getSolarSystem().systemCenter.bodyRadiusM * 0.001 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ),
      (int)( ( SolarSystem.getSolarSystem().systemCenter.bodyRadiusM * 0.001 * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ),
      (int)( ( SolarSystem.getSolarSystem().systemCenter.bodyRadiusM * 0.001 * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM )
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
   * @param orbitRef the orbit data to use for painting
   * @param simRelativeOrbitCenterPosX x offset to paint with
   * @param simRelativeOrbitCenterPosY y offset to paint with
   */
  void paintOrbital(Graphics g, Orbit orbitRef, double simRelativeOrbitCenterPosX, double simRelativeOrbitCenterPosY){
    // ---------------------------------------
    // ---------------------------------------
    // -------- base case when not given orbit

    // junkable?
    if(null==orbitRef){
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
      screenOffsetX + (int)( ( simRelativeOrbitCenterPosX - orbitRef.semiMajorAxisKM ) * SolarSystem.getSolarSystem().pixelsPerSimKM ),
      screenOffsetY + (int)( ( simRelativeOrbitCenterPosY - orbitRef.semiMajorAxisKM ) * SolarSystem.getSolarSystem().pixelsPerSimKM ),
      // use semi major axis as radius
      Math.max( (int)( ( orbitRef.semiMajorAxisKM * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ), 1 ),
      Math.max( (int)( ( orbitRef.semiMajorAxisKM * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ), 1 )
    );

    // ---------------------
    // ---------------------
    // - prepare position

    double simOrbitalBodyPosX = simRelativeOrbitCenterPosX + ( orbitRef.semiMajorAxisKM * Math.cos( ( 2.0 * LARGER_PI - 1000000.0 ) * (orbitRef.phase / 1000000.0) ) );
    double simOrbitalBodyPosY = simRelativeOrbitCenterPosY + ( orbitRef.semiMajorAxisKM * Math.sin( ( 2.0 * LARGER_PI - 1000000.0 ) * (orbitRef.phase / 1000000.0) ) );

    // ---------------------
    // ---------------------
    // - drawing soi

    // when not "infinite"
    if(Double.MAX_VALUE != orbitRef.refBody.soiRadiusKM){
      // prepare color
      // todo
      // do draw
      // todo
    }

    // ---------------------
    // ---------------------
    // - drawing orbiters

    // for all that planet's moons/moon's satellites (if exist)
    if(null!=orbitRef.refBody.satelliteOrbitList) {
      for(Orbit currOrbital : orbitRef.refBody.satelliteOrbitList){
        // handoff for moon painting
        paintOrbital(
          g,
          currOrbital,
          simOrbitalBodyPosX,
          simOrbitalBodyPosY
        );
  
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
    }

    // ---------------------
    // ---------------------
    // - drawing current orbital
    g.setColor( orbitRef.refBody.fillColor );
    g.fillOval(
      screenOffsetX + (int)( ( simOrbitalBodyPosX - ((SolarSystem.getSolarSystem().makeBiggerBodiesThanReal)?SolarSystem.getSolarSystem().makeBiggerBodiesThanRealScale:1.0) * orbitRef.refBody.bodyRadiusM * 0.001 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ),
      screenOffsetY + (int)( ( simOrbitalBodyPosY - ((SolarSystem.getSolarSystem().makeBiggerBodiesThanReal)?SolarSystem.getSolarSystem().makeBiggerBodiesThanRealScale:1.0) * orbitRef.refBody.bodyRadiusM * 0.001 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ),
      Math.max( (int)( ( ((SolarSystem.getSolarSystem().makeBiggerBodiesThanReal)?SolarSystem.getSolarSystem().makeBiggerBodiesThanRealScale:1.0) * orbitRef.refBody.bodyRadiusM * 0.001 * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ), 1 ),
      Math.max( (int)( ( ((SolarSystem.getSolarSystem().makeBiggerBodiesThanReal)?SolarSystem.getSolarSystem().makeBiggerBodiesThanRealScale:1.0) * orbitRef.refBody.bodyRadiusM * 0.001 * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM ), 1 )
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

