import java.io.IOException;

/**
 * Created by Tobz0r on 2016-09-30.
 */
public class Robot {

    boolean running = false;
    RobotTimer timer = new RobotTimer();
    private Controls controls;
    private boolean goalValue = false;


    public Robot(String host, int port, String path){
        RobotCommunication robotC=new RobotCommunication(host,port);
        Reader reader = new Reader();
        try {
            controls = new Controls(reader.getPath(path),robotC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
