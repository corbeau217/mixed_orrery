import java.awt.Color;
import java.util.List;


// ==================================================================================================
// ==================================================================================================
// ==================================================================================================

/**
 * @brief holds information about an astronomical
 */
public class AstronomicalBody {

  // ========================================================
  // ========================================================
  // ========================================================
  // ===--- specific body details

  double bodyRadiusM;
  double soiRadiusKM;
  Color fillColor;

  // ========================================================
  // ========================================================
  // ========================================================
  // ===--- specific body details
  
  // list of things orbiting this one
  List<Orbit> satelliteOrbitList;

  // ========================================================
  // ========================================================
  // ========================================================

  /**
   * 
   * @param bodyRadiusM radius from center of mass of this body
   * @param soiRadiusKM radius from center of mass that this body influences in meters
   * @param fillColor the fill colour for this body
   * @param satelliteOrbitList the list of orbits surrounding this body
   */
  public AstronomicalBody(
    // --------------------
    // :: orbit data
    double bodyRadiusM,
    double soiRadiusKM,
    Color fillColor,
    // --------------------
    // :: body data
    List<Orbit> satelliteOrbitList
    // --------------------
  ){
    // ---------------------------------------------
    // --- copy data about the astronomical body

    this.bodyRadiusM = bodyRadiusM;
    this.soiRadiusKM = soiRadiusKM;
    this.fillColor = fillColor;
    
    // ---------------------------------------------
    // --- ref copy the list of things orbiting it

    this.satelliteOrbitList = satelliteOrbitList;

  }

  // ========================================================
  // ========================================================
  // ========================================================

}

// ==================================================================================================
// ==================================================================================================
// ==================================================================================================
