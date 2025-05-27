package VIEW;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class TelaRelatos2 extends JFrame {
    private JButton btnFechar, btnEnviar, btnLimpar;
    private JTextField txtData, txtHora;
    private JCheckBox cbWhatsapp, cbLigacao, cbEmail, cbSMS;
    private JComboBox<String> cmbAssunto;
    private JTextArea txtDescricao;

    public TelaRelatos2() {
        setTitle("AntiGolpe BC");
        setSize(550, 440);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("AntiGolpe BC");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panel.add(lblTitulo, gbc);

        // Data e hora do ocorrido
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Data e hora do ocorrido"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        txtData = new JTextField("Data");
        panel.add(txtData, gbc);

        gbc.gridx = 1;
        txtHora = new JTextField("Hora");
        panel.add(txtHora, gbc);

        // Tipo de contato
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        panel.add(new JLabel("Tipo de contato"), gbc);

        cbWhatsapp = new JCheckBox("Whatsapp");
        cbLigacao = new JCheckBox("Ligação");
        cbEmail = new JCheckBox("E-mail");
        cbSMS = new JCheckBox("SMS");

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        panel.add(cbWhatsapp, gbc);
        gbc.gridx = 1;
        panel.add(cbLigacao, gbc);
        gbc.gridx = 2;
        panel.add(cbEmail, gbc);
        gbc.gridx = 3;
        panel.add(cbSMS, gbc);

        // Assunto
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        panel.add(new JLabel("Assunto"), gbc);

        gbc.gridy = 6;
        String[] assuntos = { "Valores a receber", "Pix", "Cartão de crédito", "Golpe financeiro" };
        cmbAssunto = new JComboBox<>(assuntos);
        panel.add(cmbAssunto, gbc);

        // Descrição
        gbc.gridy = 7;
        panel.add(new JLabel("Faça uma breve descrição do ocorrido"), gbc);

        gbc.gridy = 8;
        txtDescricao = new JTextArea(5, 40);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtDescricao);
        panel.add(scrollPane, gbc);

        // Botões
        btnLimpar = new JButton("Limpar");
        btnEnviar = new JButton("Enviar");
        btnFechar = new JButton("Fechar");

        btnLimpar.setBackground(Color.BLUE);
        btnLimpar.setForeground(Color.WHITE);
        btnEnviar.setBackground(Color.BLUE);
        btnEnviar.setForeground(Color.WHITE);
        btnFechar.setBackground(Color.BLUE);
        btnFechar.setForeground(Color.WHITE);

        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        panel.add(btnFechar, gbc);

        gbc.gridx = 1;
        panel.add(btnLimpar, gbc);

        gbc.gridx = 2;
        panel.add(btnEnviar, gbc);

        add(panel);

        // Ações
        btnFechar.addActionListener(e -> dispose());
        btnLimpar.addActionListener(e -> limparCampos());
        btnEnviar.addActionListener(e -> enviarFormulario());
    }

    private void limparCampos() {
        txtData.setText("");
        txtHora.setText("");
        cbWhatsapp.setSelected(false);
        cbLigacao.setSelected(false);
        cbEmail.setSelected(false);
        cbSMS.setSelected(false);
        cmbAssunto.setSelectedIndex(0);
        txtDescricao.setText("");
    }

    private void enviarFormulario() {
        JOptionPane.showMessageDialog(this, "Formulário enviado com sucesso!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaRelatos2().setVisible(true);
        });
    }
}
