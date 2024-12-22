public class ProdutoRoupas extends Produto{
	private String tamanho;
	private String genero;
	
	public ProdutoRoupas(Produto p, String id, double preco, String tamanho, String genero, String marca) {
		super(p.getIDProduto(), p.getNome(), p.getDescricao(), p.getPreco(), p.getQuantidadeStock(),
	              p.getCategoria(), p.getDataInclusao(), p.isAtivo());
		this.tamanho = tamanho;
		this.genero = genero;
	}
	
	public String getTamanho() {
		return tamanho;
	}
	
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return "ProdutoRoupas{" +
                ", preco=" + getPreco() +
                ", tamanho='" + tamanho + '\'' +
                ", genero='" + genero + '\'' +
                '}';
	}

}