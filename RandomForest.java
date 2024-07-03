import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomForest {
    private float[][] data;
    private float[] lables;
    private float[][] random;
    private float[] L;
    private  int[] rows,columes;
    ArrayList col = new ArrayList();
    public RandomForest(float[][] data,float[] lables){
        this.data = data;
        this.lables = lables;
        this.random = new float[data.length][(int)Math.sqrt(data[0].length)];
        this.L= new float[data.length];
        this.rows = new int[data.length];
        this.columes = new int[random[0].length];

    }
    public void randomize(){
        Random rand = new Random(data.length);
        float[] kh = new  float[columes.length];
        for(int i = 0 ; i < rows.length ; i++){
            rows[i] = rand.nextInt(0,data.length);
            //System.out.println(rows[i]);
            L[i] = lables[rows[i]];
        }
        ArrayList list = new ArrayList();
        for(int i = 1; i <= columes.length; i++) {
            kh[i-1] = i;

        }
        for(int i = 0 ; i < columes.length ; i++){
            int k = rand.nextInt(0,columes.length);
            if (kh[k] == -1 && i != 0)
            i--;
            else
                columes[i] = (int )kh[k];
        }


        for(int i = 0 ; i < data.length;i++){
            for(int j = 0 ; j < columes.length;j++){
                System.out.println(data[rows[i]][ columes[i]]);
                random[i][j] = data[rows[i]][ columes[i]];
            }
        }
       // System.out.println(Arrays.deepToString(random));
    }
    public float perdict(float[] dataTest,int depth) throws FileNotFoundException {
        float[] perdictions = new float[4];
        for(int i =0 ; i < 4; i++){
        randomize();
        DecisionTree dt = new DecisionTree(random,L);
        perdictions[i] = dt.Predict(dataTest,depth);}
        float max = perdictions[0];
        for (int i = 1; i < perdictions.length; i++) {
            if (perdictions[i] > max){
                max = perdictions[i];

            }
        }
        return  max;
    }
}
