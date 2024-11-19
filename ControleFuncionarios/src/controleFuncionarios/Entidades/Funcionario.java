package controleFuncionarios.Entidades;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private Date dataAdmissao;
    private BigDecimal salario;
    private TipoContrato tipoContrato;

    // Construtor
    public Funcionario(int id, String nome, String cpf, Date dataAdmissao, BigDecimal salario, TipoContrato tipoContrato) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
        this.tipoContrato = tipoContrato;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    // Método abstrato para ser implementado nas subclasses
    public abstract BigDecimal calcularSalario();
    
    @Override
    public String toString() {
        return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf +
               ", dataAdmissao=" + dataAdmissao + ", salario=" + salario +
               ", tipoContrato=" + tipoContrato + "]";
    }

	public void setDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		
	}
}
