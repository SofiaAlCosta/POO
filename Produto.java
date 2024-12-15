import java.time.LocalDate;

public class Produto {
	private String IDProduto;
	private String nome;
	private String descricao;
	private double preco;
	private int quantidadeStock;
	private String categoria;
	private LocalDate dataInclusao;
	private boolean ativo;
	
	public Produto(String IDProduto, String nome) {
		this.IDProduto = IDProduto;
		this.nome = nome;
		
		this.descricao= "";
		this.preco = 0.0;
		this.quantidadeStock = 0;
        this.categoria = "";
        this.dataInclusao = LocalDate.now();
        this.ativo = true;
	}
	public Produto(String IDProduto, String nome, String descricao, double preco, int quantidadeStock, 
            String categoria, LocalDate dataInclusao, boolean ativo) {
	 this.IDProduto = IDProduto;
	 this.nome = nome;
	 this.descricao = descricao;
	 this.preco = preco;
	 this.quantidadeStock = quantidadeStock;
	 this.categoria = categoria;
	 this.dataInclusao = dataInclusao;
	 this.ativo = ativo;
}
	public String getIDProduto() {
		return IDProduto;
	}
	public void setIDProduto(String IDProduto) {
		this.IDProduto = IDProduto;
	}
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
	    if (nome != null && !nome.trim().isEmpty()) {
	        this.nome = nome;
	    } else {
	        throw new IllegalArgumentException("O nome do produto não pode ser vazio ou nulo.");
	    }
	}

	public void setDescricao(String descricao) {
	    if (descricao != null && !descricao.trim().isEmpty()) {
	        this.descricao = descricao;
	    } else {
	        throw new IllegalArgumentException("A descrição do produto não pode ser vazia ou nula.");
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
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
    }
	public int getQuantidadeStock() {
		return quantidadeStock;
	}
	 public void setQuantidadeStock(int quantidadeEmEstoque) {
	        if (quantidadeEmEstoque >= 0) {
	            this.quantidadeStock = quantidadeEmEstoque;
	        } else {
	            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
	        }
	    }
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public LocalDate getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(LocalDate dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public boolean isAtivo() {
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
	           "  Quantidade em Estoque: " + quantidadeStock + "\n" +
	           "  Categoria: " + categoria + "\n" +
	           "  Data de Inclusão: " + dataInclusao + "\n" +
	           "  Ativo: " + ativo;
	}
	
	public boolean equals(Object obj) {
		if (obj!=null && this.getClass() == obj.getClass()) {
			Produto p = (Produto) obj;
			return this.IDProduto.equals(p.IDProduto) && this.nome.equals(p.nome) &&
					this.descricao.equals(p.descricao) && this.preco == p.preco &&
					this.quantidadeStock == p.quantidadeStock && this.categoria.equals(p.categoria) &&
					this.dataInclusao.equals(p.dataInclusao) && this.ativo == p.ativo;
		}return false;
	}
	
	public Object clone() {
		Produto copia = new Produto(this.IDProduto, this.nome, this.descricao , this.preco , 
				this.quantidadeStock ,this.categoria ,this.dataInclusao ,this.ativo);
		return copia;
	}
	
	public void verificarAtivo() {
	    if (this.quantidadeStock <= 0) {
	        this.ativo = false;
	    }
	}
	
	public void AdicionarStock(int quantidade) {
		if (quantidade > 0) {
			this.quantidadeStock += quantidade;
		}else { throw new IllegalArgumentException("A quantidade deve ser positiva");
	}
	}
		
	public void RemoverStock(int quantidade) {
		if (quantidade > 0) {
			this.quantidadeStock -= quantidade;
			verificarAtivo();
		}else {throw new IllegalArgumentException("A quantidade deve ser positiva");
	}
}
	public void aplicarDesconto(double perc) {
		if (perc > 0 && perc < 100) {
			this.preco -= this.preco * (perc /100.0);
		}else {
			throw new IllegalArgumentException("Percentual de desconto invalido");
		}
	}

	public boolean isProdutoRecente() {
	    return this.dataInclusao.isAfter(LocalDate.now().minusDays(30));
	}
	
	public double calcularValorTotalStock() {
	    return this.preco * this.quantidadeStock;
	}
	
	public int compararPorPreco(Produto outroProduto) {
	    return Double.compare(this.preco, outroProduto.preco);
	}
	
	public int compararPorNome(Produto outroProduto) {
	    return this.nome.compareToIgnoreCase(outroProduto.nome);
	}


}	
