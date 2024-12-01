package data;

import java.io.Serializable;    
import data.Item;

public class Compras implements Serializable{
    public Item[] itens;

    public Compras(Item[] itens) {
        super();
        this.itens = itens;
    }

    @Override
    public String toString() {
        String str = "";
        for (Item item : itens) {
            str += item.toString() + "\n";
        }
        return str;
    }
}