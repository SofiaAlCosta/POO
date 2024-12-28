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
                System.out.println("3. Carregar e Mostrar Todos os Clientes");
                System.out.println("4. Limpar Ficheiro");
                System.out.println("5. Remover Utilizador");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = myinputs.Ler.umInt();

                switch (opcao) {
                    case 1:
                        Cliente.registar();
                        break;
                    case 2:
                        Cliente clienteLogado = Cliente.login();

                        assert clienteLogado != null;
                        if ((clienteLogado.getNome().equals("tiago") && clienteLogado.getContacto() == 1L && clienteLogado.getNIF() == 123)
                                || (clienteLogado.getNome().equals("francisco") && clienteLogado.getContacto() == 2L && clienteLogado.getNIF() == 234)
                                || (clienteLogado.getNome().equals("hugo") && clienteLogado.getContacto() == 3L && clienteLogado.getNIF() == 345)
                                || (clienteLogado.getNome().equals("sofia") && clienteLogado.getContacto() == 4L && clienteLogado.getNIF() == 456)) {
                            System.out.println("Bem vindo, admin Tiago");

                            boolean continuar2 = true;

                            while (continuar2) {
                                System.out.println("\n-------- Menu Admins ----------");
                                System.out.println("1: Mostrar todos clientes");
                                System.out.println("2: Limpar Ficheiro ");
                                System.out.println("3: Remover Utilizador");
                                System.out.println("1: Sair");

                                int opcao2 = myinputs.Ler.umInt();

                                switch (opcao2) {
                                    case 1:
                                        ArrayList<Cliente> clientes = Cliente.lerClientes();
                                        System.out.println("Clientes registrados:");
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
                            System.out.println("1: Ver produtos Caça");
                            System.out.println("1: Ver produtos Pesca");
                            System.out.println("1: Ver Carrinho de compras");
                            System.out.println("4: Remover conta");
                            System.out.println("5: Sair");

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
                                    Stock stock2 = Stock.lerStock(); // Carregar o estado atual do estoque
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
                                    Vendas vendas = new Vendas();
                                    vendas.exibirCarrinho();
                                    break;

                                case 4:
                                    Cliente.removerCliente();
                                    break;

                                case 5:
                                    continuar3 = false;
                                    break;

                                default:
                                    System.out.println("Opçao inválida!");
                            }
                        }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no programa: " + e.getMessage());
        }
    }
}

/*
                }
            }
        } catch (Exception e) {
        	System.out.println("Erro no programa: " + e.getMessage());
		
	}

}
}
    case 3:
                        ArrayList<Cliente> clientes = Cliente.lerClientes();
                        System.out.println("Clientes registados:");
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                        break;
                    case 4:
                    	Cliente.limparFicheiro();
                        break;
                    case 5:
                    	Cliente.removerCliente();
                    	break;
                    case 6:
                    	continuar = false;
                    	break;
                    default:
                        System.out.println("Opção inválida!")*/