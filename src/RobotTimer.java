/**
 * Created by sejiaw on 2016-09-23.
 */
import java.util.Timer;
public class RobotTimer {

    private long startTime = 0;
    private long elapsedTime = 0;
    RobotTimer(){

    }
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
        //stoppa klockan
        clockStop();
        //skriv ut tid
        System.out.println("Total time elapsed = " + elapsedTime +" seconds");


    }
}
