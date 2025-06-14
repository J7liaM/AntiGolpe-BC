package VIEW;

import DAO.RelatorioDAO;
import DTO.Relatorio;
import MODEL.MensagemConfirmacao;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class TelaRelatos2 extends JFrame {
    private JButton btnFechar, btnEnviar, btnLimpar;
    private JTextField txtData, txtHora;
    private JCheckBox cbWhatsapp, cbLigacao, cbEmail, cbSMS;
    private JComboBox<String> cmbAssunto;
    private JTextArea txtDescricao;
    private int idUsuario;

    
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

        JLabel lblTitulo = new JLabel("AntiGolpe BC");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panel.add(lblTitulo, gbc);

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

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        panel.add(new JLabel("Assunto"), gbc);

        gbc.gridy = 6;
        String[] assuntos = { "Valores a receber", "Pix", "Cartão de crédito", "Golpe financeiro" };
        cmbAssunto = new JComboBox<>(assuntos);
        panel.add(cmbAssunto, gbc);

        gbc.gridy = 7;
        panel.add(new JLabel("Faça uma breve descrição do ocorrido"), gbc);

        gbc.gridy = 8;
        txtDescricao = new JTextArea(5, 40);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtDescricao);
        panel.add(scrollPane, gbc);

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

        btnFechar.addActionListener(e -> dispose());
        btnLimpar.addActionListener(e -> limparCampos());
        btnEnviar.addActionListener(e -> enviarFormulario());
    }
    public TelaRelatos2(int idUsuario) {
        this(); 
        this.idUsuario = idUsuario;
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
    try {
        // 1. Ler data e hora
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate data = LocalDate.parse(txtData.getText().trim(), formatterData);
        LocalTime hora = LocalTime.parse(txtHora.getText().trim(), formatterHora);


        // 2. Verificar o tipo de contato selecionado (só permite um)
        String tipoContato = "";
        if (cbWhatsapp.isSelected()) tipoContato = "Whatsapp";
        else if (cbLigacao.isSelected()) tipoContato = "Ligação";
        else if (cbEmail.isSelected()) tipoContato = "E-mail";
        else if (cbSMS.isSelected()) tipoContato = "SMS";

        if (tipoContato.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um tipo de contato.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Obter tipo de mensagem (combo box)
        String[] tipoMensagem = { cmbAssunto.getSelectedItem().toString() };

        // 4. Descrição
        String descricao = txtDescricao.getText().trim();
        if (descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, descreva o ocorrido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 5. Criar objeto DTO
        
        Relatorio relatorio = new Relatorio(data, hora, tipoContato, tipoMensagem, descricao, idUsuario);

        // 6. Salvar no banco
        RelatorioDAO dao = new RelatorioDAO();
        dao.cadastrarRelatorio(relatorio);

        // 7. Mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Formulário enviado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);

        // 8. Abrir próxima tela (alerta)
        TelaAlerta alerta = new TelaAlerta(new MensagemConfirmacao());
        alerta.setVisible(true);
        this.dispose();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao enviar relatório:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        int idUsuarioTeste = 1; // coloque aqui um ID de usuário existente no banco
        SwingUtilities.invokeLater(() -> new TelaRelatos2(idUsuarioTeste).setVisible(true));
}

}