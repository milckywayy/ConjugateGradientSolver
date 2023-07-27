package matrix;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class MatrixCalc {

    public MatrixCalc() {
    }

    public Matrix add(Matrix a, Matrix b) {
        if (a.size() != b.size()) {
            throw new IllegalArgumentException("Matrix sizes are different.");
        }

        List<Double> values = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            values.add(a.getElement(i) + b.getElement(i));
        }

        return new Matrix(values, a.getM(), a.getN());
    }

    public Matrix multiply(Matrix a, Matrix b) {
        if (a.getN() != b.getM()) {
            throw new IllegalArgumentException("Number of columns in matrix A must be equal to the number of rows in matrix B.");
        }

        List<Double> resultValues = new ArrayList<>();
        double sum;

        for (int i = 0; i < a.getM(); i++) {
            for (int j = 0; j < b.getN(); j++) {
                sum = 0;
                for (int k = 0; k < a.getN(); k++) {
                    sum += a.getElement(i, k) * b.getElement(k, j);
                }
                resultValues.add(sum);
            }
        }

        return new Matrix(resultValues, a.getM(), b.getN());
    }

}
