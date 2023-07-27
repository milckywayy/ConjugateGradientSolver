package matrix;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Matrix {
    private List<Double> values = new ArrayList<>();
    private int m, n;

    public Matrix(List<Double> values, int n, int m) {

        if (values.size() != n * m)
            throw new InvalidParameterException("Invalid matrix size.");

        this.values.addAll(values);
        this.m = m;
        this.n = n;
    }

    public void dotProduct(double scalar) {
        for (int i = 0; i < values.size(); i++) {
            values.set(i, values.get(i) * scalar);
        }
    }

    public int size() {
        return values.size();
    }

    public double getElement(int m, int n) {
        return values.get((m * n) + n);
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

    public void print() {
        System.out.println("[");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(values.get((i * m) + j) + " ");
            }
            System.out.print("\n");
        }
        System.out.println("]");
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
