package controleFuncionarios.Servicos;

import controleFuncionarios.Entidades.Funcionario;
import java.math.BigDecimal;
import java.util.List;

public interface IFolhaPagamentoServico {
    BigDecimal calcularSalarioLiquido(Funcionario funcionario);
    void processarFolhaPagamento(List<Funcionario> funcionarios);
}
