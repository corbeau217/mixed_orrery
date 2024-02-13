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

  double bodyRadius;
  double soiRadius;
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
   * @param bodyRadius radius from center of mass of this body
   * @param soiRadius radius from center of mass that this body influences
   * @param fillColor the fill colour for this body
   * @param satelliteOrbitList the list of orbits surrounding this body
   */
  public AstronomicalBody(
    // --------------------
    // :: orbit data
    double bodyRadius,
    double soiRadius,
    Color fillColor,
    // --------------------
    // :: body data
    List<Orbit> satelliteOrbitList
    // --------------------
  ){
    // ---------------------------------------------
    // --- copy data about the astronomical body

    this.bodyRadius = bodyRadius;
    this.soiRadius = soiRadius;
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
