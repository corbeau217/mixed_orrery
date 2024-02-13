import java.awt.Color;
import java.util.Arrays;
import java.util.ArrayList;

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

/**
 * @brief this holds the information about our solar system
 */
public class SolarSystem {
  
  // ========================================================
  // ========================================================
  // ========================================================

  // pixels per simulate meter
  public double pixelsPerSimmeter = 0.000000018;
  // public double pixelsPerSimmeter = 0.0000001;
  // public double pixelsPerSimmeter = 0.00001;

  // seconds per simulated second
  //  1 is saying there's a 1000 simulated seconds per irl second
  public double millisPerSimsecond = 1;

  // ========================================================
  // ========================================================
  // ========================================================

  // to say that everything is offset by where (0,0) means the sun is drawn in the center of the screen
  //  this is in meters from the center of the universe
  public double offsetX = 0.0, offsetY = 0.0;
  
  // ========================================================
  // ========================================================
  // ========================================================

  // singleton pattern bois, lets go
  private static SolarSystem instance;
  public static SolarSystem getSolarSystem() {
    if(null==instance){
      instance = new SolarSystem();
    }
    return instance;
  }

  // ========================================================
  // ========================================================
  // ========================================================

  // reference to the system center's astronomical body
  AstronomicalBody systemCenter;

  // ========================================================
  // ========================================================
  // ========================================================

  private SolarSystem(){
    // make the solar system
    systemCenter = generateKerbolSystem();
    // set the offset to center on whatever kerbin's position is
    this.offsetX -= (int)(systemCenter.satelliteOrbitList.get(0).semiMajorAxis);
  }

  // ========================================================
  // ========================================================
  // ======================================================== 

  private AstronomicalBody generateKerbolSystem(){
    return new AstronomicalBody(
      // ====================================
      // === kerbol details =================
      // ====================================
      261600000.0,
      Double.MAX_VALUE, // because we only have the one solar system rn
      Color.yellow,
      // ====================================
      // === planet list for kerbol =========
      // ====================================
      Arrays.asList(
        // ====================================
        // ------------------------------------
        // --- kerbin
        new Orbit(
          // --------------------
          // :: orbit data
          13599840256.0,
          0.0,
          9203544.6,
          // --------------------
          // :: body data
          new AstronomicalBody(
            600000.0,
            84159286.0,
            new Color(56,124,68),
            Arrays.asList(
              // ====================================
              // ------------------------------------
              // --- mun
              new Orbit(
                // --------------------
                // :: orbit data
                12000000.0,
                0.25,
                138984.4,
                // --------------------
                // :: body data
                new AstronomicalBody(
                  200000,
                  2429559.1,
                  Color.GRAY,
                  null
                )
                // --------------------
              ),
              // ------------------------------------
              // --- minmus
              new Orbit(
                // --------------------
                // :: orbit data
                47000000.0,
                0.75,
                1077310.5,
                // --------------------
                // :: body data
                new AstronomicalBody(
                  600000.0,
                  84159286.0,
                  //          #b6   fb   df
                  new Color(182,251,239),
                  null
                )
                // --------------------
              )
              // ------------------------------------
              // ====================================
            )
          )
          // --------------------
        )
        // ------------------------------------
        // --- other planet

        // ...
        
        // ------------------------------------
        // ====================================
      )
    );
  }


  // ========================================================
  // ========================================================
  // ========================================================

}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================
