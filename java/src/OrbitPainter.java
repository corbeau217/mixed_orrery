import java.awt.Color;
import java.awt.Graphics;

public class OrbitPainter {

  // ========================================================
  // ========================================================
  // ========================================================

  int screenOffsetX, screenOffsetY;

  Color orbitBaseColor;

  // ========================================================
  // ========================================================
  // ========================================================



  // ========================================================
  // ========================================================
  // ========================================================

  public OrbitPainter(int screenOffsetX, int screenOffsetY, Color baseColor) {
    this.screenOffsetX = screenOffsetX;
    this.screenOffsetY = screenOffsetY;
    this.orbitBaseColor = baseColor;
  }

  // ========================================================
  // ========================================================
  // ========================================================

  void paint(Graphics g, Orbit orbitRef, double simRelativeOrbitCenterPosX, double simRelativeOrbitCenterPosY){
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

    drawOrbitLine(
      g,
      orbitRef,
      simRelativeOrbitCenterPosX,
      simRelativeOrbitCenterPosY
    );

    // ---------------------
    // ---------------------

    drawOrbitLineFlavour(
      g,
      orbitRef,
      simRelativeOrbitCenterPosX,
      simRelativeOrbitCenterPosY
    );

    // ---------------------
    // ---------------------
    // - prepare position

    double simOrbitalBodyPosX = simRelativeOrbitCenterPosX + ( orbitRef.semiMajorAxisKM * Math.cos( ( 2.0 * Math.PI ) * (orbitRef.phase) ) );
    double simOrbitalBodyPosY = simRelativeOrbitCenterPosY + ( orbitRef.semiMajorAxisKM * Math.sin( ( 2.0 * Math.PI ) * (orbitRef.phase) ) );

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

        // ---------------------

        this.paint(
          g,
          currOrbital,
          simOrbitalBodyPosX,
          simOrbitalBodyPosY
        );

        // ---------------------

      }
    }

    // ---------------------
    // ---------------------
    // - drawing current orbital

    this.drawOrbitalBody(
      g,
      orbitRef,
      simOrbitalBodyPosX,
      simOrbitalBodyPosY
    );

    // ---------------------------------------
    // ---------------------------------------
    // 
  }

  // ========================================================
  // ========================================================
  // ========================================================

  void drawOrbitLine(Graphics g, Orbit orbitRef, double simRelativeOrbitCenterPosX, double simRelativeOrbitCenterPosY){

    // ---------------------------------------
    // ---------------------------------------

    double simScale_PixelsPerKM = SolarSystem.getSolarSystem().pixelsPerSimKM;
    
    // ---------------------------------------
    // ---------------------------------------

    int orbitalBodyFinalDiameter = Math.max( (int)( ( orbitRef.semiMajorAxisKM * 2.0 ) * simScale_PixelsPerKM ), 1 );

    // ---------------------------------------
    // ---------------------------------------

    g.setColor( orbitBaseColor );
    g.drawOval(
      // circle for now, want the middle to be relative x/y
      screenOffsetX + (int)( ( simRelativeOrbitCenterPosX - orbitRef.semiMajorAxisKM ) * simScale_PixelsPerKM ),
      screenOffsetY + (int)( ( simRelativeOrbitCenterPosY - orbitRef.semiMajorAxisKM ) * simScale_PixelsPerKM ),
      // use semi major axis as radius
      orbitalBodyFinalDiameter,
      orbitalBodyFinalDiameter
    );

    // ---------------------------------------
    // ---------------------------------------

  }

  // ========================================================
  // ========================================================
  // ========================================================

  void drawOrbitalBody(Graphics g, Orbit orbitRef, double simOrbitalBodyPosX, double simOrbitalBodyPosY){

    // ---------------------------------------
    // ---------------------------------------

    double orbitalBodyCosmeticScale = 1.0;
    if( SolarSystem.getSolarSystem().makeBiggerBodiesThanReal){
      orbitalBodyCosmeticScale = SolarSystem.getSolarSystem().makeBiggerBodiesThanRealScale;
    } 

    double bodyRadiusKM = orbitRef.refBody.bodyRadiusM * 0.001;

    // ---------------------------------------
    // ---------------------------------------

    double simScale_PixelsPerKM = SolarSystem.getSolarSystem().pixelsPerSimKM;
    
    // ---------------------------------------
    // ---------------------------------------

    double orbitalBodyFinalDiameter = Math.max( ( orbitalBodyCosmeticScale * bodyRadiusKM * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM, 1.0 );

    // ---------------------------------------
    // ---------------------------------------

    g.setColor( orbitRef.refBody.fillColor );
    g.fillOval(
      screenOffsetX + (int)( ( simOrbitalBodyPosX - orbitalBodyCosmeticScale * bodyRadiusKM ) * simScale_PixelsPerKM ),
      screenOffsetY + (int)( ( simOrbitalBodyPosY - orbitalBodyCosmeticScale * bodyRadiusKM ) * simScale_PixelsPerKM ),
      (int)(orbitalBodyFinalDiameter),
      (int)(orbitalBodyFinalDiameter)
      // (int)(( orbitalBodyCosmeticScale * bodyRadiusKM * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM),
      // (int)(( orbitalBodyCosmeticScale * bodyRadiusKM * 2.0 ) * SolarSystem.getSolarSystem().pixelsPerSimKM)
    );

    // ---------------------------------------
    // ---------------------------------------
    
  }

  // ========================================================
  // ========================================================
  // ========================================================


  void drawOrbitLineFlavour(Graphics g, Orbit orbitRef, double simRelativeOrbitCenterPosX, double simRelativeOrbitCenterPosY){

    double simScale_PixelsPerKM = SolarSystem.getSolarSystem().pixelsPerSimKM;
    // ...
    g.setColor(orbitBaseColor);
    for(int i = 0; i < unitCircleAngles.length; i++){
      g.drawLine(
        screenOffsetX + (int)( simRelativeOrbitCenterPosX * simScale_PixelsPerKM ),
        screenOffsetY + (int)( simRelativeOrbitCenterPosY * simScale_PixelsPerKM ),
        screenOffsetX + (int)( ( simRelativeOrbitCenterPosX + ( Math.cos( unitCircleAngles[i] ) * orbitRef.semiMajorAxisKM ) ) * simScale_PixelsPerKM ),
        screenOffsetY + (int)( ( simRelativeOrbitCenterPosY + ( Math.sin( unitCircleAngles[i] ) * orbitRef.semiMajorAxisKM ) ) * simScale_PixelsPerKM )
      );
    }
    // ...
  }


  // ========================================================
  // ========================================================
  // ========================================================

  double [] circlePoints(int numberOfPoints, double circleRadius){
    // =======================================================
    // =======================================================

    double[] resultingPoints = new double[numberOfPoints * 2];

    // =======================================================
    // =======================================================

    for(int pIdx = 0; pIdx < numberOfPoints; pIdx++){
      double pRadians = 2.0*Math.PI*(double)(pIdx)/numberOfPoints;
      resultingPoints[pIdx*2] = Math.cos(pRadians);
      resultingPoints[pIdx*2+1] = Math.sin(pRadians);
    }

    // =======================================================
    // =======================================================

    return resultingPoints;

    // =======================================================
    // =======================================================
  }

  // ========================================================
  // ========================================================
  // ========================================================

  static double[] unitCircleAngles = {
    0.0,
    Math.PI/6.0,
    Math.PI/4.0,
    Math.PI/3.0,
    Math.PI/2.0,
    2.0*Math.PI/3.0,
    3.0*Math.PI/4.0,
    5.0*Math.PI/6.0,
    Math.PI,
    Math.PI + Math.PI/6.0,
    Math.PI + Math.PI/4.0,
    Math.PI + Math.PI/3.0,
    Math.PI + Math.PI/2.0,
    Math.PI + 2.0*Math.PI/3.0,
    Math.PI + 3.0*Math.PI/4.0,
    Math.PI + 5.0*Math.PI/6.0
  };

  // ========================================================
  // ========================================================
  // ========================================================

  double unitCirclePointX(int pIdx){
    return Math.cos(unitCircleAngles[pIdx]);
  }
  double unitCirclePointY(int pIdx){
    return Math.sin(unitCircleAngles[pIdx]);
  }

  // ========================================================
  // ========================================================
  // ========================================================

}