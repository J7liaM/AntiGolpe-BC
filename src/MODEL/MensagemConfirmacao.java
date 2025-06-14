package MODEL;

public class MensagemConfirmacao extends Mensagem {
    @Override
    public String getTexto() {
        return "<html><div style='text-align: center;'>"
             + "✅ Relato enviado com sucesso!<br><br>"
             + "Obrigado por contribuir com a segurança dos usuários.<br>"
             + "O Banco Central analisará as informações fornecidas."
             + "</div></html>";
    }
}
