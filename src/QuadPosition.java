/**
 * Created by Tobias on 2016-09-27.
 */
public class QuadPosition extends Position {


    private double w;
    private double z;


    public QuadPosition(double w, double x, double y, double z){
        super();
        setX(x);
        setY(y);
        this.w=w;
        this.z=z;


    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }
}
