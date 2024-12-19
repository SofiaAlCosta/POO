import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID; // biblioteca para gerar ID's universais de 36 caracteres

public class Vendas {
    private String idVenda;
    private LocalDateTime dataVenda;

    private int numVendasAdmin;
    private double lucroVendas;

    private int numComprasCliente;
    private double valorGastoCliente;

    private ArrayList<Produto> carrinho;
    private ArrayList<Integer> quantidades;

    // Construtores

    //construtor de inicializaçao básico
    public Vendas() {
        this.idVenda = UUID.randomUUID().toString();
        this.dataVenda = LocalDateTime.now();
        this.numVendasAdmin = 0;
        this.lucroVendas = 0.0;
        this.numComprasCliente = 0;
        this.valorGastoCliente = 0.0;
        this.carrinho = new ArrayList<>();
        this.quantidades = new ArrayList<>();
    }

    public Vendas(int numVendasAdmin, double lucroVendas, int numComprasCliente, double valorGastoCliente) {
        this();//vai chamar o construtor sem parâmetros, inicializando os valores padrão e depois sobreescrevendo
        this.numVendasAdmin = numVendasAdmin;
        this.lucroVendas = lucroVendas;
        this.numComprasCliente = numComprasCliente;
        this.valorGastoCliente = valorGastoCliente;
    }

    // Getters e setters
    public String getIdVenda() {
        return idVenda;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public int getNumVendasAdmin() {
        return numVendasAdmin;
    }

    public void setNumVendasAdmin(int numVendasAdmin) {
        this.numVendasAdmin = numVendasAdmin;
    }

    public double getLucroVendas() {
        return lucroVendas;
    }

    public void setLucroVendas(double lucroVendas) {
        this.lucroVendas = lucroVendas;
    }

    public int getNumComprasCliente() {
        return numComprasCliente;
    }

    public void setNumComprasCliente(int numComprasCliente) {
        this.numComprasCliente = numComprasCliente;
    }

    public double getValorGastoCliente() {
        return valorGastoCliente;
    }

    public void setValorGastoCliente(double valorGastoCliente) {
        this.valorGastoCliente = valorGastoCliente;
    }

    // Métodos para o Carrinho de Compras
    public void adicionarProdutoAoCarrinho(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser positiva.");
        }
        int index = carrinho.indexOf(produto);
        if (index >= 0) {
            quantidades.set(index, quantidades.get(index) + quantidade); //vai buscar quantidades à classe stock, para verificar se há stock de determinado produto antes de o adicionar ao carrinho. caso n haja, throws exception
        } else {
            carrinho.add(produto);
            quantidades.add(quantidade);
        }
    }

    public void removerProdutoDoCarrinho(Produto produto) {
        int index = carrinho.indexOf(produto);
        if (index >= 0) {
            carrinho.remove(index);
            quantidades.remove(index);
        } else {
            throw new IllegalArgumentException("Produto não está no carrinho.");
        }
    }

    public void exibirCarrinho() {
        System.out.println("Carrinho de Compras:");
        for (int i = 0; i < carrinho.size(); i++) {
            Produto produto = carrinho.get(i);
            int quantidade = quantidades.get(i);
            System.out.printf("- %s (Quantidade: %d, Preço Unitário: %.2f)\n",
                    produto.getNome(), quantidade, produto.getPreco());
        }
    }

    // Métodos para Fatura
    public String gerarFatura() {
        StringBuilder fatura = new StringBuilder(); //utilização do string builder para uma questão de gestão de texto. É mais fácil ir adicionando texto do que com concatenação de strings
        fatura.append("=== Fatura ===\n");
        fatura.append("ID da Venda: ").append(idVenda).append("\n");
        fatura.append("Data: ").append(dataVenda).append("\n\n");
        fatura.append("Produtos:\n");

        double total = 0.0;
        for (int i = 0; i < carrinho.size(); i++) {
            Produto produto = carrinho.get(i);
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
        for (int i = 0; i < carrinho.size(); i++) {
            Produto produto = carrinho.get(i);
            int quantidade = quantidades.get(i);
            total += produto.getPreco() * quantidade;
        }
        return total;
    }

    // Finalizar Venda
    public void finalizarVenda() {
        double totalVenda = calcularTotalVenda();
        this.lucroVendas += totalVenda;
        this.numVendasAdmin++;
        this.numComprasCliente++;
        this.valorGastoCliente += totalVenda;

        // Limpar carrinho após a venda
        carrinho.clear();
        quantidades.clear();
    }
}
