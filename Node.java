public class Node {
    private int featureIndex;
    private float threshold;
    MArrayList<Node> children = new MArrayList<>();
    private float infoGain;
    boolean isLeaf = false;
    private float value;

    public void setDepth(int depth) {
        this.depth = depth;
    }

    float[][] data;
    float[] lables;
    int depth=0;

    public Node(int featureIndex,float[][] data,float[] lables) {
        this.featureIndex = featureIndex;
         value = -1;
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

    public float getThreshold() {
        return threshold;
    }

    public Node addchild(float kh){
        Node newborn = new Node(kh);
        children.add(newborn);
        return newborn;
    }
    public Node addchild(float kh,float[][] data,float[] lables){
        Node newborn = new Node(featureIndex,data,lables,kh);
        children.add(newborn);
        System.out.println("newchild add");
        return newborn;
    }
    public float getInfoGain() {
        return infoGain;
    }

    public float getValue() {
        return value;
    }
}