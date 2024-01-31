
public class Tree {
    int depth;
    int count=0,count1=0,count2=0;
    int getDepth(){
        return depth;
    }
//    createTree(float[][] data, float[] labels){
//
//    }
    double Entropy(float[] labels) {
        count(labels);
        float p0,p1,p2;
        p0 = (float) count / labels.length;
        p1 = (float) count1 / labels.length;
        p2 = (float) count2 / labels.length;
        if (p0 == 0) p0 = 1;
        if (p1 == 0) p1 = 1;
        if (p2 == 0) p2 = 1;
        double E = (-(p0*Math.log10(p0)))+(-(p1*Math.log10(p1)))+(-(p2*Math.log10(p2)));
        return E;
    }
    float iGain(int pEntropy,float[] weight, float[] entropies){
        return 0;
    }

    public void count(float[] labels){

        for(int i=0;i<labels.length ; i++){
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
//            if(labels[i] == 0){
//                count++;
//            }
//            else if(labels[i] == 1){
//                count1++;
//            }
//            else if(labels[i] == 2){
//
//            }
        }
    }
}

