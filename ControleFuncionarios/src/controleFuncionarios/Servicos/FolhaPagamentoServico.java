package controleFuncionarios.Servicos;

import controleFuncionarios.Entidades.Funcionario;
import controleFuncionarios.Entidades.FuncMeioPer;
import controleFuncionarios.Entidades.FuncPerIntegral;
import java.math.BigDecimal;
import java.util.List;

public class FolhaPagamentoServico implements IFolhaPagamentoServico {

    private static final BigDecimal DESCONTO_INSS = new BigDecimal("0.11"); // 11%
    private static final BigDecimal DESCONTO_IR = new BigDecimal("0.15"); // 15%

    @Override
    public BigDecimal calcularSalarioLiquido(Funcionario funcionario) {
        BigDecimal salarioBruto = funcionario.getSalario();

        // Desconto de INSS
        BigDecimal descontoInss = salarioBruto.multiply(DESCONTO_INSS);
        BigDecimal salarioComDesconto = salarioBruto.subtract(descontoInss);

        // Desconto de Imposto de Renda se aplicável
        BigDecimal descontoIr = BigDecimal.ZERO;
        if (salarioComDesconto.compareTo(new BigDecimal("2500")) > 0) {
            descontoIr = salarioComDesconto.multiply(DESCONTO_IR);
        }

        // Salário líquido
        return salarioComDesconto.subtract(descontoIr);
    }

    @Override
    public void processarFolhaPagamento(List<Funcionario> funcionarios) {
        System.out.println("Processando Folha de Pagamento...\n");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioLiquido = calcularSalarioLiquido(funcionario);
            System.out.println("Funcionário: " + funcionario.getNome());
            System.out.println("Salário Bruto: R$ " + funcionario.getSalario());
            System.out.println("Salário Líquido: R$ " + salarioLiquido + "\n");
        }
    }
}
