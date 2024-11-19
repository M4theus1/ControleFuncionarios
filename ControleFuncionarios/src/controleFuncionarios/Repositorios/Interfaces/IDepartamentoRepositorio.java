package controleFuncionarios.Repositorios.Interfaces;

import controleFuncionarios.Entidades.Departamento;
import java.util.List;

public interface IDepartamentoRepositorio {
    
    // Create: Adiciona um novo departamento
    void adicionarDepartamento(Departamento departamento);

    // Read: Busca um departamento por ID
    Departamento buscarDepartamentoPorId(int id);

    // Read: Lista todos os departamentos
    List<Departamento> listarTodosDepartamentos();

    // Update: Atualiza as informações de um departamento
    void atualizarDepartamento(Departamento departamento);

    // Delete: Remove um departamento por ID
    void removerDepartamento(int id);
}
