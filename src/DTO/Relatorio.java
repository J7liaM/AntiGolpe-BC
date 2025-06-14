package DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class Relatorio {
    private LocalDate data; 
    private LocalTime hora; 
    private String tipoContato;      
    private String[] tipoMensagem;    
    private String Descricao; 
    private int idUsuario;

    public Relatorio(LocalDate data,
            LocalTime hora,
            String tipoContato,
            String[] tipoMensagem,
            String descricao,
            int idUsuario) {
        this.data = data;
        this.hora = hora;
        this.tipoContato = tipoContato;
        this.tipoMensagem = tipoMensagem;
        this.Descricao = descricao;
        this.idUsuario = idUsuario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String[] getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(String[] tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
