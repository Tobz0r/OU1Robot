

/**
 * Created by sejiaw on 2016-09-23.
 */
import java.lang.Math;

public class Controls {


    private double angSpeed,lineSpeed,xTurnRate, yTurnRate,distance;
    RobotCommunication robotCom;


    public Controls(RobotCommunication robotC){
        this.robotCom = robotC;
    }

    /**
     * Calculate angle for robot to turn
     */
    public void calculateAngle(){

    }

    /**
     * Set turnrate for controls
     * @param x value to turn
     * @param y value to turn
     */
    public void setTurnRate(double x, double y){
        this.xTurnRate = x;
        this.yTurnRate = y;

    }

    /**
     * Set speed for controls
     * @param angSpeed angular speed
     * @param lineSpeed lineer speed.
     */
    public void setSpeed(double angSpeed, double lineSpeed){
        this.angSpeed=angSpeed;
        this.lineSpeed=lineSpeed;

    }

    /**
     * Set distance for controls
     * @param distance distance to travel
     */
    public void setDistance(double distance){
        this.distance = distance;
    }

}
