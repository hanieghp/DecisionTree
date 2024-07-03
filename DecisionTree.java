import com.sun.source.tree.DefaultCaseLabelTree;

import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

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
        while(!node.isLeaf && node != null){
            if(node.depth == depth -1){
                for(int i=0;i< node.children.size() ;i++){
                    if(dataTest[node.getFeatureIndex()] == node.sorted[i]){
                        node = node.children.get(i);
                        if(node.isLeaf)
                            return node.getOutcome();
                    }
                }
                return node.children.get(0).getOutcome();

            }
            Node p = new Node(node.getFeatureIndex(),node.data,node.lables);
            for(int i=0;i< node.children.size() ;i++){
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
        }
        //System.out.println("kh" + node.getOutcome());
        return node.getOutcome();
    }
    float[] PredictAll(float[][] dataTest, int depth){
        test= new float[dataTest.length];
        for(int i=0;i<dataTest.length ; i++){
            test[i] = Predict(dataTest[i],depth);
            //System.out.println(test[i]);
        }
       // System.out.println("m");
//        for(int i=0;i<test.length ; i++){
//            System.out.println(test[i]);
//        }
        return test;
    }
    float accuracy(float[] labels, float[] labels_predicted){
        int count = 0;
        for(int i=0;i<labels.length ; i++){
//            System.out.println("true" + labels[i] + "perdicted" + labels_predicted[i]);
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
    public void Traverse2(Node root) throws Exception {
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
                p.display2();
                if(p.isLeaf)
                    System.out.print(" out " +p.getOutcome() );
                System.out.print(" ] ");
                for (int i = 0; i < p.children.size(); i++)
                    q.add(p.children.get(i));
                n--;
            }
            System.out.println();
        }
    }
    public void Traverse3(Node root) throws Exception {
        JFrame frame = new JFrame("kh");
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("root");
       Traverse4(node,root);
        JTree tree=  new  JTree(node);
        frame.add(tree);
        frame.setSize(700,500);
        frame.setVisible(true);
//        MyQueue q = new MyQueue();
//        q.add(root);
//        while (!q.isEmpty())
//        {
//            int n = q.size();
//            while (n > 0)
//            {
//                Node p = q.peek();
//                q.remove();
//                p.display2();
//                if(p.isLeaf)
//                    System.out.print(" out " +p.getOutcome() );
//                System.out.print(" ] ");
//                for (int i = 0; i < p.children.size(); i++)
//                    q.add(p.children.get(i));
//                n--;
//            }
//            System.out.println();
    }
    public void Traverse4(DefaultMutableTreeNode node,Node root) {
        DefaultMutableTreeNode newnode = null;
        if(root.isLeaf){
             newnode = new DefaultMutableTreeNode( root.getOutcome());

        }
        else{
             newnode = new DefaultMutableTreeNode("attribute" + Main.attributes[root.getFeatureIndex()]);
        }
        node.add(newnode);
        for (int i = 0; i < root.children.size(); i++) {
            if(root.children.get(i) != null)
            Traverse4(newnode,root.children.get(i));
        }
    }
}
