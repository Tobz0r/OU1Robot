import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by sejiaw on 2016-09-23.
 */
public class Robot {



    //attribute
    Position myPos;
    Position nextPos;
    boolean running = false;
    RobotTimer timer = new RobotTimer();


    public Robot(){

    }

    public void createRobot(){
        //get startpos från json??
       // int xvalue = 5; // dessa från json?
       // int yvalue =6; //dessa från json
        //set startpos
       // myPos.setStartPosition(xvalue,yvalue);
        running = true;
        //install robot?
        timer.clockStart();

    }

    public void runRobot(){

        while(running){
            for (int i = 0 ; i < 10 ; i++) {
                System.out.println("JEBANE");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            running = false;
            timer.endTime();
        }

    }


    //CLOCKBOYS

    /**
     * Start clock used by robot
     */

}
