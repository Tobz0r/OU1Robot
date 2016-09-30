/**
 * Timer for robot to clock time from start to finish
 * @author dv13trm,dv13tes
 */
public class RobotTimer {

    private long startTime = 0;
    private long elapsedTime = 0;
    RobotTimer(){
    }
    /**
     * Starts timer, this will be called when robot starts
     */
    public void clockStart(){
        startTime = System.currentTimeMillis();
    }

    /**
     * Stop clock used by robot
     */
    private void clockStop(){
        elapsedTime = ((System.currentTimeMillis() - startTime)/1000);
    }

    /**
     * Stop clock then print out value of clock in seconds
     */
    public void endTime(){
        clockStop();
        System.out.println("Total time elapsed = " + elapsedTime +" seconds");
    }
}
