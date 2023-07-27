package test;

import matrix.Matrix;
import matrix.MatrixCalc;
import solver.ConjugateGradientSolver;

import java.util.ArrayList;
import java.util.List;

public class MatrixTest {

    public static void main(String[] args) {
        List<Double> values = new ArrayList<>();
        List<Double> values2 = new ArrayList<>();
        values.add(4.0);
        values.add(2.0);
        values.add(-1.0);
        values.add(2.0);
        values.add(5.0);
        values.add(2.0);
        values.add(-1.0);
        values.add(2.0);
        values.add(10.0);

        values2.add(12.0);
        values2.add(-8.0);
        values2.add(4.0);

        MatrixCalc calc = new MatrixCalc();
        ConjugateGradientSolver cgs = new ConjugateGradientSolver(100, 0.001);

        Matrix A = new Matrix(values, 3, 3);
        Matrix b = new Matrix(values2, 3, 1);

        System.out.println(cgs.solve(A, b));

        //System.out.println((calc.multiply(m, m2)).toString());

//        System.out.println(m.toString());
//        m = calc.transpose(m);
//        System.out.println(m.toString());

    }
}
