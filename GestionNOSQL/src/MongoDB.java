import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class MongoDB {

	public static void main(String[] args) throws IOException {
		try{
			
			@SuppressWarnings("deprecation")
			Mongo mongo = new Mongo("localhost", 27017 );
			@SuppressWarnings("deprecation")
			DB db = mongo.getDB("Test1");

			DBCollection collection = db.getCollection("test");
			System.out.println("hell");
			String fichier ="FichierJson.json";
    		
			//lecture du fichier texte	
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			 BufferedReader br = new BufferedReader(ipsr);
			 DBObject dbObject;
			 String ligne;
			 System.out.println("Debut de l'insertion");
			 long startTime = System.currentTimeMillis();

			 try {
				 while ((ligne=br.readLine())!=null){
					 dbObject = (DBObject)JSON.parse(ligne);
	
						collection.insert(dbObject);
				 }  
	            } finally {
	                br.close();
	            }
			 System.out.println("Insertion effectuée");
			 long endTime = System.currentTimeMillis();
			 System.out.println("Temps total d'execution de l'insertion :"+ (endTime-startTime) +"ms");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		    } catch (MongoException e) {
			e.printStackTrace();
		    }
	}

}

