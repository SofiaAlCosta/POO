import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable { 
    private int IDCliente;
    private String nome;
    private long contacto;
    private int NIF;

    private static int ultimoID = 0; 
    private static final String FILENAME = "clientes.dat";

    public Cliente(String nome, long contacto, int NIF) {
        this.IDCliente = ++ultimoID; // ID automático
        this.nome = nome;
        this.contacto = contacto;
        this.NIF = NIF;
    }

    public int getIDCliente() { return IDCliente; }
    public String getNome() { return nome; }
    public long getContacto() { return contacto; }
    public int getNIF() { return NIF; }

    public void setNome(String nome) { this.nome = nome; }
    public void setContacto(long contacto) { this.contacto = contacto; }
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

    public static void registar() {
        try {
            System.out.print("Nome: ");
            String nome = myinputs.Ler.umaString();

            System.out.print("Contacto: ");
            long contacto = myinputs.Ler.umLong();

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


    public static Cliente login() {
        try {
            System.out.print("Nome: ");
            String nome = myinputs.Ler.umaString();

            System.out.print("NIF: ");
            int NIF = myinputs.Ler.umInt();

            // Verificar se é um admin
            if ((nome.equals("Tiago") && NIF == 245386491) ||
                (nome.equals("Francisco") && NIF == 263594530) ||
                (nome.equals("Hugo") && NIF == 255822243) ||
                (nome.equals("Sofia") && NIF == 246385391)) {
                System.out.println("Login como administrador bem-sucedido! Bem-vindo, Admin " + nome);
                return null; // Admins retornam null
            }

            // Verificar se é um cliente normal
            List<Cliente> clientes = lerClientes(); 
            for (Cliente c : clientes) {
                if (c.getNome().equals(nome) && c.getNIF() == NIF) {
                    System.out.println("Login bem-sucedido! Bem-vindo, " + c.getNome());
                    return c; // Retorna o cliente logado
                }
            }

            throw new LojaException("Credenciais inválidas.");
        } catch (LojaException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static void adicionarCliente(Cliente cliente) {
        ArrayList<Cliente> clientes = lerClientes(); 
        clientes.add(cliente); 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (Cliente c : clientes) {
                oos.writeObject(c); 
            }
        } catch (IOException e) {
            throw new LojaException("Erro ao salvar cliente.", e);
        }
    }

    public static ArrayList<Cliente> lerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            Cliente cliente;
            while (true) {
                try {
                    cliente = (Cliente) ois.readObject();
                    clientes.add(cliente);
                } catch (EOFException e) {
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
    public static void limparClientes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
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

            if (clienteRemovido) {
               
                limparClientes();

               
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