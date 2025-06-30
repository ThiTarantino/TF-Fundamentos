import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Vetores originais
        Carro[] carro = new Carro[50];
        Cliente[] cliente = new Cliente[50];
        Emprestimo[] emprestimo = new Emprestimo[50];
        
        // Matrizes adicionadas
        int[][] historicoMensal = new int[50][12]; // conta empréstimos por mês
        String[][] statusCarros = new String[50][12]; // "LIVRE" ou "OCUPADO"
        
    
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 12; j++) {
                statusCarros[i][j] = "LIVRE";
            }
        }
        
        int decisao;
        int icarro = 0;
        int icliente = 0;
        int iemprestimo = 0;
        
        
        carro[icarro++] = new Carro("modelo", "placa", 23);
        carro[icarro++] = new Carro("modelo2", "placa2", 223);
        cliente[icliente++] = new Cliente("Jorge", "teste", 24);
        cliente[icliente++] = new Cliente("Jorge2", "123123", 34);
        
        do {
            System.out.println("\n\n=== SISTEMA DE EMPRÉSTIMO DE CARROS ===");
            System.out.println("1. Cadastrar Carro(s)");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Realizar Empréstimo");
            System.out.println("4. Listar Carros");
            System.out.println("5. Listar Clientes");
            System.out.println("6. Listar Empréstimos");
            System.out.println("7. Buscar Carro por Placa");
            System.out.println("8. Buscar Cliente por CPF");
            System.out.println("9. Exclusão de cadastro de Carro");
            System.out.println("10. Exclusão de cadastro de Cliente");
            System.out.println("11. Exclusão de cadastro de Empréstimo");
            System.out.println("12. Visualizar Relatórios (MATRIZES)");
            System.out.println("0. Sair");
            decisao = sc.nextInt();
            
            switch (decisao) {
                case 1: 
                    System.out.println("\nQual o modelo do carro?");
                    sc.nextLine();
                    String modelo = sc.nextLine();
                    System.out.println("Qual a placa do carro?");
                    String placa = sc.nextLine();
                    System.out.println("Qual o ano do carro?");
                    int ano = sc.nextInt();
                    
                    carro[icarro] = new Carro(modelo, placa, ano);
                    // Inicializa status do novo carro
                    for (int i = 0; i < 12; i++) {
                        statusCarros[icarro][i] = "LIVRE";
                    }
                    icarro++;
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 2: // Cadastrar Cliente (original)
                    System.out.println("\nQual o nome do cliente?");
                    sc.nextLine();
                    String nome = sc.nextLine();
                    System.out.println("Qual o CPF do cliente?");
                    String cpf = sc.nextLine();
                    System.out.println("Qual a idade do cliente?");
                    int idade = sc.nextInt();
                    
                    cliente[icliente] = new Cliente(nome, cpf, idade);
                    icliente++;
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 3: 
                    if (icliente == 0) {
                        System.out.println("\nNão há usuários cadastrados...");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    if (icarro == 0) {
                        System.out.println("\nNão há carros disponíveis...");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    System.out.println("Qual cliente você é?");
                    for (int i = 0; i < icliente; i++) {
                        System.out.println("[" + i + "] -> " + cliente[i].getNome());
                    }
                    int decCliente = sc.nextInt();
                    
                    System.out.println("Qual data você deseja alugar o carro? (dd-mm-aaaa)");
                    sc.nextLine();
                    String decData = sc.nextLine();
                    
                    System.out.println("Qual carro você deseja alugar?");
                    for (int i = 0; i < icarro; i++) {
                        boolean ocupado = false;
                        for (int j = 0; j < iemprestimo; j++) {
                            if (carro[i].getPlaca().equals(emprestimo[j].getCarro().getPlaca()) && 
                                decData.equals(emprestimo[j].getDataemp())) {
                                ocupado = true;
                            }
                        }
                        System.out.println("[" + i + "] -> " + carro[i].getModelo() + " -> " + 
                            (ocupado ? "OCUPADO" : "DISPONÍVEL"));
                    }
                    int decCarro = sc.nextInt();
                    
                    boolean ocupado = false;
                    for (int i = 0; i < iemprestimo; i++) {
                        if (carro[decCarro].getPlaca().equals(emprestimo[i].getCarro().getPlaca()) && 
                            decData.equals(emprestimo[i].getDataemp())) {
                            ocupado = true;
                        }
                    }
                    if (ocupado) {
                        System.out.println("Carro ocupado!!!");
                        break;
                    }
                    
                    emprestimo[iemprestimo] = new Emprestimo(decData, cliente[decCliente], carro[decCarro]);
                    
                    // Atualiza matrizes (novo)
                    String[] partesData = decData.split("-");
                    int mes = Integer.parseInt(partesData[1]) - 1; // Janeiro = 0
                    historicoMensal[decCliente][mes]++;
                    statusCarros[decCarro][mes] = "OCUPADO";
                    
                    iemprestimo++;
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 4: // Listar Carros (original)
                    if (icarro == 0) {
                        System.out.println("\nNão há carros cadastrados");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    for (int i = 0; i < icarro; i++) {
                        System.out.println("\n===========================\nCarro " + i);
                        System.out.println("\nModelo: " + carro[i].getModelo());
                        System.out.println("Placa: " + carro[i].getPlaca());
                        System.out.println("Ano: " + carro[i].getAno());
                    }
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 5: // Listar Clientes (original)
                    if (icliente == 0) {
                        System.out.println("\nNão há clientes cadastrados");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    for (int i = 0; i < icliente; i++) {
                        System.out.println("\n===========================\nCliente " + i);
                        System.out.println("\nNome: " + cliente[i].getNome());
                        System.out.println("CPF: " + cliente[i].getCpf());
                        System.out.println("Idade: " + cliente[i].getIdade());
                    }
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 6: // Listar Empréstimos (original)
                    if (iemprestimo == 0) {
                        System.out.println("\nNão há empréstimos cadastrados");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    for (int i = 0; i < iemprestimo; i++) {
                        System.out.println("\n===========================\nEmpréstimo " + i);
                        System.out.println("\nCliente: " + emprestimo[i].getCliente().getNome());
                        System.out.println("Carro: " + emprestimo[i].getCarro().getModelo());
                        System.out.println("Data: " + emprestimo[i].getDataemp());
                    }
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 7: // Buscar Carro por Placa (original)
                    if (icarro == 0) {
                        System.out.println("\nNão há carros cadastrados");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    System.out.println("\nQual a placa do carro?");
                    sc.nextLine();
                    String decPlaca = sc.nextLine();
                    boolean encontrado = false;
                    
                    for (int i = 0; i < icarro; i++) {
                        if (decPlaca.equals(carro[i].getPlaca())) {
                            System.out.println("\nModelo: " + carro[i].getModelo());
                            System.out.println("Ano: " + carro[i].getAno());
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Carro não encontrado!");
                    }
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine();
                    break;
                    
                case 8: // Buscar Cliente por CPF (original)
                    if (icliente == 0) {
                        System.out.println("\nNão há clientes cadastrados");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    System.out.println("\nQual o CPF?");
                    sc.nextLine();
                    String decCpf = sc.nextLine();
                    boolean encontradoo = false;
                    
                    for (int i = 0; i < icliente; i++) {
                        if (decCpf.equals(cliente[i].getCpf())) {
                            System.out.println("\nNome: " + cliente[i].getNome());
                            System.out.println("Idade: " + cliente[i].getIdade());
                            encontradoo = true;
                            break;
                        }
                    }
                    if (!encontradoo) {
                        System.out.println("Cliente não encontrado!");
                    }
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine();
                    break;
                    
                case 9: // Excluir Carro (original)
                    if (icarro == 0) {
                        System.out.println("\nNão há carros cadastrados");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    System.out.println("Qual carro deseja excluir?");
                    int decDelCarro = sc.nextInt();
                    icarro--;
                    
                    if (decDelCarro > icarro) {
                        System.out.println("\nCarro não existe!");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    carro[decDelCarro] = carro[icarro];
                    carro[icarro] = null;
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 10: // Excluir Cliente (original)
                    if (icliente == 0) {
                        System.out.println("\nNão há clientes cadastrados");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    System.out.println("Qual cliente deseja excluir?");
                    int decDelCliente = sc.nextInt();
                    icliente--;
                    
                    if (decDelCliente > icliente) {
                        System.out.println("\nCliente não existe!");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    cliente[decDelCliente] = cliente[icliente];
                    cliente[icliente] = null;
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 11: // Excluir Empréstimo (original)
                    if (iemprestimo == 0) {
                        System.out.println("\nNão há empréstimos cadastrados");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    System.out.println("Qual empréstimo deseja excluir?");
                    int decDelEmprestimo = sc.nextInt();
                    iemprestimo--;
                    
                    if (decDelEmprestimo > iemprestimo) {
                        System.out.println("\nEmpréstimo não existe!");
                        System.out.println("\n\nAperte qualquer tecla para continuar");
                        sc.nextLine(); sc.nextLine();
                        break;
                    }
                    
                    emprestimo[decDelEmprestimo] = emprestimo[iemprestimo];
                    emprestimo[iemprestimo] = null;
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 12: // NOVA FUNCIONALIDADE 
                    System.out.println("\n=== RELATÓRIOS MENSAIS ===");
                    
                    // Relatório de clientes
                    System.out.println("\n[CLIENTES] Empréstimos por mês:");
                    System.out.println("Cliente Jan Fev Mar Abr Mai Jun Jul Ago Set Out Nov Dez");
                    for (int i = 0; i < icliente; i++) {
                        System.out.print(cliente[i].getNome() + "\t");
                        for (int j = 0; j < 12; j++) {
                            System.out.print(historicoMensal[i][j] + "   ");
                        }
                        System.out.println();
                    }
                    
                    // Relatório de carros
                    System.out.println("\n[CARROS] Status por mês:");
                    System.out.println("Carro Jan Fev Mar Abr Mai Jun Jul Ago Set Out Nov Dez");
                    for (int i = 0; i < icarro; i++) {
                        System.out.print(carro[i].getModelo() + "\t");
                        for (int j = 0; j < 12; j++) {
                            System.out.print(statusCarros[i][j].charAt(0) + "   ");
                        }
                        System.out.println();
                    }
                    
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
                    break;
                    
                case 0:
                    System.out.println("\nCódigo encerrado!");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
                    System.out.println("\n\nAperte qualquer tecla para continuar");
                    sc.nextLine(); sc.nextLine();
            }
        } while (decisao != 0);
    }
}