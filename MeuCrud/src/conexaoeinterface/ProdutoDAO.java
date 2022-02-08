package conexaoeinterface;

import java.util.List;

public interface ProdutoDAO {
    void gravar(Produto produto);

    List<Produto> buscarTodos();

    Produto buscarTodosPorCodigo(int codigo);

    void atualizar(Produto produto);

    void remover(int codigo);
}
