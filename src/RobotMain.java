
public class RobotMain {

   public static void main(String[] args) throws Exception {
      Robot robot = new Robot("http://localhost",50000,"Path-around-table.json");
      robot.run();
   }

}

