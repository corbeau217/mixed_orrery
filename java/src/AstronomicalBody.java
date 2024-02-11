// import java.util.ArrayList;
import java.util.List;

public class AstronomicalBody {
    double radius;

    Orbit orbit;
    
    List<AstronomicalBody> satellites;

    public AstronomicalBody(double r, Orbit o, List<AstronomicalBody> s){
        radius = r;
        orbit = new Orbit(o.referenceBody, o.semiMajorAxis);
        satellites = s;
    }
}
