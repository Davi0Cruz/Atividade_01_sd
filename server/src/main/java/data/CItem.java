package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CItem implements Serializable {

    private static final long serialVersionUID = 1L;

    public String nome;
    public float quantidade;
    public String unidade;

    public CItem(String nome, float quantidade, String unidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    private void writeObject(ObjectOutputStream o) throws IOException {
        o.writeUTF(this.nome);
        o.writeFloat(this.quantidade);
        o.writeUTF(this.unidade);
    }

    private void readObject(ObjectInputStream o) throws IOException, ClassNotFoundException {
        this.nome = o.readUTF();
        this.quantidade = o.readFloat();
        this.unidade = o.readUTF();
    }

    @Override
    public String toString() {
        return this.nome + ", " + this.quantidade + " " + this.unidade;
    }
}
