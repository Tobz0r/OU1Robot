

/**
 * Created by sejiaw on 2016-09-23.
 */
import java.awt.*;
import java.io.IOException;
import java.lang.Math;

public class Controls {


    private double angSpeed,lineSpeed,xTurnRate,
            yTurnRate,distance, xCord,yCord;
    RobotCommunication robotCom;
    Position pos;
    Reader reader;
    private Position[] response;
    private Position[] path;
    private int index = 0;
    private double xCP,yCP;
    private double OriantationError, OrientationAngle;






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
    public double calculateAngle(double x, double y){
        return Math.atan2(y,x);
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
    private boolean canIMove(double angle){
        boolean canI = false;
        //System.out.println(OriantationError + "");
        double stop = 0.00;
        if(angle==stop) {
            canI = true;
        }
        return canI;
    }


    public double getCPX(int index){
        return path[index+1].getX();
    }

    public double getCPY(int index){
        return path[index+1].getY();
    }
    public double getQX(){
        QuadPosition qpos=(QuadPosition)response[1];
        return qpos.getW();
    }
    public double getQY(){
        QuadPosition qpos=(QuadPosition)response[1];
        return qpos.getZ();
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
        //generateCarrotPoint(index);
        double qx = getQX();
        double qy = getQY();
        double xcp = getCPX(index);
        double ycp = getCPY(index);
        double angle;
        angle = calculateAngle(xCP-pos.getX(),yCP-pos.getY());
        OriantationError = calculateDiffrence(qy,qx,xcp,ycp);
        double qAngle;
      //USLESS????  qAngle = calculateAngle(qx,qy);
        System.out.println("OE= "+OriantationError);
        while(!canIMove(OriantationError)){
            qx = getQX();
            qy = getQY();
            angle = calculateAngle(xCP-pos.getX(),yCP-pos.getY());
            qAngle = calculateAngle(qx,qy);
            if(qAngle ==angle){
                System.out.println("ANGLE = "+angle);
                System.out.println("Q = "+ qAngle);
            }
            //  System.out.println("OE= "+OriantationError);

            //System.out.println("QX = "+qx + " QY= " +qy + " CPX = " + xcp + " CPY = " + ycp);
            try {
                robotCom.requestPOST(0.5,0);
                response=robotCom.requestGET();

            } catch (Exception e) {
                e.printStackTrace();
            }

            OriantationError = calculateDiffrence(qy,qx,xcp,ycp);
            //jämnför robot angle mot carrot point?
            //sedan sätt nytt värde på orientationerror hela tiden
        }
        move();
        index++;
    }
    public double calculateDiffrence(double qy, double qx, double cx, double cy){
       return Math.sqrt((qx-cx)*(qx-cx)+(qy-cy)*(qy-cy));
    }

/*
    public double getBearingAngle(double x, double y){
        double angle = 2 * Math.atan2(y, x);
        return angle;
    }
*/
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
            System.out.println(" MOVE ANG = " + angSpeed + " LINE = " + lineSpeed );
            robotCom.requestPOST(0,1);
            Thread.sleep(1000);
            stopVehicle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopVehicle(){
        try {
            robotCom.requestPOST(0,0);
            Thread.sleep(1000);
            System.out.println("STOP");
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
