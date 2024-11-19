package controleFuncionarios.Repositorios;

import controleFuncionarios.Entidades.*;
import controleFuncionarios.Repositorios.Interfaces.IFuncionarioRepositorio;
import controleFuncionarios.Conexao.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositorio implements IFuncionarioRepositorio {

    @Override
    public void adicionar(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome, cpf, data_admissao, salario, tipo_contrato, tipo_funcionario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDate(3, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
            stmt.setBigDecimal(4, funcionario.getSalario());
            stmt.setString(5, funcionario.getTipoContrato().name());

            // Define o tipo de funcionário (INTEGRAL ou MEIO_PERIODO)
            if (funcionario instanceof FuncPerIntegral) {
                stmt.setString(6, "INTEGRAL");
            } else if (funcionario instanceof FuncMeioPer) {
                stmt.setString(6, "MEIO_PERIODO");
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        Funcionario funcionario = null;

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = criarFuncionario(rs);
            }
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = criarFuncionario(rs);
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }

    @Override
    public void atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, data_admissao = ?, salario = ?, tipo_contrato = ?, tipo_funcionario = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDate(3, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
            stmt.setBigDecimal(4, funcionario.getSalario());
            stmt.setString(5, funcionario.getTipoContrato().name());

            if (funcionario instanceof FuncPerIntegral) {
                stmt.setString(6, "INTEGRAL");
            } else if (funcionario instanceof FuncMeioPer) {
                stmt.setString(6, "MEIO_PERIODO");
            }

            stmt.setInt(7, funcionario.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id = ?";

        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método utilitário para criar um objeto Funcionario a partir de um ResultSet
    private Funcionario criarFuncionario(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String cpf = rs.getString("cpf");
        Date dataAdmissao = rs.getDate("data_admissao");
        BigDecimal salario = rs.getBigDecimal("salario");
        TipoContrato tipoContrato = TipoContrato.valueOf(rs.getString("tipo_contrato"));
        String tipoFuncionario = rs.getString("tipo_funcionario");

        if ("INTEGRAL".equalsIgnoreCase(tipoFuncionario)) {
            return new FuncPerIntegral(id, nome, cpf, dataAdmissao, salario, tipoContrato);
        } else if ("MEIO_PERIODO".equalsIgnoreCase(tipoFuncionario)) {
            return new FuncMeioPer(id, nome, cpf, dataAdmissao, salario, tipoContrato);
        }
        return null;
    }
}
