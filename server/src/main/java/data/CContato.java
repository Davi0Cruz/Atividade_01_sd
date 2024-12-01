package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CContato implements Serializable {

    private static final long serialVersionUID = 1L;

    public String nome;
    public String telefone;
    public String email;
    public String foto;

    public CContato(String nome, String telefone, String email, String foto) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.foto = foto;
    }

    private void writeObject(ObjectOutputStream o) throws IOException {
        o.writeUTF(this.nome);
        o.writeUTF(this.telefone);
        o.writeUTF(this.email);
        o.writeUTF(this.foto);
    }

    private void readObject(ObjectInputStream o) throws IOException, ClassNotFoundException {
        this.nome = o.readUTF();
        this.telefone = o.readUTF();
        this.email = o.readUTF();
        this.foto = o.readUTF();
    }

    @Override
    public String toString() {
        return this.nome + ", " + this.telefone + ", " + this.email + ", " + this.foto;
    }
}