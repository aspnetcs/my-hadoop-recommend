package recommendation;

import java.io.*;
import java.util.*;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
 
public class CopyOfGenericUserBasedRecommender2 {
	
	static File file=new File("filepath.properties");
	final static Properties properties=new Properties();
	
	public static String getMovieName(String movieid)
	{
		String movieName=new String();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
		
		properties.load(inputStream);
		String fileName=properties.getProperty("newTitle");
		
		
		BufferedReader in=new BufferedReader(new FileReader(fileName));
		String line="";
		while((line=in.readLine())!=null)
		{
			String title[]=line.split(",");
			if(title.length>2)
			{
				if(title[0].equals(movieid))
				{
					
					//builder.append("Movie Name:"+title[1]+"\t");
					movieName=title[1]+"\t";
					
					break;
				}
			}
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movieName;
	}
 
  public static StringBuilder main(String args) throws Exception {
      // Create a data source from the CSV file
      File userPreferencesFile = new File("input/ratings.csv");
      DataModel dataModel = new  FileDataModel(userPreferencesFile);
      
      UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
      UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(2, userSimilarity, dataModel);
     // ClusterSimilarity clusterSimilarity =new FarthestNeighborClusterSimilarity(userSimilarity);
      // Create a generic user based recommender with the dataModel, the userNeighborhood and the userSimilarity
      Recommender genericRecommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
    		  //
     
     
      // Recommend 5 items for each user
   
          long userId =Long.parseLong(args) ;
 
          StringBuilder sb=new StringBuilder();
          // Generate a list of 5 recommendations for the user
          List<RecommendedItem> itemRecommendations = genericRecommender.recommend(userId, 5);
 
          System.out.format("User Id: %d%n", userId);
          sb.append("User Id: "+userId+"\n");
 
          if (itemRecommendations.isEmpty())
          {
              System.out.println("No recommendations for this user.");
          }
          else
          {
              // Display the list of recommendations
              for (RecommendedItem recommendedItem : itemRecommendations)
              {
                  System.out.format("Recommened Item Id %d. Strength of the preference: %f%n", recommendedItem.getItemID(), recommendedItem.getValue());
                  sb.append("Recommened Item Id  "+recommendedItem.getItemID()+" \t Movie Name  :"+getMovieName(Long.toString(recommendedItem.getItemID()))+"  Strength of the preference:"+recommendedItem.getValue()+"\n");
              }
          }
		return sb;
      
  }
}