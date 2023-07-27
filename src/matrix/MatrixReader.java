package matrix;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixReader {

    public MatrixReader() {
    }

    public Matrix read(String fileName) throws IOException {
        List<Double> data = new ArrayList<>();

        int m = 0;
        int counter = 0;

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                m++;

                String line = scanner.nextLine();
                String[] elements = line.split("\\s+");

                for (String element : elements) {
                    counter++;
                    data.add(Double.parseDouble(element));
                }
            }
        }

        if (m == 0 || counter == 0) {
            throw new IOException("Wrong Matrix format.");
        }

        return new Matrix(data, m, counter / m);
    }

    public void write(Matrix m, String fileName) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(m.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("Couldn't write matrix to file.");
        }
    }

}
