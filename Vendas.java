import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Vendas {
    private String idVenda;
    private LocalDateTime dataVenda;

    private Cliente cliente;
    private ArrayList<Produto> produtos;
    private ArrayList<Integer> quantidades;

    private int numVendasAdmin;
    private double lucroVendas;

    private int numComprasCliente;
    private double valorGastoCliente;

    public Vendas(Cliente cliente) {
        this.idVenda = UUID.randomUUID().toString();
        this.dataVenda = LocalDateTime.now();
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
        this.quantidades = new ArrayList<>();
        this.numVendasAdmin = 0;
        this.lucroVendas = 0.0;
        this.numComprasCliente = 0;
        this.valorGastoCliente = 0.0;
    }

    public Vendas() {
        this(null); 
    }

 
    public String getIdVenda() {
        return idVenda;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumVendasAdmin() {
        return numVendasAdmin;
    }

    public double getLucroVendas() {
        return lucroVendas;
    }

    public int getNumComprasCliente() {
        return numComprasCliente;
    }

    public double getValorGastoCliente() {
        return valorGastoCliente;
    }

    public void adicionarProdutoAoCarrinho(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new LojaException("A quantidade deve ser positiva.");
        }

        int index = produtos.indexOf(produto);
        if (index >= 0) { 
            quantidades.set(index, quantidades.get(index) + quantidade);
        } else {
            produtos.add(produto);
            quantidades.add(quantidade);
        }
    }
    public void exibirCarrinho() {
        if (produtos.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }

        System.out.println("Carrinho de Compras:");
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            int quantidade = quantidades.get(i);
            System.out.printf("- %s (Quantidade: %d, Preço Unitário: %.2f)\n",
                    produto.getNome(), quantidade, produto.getPreco());
        }
    }

    public boolean isCarrinhoVazio() {
        return produtos.isEmpty();
    }

 
    public String gerarFatura() {
        StringBuilder fatura = new StringBuilder();
        fatura.append("=== Fatura ===\n");
        fatura.append("ID da Venda: ").append(idVenda).append("\n");
        fatura.append("Data: ").append(dataVenda).append("\n\n");
        fatura.append("Cliente: ").append(cliente.getNome()).append("\n\n");
        fatura.append("Produtos:\n");

        double total = 0.0;
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            int quantidade = quantidades.get(i);
            double subtotal = produto.getPreco() * quantidade;
            total += subtotal;
            fatura.append(String.format("- %s (x%d): %.2f\n", produto.getNome(), quantidade, subtotal));
        }

        fatura.append("\nTotal: ").append(String.format("%.2f", total)).append("\n");
        return fatura.toString();
    }

    public double calcularTotalVenda() {
        double total = 0.0;
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            int quantidade = quantidades.get(i);
            total += produto.getPreco() * quantidade;
        }
        return total;
    }

  
    public void finalizarVenda() {
        double totalVenda = calcularTotalVenda();
        this.lucroVendas += totalVenda;
        this.numVendasAdmin++;
        this.numComprasCliente++;
        this.valorGastoCliente += totalVenda;

     
        produtos.clear();
        quantidades.clear();
    }

    public ArrayList<Produto> getProdutos() {
        return produtos; 
    }

    public ArrayList<Integer> getQuantidades() {
        return quantidades; 
    }
}
