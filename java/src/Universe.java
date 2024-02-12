import java.awt.Color;
import java.util.ArrayList;

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

/**
 * @brief we doing relativity stuffs here for our universe distances
 */
public class Universe {

  // ========================================================
  // ========================================================
  // ========================================================

  // public Instant programStart = Instant.now();

  // public Instant lastUpdateInstant = null;
  
  // ========================================================
  // ========================================================
  // ========================================================

  // pixels per simulate meter
  public double pixelsPerSimmeter = 0.1;
  // seconds per simulated second
  //  1 is saying there's a 1000 simulated seconds per irl second
  public double millisPerSimsecond = 1;

  // ========================================================
  // ========================================================
  // ========================================================

  // to say that everything is offset by where (0,0) means the sun is drawn in the center of the screen
  //  this is in meters from the center of the universe
  public double offsetX = 0, offsetY = 0;

  // ========================================================
  // ========================================================
  // ========================================================

  // todo: have code here to determine if we should perform another update
  
  // ========================================================
  // ========================================================
  // ========================================================

  // singleton pattern bois, lets go
  private static Universe instance;
  public static Universe getUniverse() {
    if(null==instance){
      instance = new Universe();
    }
    return instance;
  }

  // ========================================================
  // ========================================================
  // ========================================================

  AstronomicalBody sun;

  // ========================================================
  // ========================================================
  // ========================================================


  private Universe(){
    // add sun instance
    sun = new AstronomicalBody(
      400.0,
      Double.MAX_VALUE,
      new ArrayList<AstronomicalBody>(),
      null, // sun doesnt have an orbit
      Color.yellow
    );
    // make kerbin
    AstronomicalBody kerbin = new AstronomicalBody(
      75,
      84159286,
      new ArrayList<AstronomicalBody>(),
      // new Orbit(sun,13599840256.0),
      //                261600000
      new Orbit(sun,640),
      // new Orbit(sun, .0),
      Color.GREEN
    );
    // add mun
    kerbin.satellites.add(
      new AstronomicalBody(
        20,
        2429559.1,
        null,
        new Orbit(kerbin, 150.0),
        Color.DARK_GRAY
      )
    );
    // add kerbin to sun
    sun.satellites.add( kerbin );
  }

  // ========================================================
  // ========================================================
  // ======================================================== 




  // ========================================================
  // ========================================================
  // ========================================================

  public int getDiamInt(AstronomicalBody refBody){
    // assume good people exist and didnt give null
    return (int)((refBody.bodyRadius*2)*pixelsPerSimmeter);
  }
}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================
