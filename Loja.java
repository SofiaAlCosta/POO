import myinputs.Ler;
import java.util.ArrayList;
public class Loja {

    public static void main(String[] args) {
        // Simula um menu de opções para o usuário

        try {
            boolean continuar = true;

            while (continuar) {
                System.out.println("------------ Bem vindo à loja de Caça e Pesca! --------------");
                System.out.println("\nMenu:");
                System.out.println("1. Registar Cliente");
                System.out.println("2. Login");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = myinputs.Ler.umInt();

                switch (opcao) {
                    case 1:
                        Cliente.registar();
                        break;
                    case 2:
                        Cliente clienteLogado = Cliente.login();

                        assert clienteLogado != null;
                        if ((clienteLogado.getNome().equals("Tiago") && clienteLogado.getContacto() == 1L && clienteLogado.getNIF() == 123)
                                || (clienteLogado.getNome().equals("Francisco") && clienteLogado.getContacto() == 2L && clienteLogado.getNIF() == 234)
                                || (clienteLogado.getNome().equals("Hugo") && clienteLogado.getContacto() == 935929079 && clienteLogado.getNIF() == 255822243)
                                || (clienteLogado.getNome().equals("Sofia") && clienteLogado.getContacto() == 4L && clienteLogado.getNIF() == 456)) {
                           if (clienteLogado.getNome().equals("Tiago"))
                        	   System.out.println("Bem vindo, admin Tiago");
                           if (clienteLogado.getNome().equals("Francisco"))
                        	   System.out.println("Bem vindo, admin Francisco");
                           if (clienteLogado.getNome().equals("Hugo"))
                            	   System.out.println("Bem vindo, admin Hugo");
                           if (clienteLogado.getNome().equals("Sofia"))
                                	   System.out.println("Bem vinda, admin Sofia");

                            boolean continuar2 = true;

                            while (continuar2) {
                                System.out.println("\n-------- Menu Admins ----------");
                                System.out.println("1: Mostrar todos clientes");
                                System.out.println("2: Limpar Ficheiro ");
                                System.out.println("3: Remover Utilizador");
                                System.out.println("4: Sair");
                                System.out.print("Escolha uma opção: ");

                                int opcao2 = myinputs.Ler.umInt();

                                switch (opcao2) {
                                    case 1:
                                        ArrayList<Cliente> clientes = Cliente.lerClientes();
                                        System.out.println("Clientes registados:");
                                        for (Cliente c : clientes) {
                                            System.out.println(c);
                                        }
                                        break;

                                    case 2:
                                        Cliente.limparFicheiro();
                                        break;

                                    case 3:
                                        Cliente.removerCliente();
                                        break;

                                    case 4:
                                        continuar2 = false;
                                        break;
                                }
                            }
                        } else {
                            boolean continuar3 = true;

                            System.out.println("--------- Menu Cliente ---------");
                            System.out.println("1: Ver produtos de Caça");
                            System.out.println("2: Ver produtos de Pesca");
                            System.out.println("3: Ver produtos de Roupa");
                            System.out.println("4: Ver Carrinho de compras");
                            System.out.println("5: Remover conta");
                            System.out.println("6: Sair");
                            System.out.print("Escolha uma opção: ");

                            int opcao3 = myinputs.Ler.umInt();


                            switch (opcao3){
                                case 1:
                                    Stock stock = Stock.lerStock();
                                    ArrayList<ProdutoCaca> produtosCaca = stock.obterProdutosCaca();

                                    if(produtosCaca.isEmpty()){
                                        System.out.println("produtos de caça indisponíveis");

                                    } else {
                                        System.out.println("Produtos de caça disponíveis:");
                                        for(ProdutoCaca produtoCaca : produtosCaca){
                                            System.out.println(produtoCaca);
                                        }
                                    }
                                    break;

                                case 2:
                                    Stock stock2 = Stock.lerStock(); 
                                    ArrayList<ProdutoPesca> produtosPesca = stock2.obterProdutosPesca();

                                    if (produtosPesca.isEmpty()) {
                                        System.out.println("Não há produtos de pesca disponíveis no momento.");
                                    } else {
                                        System.out.println("Produtos de pesca disponíveis:");
                                        for (ProdutoPesca produtoPesca : produtosPesca) {
                                            System.out.println(produtoPesca);
                                        }
                                    }
                                    break;
                                case 3:
                                    Stock stock3 = Stock.lerStock();
                                    ArrayList<ProdutoRoupas> produtosRoupas = stock3.obterProdutosRoupas();

                                    if(produtosRoupas.isEmpty()){
                                        System.out.println("produtos de roupa indisponíveis");

                                    } else {
                                        System.out.println("Produtos de roupa disponíveis:");
                                        for(ProdutoRoupas produtoRoupas : produtosRoupas){
                                            System.out.println(produtoRoupas);
                                        }
                                    }
                                    break;

                                case 4:
                                    Vendas vendas = new Vendas();
                                    vendas.exibirCarrinho();
                                    break;

                                case 5:
                                    Cliente.removerCliente();
                                    break;

                                case 6:
                                    continuar3 = false;
                                    break;

                                default:
                                    System.out.println("Opçao inválida!");
                            }
                        }
                    case 3:
                    	continuar = false;
                    	break;
                    	}
            }
        } catch (Exception e) {
            System.out.println("Erro no programa: " + e.getMessage());
        }
    }
}
