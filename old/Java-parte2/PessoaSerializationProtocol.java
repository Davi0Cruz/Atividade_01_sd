import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PessoaSerializationProtocol implements Serializable {

	private static final long serialVersionUID = 1L;

	public String nome;
	public String cidade;
	public long telefone;
	public int ano;

	public PessoaSerializationProtocol(String nome, String cidade, long telefone, int ano) {
		super();
		this.nome = nome;
		this.cidade = cidade;
		this.telefone = telefone;
		this.ano = ano;
	}

	private void writeObject(ObjectOutputStream o) throws IOException {
		o.writeUTF(this.nome);
		o.writeUTF(this.cidade);
		o.writeInt(this.ano);
		o.writeLong(this.telefone);
	}

	private void readObject(ObjectInputStream o) throws IOException, ClassNotFoundException {
		this.nome = o.readUTF();
		this.cidade = o.readUTF();
		this.ano = o.readInt();
		this.telefone = o.readLong();
	}

	@Override
	public String toString() {
		return this.nome + ", " + this.cidade + ", " + this.telefone + ", " + this.ano;
	}
}
