public class Cliente {
	private int IDCliente;
	private String nome;
	private String contacto;
	private int NIF;
		
	private static int ultimo = 0;
		
	public Cliente(int IDCliente, String nome, String contacto, int NIF) {
        this.IDCliente = getIDCliente();
	    this.nome = nome;
        this.contacto = contacto;
        this.NIF = NIF;
    }
		
	public Cliente (int IDCliente) {
		this.IDCliente = IDCliente;
		ultimo++;
		IDCliente = ultimo;
	}
		
	public int getIDCliente() {
		return IDCliente;
	}
		
	public String getNome() {
		return nome;
	}
		
	public String getContacto() {
		return contacto;
	}
	
	public int getNIF() {
		return NIF;
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
		
	public void setNIF(int NIF) {
		this.NIF = NIF;
	}
		
	@Override
 	public String toString() {
		return "Cliente {" +
	            "IDCliente='" + IDCliente + '\'' +
	            ", Nome='" + nome + '\'' +
	            ", Contacto='" + contacto + '\'' +
	            ", NIF=" + NIF +
	            '}';	
	}
	
	public boolean equals(Object obj) {
		if (obj != null && this.getClass() == obj.getClass()) {
			Cliente c = (Cliente) obj;
			return this.IDCliente == c.IDCliente && this.nome.equals(c.nome) && 
				   this.contacto.equals(c.contacto) && this.NIF == c.NIF;
		}
		return false;
	}
	
	public Object clone() {
		Cliente copia = new Cliente(this.IDCliente, this.nome, this.contacto, this.NIF);
		return copia;
	}
}