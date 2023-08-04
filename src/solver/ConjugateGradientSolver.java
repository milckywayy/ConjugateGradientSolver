package solver;

import matrix.Matrix;
import matrix.MatrixCalc;
import matrix.MatrixPropertiesChecker;

public class ConjugateGradientSolver {
    int maxIterations;
    double precision;
    MatrixCalc calc;
    MatrixPropertiesChecker matrixChecker;

    public ConjugateGradientSolver(int maxIterations, double precision) {
        calc = new MatrixCalc();
        matrixChecker = new MatrixPropertiesChecker();
        this.maxIterations = maxIterations;
        this.precision = precision;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public Matrix solve(Matrix A, Matrix b) {
        if (!matrixChecker.isSymmetric(A)) {
            throw new IllegalArgumentException("The matrix is not symmetrical.");
        }

        if (!matrixChecker.isPositiveDefinite(A, precision)) {
            throw new IllegalArgumentException("The matrix is not positive definite.");
        }

        Matrix x0 = new Matrix(b.getM(), b.getN());
        Matrix x1;
        Matrix r0 = b.copy();
        Matrix r1;
        Matrix p0 = b.copy();
        Matrix p1;

        double alpha;
        double beta;
        int iteration = 0;

        Matrix r0_r = calc.multiply(calc.transpose(r0), r0);

        while (r0_r.getElement(0, 0) > precision && iteration < maxIterations) {
            iteration++;

            alpha = r0_r.getElement(0, 0);
            alpha /= calc.multiply(calc.transpose(p0), calc.multiply(A, p0)).getElement(0, 0);

            x1 = calc.add(x0, calc.dotProduct(p0, alpha));

            r1 = calc.add(r0, calc.dotProduct(calc.multiply(A, p0), alpha * -1));

            beta = calc.multiply(calc.transpose(r1), r1).getElement(0, 0);
            beta /= r0_r.getElement(0, 0);

            p1 = calc.add(r1, calc.dotProduct(p0, beta));

            x0 = x1;
            p0 = p1;
            r0 = r1;

            r0_r = calc.multiply(calc.transpose(r0), r0);
        }

        return x0;
    }
}
