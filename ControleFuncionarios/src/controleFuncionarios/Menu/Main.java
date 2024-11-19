package controleFuncionarios.Menu;

import controleFuncionarios.Entidades.*;
import controleFuncionarios.Repositorios.DepartamentoRepositorio;
import controleFuncionarios.Repositorios.FuncionarioRepositorio;
import controleFuncionarios.Servicos.FolhaPagamentoServico;
import controleFuncionarios.Conexao.ConnectionFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializa as conexões e serviços
        DepartamentoRepositorio departamentoRepositorio = new DepartamentoRepositorio();
        FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();
        FolhaPagamentoServico folhaPagamentoServico = new FolhaPagamentoServico();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Departamento");
            System.out.println("2. Listar Departamentos");
            System.out.println("3. Atualizar Departamento");
            System.out.println("4. Remover Departamento");
            System.out.println("5. Adicionar Funcionário");
            System.out.println("6. Listar Funcionários");
            System.out.println("7. Atualizar Funcionário");
            System.out.println("8. Remover Funcionário");
            System.out.println("9. Processar Folha de Pagamento");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            try {
                switch (opcao) {
                    case 1:
                        // Adicionar Departamento
                        System.out.print("Informe o nome do departamento: ");
                        String nomeDepartamento = scanner.nextLine();
                        Departamento departamento = new Departamento(0, nomeDepartamento); // ID 0, será gerado pelo banco
                        departamentoRepositorio.adicionarDepartamento(departamento);
                        break;

                    case 2:
                        // Listar Departamentos
                        List<Departamento> departamentos = departamentoRepositorio.listarTodosDepartamentos();
                        System.out.println("\nDepartamentos cadastrados:");
                        for (Departamento depto : departamentos) {
                            System.out.println(depto.getId() + " - " + depto.getNome());
                        }
                        break;

                    case 3:
                        // Atualizar Departamento
                        System.out.print("Informe o ID do departamento a ser atualizado: ");
                        int idDepartamento = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner
                        Departamento departamentoAtualizar = departamentoRepositorio.buscarDepartamentoPorId(idDepartamento);
                        if (departamentoAtualizar != null) {
                            System.out.print("Informe o novo nome do departamento: ");
                            String novoNome = scanner.nextLine();
                            departamentoAtualizar.setNome(novoNome);
                            departamentoRepositorio.atualizarDepartamento(departamentoAtualizar);
                        } else {
                            System.out.println("Departamento não encontrado!");
                        }
                        break;

                    case 4:
                        // Remover Departamento
                        System.out.print("Informe o ID do departamento a ser removido: ");
                        int idRemoverDepartamento = scanner.nextInt();
                        departamentoRepositorio.removerDepartamento(idRemoverDepartamento);
                        break;

                    case 5:
                        // Adicionar Funcionário
                        System.out.print("Informe o nome do funcionário: ");
                        String nomeFuncionario = scanner.nextLine();
                        System.out.print("Informe o CPF do funcionário: ");
                        String cpfFuncionario = scanner.nextLine();
                        
                        // Converte a data de admissão para o formato correto
                        System.out.print("Informe a data de admissão (dd-MM-yyyy): ");
                        String dataAdmissaoStr = scanner.nextLine();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date dataAdmissao = sdf.parse(dataAdmissaoStr); // Converte a string para o formato Date

                        // Converte a data para o formato SQL
                        java.sql.Date sqlDate = new java.sql.Date(dataAdmissao.getTime());

                        System.out.print("Informe o salário do funcionário: ");
                        BigDecimal salarioFuncionario = scanner.nextBigDecimal();
                        scanner.nextLine(); // Limpar o buffer do scanner
                        System.out.print("Informe o tipo de contrato (CLT/ESTAGIO): ");
                        String tipoContratoStr = scanner.nextLine();
                        TipoContrato tipoContrato = TipoContrato.valueOf(tipoContratoStr);

                        System.out.print("Informe o tipo de funcionário (INTEGRAL/MEIO_PERIODO): ");
                        String tipoFuncionarioStr = scanner.nextLine();
                        Funcionario funcionario;
                        if ("INTEGRAL".equalsIgnoreCase(tipoFuncionarioStr)) {
                            funcionario = new FuncPerIntegral(0, nomeFuncionario, cpfFuncionario, sqlDate, salarioFuncionario, tipoContrato);
                        } else {
                            funcionario = new FuncMeioPer(0, nomeFuncionario, cpfFuncionario, sqlDate, salarioFuncionario, tipoContrato);
                        }

                        funcionarioRepositorio.adicionar(funcionario);
                        break;

                    case 6:
                        // Listar Funcionários
                        List<Funcionario> funcionarios = funcionarioRepositorio.listarTodos();
                        System.out.println("\nFuncionários cadastrados:");
                        for (Funcionario f : funcionarios) {
                            System.out.println(f.getId() + " - " + f.getNome() + " - " + f.getSalario());
                        }
                        break;

                    case 7:
                        // Atualizar Funcionário
                        System.out.print("Informe o ID do funcionário a ser atualizado: ");
                        int idFuncionario = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner
                        Funcionario funcionarioAtualizar = funcionarioRepositorio.buscarPorId(idFuncionario);
                        if (funcionarioAtualizar != null) {
                            System.out.print("Informe o novo nome do funcionário: ");
                            funcionarioAtualizar.setNome(scanner.nextLine());
                            System.out.print("Informe o novo CPF: ");
                            funcionarioAtualizar.setCpf(scanner.nextLine());
                            System.out.print("Informe o novo salário: ");
                            funcionarioAtualizar.setSalario(scanner.nextBigDecimal());
                            scanner.nextLine(); // Limpar o buffer do scanner
                            funcionarioRepositorio.atualizar(funcionarioAtualizar);
                        } else {
                            System.out.println("Funcionário não encontrado!");
                        }
                        break;

                    case 8:
                        // Remover Funcionário
                        System.out.print("Informe o ID do funcionário a ser removido: ");
                        int idRemoverFuncionario = scanner.nextInt();
                        funcionarioRepositorio.remover(idRemoverFuncionario);
                        break;

                    case 9:
                        // Processar Folha de Pagamento
                        List<Funcionario> funcionariosFolha = funcionarioRepositorio.listarTodos();
                        folhaPagamentoServico.processarFolhaPagamento(funcionariosFolha);
                        break;

                    case 0:
                        // Sair
                        System.out.println("Saindo...");
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch(ParseException e) {
            	System.out.println("Erro ao formatar a data: " + e.getMessage()); 
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            }
        }
    }
}
