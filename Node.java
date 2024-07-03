import java.util.ArrayList;

public class Node {
    Node next;

    public Node getNext() {
        return next;
    }

    private int featureIndex;

    public void setNext(Node next) {
        this.next = next;
    }

    float[] sorted;
//    private float outcome = -1;
    ArrayList<Node> children = new ArrayList<>();
    boolean isLeaf = true;
    private float value;

    public boolean isLeaf() {
        return isLeaf;
    }
    float[][] data;
    float[] lables;
    int depth=0;


    public void setFeatureIndex(int featureIndex) {
        this.featureIndex = featureIndex;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    public  float getOutcome(){
        float outcome = -1,temp=0;
        if(isLeaf) {
            int[] count = new int[3];
            float[] arr2 = new float[data.length];
            for (int i = 0; i < arr2.length; i++) {
                switch ((int) lables[i]) {
                    case 0:
                        count[0]++;
                        break;
                    case 1:
                        count[1]++;
                        break;
                    case 2:
                        count[2]++;
                        break;
                }
            }
            int indexmax = count[0];
            for (int i = 1; i < count.length; i++) {
                if (count[i] > indexmax){
                    indexmax = count[i];
                    temp = i;
                }
            }
            outcome = temp;
        }
        return  outcome;
        }



    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public Node(int featureIndex, float[][] data, float[] lables) {
        this.featureIndex = featureIndex;
         value = 0;
         this.data =data;
         this.lables=lables;
    }
    public Node(float value, float[][] data, float[] lables) {
        this.value = value;
        this.data =data;
        this.lables=lables;
    }
    public Node(int featureIndex,float[][] data,float[] lables,float value) {
        this.featureIndex = featureIndex;
        this.value = value;
        this.data =data;
        this.lables=lables;
    }

    public Node(float value) {
        this.value = value;
    }

    public Integer getFeatureIndex() {
        return featureIndex;
    }


    public Node addchild(float kh){
        Node newborn = new Node(kh);
        children.add(newborn);
        this.isLeaf = false;
        return newborn;
    }
    public Node addchild(float value,float[][] data,float[] lables){
        Node newborn = new Node(value,data,lables);
        children.add(newborn);
//        System.out.println("newchild add");
        this.isLeaf = false;
        return newborn;
    }
    public void display(){
        System.out.println("attribute "+ Main.attributes[featureIndex] + " value "+ value + " depth "+ depth + " isLeaf " + isLeaf + " children " + children.size());
//        if(sorted != null){
//            for(int i =0;i < sorted.length ; i++){
//                System.out.println(sorted[i]);
//            }
//        }
        for(int i = 0; i <data.length ;i++){
            System.out.print("data ");
            for(int j = 0 ; j< data[0].length;j++){
                System.out.print(data[i][j] + " ");
            }
            System.out.println(" label "+ lables[i]);
        }
    }

    public void display2(){
        System.out.print(" [ attribute "+ Main.attributes[featureIndex] + " value "+ value + " depth "+ depth + " isLeaf " + isLeaf + " children " + children.size());

    }

    public float getValue() {
        return value;
    }
}
