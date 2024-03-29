package recommendation;
import java.io.*;
import java.util.*;
 
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
 
public class GenericUserbasedRecommender1 {
 
  public static void main(String[] args) throws Exception {
      // Create a data source from the CSV file
      File userPreferencesFile = new File("input/ratings.csv");
      DataModel dataModel = new FileDataModel(userPreferencesFile);
      
      UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
      UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(2, userSimilarity, dataModel);
 
      // Create a generic user based recommender with the dataModel, the userNeighborhood and the userSimilarity
      Recommender genericRecommender =  new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
 
      // Generate a list of 3 recommended items for user 1001
      List<RecommendedItem> itemRecommendations = genericRecommender.recommend(1025,5);
      System.out.println("Started");
      // Display the item recommendations generated by the recommendation engine
      for (RecommendedItem recommendedItem : itemRecommendations) {
          System.out.println(recommendedItem);
      }
      System.out.println("finished");
  }
}