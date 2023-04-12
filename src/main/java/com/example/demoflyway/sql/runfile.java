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
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class runfile{
   @Autowired
    private DataSource dataSource;


    @Transactional
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
               String regex = "/db/migration/"+undo[i-2];
               Resource resource = new ClassPathResource(regex);
               ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
               DatabasePopulatorUtils.execute(databasePopulator, dataSource);

               Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fly","postgres","admin");
               String sql = "Insert Into undo_schema_history (version,date) VALUES (?,CAST(CURRENT_DATE AS DATE));";
               String sql2 = "Delete From flyway_schema_history where version = ?;";
               PreparedStatement stmt2 = conn.prepareStatement(sql2);
               PreparedStatement stmt = conn.prepareStatement(sql);
               stmt.setInt(1,i);
               stmt.executeUpdate();
               stmt.close();

               stmt2.setString(1,String.valueOf(i));
               stmt2.executeUpdate();
               stmt2.close();


               conn.close();

           }

       }

}




