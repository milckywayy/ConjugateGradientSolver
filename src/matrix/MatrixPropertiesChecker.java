package matrix;

public class MatrixPropertiesChecker {
    public boolean isSquare(Matrix matrix) {
        return matrix.getM() == matrix.getN();
    }

    public boolean isSymmetric(Matrix matrix) {
        if (!isSquare(matrix)) {
            return false;
        }

        for (int m = 0; m < matrix.getM(); m++) {
            for (int n = 0; n < matrix.getN(); n++) {
                if (matrix.getElement(m, n) != matrix.getElement(n, m)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isPositiveDefinite(Matrix matrix, double precision) {
        if (!isSquare(matrix)) {
            return false;
        }

        int n = matrix.getM();
        double[][] L = new double[n][n];

        // Cholesky's decomposition
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int k = 0; k < i; k++) {
                sum += L[i][k] * L[i][k];
            }
            double diagonalElement = matrix.getElement(i, i) - sum;
            if (diagonalElement <= precision) {
                return false;
            }
            L[i][i] = Math.sqrt(diagonalElement);
            for (int j = i + 1; j < n; j++) {
                sum = 0.0;
                for (int k = 0; k < i; k++) {
                    sum += L[i][k] * L[j][k];
                }
                L[j][i] = (matrix.getElement(j, i) - sum) / L[i][i];
            }
        }

        return true;
    }
}
