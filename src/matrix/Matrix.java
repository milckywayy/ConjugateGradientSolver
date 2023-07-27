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

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;

        for (int i = 0; i < m * n; i++) {
            values.add(0.0);
        }
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

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public void setElement(int index, double value) {
        values.set(index, value);
    }

    public void setElement(int m, int n, double value) {
        values.set((m * this.getN()) + n, value);
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Matrix copy() {
        return new Matrix(values, m, n);
    }

    @Override
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
