import myinputs.Ler;
import java.util.ArrayList;
public class Loja {

    public static void main(String[] args) {
        // Simula um menu de opções para o usuário
        Stock stock = new Stock();

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
                        System.out.println("Login");
                        System.out.print("Nome: ");
                        String nome = myinputs.Ler.umaString();

                        System.out.print("Contacto: ");
                        int contacto = myinputs.Ler.umInt();

                        System.out.print("NIF: ");
                        int nif = myinputs.Ler.umInt();

                        // Verificar se o login é de um admin
                        if ((nome.equals("Tiago") && contacto == 912293655 && nif == 245386491) ||
                                (nome.equals("Francisco") && contacto == 918815254 && nif == 263594530) ||
                                (nome.equals("Hugo") && contacto == 935929079 && nif == 255822243) ||
                                (nome.equals("Sofia") && contacto == 962196645 && nif == 246385391)) {

                            System.out.println("\n\n\nBem-vindo, admin " + nome);

                            boolean continuarAdmin = true;

                            while (continuarAdmin) {
                                System.out.println("\n-------- Menu Admins ----------");
                                System.out.println("1: Mostrar todos clientes");
                                System.out.println("2: Limpar Ficheiro");
                                System.out.println("3: Remover Utilizador");
                                System.out.println("4: Voltar ao Menu Inicial");
                                System.out.println("5: Sair");
                                System.out.println("------- gestao de stock -------");
                                System.out.println("6: Adicionar produto de Caça ao stock");
                                System.out.println("7: Adicionar produto de Pesca ao stock");
                                System.out.println("8: Adicionar Roupas ao stock");
                                System.out.println("9: Mostrar stock");
                                System.out.print("Escolha uma opção: ");

                                int opcaoAdmin = myinputs.Ler.umInt();

                                switch (opcaoAdmin) {
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
                                        continuarAdmin = false;
                                        break;

                                    case 5:
                                        continuar = false;
                                        continuarAdmin = false;
                                        break;

                                    case 6:
                                        System.out.println("Adicionar Produto de Caça");

                                        // Coletando informações básicas do produto
                                        System.out.print("Nome: ");
                                        String nomeCaca = myinputs.Ler.umaString();

                                        System.out.print("Descrição: ");
                                        String descricaoCaca = myinputs.Ler.umaString();

                                        System.out.print("Preço: ");
                                        double precoCaca = myinputs.Ler.umDouble();

                                        System.out.print("Categoria (fixado como 'Caça'): ");
                                        String categoriaCaca = "Caça"; // Categoria predefinida

                                        System.out.print("Ativo (true/false): ");
                                        boolean ativoCaca = myinputs.Ler.umBoolean();

                                        // Coletando informações específicas do ProdutoCaca
                                        System.out.print("País de Origem: ");
                                        String paisOrigem = myinputs.Ler.umaString();

                                        System.out.print("Alcance Máximo: ");
                                        double alcanceMaximo = myinputs.Ler.umDouble();

                                        System.out.print("Peso: ");
                                        double pesoCaca = myinputs.Ler.umDouble();

                                        System.out.print("Material: ");
                                        String materialCaca = myinputs.Ler.umaString();

                                        System.out.print("Requisitos de Licença (true/false): ");
                                        boolean requisitosLicenca = myinputs.Ler.umBoolean();

                                        System.out.print("Garantia (em meses): ");
                                        int garantiaCaca = myinputs.Ler.umInt();

                                        System.out.print("Resistência à Água (true/false): ");
                                        boolean resistenciaAgua = myinputs.Ler.umBoolean();

                                        System.out.print("Nível de Segurança: ");
                                        String nivelSeguranca = myinputs.Ler.umaString();

                                        System.out.println("Quantidade inicial de unidades: ");
                                        int quantidadeInicial = myinputs.Ler.umInt();

                                        // Criando o produto base
                                        Produto produtoBaseCaca = new Produto(nomeCaca, descricaoCaca, precoCaca, categoriaCaca, ativoCaca);

                                        // Criando o produto de caça com base no produto base
                                        ProdutoCaca produtoCaca = new ProdutoCaca(produtoBaseCaca, paisOrigem, alcanceMaximo, pesoCaca, materialCaca, requisitosLicenca, garantiaCaca, resistenciaAgua, nivelSeguranca);

                                        // Adicionando ao estoque
                                        stock.adicionarProduto(produtoCaca, quantidadeInicial); // vai já com a quantidade inicial
                                        stock.salvarStock();

                                        System.out.println("Produto de Caça adicionado com sucesso!");
                                        break;

                                    case 7:
                                        System.out.println("Adicionar Produto de Pesca");

                                        System.out.print("Nome: ");
                                        String nomePesca = myinputs.Ler.umaString();

                                        System.out.print("Descrição: ");
                                        String descricaoPesca = myinputs.Ler.umaString();

                                        System.out.print("Preço: ");
                                        double precoPesca = myinputs.Ler.umDouble();

                                        System.out.print("Categoria (fixado como 'Pesca'): ");
                                        String categoriaPesca = "Pesca"; // Categoria predefinida

                                        System.out.print("Ativo (true/false): ");
                                        boolean ativoPesca = myinputs.Ler.umBoolean();

                                        // Coletando informações específicas do ProdutoPesca
                                        System.out.print("Tipo de Equipamento: ");
                                        String tipoEquipamento = myinputs.Ler.umaString();

                                        System.out.print("Material: ");
                                        String materialPesca = myinputs.Ler.umaString();

                                        System.out.print("Peso: ");
                                        double pesoPesca = myinputs.Ler.umDouble();

                                        System.out.print("Tamanho (em cm): ");
                                        int tamanhoPesca = myinputs.Ler.umInt();

                                        System.out.print("País de Origem: ");
                                        String paisOrigemPesca = myinputs.Ler.umaString();

                                        System.out.print("Garantia (em meses): ");
                                        int garantiaPesca = myinputs.Ler.umInt();

                                        System.out.println("Quantidade inicial de unidades: ");
                                        int quantidadeInicialPesca = myinputs.Ler.umInt();

                                        // Criando o produto base
                                        Produto produtoBasePesca = new Produto(nomePesca, descricaoPesca, precoPesca, categoriaPesca, ativoPesca);

                                        // Criando o produto de pesca com base no produto base
                                        ProdutoPesca produtoPesca = new ProdutoPesca(produtoBasePesca, tipoEquipamento, materialPesca, pesoPesca, tamanhoPesca, paisOrigemPesca, garantiaPesca);

                                        // Adicionando ao estoque
                                        stock.adicionarProduto(produtoPesca, quantidadeInicialPesca); // vai já com a quantidade inicial
                                        stock.salvarStock();

                                        System.out.println("Produto de Pesca adicionado com sucesso!");
                                        break;

                                    case 8:
                                        System.out.println("Adicionar Produto de Roupa");

                                        System.out.print("Nome: ");
                                        String nomeRoupa = myinputs.Ler.umaString();

                                        System.out.print("Descrição: ");
                                        String descricaoRoupa = myinputs.Ler.umaString();

                                        System.out.print("Preço: ");
                                        double precoRoupa = myinputs.Ler.umDouble();

                                        System.out.print("Categoria (fixado como 'Roupa'): ");
                                        String categoriaRoupa = "Roupa"; // Categoria predefinida

                                        System.out.print("Ativo (true/false): ");
                                        boolean ativoRoupa = myinputs.Ler.umBoolean();

                                        // Coletando informações específicas do ProdutoRoupas
                                        System.out.print("Tamanho (P/M/G/Outro): ");
                                        String tamanhoRoupa = myinputs.Ler.umaString();

                                        System.out.print("Gênero (Masculino/Feminino/Unissex): ");
                                        String generoRoupa = myinputs.Ler.umaString();

                                        System.out.println("Quantidade inicial de unidades: ");
                                        int quantidadeInicialRoupa = myinputs.Ler.umInt();

                                        // Criando o produto base
                                        Produto produtoBaseRoupa = new Produto(nomeRoupa, descricaoRoupa, precoRoupa, categoriaRoupa, ativoRoupa);

                                        // Criando o produto de roupa com base no produto base
                                        ProdutoRoupas produtoRoupa = new ProdutoRoupas(produtoBaseRoupa, null, precoRoupa, tamanhoRoupa, generoRoupa, null);

                                        // Adicionando ao estoque
                                        stock.adicionarProduto(produtoRoupa, quantidadeInicialRoupa); // vai já com a quantidade inicial
                                        stock.salvarStock();

                                        System.out.println("Produto de Roupa adicionado com sucesso!");
                                        break;

                                    case 9:
                                        stock.mostrarStock();
                                        break;

                                    default:
                                        System.out.println("Opção inválida!");
                                }
                            }
                        } else {
                            // Não é admin, verificar como cliente
                            Cliente clienteLogado = Cliente.login();

                            if (clienteLogado != null) {
                                System.out.println("Bem-vindo, " + clienteLogado.getNome());
                                boolean continuarCliente = true;

                                while (continuarCliente) {
                                    System.out.println("--------- Menu Cliente ---------");
                                    System.out.println("1: Ver produtos de Caça");
                                    System.out.println("2: Ver produtos de Pesca");
                                    System.out.println("3: Ver produtos de Roupa");
                                    System.out.println("4: Ver Carrinho de compras");
                                    System.out.println("5: Remover conta");
                                    System.out.println("6: Voltar ao Menu Inicial");
                                    System.out.println("7: Sair");
                                    System.out.print("Escolha uma opção: ");

                                    int opcaoCliente = myinputs.Ler.umInt();

                                    switch (opcaoCliente) {
                                        case 1:
                                            Stock.lerStock();
                                            ArrayList<ProdutoCaca> produtosCaca = stock.obterProdutosCaca();

                                            if (produtosCaca.isEmpty()) {
                                                System.out.println("Produtos de caça indisponíveis.");
                                            } else {
                                                System.out.println("Produtos de caça disponíveis:");
                                                for (ProdutoCaca produtoCaca : produtosCaca) {
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

                                            if (produtosRoupas.isEmpty()) {
                                                System.out.println("Produtos de roupa indisponíveis.");
                                            } else {
                                                System.out.println("Produtos de roupa disponíveis:");
                                                for (ProdutoRoupas produtoRoupas : produtosRoupas) {
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
                                            continuarCliente = false;
                                            break;

                                        case 7:
                                            continuar = false;
                                            continuarCliente = false;
                                            break;

                                        default:
                                            System.out.println("Opção inválida!");
                                    }
                                }
                            }
                        }
                        break;

                    case 3:
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
