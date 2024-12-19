import java.util.ArrayList;

public class Stock {
    private ArrayList<Produto> catalogo;
    private ArrayList<Integer> quantidades;

    public Stock() {
        this.catalogo = new ArrayList<>();
        this.quantidades = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto, int quantidadeInicial) {
        if (quantidadeInicial < 0) {
            throw new LojaException("Quantidade inicial não pode ser negativa.");
        }
        if (catalogo.contains(produto)) {
            throw new LojaException("Produto já existe no catálogo.");
        }
        catalogo.add(produto);
        quantidades.add(quantidadeInicial);
    }

    public void atualizarStock(String IDProduto, int quantidade) {
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getIDProduto().equals(IDProduto)) {
                int novaQuantidade = quantidades.get(i) + quantidade;
                if (novaQuantidade < 0) {
                    throw new LojaException("Stock não pode ser negativo.");
                }
                quantidades.set(i, novaQuantidade);
                return;
            }
        }
        throw new LojaException("Produto não encontrado no catálogo.");
    }

    public boolean isDisponivel(String IDProduto) {
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getIDProduto().equals(IDProduto)) {
                return quantidades.get(i) > 0;
            }
        }
        return false;
    }
}
