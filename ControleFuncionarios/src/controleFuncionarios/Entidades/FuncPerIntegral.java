package controleFuncionarios.Entidades;

import java.math.BigDecimal;
import java.util.Date;

public class FuncPerIntegral extends Funcionario {

    // Construtor
    public FuncPerIntegral(int id, String nome, String cpf, Date dataAdmissao, BigDecimal salario, TipoContrato tipoContrato) {
        super(id, nome, cpf, dataAdmissao, salario, tipoContrato);
    }

    // Implementação do método abstrato para cálculo de salário
    @Override
    public BigDecimal calcularSalario() {
        return getSalario(); // Salário integral sem ajustes
    }

    @Override
    public String toString() {
        return "FuncPerIntegral: " + super.toString();
    }
}
