package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import data.CContato;

public class CAgenda implements Serializable {

    private static final long serialVersionUID = 1L;

    public CContato[] itens;

    public CAgenda(CContato[] itens) {
        this.itens = itens;
    }

    private void writeObject(ObjectOutputStream o) throws IOException {
        o.writeInt(this.itens.length);
        for (CContato item : this.itens) {
            o.writeObject(item);
        }
    }

    private void readObject(ObjectInputStream o) throws IOException, ClassNotFoundException {
        int length = o.readInt();
        this.itens = new CContato[length];
        for (int i = 0; i < length; i++) {
            this.itens[i] = (CContato) o.readObject();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CContato item : this.itens) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}