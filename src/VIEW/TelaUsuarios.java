package VIEW;

import DAO.ConexaoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TelaUsuarios extends JFrame {

    private JTable tabelaUsuarios;
    private DefaultTableModel modelo;
    private JTextArea painelRelato;

    public TelaUsuarios() {
        setTitle("Usuários Cadastrados");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Email", "CPF", "Telefone"}, 0);
        tabelaUsuarios = new JTable(modelo);
        JScrollPane scrollTabela = new JScrollPane(tabelaUsuarios);

        painelRelato = new JTextArea(8, 40);
        painelRelato.setEditable(false);
        painelRelato.setBorder(BorderFactory.createTitledBorder("Relato do Usuário"));
        JScrollPane scrollRelato = new JScrollPane(painelRelato);

        add(scrollTabela, BorderLayout.CENTER);
        add(scrollRelato, BorderLayout.SOUTH);

        carregarUsuarios();

        // Evento de clique em uma linha da tabela
        tabelaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabelaUsuarios.getSelectedRow() != -1) {
                int idUsuario = (int) tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 0);
                carregarRelato(idUsuario);
            }
        });
    }

    private void carregarUsuarios() {
        try (Connection conn = ConexaoDAO.conectar()) {
            String sql = "SELECT * FROM usuarios";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");

                modelo.addRow(new Object[]{id, nome, email, cpf, telefone});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários: " + e.getMessage());
        }
    }

    private void carregarRelato(int idUsuario) {
        Connection conn = ConexaoDAO.conectar();
        try {
            String sql = "SELECT * FROM relatorio WHERE id_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Data: ").append(rs.getDate("data")).append("\n");
                sb.append("Hora: ").append(rs.getTime("hora")).append("\n");
                sb.append("Tipo de Contato: ").append(rs.getString("tipo_contato")).append("\n");
                sb.append("Assunto: ").append(rs.getString("tipo_mensagem")).append("\n");
                sb.append("Descrição:\n").append(rs.getString("descricao"));

                painelRelato.setText(sb.toString());
            } 
            else {
                painelRelato.setText("Nenhum relatório cadastrado para este usuário.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar relato: " + e.getMessage());
        }
    }
}

