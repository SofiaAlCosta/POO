public class ProdutoPesca extends Produto{
	private String tipoEquipamento;
	private String material;
	private double peso;
	private int tamanho;
	private String paisOrigem;
	private int garantia;
	
	public ProdutoPesca(Produto p, String tipoEquipamento, String material, double peso, int tamanho, String paisOrigem, int garantia)
	{
		super(p.getIDProduto(), p.getNome(), p.getDescricao(), p.getPreco(), p.getCategoria(), p.getAtivo());
		this.tipoEquipamento = tipoEquipamento;
		this.material = material;
		this.peso = peso;
		this.tamanho = tamanho;
		this.paisOrigem= paisOrigem;
		this.garantia = garantia;
}

	public String getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(String tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}


	public double getPeso() {
		return peso;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		if (tamanho >= 0) {
			this.tamanho = tamanho;
		}else {
			throw new LojaException("A garantia não pode ser negativa."); }
	}
	
	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	
	public int getGarantia() {
		return garantia; 
	}

	
	public void setGarantia(int garantia) {
		if (garantia >= 0) {
			this.garantia = garantia;
		}else {
			throw new LojaException("A garantia não pode ser negativa.");
		}
	}

	public void setPeso(double peso) {
		if (peso >= 0) {
            this.peso = peso;
        } else {
            throw new LojaException("O peso não pode ser negativo.");
        }
	}

 	 @Override
     public boolean equals(Object obj) {
         if (obj != null && this.getClass() == obj.getClass()) {
             ProdutoPesca other = (ProdutoPesca) obj;
             return super.equals(other) &&
            		 this.tipoEquipamento.equals(other.tipoEquipamento) &&
                    this.material.equals(other.material) &&
                    this.peso == other.peso &&
                    this.tamanho == other.tamanho &&
                    this.paisOrigem.equals(other.paisOrigem) &&
                    this.garantia == other.garantia;
         }
         return false;
     }

     @Override
     public ProdutoPesca clone() {
         Produto produtoCopia = new Produto();
         produtoCopia.setNome(this.getNome());
         produtoCopia.setDescricao(this.getDescricao());
         produtoCopia.setPreco(this.getPreco());
         produtoCopia.setCategoria(this.getCategoria());
         produtoCopia.setAtivo(this.getAtivo());
         
         ProdutoPesca copia = new ProdutoPesca(produtoCopia, this.tipoEquipamento, this.material, this.peso, 
        		 this.tamanho, this.paisOrigem, this.garantia);
         return copia;
     }
     
     @Override
     public String toString() {
         return super.toString() + "\n" +
                "  Tipo De Equipamento: " + tipoEquipamento + "\n" +
                "  Material: " + material + "\n" +
                "  Peso: " + peso + " kg\n" +
                "  Tamanho: " + tamanho + "cm\n" +
                "  Pais de Origem: " + paisOrigem + "\n" +
                "  Garantia: " + garantia + " meses";
     }
	
}
