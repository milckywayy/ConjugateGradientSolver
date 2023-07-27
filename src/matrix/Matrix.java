package matrix;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Matrix {
    private List<Double> values = new ArrayList<>();
    private int m, n;

    public Matrix(List<Double> values, int m, int n) {

        if ((values.size() != m * n) || values.size() < 1) {
            throw new IllegalArgumentException("Invalid matrix size.");
        }

        this.values.addAll(values);
        this.m = m;
        this.n = n;
    }

    public void dotProduct(double scalar) {
        for (int i = 0; i < values.size(); i++) {
            values.set(i, values.get(i) * scalar);
        }
    }

    public void transpose() {
        List<Double> transposedValues = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                transposedValues.add(getElement(j, i));
            }
        }

        int temp = m;
        m = n;
        n = temp;

        values = transposedValues;
    }

    public int size() {
        return values.size();
    }

    public double getElement(int m, int n) {
        return values.get((m * this.getN()) + n);
    }

    public double getElement(int index) {
        return values.get(index);
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("[\n");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.append(values.get((i * n) + j)).append(" ");
            }
            result.append("\n");
        }

        return result + "]";
    }

    public Matrix copy() {
        return new Matrix(values, m, n);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix))
            return false;

        if (m != ((Matrix)o).getM() || n != ((Matrix)o).getN()) {
            return false;
        }

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) != ((Matrix)o).getElement(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, m, n);
    }

}
