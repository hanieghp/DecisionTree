public class Node {
    MArrayList aaraylist = new MArrayList<>();
    private Integer featureIndex;
    private Double threshold;
    private Node left;
    private Node right;
    private Double infoGain;
    private Double value;

    public Node(Integer featureIndex, Double threshold, Node left, Node right, Double infoGain) {
        this.featureIndex = featureIndex;
        this.threshold = threshold;
        this.left = left;
        this.right = right;
        this.infoGain = infoGain;
    }

    public Node(Double value) {
        this.value = value;
    }

    public Integer getFeatureIndex() {
        return featureIndex;
    }

    public Double getThreshold() {
        return threshold;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Double getInfoGain() {
        return infoGain;
    }

    public Double getValue() {
        return value;
    }
}