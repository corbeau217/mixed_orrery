public class Orbit {
    AstronomicalBody referenceBody;
    
    // do the ellipse stuff later and (apo/peri)apsis
    // double inclination;
    // double eccentricity;

    // for now this is just the radius of the circular orbit
    double semiMajorAxis;

    // the percentage of the orbit that the body is through the orbit, 0 means they're starting at (sma,0), 1 is that they're back
    // 0.0 - 1.0
    double phase; 

    public Orbit(AstronomicalBody r, double sma){
        referenceBody = r;
        
        //...
    }
}
