package com.scanly.app.ProductRecommendations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.Math;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SimpleSimilarityAlgorithm {

    private int[][] productUserMatrix;


//      public double[][] calculateSimilarityViaHash() {

//          for (HashMap productRow : productUser) {
//              Set set = productRow.entrySet();
//              Iterator iterator = set.iterator();
//              while (iterator.hasNext()) {
//                  Map.Entry mentry = (Map.Entry) iterator.next();
//                  List<int> row= mentry.getValue();
//                  System.out.print("key is: " + mentry.getKey() + " & Value is: ");
//                  System.out.println(mentry.getValue());
//              }
//          }
//          HashMap<String, HashMap<String, Double>> distance = new HashMap<String, HashMap<String, Double>>();
//        String[] product_ids = this.productUser.keySet().toArray(new String[this.productUser.size()]);
//        for (int i = 0; i < product_ids.length; i++) {
//            distance[i][i]=0;
//        }

        public double[][] calculateSimilarity(){
        double[][] distance = new double[this.productUserMatrix.length][this.productUserMatrix.length];

        for (int rowi = 0; rowi < this.productUserMatrix.length; rowi++) {
            //for (int coli = 0; coli < this.productUserMatrix[rowi].length; coli++) {
                distance[rowi][rowi]=0;
                for (int rowj = rowi+1; rowj < this.productUserMatrix.length; rowj++) {
                    double d = this.computeDistance(rowi, rowj);
                    distance[rowi][rowj] = d;
                    distance[rowi][rowj] = d;
                }
        }
        return distance;
    }

    public double computeDistance(int row_i,int row_j){
        double sum=0;
        for(int i=0; i<this.productUserMatrix[0].length; i++){
            sum += Math.pow(this.productUserMatrix[row_i][i], 2) + Math.pow(this.productUserMatrix[row_j][i], 2);
        }
        return Math.sqrt(sum);
    }
}
