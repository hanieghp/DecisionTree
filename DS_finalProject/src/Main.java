import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
//    public static final int rows = 10;
//    public static final int cols = 17;
    public static int rows,cols;
    public static void main(String[] args) throws IOException {
        rows = getRowCount("feature_trainT.txt");
        cols = getColumnCount("feature_trainT.txt");
        float[][] data = readData();
        float[] label = readLabel();
        System.out.println(rows);
        System.out.println(cols);
        System.out.println(Arrays.deepToString(data));
        Tree kh = new Tree();
        System.out.println(kh.Entropy(label));
    }

    private static float[][] readData() throws FileNotFoundException {
        float[][] data = new float[rows][cols];
        File file = new File("feature_trainT.txt");

        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
            for (int i = 0; i < data.length && sc.hasNextLine(); i++) {
                String[] line = sc.nextLine().trim().split(",");
                for (int j = 0; j < Math.min(line.length, data[i].length); j++) {
                    data[i][j] = Float.parseFloat(line[j]);
                }
            }
        }
        return data;
    }
    private static float[] readLabel() throws FileNotFoundException{
        float[] label = new float[rows];
        File file = new File("label_trainT.txt");

        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
            for (int i = 0; i < label.length && sc.hasNextLine(); i++) {
                String line = sc.nextLine();
                label[i] = Float.parseFloat(line);
            }
        }
        return label;
    }
    private static int getRowCount(String filePath) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(filePath))) {
            int count = 0;
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            return count;
        }
    }
    private static int getColumnCount(String filePath) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(filePath))) {
            if (sc.hasNextLine()) {
                String[] line = sc.nextLine().trim().split(",");
                return line.length;
            }
            return 0;
        }
    }
}
