import graphicaluserinterface.GraphicalUserInterface;
import com.formdev.flatlaf.FlatDarculaLaf;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        GraphicalUserInterface gui = new GraphicalUserInterface();
    }
}
