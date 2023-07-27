package test;

import matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixTest {

    public static void main(String[] args) {
        List<Double> values = new ArrayList<>();
        List<Double> values2 = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            values.add((double)i);
        }

        for (int i = 0; i < 9; i++) {
            values2.add((double)i / 2);
        }

        Matrix m = new Matrix(values2, 3, 3);
        Matrix m2 = new Matrix(values, 3, 3);

        System.out.println(m.equals(m2));

        m.print();

        //m.dotProduct(2.0);

        m2.print();

    }
}
