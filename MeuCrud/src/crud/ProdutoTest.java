package crud;

import conexaoeinterface.Produto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ProdutoTest {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2022, 2, 22);
        Produto produto = new Produto(1, "teste", 44.5f, calendar);
        System.out.println(produto.toString());
        List<String> a = new ArrayList<String>();

    }
}
