package com.example.demoflyway.sql;
import com.example.demoflyway.model.fileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import java.sql.*;
import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class runfile{
   @Autowired
    private DataSource dataSource;



    public void attem(int did) throws Exception {
        int id =0;
        try{
            //Class.forName(org.postgresql.Driver);
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fly","postgres","admin");
            String sql = "Select version from flyway_schema_history order by installed_rank desc limit 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            id = rs.getInt("version");
            rs.close();
            stmt.close();
            conn.close();
        } catch ( Exception ex){
            ex.printStackTrace();
        }

        String[] undo = fileName.getFiles();
           for(int i=id;i>did;i--){
               //System.out.println(id);
               String regex = "/db/migration/"+undo[i-2];
               Resource resource = new ClassPathResource(regex);
               ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
               DatabasePopulatorUtils.execute(databasePopulator, dataSource);
           }

       }

}




