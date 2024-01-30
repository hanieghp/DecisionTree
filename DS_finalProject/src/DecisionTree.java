class DecisionTree {

    private float[][] data;
    private float[] labels;
    private int depth;

    public DecisionTree(float[][] data, float[] labels) {
        this.data = data;
        this.labels = labels;
    }
    float Predict(float[][] data, int depth){
        return 0;
    }
    float accuracy(int[] labels, int[] labels_predicted){
        return 0;
    }
}
class Node {
    Node left;
    Node right;
    double infoGain;
    double value;
    int featureIndex;
    double threshold;

    public Node(int featureIndex, double threshold, Node left, Node right, double infoGain) {
        this.featureIndex = featureIndex;
        this.threshold = threshold;
        this.left = left;
        this.right = right;
        this.infoGain = infoGain;
    }

    public Node(double value) {
        this.value = value;
    }

    public int getFeatureIndex() {
        return featureIndex;
    }

    public double getThreshold() {
        return threshold;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public double getInfoGain() {
        return infoGain;
    }

    public double getValue() {
        return value;
    }
}