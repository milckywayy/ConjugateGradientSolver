package graphicaluserinterface;

import matrix.Matrix;
import solver.ConjugateGradientSolver;
import matrix.MatrixReader;
import utils.Security;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;

public class GraphicalUserInterface extends JFrame {
    private JPanel GradientConjugateSolverGUI;
    private JTextField matrixSizeField;
    private JTextField maxIterationsField;
    private JTextField precisionField;
    private JButton solveButton;
    private JButton readMatrixFileButton;
    private JPanel matrixAField;
    private JPanel matrixXField;
    private JPanel matrixBField;
    private JPanel topPanel;
    private JPanel contentPanel;
    private final Security security;
    private final MatrixReader matrixReader;
    private final ConjugateGradientSolver solver;
    private final MatrixFieldReader matrixFieldReader;

    public GraphicalUserInterface() {
        setContentPane(GradientConjugateSolverGUI);

        setTitle("Conjugate Gradient Solver");
        setMinimumSize(new Dimension(800, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        matrixSizeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setMatrixSize();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setMatrixSize();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setMatrixSize();
            }
        });
        readMatrixFileButton.addActionListener(e -> readMatrixButtonHandle());
        solveButton.addActionListener(e -> solve());

        security = new Security();
        matrixReader = new MatrixReader();
        solver = new ConjugateGradientSolver(Integer.parseInt(maxIterationsField.getText()),Double.parseDouble(precisionField.getText()));
        matrixFieldReader = new MatrixFieldReader();

        setMatrixSize();
    }

    private void setMatrixSize() {
        int size;

        if (security.isInteger(matrixSizeField.getText())) {
            size = Integer.parseInt(matrixSizeField.getText());
        }
        else {
            return;
        }

        if (size > 0) {
            matrixAField.removeAll();
            matrixXField.removeAll();
            matrixBField.removeAll();

            matrixAField.setLayout(new GridLayout(size, size));
            matrixXField.setLayout(new GridLayout(size, 1));
            matrixBField.setLayout(new GridLayout(size, 1));

            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    matrixAField.add(new matrixElementField(4));
                }
            }

            for (int row = 0; row < size; row++) {
                JTextField matrixElement = new matrixElementField(1);
                matrixElement.setEnabled(false);
                matrixXField.add(matrixElement);
                matrixBField.add(new matrixElementField(1));
            }

            matrixAField.revalidate();
            matrixXField.revalidate();
            matrixBField.revalidate();
        }
    }

    private void readMatrixButtonHandle() {
        Matrix matrix;

        try {
            String path = showFileDialog();
            if (path == null)
                return;
            matrix = matrixReader.read(path);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!security.isInteger(matrixSizeField.getText())) {
            matrixSizeField.setText("0");
        }

        if (matrix.getM() == matrix.getN()) {
            if (Integer.parseInt(matrixSizeField.getText()) != matrix.getM()) {
                matrixSizeField.setText(matrix.getM() + "");
                this.setMatrixSize();
            }

            matrixFieldReader.writeToPanel(matrix, matrixAField);

            return;
        }
        else if (matrix.getN() == 1) {
            if (Integer.parseInt(matrixSizeField.getText()) != matrix.getM()) {
                matrixSizeField.setText(matrix.getM() + "");
                this.setMatrixSize();
            }

            matrixFieldReader.writeToPanel(matrix, matrixBField);

            return;
        }

        JOptionPane.showMessageDialog(this, "Invalid matrix file.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void solve() {
        int matrixSize;
        Matrix resultMatrix;

        if (security.isInteger(maxIterationsField.getText())) {
            solver.setMaxIterations(Integer.parseInt(maxIterationsField.getText()));
        }
        else {
            JOptionPane.showMessageDialog(this, "Iterations value must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (security.isDouble(precisionField.getText())) {
            solver.setPrecision(Double.parseDouble(precisionField.getText()));
        }
        else {
            JOptionPane.showMessageDialog(this, "Precision value must be a float number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!matrixFieldReader.areFieldsCorrect(matrixAField) || !matrixFieldReader.areFieldsCorrect(matrixBField)) {
            JOptionPane.showMessageDialog(this, "Matrix elements must be a float number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (security.isInteger(matrixSizeField.getText())) {
            matrixSize = Integer.parseInt(matrixSizeField.getText());
        }
        else {
            JOptionPane.showMessageDialog(this, "Matrix size must  be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (matrixFieldReader.isFieldFull(matrixAField) && (matrixFieldReader.isFieldFull(matrixBField))) {
            resultMatrix = solver.solve(matrixFieldReader.readFromPanel(matrixAField, matrixSize, matrixSize),
                        matrixFieldReader.readFromPanel(matrixBField, matrixSize, 1));

            matrixFieldReader.writeToPanel(resultMatrix, matrixXField);
        }
    }

    private String showFileDialog() {
        FileDialog fileDialog = new FileDialog(this, "Choose a matrix file", FileDialog.LOAD);
        fileDialog.setDirectory("%userprofile%");
        fileDialog.setVisible(true);

        if (fileDialog.getDirectory() != null && fileDialog.getFile() != null) {
            return fileDialog.getDirectory() + fileDialog.getFile();
        }

        return null;
    }
}
