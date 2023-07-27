package test;

import matrix.Matrix;
import matrix.MatrixReader;
import solver.ConjugateGradientSolver;

import java.io.IOException;

public class MatrixTest {
    public static void main(String[] args) throws IOException {
        ConjugateGradientSolver cgs = new ConjugateGradientSolver(1000, 1e-6);
        MatrixReader reader = new MatrixReader();

        Matrix A;
        Matrix b;

        A = reader.read("src/test/data/A3.txt");
        b = reader.read("src/test/data/b3.txt");

        long startTime, endTime, elapsedTime;

        startTime = System.currentTimeMillis();
        cgs.solve(A, b);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Execution time: " + elapsedTime + " ms");
    }
}

