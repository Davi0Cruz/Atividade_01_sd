package low_data;

import java.io.Serializable;

public class Item implements Serializable{
    public String nome;
    public double quantidade;
    public String unidade;

    public Item(String nome, double quantidade, String unidade) {
        super();
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return this.nome + ", " + this.quantidade + ", " + this.unidade;
    }
}