import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;


public class NEO4J {

	public static void main (String[] args) throws Exception{	 
		
		
		   insertion();
				 
	}
	//
	public static void insertion(){
		//connexion à la BDD
		Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic("neo4j", "46wjxxpc"), Config.build()
		        .withEncryptionLevel( Config.EncryptionLevel.REQUIRED )
		        .withTrustStrategy( Config.TrustStrategy.trustOnFirstUse( new File( "/path/to/neo4j_known_hosts" ) ) )
		        .toConfig() );
	Session session = driver.session();
	
		//ouverture du fichier destiné à NEO4J
		String fichier ="FichierNEO.txt";
		System.out.println("Debut de l'insertion");
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				if(ligne.length() != 0){
					session.run( ligne );
				}
			}
			br.close(); 
			System.out.println("Insertion effectuée");
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		session.close();
		driver.close();
	}
	
	
}
