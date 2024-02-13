public class Orbit {

  // ========================================================
  // ========================================================
  // ========================================================
  // ===--- orbit details

  // for now this is just the radius of the circular orbit
  double semiMajorAxis;

  // the percentage of the orbit that the body is through the orbit, 0 means they're starting at (sma,0), 1 is that they're back
  // 0.0 - 1.0
  double phase; 

  // the length of time for a complete orbit in seconds
  double period;

  // ========================================================
  // ========================================================
  // ========================================================
  // ===--- orbital body reference

  AstronomicalBody refBody;

  // ========================================================
  // ========================================================
  // ========================================================

  public Orbit(double semiMajorAxis, double phase, double period, AstronomicalBody refBody){
    // ---------------------------------------------
    // orbit details

    this.semiMajorAxis = semiMajorAxis;
    this.phase = phase;
    this.period = period;

    // ---------------------------------------------
    // --- which body it's for

    this.refBody = refBody;

    // ---------------------------------------------
  }
}
