package GraphicalUserInterface;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GraphicalUserInterface extends JFrame {
    private JButton readFileButton;
    private JTextField matrixSize;
    private JPanel GradientConjugateSolverGUI;
    private JButton solveButton;
    private JPanel matrixA;
    private JPanel matrixX;
    private JPanel matrixB;
    private JTextField maxIterations;
    private JTextField precision;

    public GraphicalUserInterface() {
        setContentPane(GradientConjugateSolverGUI);

        setTitle("Conjugate Gradient Solver");
        setMinimumSize(new Dimension(700, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        matrixSize.getDocument().addDocumentListener(new DocumentListener() {
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
        readFileButton.addActionListener(e -> readMatrixFile());

        setMatrixSize();
    }

    private void setMatrixSize() {
        int size;

        if (!matrixSize.getText().isEmpty()) {
            size = Integer.parseInt(matrixSize.getText());
        }
        else {
            return;
        }

        if (size > 0) {
            matrixA.setLayout(new GridLayout(size, size));
            matrixX.setLayout(new GridLayout(size, 1));
            matrixB.setLayout(new GridLayout(size, 1));

            matrixA.removeAll();
            matrixX.removeAll();
            matrixB.removeAll();

            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    JTextField matrixElement = new matrixElementField(4);
                    matrixA.add(matrixElement);
                }
            }

            for (int row = 0; row < size; row++) {
                JTextField matrixElement = new matrixElementField(1);
                matrixElement.setEnabled(false);
                matrixX.add(matrixElement);
            }

            for (int row = 0; row < size; row++) {
                JTextField matrixElement = new matrixElementField(1);
                matrixB.add(matrixElement);
            }

            matrixA.revalidate();
            matrixX.revalidate();
            matrixB.revalidate();
        }
    }

    private void readMatrixFile() {
    }
}
