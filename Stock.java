import java.util.ArrayList;
import java.io.*;
public class Stock implements Serializable{
    private ArrayList<Produto> catalogo;
    private ArrayList<Integer> quantidades;
    private static final String FILENAME = "stock.dat";
    
    public Stock() {
        this.catalogo = new ArrayList<>();
        this.quantidades = new ArrayList<>();
    }
    public ArrayList<Produto> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<Produto> catalogo) {
        if (catalogo == null) {
            throw new LojaException("O catálogo não pode ser nulo.");
        }
        this.catalogo = catalogo;
    }

    public ArrayList<ProdutoCaca> obterProdutosCaca(){
        ArrayList<ProdutoCaca> produtosCaca = new ArrayList<>();

        for (Produto produto : catalogo){
            if(produto instanceof ProdutoCaca){
                produtosCaca.add((ProdutoCaca) produto);
            }
        }

        return produtosCaca;
    }

    public ArrayList<ProdutoPesca> obterProdutosPesca(){
        ArrayList <ProdutoPesca> produtosPesca = new ArrayList<>();

        for (Produto produto : catalogo) {
            if (produto instanceof ProdutoPesca) {
                produtosPesca.add((ProdutoPesca) produto);
            }
        }
        return produtosPesca;
    }
  
    public ArrayList<ProdutoRoupas> obterProdutosRoupas(){
        ArrayList<ProdutoRoupas> produtosRoupas = new ArrayList<>();

        for (Produto produto : catalogo){
            if(produto instanceof ProdutoRoupas){
                produtosRoupas.add((ProdutoRoupas) produto);
            }
        }

        return produtosRoupas;
    }
    public void adicionarProduto(Produto produto, int quantidadeInicial) {
        if (quantidadeInicial < 0) {
            throw new LojaException("Quantidade inicial não pode ser negativa.");
        }
        for (Produto p : catalogo) {
            if (p.getIDProduto() == produto.getIDProduto()) {
                throw new LojaException("Produto já existe no catálogo com ID duplicado: " + produto.getIDProduto());
            }
        }
        if (catalogo.contains(produto)) {
            throw new LojaException("Produto já existe no catálogo.");
        }
        System.out.println("[DEBUG] Adicionando Produto ao catálogo. ID: " + produto.getIDProduto());
        catalogo.add(produto);
        quantidades.add(quantidadeInicial);
    }

    public void atualizarStock(int IDProduto, int quantidade) {
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getIDProduto() == IDProduto) {
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

    public boolean isDisponivel(int IDProduto) {
        try {
            int quantidade = getQuantidade(IDProduto); 
            return quantidade > 0; 
        } catch (LojaException e) {
            return false; 
        }
    }
    
    public void salvarStock() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new LojaException("Erro ao salvar o stock.", e);
        }
    }
    public static Stock lerStock() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            Stock stock = (Stock) ois.readObject();

            // Garantir que o último ID seja atualizado corretamente
            int maiorID = 0;
            for (Produto produto : stock.getCatalogo()) {
                if (produto.getIDProduto() > maiorID) {
                    maiorID = produto.getIDProduto();
                }
            }
            Produto.setUltimoID(maiorID);
            System.out.println("[DEBUG] Maior ID carregado do ficheiro: " + maiorID);

            return stock;
        } catch (FileNotFoundException e) {
            System.out.println("[DEBUG] Ficheiro de stock não encontrado. Criando novo.");
            return new Stock();
        } catch (IOException | ClassNotFoundException e) {
            throw new LojaException("Erro ao carregar o stock.", e);
        }
    }

     
    public void mostrarStock() {
        System.out.println("Estado atual do stock:");
        for (int i = 0; i < catalogo.size(); i++) {
            Produto produto = catalogo.get(i);
            int quantidade = quantidades.get(i);
            System.out.println(produto + "\n  Quantidade: " + quantidade);
        }
    }
    public int getQuantidade(int IDProduto) {
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getIDProduto() == IDProduto) {
                return quantidades.get(i);
            }
        }
        throw new LojaException("Produto não encontrado no stock.");
    }

}
