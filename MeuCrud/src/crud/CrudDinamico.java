package crud;

import conexaoeinterface.ImplProdutoDAO;
import conexaoeinterface.Produto;
import conexaoeinterface.ProdutoDAO;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CrudDinamico {
    public static void main(String[] args) {
        ProdutoDAO dao = new ImplProdutoDAO();
        Produto produto = new Produto();
        produto.setNome("Uva roxa");
        produto.setValor((float) 7.45);
        Calendar calendar  = new GregorianCalendar(2022, 8, 10);
        produto.setDataValidade(calendar);
        //dao.gravar(produto);
        System.out.println(dao.buscarTodos());
        /*
        * Para executar comandos SQLs no SGBDR, existem três objetos do tipo Statement
        * Statement: Utilizado para executar um comando SQL estático
        * Prepared Statement: utilizando para executar um comandos SQL que recebe um ou mais parâmetros
        * Callable Statement: utilizado para chamar stored procedures ou functions
        * */
    }
}
