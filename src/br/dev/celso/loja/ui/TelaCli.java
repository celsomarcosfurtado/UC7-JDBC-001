package br.dev.celso.loja.ui;

import br.dev.celso.loja.dao.ContatoDao;
import br.dev.celso.loja.data.Conexao;
import br.dev.celso.loja.model.Contato;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelaCli {

    public static void criarMenuPrincipal(){
        Scanner leitor = new Scanner(System.in);
        System.out.println("----------------------------------------------------------");
        System.out.println(" C A D A S T R O  D E  C O N T A T O S");
        System.out.println("----------------------------------------------------------");
        System.out.println();
        System.out.println("M E N U");
        System.out.println("----------------------------------------------------------");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Buscar por ID");
        System.out.println("3 - Listar todos os contatos");
        System.out.println("4 - Excluir por ID");
        System.out.println("5 - Sair");
        System.out.println("----------------------------------------------------------");
        System.out.print("Escolha uma opção (1 - 5): ");

        int escolha = leitor.nextInt();

        switch (escolha){
            case 1:
                cadastrar();
                break;
            case 2:
                buscarContatoPorId();
                break;
            case 3:
                listar();
                break;
            case 4:
                System.out.println("Excluir por ID");
                break;
            case 5:
                System.out.println("Saindo do sistema...");
                Conexao.fecharConexao();
                break;
            default:
                System.out.println("Escolha incorreta!!!");
                criarMenuPrincipal();
        }

    }

    private static void cadastrar(){
        Scanner leitor = new Scanner(System.in);

        System.out.println("----------------------------------------------------------");
        System.out.println("N O V O  C O N T A T O");
        System.out.println("----------------------------------------------------------");
        System.out.println();
        System.out.print("Nome do contato: ");
        String nome = leitor.nextLine();
        System.out.print("E-mail do contato: ");
        String email = leitor.nextLine();
        System.out.print("Salário do contato: ");
        double salario = leitor.nextDouble();

        ContatoDao dao = new ContatoDao(
                new Contato(nome, email, salario)
        );

        int id = dao.salvar();

        System.out.println("Contato gravado com ID: " + id);
        System.out.println();

        confirmarSair();
    }

    private static void confirmarSair() {
        Scanner leitor = new Scanner(System.in);
        System.out.print("Voltar ao menu principal (S/N)? ");
        String resposta = leitor.next();

        if (resposta.equalsIgnoreCase("s")){
            criarMenuPrincipal();
        }

        Conexao.fecharConexao();
    }

    private static void listar(){

        List<Contato> contatos = new ArrayList<>();

        ContatoDao dao = new ContatoDao();
        contatos = dao.listar();

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("L I S T A   D E   C O N T A T OS");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.printf("%5s %-35s %-35s %15s%n", "ID", "NOME", "E-MAIL", "SALÁRIO");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for(Contato c : contatos){
            System.out.printf("%5s %-35s %-35s %15s%n", c.getId(), c.getNome(), c.getEmail(), String.format("%.2f", c.getSalario()));
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        confirmarSair();

    }

    private static void buscarContatoPorId(){
        Scanner leitor = new Scanner(System.in);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------------------");
        System.out.println("B U S C A R  C O N T A T O");
        System.out.println("----------------------------------------------------------");
        System.out.println();
        System.out.print("ID do contato: ");
        int id = leitor.nextInt();

        ContatoDao dao = new ContatoDao();
        Contato c = dao.buscarContatoPeloId(id);
        System.out.println(c);

        System.out.println();
        confirmarSair();
    }

}
