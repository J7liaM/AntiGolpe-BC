 package VIEW;

import DAO.UsuarioDAO;
import DTO.Usuario;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.MaskFormatter;

public class TelaCadastro1 extends JFrame {

    private JTextField txtTitulo, txtNome, txtEmail;
    private JFormattedTextField txtCPF, txtTelefone;
    private JButton btnEnviar, btnLimpar, btnFechar;
    private JButton btnAcessoBanco;
    private int id;

    public TelaCadastro1() {
        setTitle("AntiGolpes BC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal com fundo cinza claro
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Fonte e cores
        Font fonte = new Font("Arial", Font.PLAIN, 14);
        JLabel lblTitulo = new JLabel("Bem vindos ao AntiGolpe do Banco Central");
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCPF = new JLabel("CPF:");
        JLabel lblEmail = new JLabel("E-mail:");
        JLabel lblTelefone = new JLabel("Telefone:");

        for (JLabel lbl : new JLabel[]{lblTitulo, lblNome, lblCPF, lblEmail, lblTelefone}) {
            lbl.setForeground(Color.BLACK);
            lbl.setFont(fonte);
        }

        txtNome = new JTextField(20);
        txtCPF = CampoFormatado("###.###.###-##");
        txtEmail = new JTextField(20);
        txtTelefone = CampoFormatado("(##)#####-####");

        btnEnviar = new JButton("Enviar");
        btnLimpar = new JButton("Limpar");
        btnFechar = new JButton("Fechar");
        btnAcessoBanco = new JButton("Acesso ao Banco");



    

        // Estilo padrão, sem borda branca personalizada
        Font fonteBotao = new Font("Arial", Font.BOLD, 13);
        btnEnviar.setFont(fonteBotao);
        btnLimpar.setFont(fonteBotao);
        btnFechar.setFont(fonteBotao);
        btnAcessoBanco.setFont(fonteBotao);
        btnAcessoBanco.setBackground(Color.DARK_GRAY);
        btnAcessoBanco.setForeground(Color.WHITE);

        // Adicionando componentes
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(btnAcessoBanco, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(lblTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblNome, gbc);
        gbc.gridx = 1;
        panel.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblCPF, gbc);
        gbc.gridx = 1;
        panel.add(txtCPF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblEmail, gbc);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblTelefone, gbc);
        gbc.gridx = 1;
        panel.add(txtTelefone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(btnLimpar, gbc);
        gbc.gridx = 1;
        panel.add(btnEnviar, gbc);

        add(panel, BorderLayout.CENTER);
        add(btnFechar, BorderLayout.SOUTH);

        // Ações
        btnEnviar.addActionListener(e -> enviarDados());
        btnLimpar.addActionListener(e -> limparCampos());
        btnFechar.addActionListener(e -> dispose());
        btnAcessoBanco.addActionListener(e -> {
        TelaLoginBanco loginBanco = new TelaLoginBanco();
        loginBanco.setVisible(true);
    });

    }

private void enviarDados() {
    if (txtNome.getText().isEmpty() || txtCPF.getText().contains("_")
            || txtEmail.getText().isEmpty() || txtTelefone.getText().contains("_")) {
        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
    } else {
        String nome = txtNome.getText().trim();
        String cpf = txtCPF.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        // Primeiro cria o usuário sem ID
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);

        try {
            UsuarioDAO dao = new UsuarioDAO();
            int idGerado = dao.cadastrarUsuario(usuario); // ← pega o ID do banco

            if (idGerado != -1) {
                usuario.setId(idGerado); // ← define o ID no objeto

                JOptionPane.showMessageDialog(
                        this,
                        "<html><body style='font-size:12px;'>Dados enviados com sucesso!</body></html>",
                        "Sucesso!",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Agora pode abrir a tela de relatos passando o usuário
                TelaRelatos2 tela = new TelaRelatos2(usuario.getId()); 
                tela.setVisible(true);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Erro ao obter o ID do usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar dados no banco de dados:\n" + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}



    private void limparCampos() {
        txtNome.setText("");
        txtCPF.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
    }

    public static void main(String[] args) {

        try {
            // Estilo Metal do Java
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new TelaCadastro1().setVisible(true);
        });
    }

    //Para formatar o cpf e telefone
    private JFormattedTextField CampoFormatado(String mascara) {
        try {
            MaskFormatter formatter = new MaskFormatter(mascara);
            formatter.setPlaceholderCharacter('_');
            return new JFormattedTextField(formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return new JFormattedTextField();
        }
    }
}
