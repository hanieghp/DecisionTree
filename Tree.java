import java.util.Arrays;

public class Tree {
    int depth;
    Node root=null;
    MArrayList<Integer> maxd = new MArrayList<>();
    int count=0,count1=0,count2=0;
    public Tree(){
        this.depth = 0;
    }
    int getDepth(){
        return depth;
    }
    Node creatTree(float[][] data, float[] labels) {
        creatTree2(root,data,labels);
        return root;
    }
    private Node creatTree2(Node node,float[][] data,float[] lables){
        Node p = null;
//        if(node!=null && node.lables.length ==  2 && (node.lables[0]!=node.lables[1])){
//            float[][] kh2 = new float[1][ node.data[0].length] ;
//            float[][] kh3 = new float[1][ node.data[0].length] ;
//            for(int i=0 ; i< node.data[0].length ; i++){
//                kh2[0][i] = node.data[0][i];
//                kh3[0][i] = node.data[1][i];
//
//            }
//            float[] kh = new float[1];
//            kh[0] = node.lables[0];
//
//            node.addchild(-1,kh2,kh);
//            Node child =  node.children.get(0);
//            child.setDepth(node.depth+1);
//            maxd.add(child.depth);
//            Node child2 =  node.children.get(1);
//            child.setDepth(node.depth+1);
//            maxd.add(child.depth);
//            kh[0] = node.lables[1];
//            node.addchild(-1,kh3,kh);
//
//
//            return node;
//
//        }

        if(iGain(data,HighestInformationGain(data,lables),lables) > 0){

         p = new Node(HighestInformationGain(data,lables),data,lables);

            if(root == null){
                root = p;
                p.depth =0;
                maxd.add(0);
            }
            else{
                p.depth = node.depth;
            }

        System.out.println("F "+HighestInformationGain(data,lables));
        float[] sort = Sort(p.data, p.getFeatureIndex(),p.data.length);
        for (int i = 0 ; i < sort.length;i++){
            split(p,sort[i]);
            Node child =  p.children.get(i);
            child.setDepth(p.depth+1);
            maxd.add(child.depth);
            System.out.println("depth" + child.depth);
            if(Entropy(child.data, 0,-1, child.lables) > 0 && Entropy(child.data, 0,-1, child.lables) < 1){
                creatTree2(child,child.data,child.lables);
            }
        }}
        //display();
        return  p ;


    }
    public int findDepth(){
        int indexmax = maxd.get(0);
        for(int i = 1 ; i < maxd.size(); i++ ){
            if(maxd.get(i) >indexmax)
                indexmax = maxd.get(i);
        }
        return indexmax;

    }
    public Node getRoot() {
        return root;
    }
    void split(Node parent,float control){
        Node child1 = null;
        Node child2 = null;
        float[][] data = new float[parent.data.length][parent.data[0].length];
        float[] label = new float[parent.lables.length];
        int countchild = 0;
        for(int i = 0 ; i <parent.data.length; i++){
            if(parent.data[i][parent.getFeatureIndex()] == control ){
                data[countchild] =parent.data[i];

                label[countchild]=parent.lables[i];
                countchild++;
                }
            }

        float[][] tdata = new float[countchild][parent.data[0].length];
        float[] tlabel = new float[countchild];
        for(int i = 0 ; i<countchild;i++){
            tdata[i]=data[i];
            tlabel[i]=label[i];
        }
        if(tlabel.length == 2 && (tlabel[0] != tlabel[1])){
            float[][] kh2 = new float[1][ tdata[0].length] ;
            float[][] kh3 = new float[1][ tdata[0].length] ;
            for(int i=0 ; i< tdata[0].length ; i++){
                kh2[0][i] = tdata[0][i];
                kh3[0][i] = tdata[1][i];

            }
            float[] kh = new float[1];
            kh[0] = tlabel[0];

            parent.addchild(control,kh2,kh);
             child1 =parent.children.get(0);
            child1.setDepth(parent.depth+2);
            maxd.add(child1.depth);
//            System.out.println("control bargh : "+control + "data split " +Arrays.deepToString(child1.data));
//            for(int i=0; i<child1.lables.length ; i++){
//                System.out.println(tlabel[i]);
//            }
            kh[0] = tlabel[1];
            parent.addchild(control,kh3,kh);
             child2 =parent.children.get(1);
            maxd.add(child2.depth);
//            System.out.println("control bargh: "+control + "data split " +Arrays.deepToString(child2.data));
//            for(int i=0; i<child2.lables.length ; i++){
//                System.out.println(child2.lables[i]);
//            }
        }
        parent.addchild(control,tdata,tlabel);
        System.out.println("control : "+control + "data split " +Arrays.deepToString(tdata) + "attribute " + parent.children.get((int)control).getFeatureIndex());
        for(int i=0; i<tlabel.length ; i++){
            System.out.println(tlabel[i]);
        }
        if(child1 != null && child2!= null){
        System.out.println("control bargh : "+control + "data split " +Arrays.deepToString(child1.data) + "attribute: " + child1.getFeatureIndex());
        for(int i=0; i<child1.lables.length ; i++){
            System.out.println(child1.lables[i]);
        }
            System.out.println("control bargh: "+control + "data split " +Arrays.deepToString(child2.data)+ "attribute: " + child2.getFeatureIndex());
            for(int i=0; i<child2.lables.length ; i++){
                System.out.println(child2.lables[i]);
            }
    }}
    public void display() {
        displayRecursively(root, 0);
    }
    private void displayRecursively(Node node, int depth) {
        if (node != null) {
            //System.out.println("khkhkh"+ node.children.get(0));
            StringBuilder indentation = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                indentation.append("  ");
            }
            int featureIndex = node.getFeatureIndex();
            float featureValue = node.getValue();

            System.out.println(indentation + " Feature: " + featureIndex + ", Value: " + featureValue+
                    "  att: " + Main.attributes[featureIndex]);
            for (int i = 0; i < node.children.size(); i++) {
                System.out.println("bache");
                displayRecursively(node.children.get(i), depth + 1);
            }
        }
    }
   public int HighestInformationGain(float[][] data,float[] labels){
        int indexmax = 0;
        float igain0 = iGain(data,0,labels);
        for(int i = 1 ; i < data[0].length; i++ ){
            float igain = iGain(data,i,labels);
            if(igain >igain0)
                indexmax = i;
        }
        return indexmax;
    }
    float Entropy(float[][] data,int columeofintrest,float control, float[] labels ) {
        count=0;count1=0;count2=0;
        float[] arr2 = new float[data.length];
        for(int i =0;i < data.length;i++) {
            arr2[i] = data[i][columeofintrest];
        }
        for(int i = 0 ; i <arr2.length; i++){
            if(arr2[i] == control || control == -1){
                switch ((int) labels[i]){
                    case 0:
                        count++;
                        break;
                    case 1:
                        count1++;
                        break;
                    case 2:
                        count2++;
                        break;
                }
            }
        }
        int total = count + count1 + count2;
        float p0,p1,p2;
        p0 = (float) count / total;
        p1 = (float) count1 / total;
        p2 = (float) count2 / total;
        if (p0 == 0) p0 = 1;
        if (p1 == 0) p1 = 1;
        if (p2 == 0) p2 = 1;
        float E = (float) -(p0*(Math.log10(p0)/Math.log10(2))+(p1*(Math.log10(p1)/Math.log10(2)))+(p2*(Math.log10(p2)/Math.log10(2))));
        if(E == (-0.0)) E=0;
        return E;
    }
    float iGain(float[][] data,int columeofintrest,float[] lables) {
        float tEntropy = Entropy(data, columeofintrest, -1, lables);
        float[] children = Sort(data, columeofintrest, data.length);
        float[] entropys = new float[children.length];
        float w,E = 0 ;
        for (int i = 0; i < children.length; i++) {
            entropys[i]= Entropy(data, columeofintrest, children[i], lables);
            w = (float)(count + count1 + count2) / lables.length;
            if (w == 0) w = 1;
            E += (float) (w*entropys[i]);
        }
        float infoGain = tEntropy - E;
        if(infoGain == (-0.0)) infoGain=0;
//        System.out.println(infoGain);
        return infoGain;

    }
    float[] Sort(float[][] arr,int columeofintrest, int n){
        float[] arr2 = new float[arr.length];
        for(int i =0;i < arr.length;i++) {
            arr2[i] = arr[i][columeofintrest];
        }
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr2[j] > arr2[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = (int) arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
//        for(int p =0;p < arr2.length;p++) {
//            System.out.println(arr2[p]);
//        }
        if( n == 0 || n ==1)
            return arr2;
        float[] kh = new float[n];
        int k = 0;
        for(int z = 0; z < n - 1; z++){
            if (arr2[z] != arr2[z + 1])
                kh[k++] = arr2[z];
        }
        kh[k++] = arr2[n -1];
        float[] arr3 = new float[k];
        for(int z = 0 ; z < k;z++)
            arr3[z]=kh[z];
        return arr3;
    }


}

