
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    
   private static final String URL = "jdbc:mysql://localhost:3306/antigolpes_db"; 
    private static final String USUARIO = "root"; 
    private static final String SENHA = "root";  

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro na conexão com o banco de dados");
        }
    }
} 