package data;

import java.io.Serializable;    
import data.Contato;

public class Agenda implements Serializable {
    public Contato[] itens;

    public Agenda(Contato[] itens) {
        super();
        this.itens = itens;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Contato item : itens) {
            str.append(item.toString()).append("\n");
        }
        return str.toString();
    }
}