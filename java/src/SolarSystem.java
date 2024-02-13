import java.awt.Color;
import java.util.Arrays;
import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

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

  public Instant creationInstant;
  
  // ========================================================
  // ========================================================
  // ========================================================

  // pixels per simulate meter
  // public double pixelsPerSimmeter = 0.000000018;
  // public double pixelsPerSimmeter = 0.0000001;
  public double pixelsPerSimmeter = 0.000007;

  // seconds per simulated second
  //  1 is saying there's a 1000 simulated seconds per irl second
  public double millisPerSimsecond = 0.01;

  // ========================================================
  // ========================================================
  // ========================================================

  // to say that everything is offset by where (0,0) means the sun is drawn in the center of the screen
  //  this is in meters from the center of the universe
  public double offsetX = 0.0;
  public double offsetY = 0.0;
  
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
  // === stuff for debugging etc

  private boolean makeBiggerBodiesThanReal = true;

  private boolean centerOnCurrentFocusReference = true;
  
  private Orbit orbitalFocusReference;

  // ========================================================
  // ========================================================
  // ========================================================

  private SolarSystem(){
    // make the solar system
    systemCenter = generateKerbolSystem();
    creationInstant = Instant.now();
  }

  // ========================================================
  // ========================================================
  // ======================================================== 

  private AstronomicalBody generateKerbolSystem(){
    Orbit kerbinReference = new Orbit(
      // --------------------
      // :: orbit data
      13599840256.0,
      0.0,
      9203544.6,
      // --------------------
      // :: body data
      new AstronomicalBody(
        ((makeBiggerBodiesThanReal)? 6000000.0 : 600000.0),
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
              ((makeBiggerBodiesThanReal)? 2000000.0 : 200000.0),
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
              ((makeBiggerBodiesThanReal)? 600000.0 : 60000.0),
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
    );
    this.orbitalFocusReference = kerbinReference;

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
        
        kerbinReference

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

  void setOffsetToOrigin(){
    offsetX = 0.0;
    offsetY = 0.0;
  }

  // ========================================================
  // ========================================================
  // ========================================================

  void setOffsetToOrbital(Orbit refToCenter){
    offsetX = 0.0 - ( refToCenter.semiMajorAxis * Math.cos( ( 2 * Math.PI - 1 ) * refToCenter.phase ) );
    offsetY = 0.0 - ( refToCenter.semiMajorAxis * Math.sin( ( 2 * Math.PI - 1 ) * refToCenter.phase ) );
  }

  // ========================================================
  // ========================================================
  // ========================================================

  void progressTime(Instant currentInstant){

    if(centerOnCurrentFocusReference){ setOffsetToOrigin(); }

    // ... get focus position
    double timeSinceStartMillis = Duration.between(creationInstant, currentInstant).toMillis();

    // .... use it
    for( Orbit orbital : systemCenter.satelliteOrbitList){
      progressTimeOnOrbital(orbital, timeSinceStartMillis);
    }


    
    if(centerOnCurrentFocusReference){ setOffsetToOrbital( orbitalFocusReference ); }
  }

  // ========================================================
  // ========================================================
  // ========================================================

  void progressTimeOnOrbital(Orbit orbital, double irlTimeSinceStartMillis ){
    // handle spooky bad providing
    if(null==orbital){
      return;
    }
    // get the thing
    double irlOrbitalPeriodInMillis = orbital.period * millisPerSimsecond;

    // make sure non 0 period
    if(0.0 != orbital.period){
      orbital.phase = ( irlTimeSinceStartMillis / irlOrbitalPeriodInMillis );
    }

    // see if we have things orbiting it and recurse
    if(null != orbital.refBody.satelliteOrbitList){
      for(Orbit currOrbital : orbital.refBody.satelliteOrbitList){
        progressTimeOnOrbital(currOrbital, irlTimeSinceStartMillis);
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
