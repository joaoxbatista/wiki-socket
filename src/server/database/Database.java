package server.database;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Database {
    
    private static ObjectContainer instance = null;
    
    //Singleton Pattern
    public static ObjectContainer getInstance(){
        if(Database.instance == null)
        {
        	Database.instance = Db4oEmbedded.openFile("Banco");
        }
        
//        System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        return Database.instance;
    }
    
    //Close connection and clear
    public static void close(){
    	Database.instance.close();
    	Database.instance = null;
//        System.out.println("Conexão com o banco de dados finalizada!");
    }
    
}