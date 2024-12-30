import java.util.ArrayList;
import java.io.*;
public class Stock implements Serializable{
    private ArrayList<Produto> catalogo;
    private ArrayList<Integer> quantidades;
    private static final String FILENAME = "stock.dat"; //Ficheiro para salvar stock
    
    public Stock() {
        this.catalogo = new ArrayList<>();
        this.quantidades = new ArrayList<>();
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
        if (catalogo.contains(produto)) {
            throw new LojaException("Produto já existe no catálogo.");
        }
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
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getIDProduto() == IDProduto) {
                return quantidades.get(i) > 0;
            }
        }
        return false;
    }
    
    public void salvarStock() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new LojaException("Erro ao salvar o estado do stock.", e);
        }
    }
    //ler o ficheiro
    public static Stock lerStock() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (Stock) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de stock não encontrado. Um novo será criado.");
            return new Stock();
        } catch (IOException | ClassNotFoundException e) {
            throw new LojaException("Erro ao carregar o estado do stock.", e);
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
}
