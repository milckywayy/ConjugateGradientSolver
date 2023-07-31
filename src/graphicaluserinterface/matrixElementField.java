package graphicaluserinterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class matrixElementField extends JTextField {
    public matrixElementField(int columns) {
        super(columns);

        setFont(getFont().deriveFont(16f));

    }
}
