/**
 * Created by sejiaw on 2016-09-23.
 */
public class Robot {


    //attribute
    Position myPos;
    Position nextPos;
    boolean running = false;


    public Robot(){

    }

    private void createRobot(){
        //get startpos från json??
        int xvalue = 5; // dessa från json?
        int yvalue =6; //dessa från json
        //set startpos
        myPos.setStartPosition(xvalue,yvalue);
        running = true;
        //install robot?

    }

    private void runRot(){

        while(running){
            System.out.println("JEBANE");
        }

    }

}
