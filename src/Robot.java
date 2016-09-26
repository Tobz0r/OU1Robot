/**
 * Created by sejiaw on 2016-09-23.
 */
public class Robot {



    //attribute
    Position myPos;
    Position nextPos;
    boolean running = false;
    RobotTimer timer;


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
            for (int i = 0 ; i < 20 ; i++) {
                System.out.println("JEBANE");
            }
            running = false;
            timer.endTime();
        }

    }
}
