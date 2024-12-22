import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable { //Serializable serve para salvar o arquivo como binário
    private int IDCliente;
    private String nome;
    private String contacto;
    private int NIF;

    private static int ultimoID = 0; 
    private static final String FILENAME = "clientes.dat"; // Arquivo binário para armazenar os clientes

    // Construtor
    public Cliente(String nome, String contacto, int NIF) {
        this.IDCliente = ++ultimoID; // ID automático
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

    // Método para registar um novo Cliente
    public static void registar() {
        try {
            System.out.print("Nome: ");
            String nome = myinputs.Ler.umaString();

            System.out.print("Contacto: ");
            String contacto = myinputs.Ler.umaString();

            System.out.print("NIF: ");
            int NIF = myinputs.Ler.umInt();
            
            if (NIF < 100000000 || NIF > 999999999) { 
                throw new LojaException("O NIF deve ter 9 dígitos.");
            }
            
            Cliente novoCliente = new Cliente(nome, contacto, NIF);
            adicionarCliente(novoCliente); 
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

            List<Cliente> clientes = lerClientes(); 
            for (Cliente c : clientes) {
                if (c.getNome().equals(nome) && c.getNIF() == NIF) { //se for igual ao que tiver no ficheiro binário
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

    public static void adicionarCliente(Cliente cliente) {
        ArrayList<Cliente> clientes = lerClientes(); // Carrega os clientes atuais
        clientes.add(cliente); // Adiciona o novo cliente à lista
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (Cliente c : clientes) {
                oos.writeObject(c); // Salva todos os clientes no arquivo binário
            }
        } catch (IOException e) {
            throw new LojaException("Erro ao salvar cliente.", e);
        }
    }

    // Carregar Clientes do ficheiro
    public static ArrayList<Cliente> lerClientes() {
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
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de clientes não encontrado");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os clientes. Detalhes: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe Cliente não encontrada. Verifique a compatibilidade do arquivo.");
        }
        
        return clientes;
    }
    public static void limparFicheiro() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            // Só cria um ficheiro vazio
        } catch (IOException e) {
            System.out.println("Erro ao limpar o ficheiro de clientes. Detalhes: " + e.getMessage());
        }
    }
    
    public static void removerCliente() {
        try {
            System.out.print("Digite o ID do cliente para ser removido: ");
            int idCliente = myinputs.Ler.umInt(); 
            
            ArrayList<Cliente> clientes = lerClientes();

            boolean clienteRemovido = false;

            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getIDCliente() == idCliente) {
                    clientes.remove(i); 
                    clienteRemovido = true;
                    break;
                }
            }

            // se clienteRemovido == true
            if (clienteRemovido) {
               
                limparFicheiro();

                // Volta a meter os clientes no arquivo
                for (Cliente cliente : clientes) {
                    adicionarCliente(cliente);
                }

                System.out.println("Cliente removido com sucesso!");
            } else {
                throw new LojaException("Cliente não encontrado.");
            }
        } catch (LojaException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
        } catch (Exception e) {
            throw new LojaException("Erro inesperado ao remover o cliente.", e);
        }
    }



}
