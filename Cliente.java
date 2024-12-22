import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Cloneable, Serializable {
    private int IDCliente;
    private String nome;
    private String contacto;
    private int NIF;

    private static int ultimoID = 0; 
    private static final String FILENAME = "clientes.dat"; // Arquivo binário para armazenar os clientes

    // Construtor
    public Cliente(String nome, String contacto, int NIF) {
        this.IDCliente = ++ultimoID; // ID automatico
        this.nome = nome;
        this.contacto = contacto;
        this.NIF = NIF;
    }

    // Getters
    public int getIDCliente() { return IDCliente; }
    public String getNome() { return nome; }
    public String getContacto() { return contacto; }
    public int getNIF() { return NIF; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public void setNIF(int NIF) { this.NIF = NIF; }

    @Override
 	public String toString() {
		return "Cliente {" +
	            "IDCliente='" + IDCliente + '\'' +
	            ", Nome='" + nome + '\'' +
	            ", Contacto='" + contacto + '\'' +
	            ", NIF=" + NIF +
	            '}';	
	}

    // Método para fazer registo dum Cliente
    public static void registrar() {
        try {
            System.out.print("Nome: ");
            String nome = myinputs.Ler.umaString();

            System.out.print("Contacto: ");
            String contacto = myinputs.Ler.umaString();

            System.out.print("NIF: ");
            int NIF = myinputs.Ler.umInt();

            Cliente novoCliente = new Cliente(nome, contacto, NIF);
            salvarCliente(novoCliente); // Salva no ficheiro
            System.out.println("Cliente registrado com sucesso!");
        } catch (LojaException e) {
            System.out.println("Erro ao registrar cliente: " + e.getMessage());
        }
    }

    // Método para Login
    public static Cliente login() {
        try {
            System.out.print("Nome: ");
            String nome = myinputs.Ler.umaString();

            System.out.print("NIF: ");
            int NIF = myinputs.Ler.umInt();

            List<Cliente> clientes = carregarClientes(); // Carrega os clientes
            for (Cliente c : clientes) {
                if (c.getNome().equals(nome) && c.getNIF() == NIF) { //se for igual ao que tiver no ficheiro binario
                    System.out.println("Login bem-sucedido! Bem-vindo, " + c.getNome()); //login
                    return c;
                }
            }

            throw new LojaException("Credenciais inválidas.");
        } catch (LojaException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Salvar Cliente no ficheiro usando serialização
    public static void salvarCliente(Cliente cliente) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, true))) {
            oos.writeObject(cliente); // Salva o objeto Cliente no arquivo binário
        } catch (IOException e) {
            throw new LojaException("Erro ao salvar cliente.", e);
        }
    }

    // Carregar Clientes do ficheiro
    public static ArrayList<Cliente> carregarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        // Usando try-with-resources para garantir o fechamento do ObjectInputStream
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            Cliente cliente;
            // Tentamos ler objetos até o final do arquivo
            while (true) {
                try {
                    // Lê um objeto Cliente
                    cliente = (Cliente) ois.readObject();
                    clientes.add(cliente);
                } catch (EOFException e) {
                    // EOFException é esperada quando o arquivo chega ao final
                    break;
                }
            }
        } catch (LojaException e) {
            System.out.println("Ficheiro de clientes não encontrado. Criando novo ficheiro...");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os clientes. Detalhes: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Cliente não encontrada. Verifique a compatibilidade do arquivo.");
        }
        
        return clientes;
    }

}
