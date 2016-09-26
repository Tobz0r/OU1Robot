

/**
 * Created by sejiaw on 2016-09-23.
 */
import java.lang.Math;

public class Controls {


    private double angSpeed,lineSpeed,xTurnRate,
            yTurnRate,distance, xCord,yCord,angle;
    RobotCommunication robotCom;
    Position pos;
    Reader reader;
    private Position[] path;
    private int index = 0;



    public Controls(RobotCommunication robotC, Position pos, Reader reader){
        this.robotCom = robotC;
        this.pos = pos;
        this.reader = reader;
    }

    /**
     * Calculate angle for robot to turn
     */
    public void calculateAngle(){
        path = reader.getPaths();

        xCord = path[index].getX() - pos.getX();
        yCord = path[index].getY() - pos.getY();
        index++;
        
        angle = Math.atan2(yCord,xCord);
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
