import javafx.geometry.Pos;

import java.io.IOException;

/**
 * Created by Tobz0r on 2016-09-30.
 */
public class Controls {

    private Position[] path;
    private int lenght;
    private int index;
    private RobotCommunication robotC;
    private double distance;
    private Position pos, carrotPoint;
    private double OriantationError;

    public Controls(Position[] path, RobotCommunication robotC){
        this.path=path;
        lenght=path.length;
        index=0;
        this.robotC=robotC;
        carrotPoint=null;
        OriantationError=0;
    }
    public  boolean isDone(){
        if(index==lenght){
            setSpeed(0.0,0.0);
            return true;
        }
        return false;
    }
    private void findCarrotPoint(){
        while(carrotPoint==null && index!=lenght){
            carrotPoint=path[index];
            if(carrotPoint.getDistanceTo(getPosition())<1){
                carrotPoint=null;
            }
            index++;
        }
    }

    public void makeMove(){
        pos=getPosition();
        if(carrotPoint==null){
            findCarrotPoint();
        }
        //check again to see if no carropoint could be found
        if(carrotPoint==null) return;
        distance=pos.getDistanceTo(carrotPoint);
        if(distance<1.0){
            carrotPoint=null;
        }
        else {
            double carrotAngle = pos.getBearingTo(carrotPoint);
            double robotAngle = getBearing();
            OriantationError= diffBearing(robotAngle, carrotAngle);
            double speed = OriantationError > 1 ? 0 : 1;
            setSpeed(speed, OriantationError);
        }
    }


    private double diffBearing(double y, double x){
        return Math.atan2(Math.sin(x-y), Math.cos(x-y));

    }


    public void setSpeed(double linearSpeed, double angularSpeed) {
        DifferentialDriveRequest dr = new DifferentialDriveRequest();
        dr.setAngularSpeed(angularSpeed);
        dr.setLinearSpeed(linearSpeed);
        try {
            robotC.putRequest(dr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Position getPosition() {
        LocalizationResponse lr = new LocalizationResponse();
        try {
            robotC.getResponse(lr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        double[] coords = lr.getPosition();
        Position pos = new Position(coords[0],coords[1]);
        return pos;
    }
    public double getBearing() {
        LocalizationResponse lr = new LocalizationResponse();
        try {
            robotC.getResponse(lr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lr.getHeadingAngle();
    }

}
