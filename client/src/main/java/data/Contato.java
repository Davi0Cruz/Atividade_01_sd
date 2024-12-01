package data;

import java.io.Serializable;

public class Contato implements Serializable {
    public String nome;
    public String telefone;
    public String email;
    public String foto;

    public Contato(String nome, String telefone, String email, String foto) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + ", Telefone: " + this.telefone + ", Email: " + this.email + ", Foto: " + this.foto;
    }
}