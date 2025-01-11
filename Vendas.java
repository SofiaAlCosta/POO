import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.io.*;

public class Vendas implements Serializable{
    private String idVenda;
    private LocalDateTime dataVenda;
    private static final String FILENAME = "vendas.dat";

    private Cliente cliente;
    private ArrayList<Produto> produtos;
    private ArrayList<Integer> quantidades;

    private double lucroVendas;

    public Vendas(Cliente cliente) {
        this.idVenda = UUID.randomUUID().toString();
        this.dataVenda = LocalDateTime.now();
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
        this.quantidades = new ArrayList<>();
        this.lucroVendas = 0.0;
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

    public static void imprimirClientesProduto(String nomeProduto, ArrayList<Vendas> todasVendas) {
        System.out.println("Clientes que compraram o produto \"" + nomeProduto + "\":");
        for (int i = 0; i < todasVendas.size(); i++) {
            Vendas venda = todasVendas.get(i);
            ArrayList<Produto> produtos = venda.getProdutos();
            for (int j = 0; j< produtos.size(); j++) {
                Produto produto = produtos.get(j);
                if(produto.getNome().equals(nomeProduto)) {
                    System.out.println("*" + venda.getCliente().getNome());
                }
            }
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

    public void salvarVenda() {
        ArrayList<Vendas> vendas = lerTodasVendas();
        vendas.add(this);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(vendas);
            System.out.println("Venda salva com sucesso!");
        } catch (IOException e) {
            throw new LojaException("Erro ao salvar a venda em ficheiro binário.", e);
        }
    }

    public static ArrayList<Vendas> lerTodasVendas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            System.out.println("Carregando vendas do ficheiro...");
            return (ArrayList<Vendas>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de vendas não encontrado. Criando novo.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            throw new LojaException("Erro ao carregar vendas do ficheiro: classe não encontrada.", e);
        }
    }

    
    public static void limparVendas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
        } catch (IOException e) {
            System.out.println("Erro ao limpar o ficheiro de clientes. Detalhes: " + e.getMessage());
        }
    }
    public void finalizarVenda() {
        double totalVenda = calcularTotalVenda();
        this.lucroVendas += totalVenda;
        salvarVenda();
        produtos.clear();
        quantidades.clear();
    }

    public ArrayList<Produto> getProdutos() {
        return produtos; 
    }

    public ArrayList<Integer> getQuantidades() {
        return quantidades; 
    }

    public static void listarVendas() {
        ArrayList<Vendas> todasVendas = lerTodasVendas();
        System.out.println("=== Todas as Vendas ===");
        for (Vendas venda : todasVendas) {
            System.out.println("ID da Venda: " + venda.getIdVenda());
            System.out.println("Data: " + venda.getDataVenda());
            System.out.println("Cliente: " + venda.getCliente().getNome());
            System.out.println("Produtos:");
            for (int i = 0; i < venda.getProdutos().size(); i++) {
                Produto produto = venda.getProdutos().get(i);
                int quantidade = venda.getQuantidades().get(i);
                System.out.printf("- %s (x%d)\n", produto.getNome(), quantidade);
            }
            System.out.println();
        }
    }
}
