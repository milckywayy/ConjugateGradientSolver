package GraphicalUserInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class matrixElementField extends JTextField {
    public matrixElementField(int columns) {
        super(columns);

        setFont(getFont().deriveFont(16f));

    }
}
