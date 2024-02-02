import java.io.*;
//import java.util.Arrays;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final int rows = 10;
    public static final int cols = 8;
    public static final String filePathData = "src/Data/feature_train.csv";
    public static final String filePathLabel = "src/Data/label_train.csv";
    public static String[] attributes;
    //public static int rows,cols;
    public static void main(String[] args) throws IOException {
//        rows = getRowCount(filePathData);
//        cols = getColumnCount(filePathData);
        float[][] data = readData();
        float[] label = readLabel();
        System.out.println(rows);
        System.out.println(cols);
        attributes =readAttributes(filePathData);
//        for(int i = 0 ;i<attributes.length ; i++){
//            System.out.println(attributes[i]);
//        }
//        System.out.println(Arrays.deepToString(data));
        Tree kh = new Tree();
        kh.creatTree(data,label);
        kh.display();
        System.out.println(kh.findDepth() - 2);
//        float[] kc =kh.Sort(data,0,rows);
//        for(int i=0 ;i < kc.length ; i++){
//            System.out.println(kc[i]);
//        }
    }

    private static float[][] readData() throws FileNotFoundException {
        float[][] data = new float[rows][cols];
        File file = new File(filePathData);

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
        File file = new File(filePathLabel);

        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
            for (int i = 0; i < label.length && sc.hasNextLine(); i++) {
                String line = sc.nextLine();
                label[i] = Float.parseFloat(line);
            }
        }
        return label;
    }
    private static String[] readAttributes(String filePath){
        String[] attributes = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String firstLine = br.readLine();
            if (firstLine != null) {
                String[] dataArray = firstLine.split(",");
                attributes = new String[dataArray.length];

                for (int i = 0; i < dataArray.length; i++) {
                    attributes[i] = dataArray[i];
                    //System.out.println(dataArray[i]);
                }
            } else {
                System.out.println("File is Empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attributes;
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
