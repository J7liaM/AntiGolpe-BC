package MODEL;

public class MensagemAlerta extends Mensagem {
    @Override
    public String getTexto() {
        return "<html><div style='text-align: center;'>"
             + "⚠ Suspeita de golpe detectada!<br><br>"
             + "- NÃO envie dados pessoais ou dinheiro.<br>"
             + "- Tire prints das mensagens ou comprovantes.<br>"
             + "- Registre um boletim de ocorrência.<br>"
             + "- Entre em contato com o banco verdadeiro.<br><br>"
             + "Você está ajudando a combater golpes!"
             + "</div></html>";
    }
}
