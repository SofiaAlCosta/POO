public class ProdutoCaca extends Produto {
	private String paisOrigem;
    public double alcanceMaximo;
    private double peso;
    private String material;
    private boolean requisitosLicenca;
    private int garantia;
    private boolean resistenciaAgua;
    private String nivelSeguranca;

    public ProdutoCaca(Produto p,String paisOrigem, double alcanceMaximo,  double peso, String material,
    		boolean requisitosLicenca, int garantia, boolean resistenciaAgua, String nivelSeguranca) {
        super(p.getIDProduto(),p.getNome(), p.getDescricao(), p.getPreco(),p.getCategoria(), p.getAtivo());
        this.alcanceMaximo = alcanceMaximo; 
        this.paisOrigem = paisOrigem;
        this.peso = peso;
        this.material = material;
        this.requisitosLicenca = requisitosLicenca;
        this.garantia = garantia;
        this.resistenciaAgua = resistenciaAgua;
        this.nivelSeguranca = nivelSeguranca;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }
    	
    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public double getPeso() {
        return peso;
    }

	public void setPeso(double peso) {
        if (peso >= 0) {
            this.peso = peso;
        } else {
            throw new LojaException("O peso não pode ser negativo.");
        }
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isRequisitosLicenca() {
        return requisitosLicenca;
    }

    public void setRequisitosLicenca(boolean requisitosLicenca) {
        this.requisitosLicenca = requisitosLicenca;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        if (garantia >= 0) {
            this.garantia = garantia;
        } else {
            throw new LojaException("A garantia não pode ser negativa.");
        }
    }

    public double getAlcanceMaximo() {
		return alcanceMaximo;
	}

	public void setAlcanceMaximo(double alcanceMaximo) {
		this.alcanceMaximo = alcanceMaximo;
	}

	public boolean isResistenciaAgua() {
		return resistenciaAgua;
	}

	public void setResistenciaAgua(boolean resistenciaAgua) {
		this.resistenciaAgua = resistenciaAgua;
	}

	public String getNivelSeguranca() {
		return nivelSeguranca;
	}

	public void setNivelSeguranca(String nivelSeguranca) {
		this.nivelSeguranca = nivelSeguranca;
	}

	@Override
	public String toString() {
	    return super.toString() + "\n" + 
	           "  País de Origem: " + paisOrigem + "\n" +
	           "  Alcance Máximo: " + alcanceMaximo + " metros\n" +
	           "  Peso: " + peso + " kg\n" +
	           "  Material: " + material + "\n" +
	           "  Requisitos de Licença: " + requisitosLicenca + "\n" +
	           "  Garantia: " + garantia + " meses\n" +
	           "  Resistência à Água: " + resistenciaAgua + "\n" +
	           "  Nível de Segurança: " + nivelSeguranca;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            ProdutoCaca other = (ProdutoCaca) obj;
            return super.equals(other) &&
                   this.alcanceMaximo == other.alcanceMaximo &&
                   this.peso == other.peso &&
                   this.material.equals(other.material) &&
                   this.requisitosLicenca == other.requisitosLicenca &&
                   this.paisOrigem.equals(other.paisOrigem) &&
                   this.nivelSeguranca.equals(other.nivelSeguranca) &&
                   this.resistenciaAgua == other.resistenciaAgua &&
                   this.garantia == other.garantia;
        }
        return false;
    }

    @Override
    public ProdutoCaca clone() {
    	 Produto produtoCopia = new Produto();
         produtoCopia.setNome(this.getNome());
         produtoCopia.setDescricao(this.getDescricao());
         produtoCopia.setPreco(this.getPreco());
         produtoCopia.setCategoria(this.getCategoria());
         produtoCopia.setAtivo(this.getAtivo());
        ProdutoCaca copia = new ProdutoCaca(produtoCopia, this.paisOrigem, this.alcanceMaximo, 
        		this.peso, this.material, this.requisitosLicenca, this.garantia, this.resistenciaAgua, this.nivelSeguranca);
        return copia;
    }
    
    public void aplicarGarantiaEstendida(int mesesAdicionais) {
        if (mesesAdicionais > 0) {
            this.garantia += mesesAdicionais;
        } else {
            throw new LojaException("O tempo adicional deve ser positivo.");
        }
    }

    public String verificarNecessidadeLicenca() {
        if (this.requisitosLicenca) {
            return "Atenção: Este produto requer licença especial para ser adquirido.";
        } else {
            return "Este produto não necessita de licença especial.";
        }
    }

    public String avaliarResistenciaAgua() {
        if (this.resistenciaAgua) {
            return "Este produto possui resistência à água.";
        } else {
            return "Este produto não é resistente à água.";
        }
    }
    
    public int compararPorAlcance(ProdutoCaca outroProduto) {
        return Double.compare(this.alcanceMaximo, outroProduto.alcanceMaximo);
    }
    
    public boolean isAltaSeguranca() {
        return this.nivelSeguranca.equalsIgnoreCase("Alta");
    }

}
