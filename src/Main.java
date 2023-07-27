import matrix.Matrix;
import matrix.MatrixReader;
import solver.ConjugateGradientSolver;

public class Main {
    public static void main(String[] args) {
        ConjugateGradientSolver cgs = new ConjugateGradientSolver(1000, 1e-6);
        MatrixReader reader = new MatrixReader();

        Matrix A;
        Matrix b;
        Matrix x;

        try {
            A = reader.read("src/test/data/A1.txt");
            b = reader.read("src/test/data/b1.txt");

            System.out.println("A:\n" + A);
            System.out.println("b:\n" + b);

            x = cgs.solve(A, b);
            reader.write(x, "src/test/data/output.txt");

            System.out.println("Result:\n" + x);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
