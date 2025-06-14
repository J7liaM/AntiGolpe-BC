package VIEW;

import MODEL.Mensagem;
import javax.swing.*;
import java.awt.*;

public class TelaAlerta extends JFrame {

    public TelaAlerta(Mensagem mensagem) {
        setTitle("AntiGolpes BC");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel central
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblTitulo = new JLabel("AntiGolpes BC", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        painelCentral.add(lblTitulo, gbc);

        gbc.gridy++;
        JLabel lblMensagem = new JLabel(mensagem.getTexto(), SwingConstants.CENTER);
        lblMensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        painelCentral.add(lblMensagem, gbc);

        add(painelCentral, BorderLayout.CENTER);

        // Botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setBackground(new Color(240, 240, 240));
        JButton btnFechar = new JButton("Fechar");
        painelInferior.add(btnFechar);
        add(painelInferior, BorderLayout.SOUTH);

        btnFechar.addActionListener(e -> dispose());
    }
}
