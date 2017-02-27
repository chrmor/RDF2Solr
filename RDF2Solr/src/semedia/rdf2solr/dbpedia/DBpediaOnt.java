package semedia.rdf2solr.dbpedia;

import java.io.File;
import java.io.IOException;

import org.openrdf.model.ValueFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.memory.MemoryStore;

public class DBpediaOnt {
	
	static RepositoryConnection connection;
	static ValueFactory vf;
	static final String DBpedia_Ont_Namespace = "http://dbpedia.org/ontology/";
	
	static void init() throws RepositoryException, RDFParseException, IOException {
		
		Repository repo = new SailRepository(new MemoryStore());
		repo.initialize();
		connection = repo.getConnection();
		vf = connection.getValueFactory();
		connection.add(new File("files/dbpedia_2014.owl"), "", RDFFormat.RDFXML, vf.createURI(DBpedia_Ont_Namespace));
		
	}
	
	public static RepositoryConnection getConnection() {
		if (connection == null) {
			try {
				init();
			} catch (RepositoryException | RDFParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	static void close() throws RepositoryException {
		connection.close();
	}
	
	

}
