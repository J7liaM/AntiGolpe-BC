package DAO;

import DTO.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class UsuarioDAO {
    
    public int cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, cpf, email, telefone) VALUES (?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection conn = ConexaoDAO.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1); // pega o ID gerado
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGerado;
    }
}
