

/**
 * Created by sejiaw on 2016-09-23.
 */
import java.awt.*;
import java.io.IOException;
import java.lang.Math;

public class Controls {


    private double angSpeed,lineSpeed,xTurnRate,
            yTurnRate,distance, xCord,yCord,angle;
    RobotCommunication robotCom;
    Position pos;
    Reader reader;
    private Position[] response;
    private Position[] path;
    private int index = 0;
    private double xCP,yCP;
    private double OriantationError;
    private double q;




    public Controls(RobotCommunication robotC, Reader reader){
        this.robotCom = robotC;
        this.reader = reader;
        try {
            reader.readPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        path=reader.getPaths();
    }


    /**
     * Calculate angle for robot to turn
     */
    public void calculateAngle(){
        double deltaX=xCP-pos.getX();
        double deltaY=yCP-pos.getY();
        angle = Math.atan2(deltaY,deltaX);

    }

    public  boolean isDone(){
        return index==path.length;
    }


    /**
     * Generate a carrot point for robot to follow
     * @param index of carrotpoint
     */
    public void generateCarrotPoint(int index){
        xCP = path[index].getX();
        yCP = path[index].getY();


    }

    /**
     * Check if robot can move or not
     * @return true if allowed to move else false
     */
    private boolean canIMove(){
        boolean canI = false;
        System.out.println(OriantationError + "");
        if((OriantationError)==0) {
            canI = true;
        }
        return canI;
    }


    public void getQ(){
        QuadPosition qpos=(QuadPosition)response[1];
        q=getBearingAngle(qpos.getW(),qpos.getZ());
    }
    /**
     * Calculate error
     */
    public void calculateError(){
        QuadPosition pos=(QuadPosition)response[1];
        OriantationError=pos.getW();
    }
    
    /**
     * Run method for robot
     */
    public void running(){
        try {
            response=robotCom.requestGET();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pos=response[0];
        //calculate angle
        generateCarrotPoint(index);
        getQ();
        calculateAngle();
        OriantationError=Math.atan2(Math.sin(angle-q), Math.cos(angle-q));

        //generate carrot point
        //calculate error
        try {
            robotCom.requestPOST(OriantationError,0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while(!canIMove()){
            try {
                response=robotCom.requestGET();

            } catch (Exception e) {
                e.printStackTrace();
            }
            getQ();
            OriantationError=Math.atan2(Math.sin(angle-q), Math.cos(angle-q));
            //jämnför robot angle mot carrot point?
            //sedan sätt nytt värde på orientationerror hela tiden
        }
        move();
        index++;
    }

    public double getBearingAngle(double x, double y){
        double angle = 2 * Math.atan2(y, x);
        return angle;
    }

    /**
     * Set speed for controls
     * @param angSpeed angular speed
     * @param lineSpeed lineer speed.
     */
    public void setSpeed(double angSpeed, double lineSpeed){
        this.angSpeed=angSpeed;
        this.lineSpeed=lineSpeed;
        try {
            robotCom.requestPOST(angSpeed,lineSpeed);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Set distance for controls
     * @param distance distance to travel
     */
    public void setDistance(double distance){
        this.distance = distance;
    }
    public void move(){
        try {
            System.out.println(" MOVE ANG = " + angSpeed + " LINE = " + lineSpeed + " ANGLE "+ angle);
            robotCom.requestPOST(OriantationError,1);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/**
    public double getDirection(double x, double y){
        System.out.println("X = "+ x + " Y = " + y);
        return Math.atan2(y,x ) / Math.PI * 180;
    }


    **
     * Set turnrate for controls
     * @param x value to turn
     * @param y value to turn
     *
    public void setTurnRate(double x, double y){
        this.xTurnRate = x;
        this.yTurnRate = y;

    }
**/
}
