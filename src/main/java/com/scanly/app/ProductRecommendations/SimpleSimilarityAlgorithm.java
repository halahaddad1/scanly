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

    private int[][] userProductsMatrix;


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
        double[][] distance = new double[this.userProductsMatrix[0].length][this.userProductsMatrix[0].length];

        for (int coli = 0; coli < this.userProductsMatrix[0].length; coli++) {
            //for (int coli = 0; coli < this.productUserMatrix[rowi].length; coli++) {
                distance[coli][coli]=0;
                for (int colj = coli+1; colj < this.userProductsMatrix[0].length; colj++) {
                    double d = this.computeDistance(coli, colj);
                    distance[coli][colj] = d;
                    distance[coli][colj] = d;
                }
        }
        return distance;
    }

    public double computeDistance(int col_i,int col_j){
        double sum=0;
        for(int i=0; i<this.userProductsMatrix.length; i++){
            sum += Math.pow(this.userProductsMatrix[i][col_i], 2) + Math.pow(this.userProductsMatrix[i][col_j], 2);
        }
        return Math.sqrt(sum);
    }
}
