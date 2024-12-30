public class ProdutoRoupas extends Produto{
	private static final long serialVersionUID = 1L;
	private String tamanho;
	private String genero;
	
	public ProdutoRoupas(Produto p, String id, double preco, String tamanho, String genero) {
		super(p.getNome(), p.getDescricao(), p.getPreco(), p.getCategoria(), p.getAtivo());
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
	    return super.toString() + "\n" + 
	           "  Tamanho: " + tamanho + "\n" +
	           "  Género: " + genero;
	}

}
