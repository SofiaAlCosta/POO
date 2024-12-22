import myinputs.Ler;
import java.util.ArrayList;
public class Loja {

	public static void main(String[] args) {
        // Simula um menu de opções para o usuário
        try {
            boolean continuar = true;

            while (continuar) {
                System.out.println("\nMenu:");
                System.out.println("1. Registrar Cliente");
                System.out.println("2. Login");
                System.out.println("3. Carregar e Mostrar Todos os Clientes");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = myinputs.Ler.umInt();

                switch (opcao) {
                    case 1:
                        Cliente.registrar();
                        break;
                    case 2:
                        Cliente.login();
                        break;
                    case 3:
                        ArrayList<Cliente> clientes = Cliente.carregarClientes();
                        System.out.println("Clientes registrados:");
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                        break;
                    case 4:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
        	System.out.println("Erro no programa: " + e.getMessage());
		
	}

}
}
