import javax.naming.ldap.Control;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by sejiaw on 2016-09-23.
 */
public class Robot {



    //attribute
    private Position myPos;
    private Position nextPos;
    boolean running = false;
    RobotTimer timer = new RobotTimer();
    private Controls controls;


    public Robot(){
        RobotCommunication robotCommunication=new RobotCommunication();
        Reader reader = new Reader();
        controls = new Controls(robotCommunication,reader);
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
            controls.calculateAngle();
            controls.move();
            controls.setSpeed(0,0);
            timer.endTime();
        }

    }


}
