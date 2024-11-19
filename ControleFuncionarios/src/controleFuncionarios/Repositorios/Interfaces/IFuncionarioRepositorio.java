package controleFuncionarios.Repositorios.Interfaces;

import controleFuncionarios.Entidades.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface IFuncionarioRepositorio {
    void adicionar(Funcionario funcionario) throws SQLException;
    Funcionario buscarPorId(int id) throws SQLException;
    List<Funcionario> listarTodos() throws SQLException;
    void atualizar(Funcionario funcionario) throws SQLException;
    void remover(int id) throws SQLException;
}
