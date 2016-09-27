

/**
 * Created by sejiaw on 2016-09-23.
 */
import java.io.IOException;
import java.lang.Math;

public class Controls {


    private double angSpeed,lineSpeed,xTurnRate,
            yTurnRate,distance, xCord,yCord,angle;
    RobotCommunication robotCom;
    Position pos;
    Reader reader;
    private Position[] path;
    private int index = 0;
    private double xCP,yCP;




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
        try {

            pos=robotCom.requestGET()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        xCord = path[index].getX() - pos.getX();
        yCord = path[index].getY() - pos.getY();
        double distance=Math.sqrt((xCord*xCord)+(yCord*yCord));
        System.out.println("DISTANCE " + distance);
        index++;
        double oriX=0,oriY=0;
        QuadPosition oriPos=null;
        try {
            oriPos=(QuadPosition)robotCom.requestGET()[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        oriX=oriPos.getW();
        oriY=oriPos.getZ();
        System.out.println(oriX + "" + oriY);
        //angle = Math.atan2(yCord,xCord);
        angle=getBearingAngle(oriX,oriY);
        try {
            robotCom.requestPOST(angle,getDirection(oriX,oriY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(angle);
    }

    public  boolean isDone(){
        return index==path.length;
    }
    public double getDirection(double x, double y){
        System.out.println("X = "+ x + " Y = " + y);
        return Math.atan2(y,x ) / Math.PI * 180;
    }

    public double getBearingAngle(double x, double y){
        double angle = 2 * Math.atan2(y, x);
        return angle * (180 / Math.PI);
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
            Thread.sleep(1000);
            robotCom.requestPOST(1,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
