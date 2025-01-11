import java.io.Serializable;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int IDProduto;
	private String nome;
	private String descricao;
	private double preco;
	private String categoria;
	private boolean ativo;
	private static int ultimo = 0;
	
	public Produto() {  
		
	    this.IDProduto = ++ultimo;
        this.nome = "";
        this.descricao = "";
        this.preco = 0.0;
        this.categoria = "";
        this.ativo = true;
    }

	public Produto(String nome) {
	    this.IDProduto = ++ultimo;
	    
		this.nome = nome;
		
		this.descricao= "";
		this.preco = 0.0;
        this.categoria = "";
        this.ativo = true;
	}
	public Produto(String nome, String descricao, double preco, 
            String categoria, boolean ativo) { 
	    this.IDProduto = ++ultimo;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
		this.ativo = ativo;
}
	public Produto(int IDProduto, String nome, String descricao, double preco, String categoria, boolean ativo) {
	    this.IDProduto = IDProduto; // Reutiliza o ID fornecido
	    this.nome = nome;
	    this.descricao = descricao;
	    this.preco = preco;
	    this.categoria = categoria;
	    this.ativo = ativo;
	}
	public int getIDProduto() {
		return IDProduto;
	}
	public void setIDProduto(int IDProduto) {
		this.IDProduto = IDProduto;
	}
	
	public static int getUltimoID() {
	    return ultimo;
	}

	public static void setUltimoID(int novoUltimoID) {
	    if (novoUltimoID > ultimo) {
	        ultimo = novoUltimoID;
	    }
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
	    if (nome != null && !nome.trim().isEmpty()) {
	        this.nome = nome;
	    } else {
	        throw new LojaException("O nome do produto não pode ser vazio ou nulo.");
	    }
	}

	public void setDescricao(String descricao) {
	    if (descricao != null && !descricao.trim().isEmpty()) {
	        this.descricao = descricao;
	    } else {
	        throw new LojaException("A descrição do produto não pode ser vazia ou nula.");
	    }
	}
	public String getDescricao() {
		return descricao;
	}
	
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        } else {
            throw new LojaException("O preço não pode ser negativo.");
        }
    }

	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
	    return "Produto " + IDProduto + ":\n" +
	           "  Nome: " + nome + "\n" +
	           "  Descrição: " + descricao + "\n" +
	           "  Preço: " + preco + "\n" +
	           "  Categoria: " + categoria + "\n" +
	           "  Ativo: " + ativo;
	}
	
	public boolean equals(Object obj) {
		if (obj!=null && this.getClass() == obj.getClass()) {
			Produto p = (Produto) obj;
			return this.IDProduto == p.IDProduto && this.nome.equals(p.nome) &&
					this.descricao.equals(p.descricao) && this.preco == p.preco && this.categoria.equals(p.categoria) && this.ativo == p.ativo;
		}return false;
	}
	
	public Object clone() {
        Produto copia = new Produto(this.nome, this.descricao, this.preco, this.categoria, this.ativo);
        return copia;
    }
	
	public void aplicarDesconto(double perc) {
		if (perc > 0 && perc < 100) {
			this.preco -= this.preco * (perc /100.0);
		}else {
			throw new LojaException("Percentual de desconto invalido");
		}
	}
	
	public int compararPorPreco(Produto outroProduto) {
	    return Double.compare(this.preco, outroProduto.preco);
	}
	
	public int compararPorNome(Produto outroProduto) {
	    return this.nome.compareToIgnoreCase(outroProduto.nome);
	}


}	
