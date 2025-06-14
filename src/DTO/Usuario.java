package DTO;

public class Usuario extends Pessoa {
    
    public Usuario() {
    super(); 
}
    
    public Usuario(int id, String nome, String cpf, String email, String telefone) {
        super(id, nome, cpf, email, telefone); 
        //O super é usado para chamar o construtor da superclasse e não precisar reescreve-las
    }

    
}
