import javafx.geometry.Pos;

/**
 * Created by sejiaw on 2016-09-23.
 */
public class Position {


    double x,y;

    public Position(){

    }
    public Position(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String toString(){
        return x + "," + y;
    }

    public void setStartPosition(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double getDistanceTo(Position p){
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
    }

    public double getBearingTo(Position p) {
        return Math.atan2(p.y - y, p.x - x);
    }

}
