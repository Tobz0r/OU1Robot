/**
 * Controls for the movement of the robot
 * @author Tobias Estefors, Thom Renström
 */
public class Controls {

    private Position[] path;
    private int lenght;
    private int index;
    private RobotCommunication robotC;
    private Position carrotPoint;
    private double OriantationError;

    public Controls(Position[] path, RobotCommunication robotC){
        this.path=path;
        lenght=path.length;
        index=0;
        this.robotC=robotC;
        carrotPoint=null;
        OriantationError=0;
    }

    /**
     * Checks if the robot is on its final point
     * @return true if path is finished, else false
     */
    public  boolean isDone(){
        if(index==lenght){
            setSpeed(0.0,0.0);
            return true;
        }
        return false;
    }

    /**
     * Takes a new point from the path, skips a point
     * if its to close to the robot to save time
     */
    private void findCarrotPoint(){
        while(carrotPoint==null && index!=lenght){
            carrotPoint=path[index];
            if(carrotPoint.getDistanceTo(getPosition())<1){
                carrotPoint=null;
            }
            index++;
        }
    }

    /**
     * Moves the robot by calculating the angles of the robot orientation
     * and the angle to the carrotpoint and takes the difference between
     * the two angles
     */
    public void makeMove(){
        Position pos = getPosition();
        if(carrotPoint==null){
            findCarrotPoint();
        }
        //check again to see if no carropoint could be found
        if(carrotPoint==null) return;
        double distance = pos.getDistanceTo(carrotPoint);
        if(distance <1.0){
            carrotPoint=null;
        }
        else {
            double carrotAngle = pos.getBearingTo(carrotPoint);
            double robotAngle = getBearing();
            OriantationError= diffBearing(robotAngle, carrotAngle);
            double speed = OriantationError > (Math.PI/2) ? 0 :2;
            setSpeed(speed, OriantationError);
        }
    }

    /**
     * Calculates the diffrence between two nalges
     * @param y the source angle
     * @param x the target angle
     * @return the diffrence
     */
    private double diffBearing(double y, double x){
        return Math.atan2(Math.sin(x-y), Math.cos(x-y));

    }

    /**
     * Changes the speed for the robot
     * @param linearSpeed the robots speed in a straight line
     * @param angularSpeed the robots turnrate
     */
    public void setSpeed(double linearSpeed, double angularSpeed) {
        DifferentialDriveRequest dr = new DifferentialDriveRequest();
        dr.setAngularSpeed(4*angularSpeed);
        dr.setLinearSpeed(linearSpeed);
        try {
            robotC.putRequest(dr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Requests the robots current position
     * @return the robots positon
     */
    public Position getPosition() {
        LocalizationResponse lr = new LocalizationResponse();
        try {
            robotC.getResponse(lr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        double[] coords = lr.getPosition();
        return new Position(coords[0],coords[1]);
    }

    /**
     * Gets the robots orientation
     * @return the robots orientation
     */
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
