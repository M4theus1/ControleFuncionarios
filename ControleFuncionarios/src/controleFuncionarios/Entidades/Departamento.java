package controleFuncionarios.Entidades;

public class Departamento {
    private int id;
    private String nome;

    public Departamento() {}

    public Departamento(int id, String nome) {
        this.setId(id);
        this.setNome(nome);
    }
    // Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
