import java.util.Arrays;

public class Tree {
    int depth;
    Node root=null;
    float[][] families;
    int count=0,count1=0,count2=0;
    public Tree(){
        this.depth = 0;
    }
    int getDepth(){
        return depth;
    }
    Node creatTree(float[][] data, float[] labels) {
        Node p =new Node(HighestInformationGain(data, labels), data, labels);
        if (iGain(data,p.getFeatureIndex(),labels) > 0) {

            if (root == null) {
                root = p;
                float[] sort = Sort(data, root.getFeatureIndex(), data.length);
                for (int i = 0; i < sort.length; i++) {
                    split(p, sort[i]);
                    display(p);
                    creatTree(root.children.get(i).data,root.children.get(i).lables);
                }
              }
            else{
                float[] sort = Sort(data, p.getFeatureIndex(), data.length);
                for (int i = 0; i < sort.length; i++) {
                    split(p, sort[i]);
                    display(p);
                    creatTree(p.children.get(i).data,p.children.get(i).lables);
                }
            }

        }
        display(p);
        return null;
    }
    public void display(Node root){
        if(root != null){
           root.display(root);
        }
    }
    void split(Node parent,float control){
        float[][] data = parent.data;
        float[] label = parent.lables;
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
        parent.addchild(control,tdata,tlabel);
//        for(int i=0; i<tlabel.length ; i++){
//            System.out.println(tlabel[i]);
//        }
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
        float E = (float) ((-(p0*Math.log(p0)))+(-(p1*Math.log(p1)))+(-(p2*Math.log(p2))));
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
//        System.out.println(infoGain);
        return infoGain;

    }

//    float iGain(int pEntropy,float[] weight, float[] entropies){
//
//        return 0;
//    }

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

