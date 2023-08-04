package graphicaluserinterface;

import matrix.Matrix;
import utils.Security;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixFieldReader {
    Security security;

    public MatrixFieldReader() {
        security = new Security();
    }

    public Matrix readFromPanel(JPanel panel, int mSize, int nSize) {
        List<Double> values = new ArrayList<>();

        Component[] components = panel.getComponents();
        for (Component component : components) {
            values.add(Double.valueOf(((JTextField)component).getText()));
        }

        return new Matrix(values, mSize, nSize);
    }

    public void writeToPanel(Matrix matrix, JPanel panel) {
        Component[] components = panel.getComponents();
        List<Double> values = new ArrayList<>(matrix.getValues());
        for (int i = 0; i < matrix.size(); i++) {
            ((JTextField)components[i]).setText(String.valueOf(values.get(i)));
        }
    }

    public boolean isMatrixFieldFull(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (((JTextField)component).getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean areMatricPanelsCorrect(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (!security.isDouble(((JTextField)component).getText())) {
                return false;
            }
        }
        return true;
    }
}
