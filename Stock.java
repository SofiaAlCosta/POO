import java.util.ArrayList;

public class Stock {
    private ArrayList<String> idsProdutos; // Lista de IDs dos produtos
    private ArrayList<Integer> quantidades; // Lista de quantidades correspondentes
    private ArrayList<Produto> catalogo; // Lista de objetos Produto

    public Stock() {
        this.idsProdutos = new ArrayList<>();
        this.quantidades = new ArrayList<>();
        this.catalogo = new ArrayList<>();
    }

    // Adicionar produto ao catálogo e ao stock inicial
    public void adicionarProduto(Produto produto, int quantidadeInicial) {
        if (idsProdutos.contains(produto.getIDProduto())) {
            throw new LojaException("Produto já existe no catálogo.");
        }
        if (quantidadeInicial < 0) {
            throw new LojaException("Quantidade inicial não pode ser negativa.");
        }
        idsProdutos.add(produto.getIDProduto());
        quantidades.add(quantidadeInicial);
        catalogo.add(produto);
    }

    // Atualizar stock de um produto
    public void atualizarStock(String IDProduto, int quantidade) {
        int index = idsProdutos.indexOf(IDProduto);
        if (index == -1) {
            throw new LojaException("Produto não encontrado no stock.");
        }
        int quantidadeAtual = quantidades.get(index);
        int novaQuantidade = quantidadeAtual + quantidade;
        if (novaQuantidade < 0) {
            throw new LojaException("Stock não pode ser negativo.");
        }
        quantidades.set(index, novaQuantidade);
    }

    // Consultar quantidade em stock de um produto
    public int consultarStock(String IDProduto) {
        int index = idsProdutos.indexOf(IDProduto);
        if (index == -1) {
            throw new LojaException("Produto não encontrado no stock.");
        }
        return quantidades.get(index);
    }

    // Remover produto do stock e do catálogo
    public void removerProduto(String IDProduto) {
        int index = idsProdutos.indexOf(IDProduto);
        if (index == -1) {
            throw new LojaException("Produto não encontrado no stock.");
        }
        idsProdutos.remove(index);
        quantidades.remove(index);
        catalogo.remove(index);
    }

    // Verificar disponibilidade de um produto
    public boolean isDisponivel(String IDProduto) {
        int index = idsProdutos.indexOf(IDProduto);
        return index != -1 && quantidades.get(index) > 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stock:\n");
        for (int i = 0; i < idsProdutos.size(); i++) {
            Produto produto = catalogo.get(i);
            builder.append("Produto: ").append(produto.getNome())
                    .append(", ID: ").append(idsProdutos.get(i))
                    .append(", Quantidade: ").append(quantidades.get(i))
                    .append("\n");
        }
        return builder.toString();
    }
}
