package repository;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Properties;

public class PropertiesLoader {
    public static final Properties properties = new Properties();
    public static void loadingProperties(){
        try (BufferedReader reader = new BufferedReader(new FileReader("application.properties"))){
            properties.load(reader);
        }catch (Exception exception){
            System.out.println("Problem during loading properties: " + exception.getMessage());
        }
    }
}