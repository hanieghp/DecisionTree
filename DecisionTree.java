import java.io.FileNotFoundException;
public class DecisionTree {

    private float[][] data;
    private float[] labels;
    private int depth;
    Tree kh;
    float[] test;
    public Node root ;

    public DecisionTree(float[][] data, float[] labels) throws FileNotFoundException {
        kh = new Tree();
        kh.createTree(data,labels);
        root = kh.getRoot();
        this.data = data;
        this.labels = labels;
    }
    float Predict(float[] dataTest, int depth){
        Node node = root;
        System.out.println();
        while(!node.isLeaf && node != null){
            Node p = new Node(node.getFeatureIndex(),node.data,node.lables);
            for(int i=0;i< node.children.size() ;i++){
                //System.out.println("node" + node.getFeatureIndex() + "kh "+node.sorted[i] + "in" +dataTest[node.getFeatureIndex()]);

                if(dataTest[node.getFeatureIndex()] == node.sorted[i]){
                    node = node.children.get(i);
                    break;
                }
            }
            if(p.data == node.data && p.lables == node.lables){
                node = node.children.get(0);

            }
            if(node.children.size() == 0)
                break;
            //System.out.println("gir");
            //node.display();
        }
        //System.out.println("kh" + node.getOutcome());
        return node.getOutcome();
    }
    float[] PredictAll(float[][] dataTest, int depth){
        test= new float[dataTest.length];
        for(int i=0;i<dataTest.length ; i++){
            test[i] = Predict(dataTest[i],0);
            //System.out.println(test[i]);
        }
       // System.out.println("m");
//        for(int i=0;i<test.length ; i++){
//            System.out.println(test[i]);
//        }
        return test;
    }
    float accuracy(float[] labels, float[] labels_predicted){
        System.out.println("tosham");
        int count = 0;
        for(int i=0;i<labels.length ; i++){
            System.out.println("true" + labels[i] + "perdicted" + labels_predicted[i]);
            if(labels[i]== labels_predicted[i])
                count++;
        }

        return (float) count/labels.length * 100;
    }

    public void Traverse(Node root) throws Exception {
        if (root == null)
            return;
        MyQueue q = new MyQueue();
        q.add(root);
        while (!q.isEmpty())
        {
            int n = q.size();

            while (n > 0)
            {
                Node p = q.peek();
                q.remove();
                //if(p.getFeatureIndex() == 15)
                p.display();
                if(p.isLeaf)
                    System.out.println("out " +p.getOutcome());
                for (int i = 0; i < p.children.size(); i++)
                    q.add(p.children.get(i));
                n--;
            }
            System.out.println();
        }
    }

}