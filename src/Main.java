import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new Reader().readPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Hello World!");
    }
}
