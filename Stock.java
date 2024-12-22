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
    
    public void salvarStock() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new LojaException("Erro ao salvar o estado do stock.", e);
        }
    }
    public static Stock carregarStock() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (Stock) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de stock não encontrado. Um novo será criado.");
            return new Stock();
        } catch (IOException | ClassNotFoundException e) {
            throw new LojaException("Erro ao carregar o estado do stock.", e);
        }
    }
}
