import java.io.IOException;

/**
 * Robot class is used to create a new robot for robotsimulation.
 * Then solve a path with given controls.
 * @author dv13trm,dv13tes
 */
public class Robot {

    RobotTimer timer = new RobotTimer();
    private Controls controls;

    /**
     * Constructor to create a robot
     * @param host host for simulator
     * @param port port for simulator
     * @param path robots path
     */
    public Robot(String host, int port, String path){
        RobotCommunication robotC=new RobotCommunication(host,port);
        Reader reader = new Reader();
        try {
            controls = new Controls(reader.getPath(path),robotC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run function for robot, will start time and move until
     * goal is found
     * @throws Exception Will be called when thread fail to sleep
     */
    public void run() throws Exception {
        System.out.println("Robot started and taking time!");
        timer.clockStart();
        while(!controls.isDone()){
            controls.makeMove();
            Thread.sleep(1);
        }
        timer.endTime();
    }
}
