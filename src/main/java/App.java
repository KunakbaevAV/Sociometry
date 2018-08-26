import backend.DataBase;
import frontend.WindowMain;
import frontend.WindowBase;

/**
 * @autor Kunakbaev Artem
 */
public class App {
    public static void main(String[] args) {
        DataBase base = new DataBase();
        new WindowMain();
        new WindowBase(base);
    }
}
