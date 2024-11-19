package controleFuncionarios.Entidades;

import java.math.BigDecimal;
import java.util.Date;

public class FuncMeioPer extends Funcionario {

    // Construtor
    public FuncMeioPer(int id, String nome, String cpf, Date dataAdmissao, BigDecimal salario, TipoContrato tipoContrato) {
        super(id, nome, cpf, dataAdmissao, salario, tipoContrato);
    }

    // Implementação do método abstrato para cálculo de salário
    @Override
    public BigDecimal calcularSalario() {
        return getSalario().divide(BigDecimal.valueOf(2)); // Salário reduzido pela metade
    }

    @Override
    public String toString() {
        return "FuncMeioPer: " + super.toString();
    }
}
