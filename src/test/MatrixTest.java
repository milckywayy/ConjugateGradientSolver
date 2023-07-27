package test;

import matrix.Matrix;
import matrix.MatrixCalc;

import java.util.ArrayList;
import java.util.List;

public class MatrixTest {

    public static void main(String[] args) {
        List<Double> values = new ArrayList<>();
        List<Double> values2 = new ArrayList<>();
        values.add(1.0);
        values.add(2.0);
        values.add(3.0);
        values.add(4.0);
        values.add(5.0);
        values.add(6.0);
        values2.add(7.0);
        values2.add(8.0);
        values2.add(9.0);
        values2.add(10.0);
        values2.add(11.0);
        values2.add(12.0);

        MatrixCalc calc = new MatrixCalc();


        Matrix m = new Matrix(values, 2, 3);
        Matrix m2 = new Matrix(values2, 3, 2);

        System.out.println((calc.multiply(m, m2)).toString());

    }
}
