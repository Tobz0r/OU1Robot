/**
 * Created by sejiaw on 2016-09-23.
 */
import java.util.Timer;
public class RobotTimer {

    private long startTime = 0;
    private boolean isRunning = false;
    private long totalTimeElapsed = 0;
    RobotTimer(){

    }
    public void clockStart(){
        if(!isRunning){
            startTime = System.nanoTime();
            isRunning = true;
        }

    }
    private void clockStop(){
        if(isRunning){
            totalTimeElapsed += System.nanoTime() - startTime;
            isRunning = false;
        }
    }

    public void endTime(){
        //stoppa klockan
        clockStop();
        //skriv ut tid
        System.out.println(totalTimeElapsed);


    }
}
