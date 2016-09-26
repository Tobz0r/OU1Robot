/**
 * Created by sejiaw on 2016-09-26.
 */
public class Main {
    public static void main(String [] args){
        HttpRequester hyyp=new HttpRequester();
        try {
            hyyp.requestGET();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Robot robot = new Robot();
        robot.createRobot();
        robot.runRobot();

    }
}
