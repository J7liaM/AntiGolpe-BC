package VIEW;

import DAO.ConexaoDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaLoginBanco extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnCancelar;

    public TelaLoginBanco() {
        setTitle("Login do Banco");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // Painel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Componentes
        JLabel lblUsuario = new JLabel("Login:");
        JLabel lblSenha = new JLabel("Senha:");

        txtLogin = new JTextField(15);
        txtSenha = new JPasswordField(15);

        btnEntrar = new JButton("Entrar");
        btnCancelar = new JButton("Cancelar");

        // Adicionando componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panel.add(txtLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblSenha, gbc);
        gbc.gridx = 1;
        panel.add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(btnCancelar, gbc);
        gbc.gridx = 1;
        panel.add(btnEntrar, gbc);

        add(panel);

        // Ação do botão Cancelar
        btnCancelar.addActionListener(e -> dispose());

        // Ação do botão Entrar
        btnEntrar.addActionListener(e -> autenticar());

        // Pressionar ENTER no campo senha aciona o login
        txtSenha.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    autenticar();
                }
            }
        });
    }

    private void autenticar() {
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexaoDAO.conectar()) {
            String sql = "SELECT * FROM login_banco WHERE login = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Autenticado", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new TelaUsuarios().setVisible(true);  // <- Tela que deve mostrar usuários e relatos
            } else {
                JOptionPane.showMessageDialog(this, "Login ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro na autenticação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
