package DAO;

import DTO.Relatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class RelatorioDAO {
    public void cadastrarRelatorio(Relatorio relatorio) {
        String sql = "INSERT INTO relatorio (data, hora, tipo_contato, tipo_mensagem, descricao, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(relatorio.getData()));
            stmt.setTime(2, java.sql.Time.valueOf(relatorio.getHora()));
            stmt.setString(3, relatorio.getTipoContato());

            String mensagemFormatada = String.join(", ", relatorio.getTipoMensagem());
            stmt.setString(4, mensagemFormatada);

            stmt.setString(5, relatorio.getDescricao());
            
            stmt.setInt(6, relatorio.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Relatório cadastrado com sucesso!");
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dado = rs.getString("campo"); 
}


        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar relatório: " + e.getMessage());
        }
    }
}
