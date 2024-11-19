package controleFuncionarios.Repositorios;

import controleFuncionarios.Conexao.ConnectionFactory;
import controleFuncionarios.Entidades.Departamento;
import controleFuncionarios.Repositorios.Interfaces.IDepartamentoRepositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoRepositorio implements IDepartamentoRepositorio {

    @Override
    public void adicionarDepartamento(Departamento departamento) {
        String sql = "INSERT INTO departamento (nome) VALUES (?)";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, departamento.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Departamento buscarDepartamentoPorId(int id) {
        String sql = "SELECT * FROM departamento WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Departamento(rs.getInt("id"), rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Departamento> listarTodosDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM departamento";

        try (Connection conn = ConnectionFactory.createConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Departamento departamento = new Departamento(rs.getInt("id"), rs.getString("nome"));
                departamentos.add(departamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamentos;
    }

    @Override
    public void atualizarDepartamento(Departamento departamento) {
        String sql = "UPDATE departamento SET nome = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, departamento.getNome());
            stmt.setInt(2, departamento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerDepartamento(int id) {
        String sql = "DELETE FROM departamento WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
