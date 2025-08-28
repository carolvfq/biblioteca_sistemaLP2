package entities;

import java.util.Objects;

public class usuario {	
	private String nome;
	private String id;
	private  String senha;
	public usuario() {
		
	}
	public usuario(String nome, String id, String senha) {
		super();
		this.nome = nome;
		this.id = id;
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		 if(nome.isEmpty()) {
	            System.out.println("Erro: o nome não pode ser vazio.");
	        } else {
		this.nome = nome;
	}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if(this.id.length() > 11) {
            System.out.println("Erro: valor inválido.");
        } else {
		this.id = id;
	}
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


@Override
public String toString() {
    return "Usuario{" +
            "nome='" + nome + '\'' +
            ", id='" + id + '\'' +
            '}';
}

@Override
public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    usuario usuario = (usuario) o;
    return Objects.equals(id, usuario.id);
}

@Override
public int hashCode() {
    return Objects.hashCode(id);
}
}