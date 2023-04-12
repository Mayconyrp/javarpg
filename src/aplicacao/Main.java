package aplicacao;

import java.util.Date;
import java.util.Scanner;

import models.personagem;
import rpgDAO.rpgDAO;



public class Main {

    private static rpgDAO rpgDAO = new rpgDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = 0;

        do {
            System.out.println("Menu");
            System.out.println("1 - Listar personagens");
            System.out.println("2 - Adicionar personagem");
            System.out.println("3 - Batalhar");

            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarPersonagens();
                    break;
                case 2:
                    adicionarPersonagem();
                    break;
                case 3:
                    batalhar();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);

    }

    private static void listarPersonagens() {

        System.out.println("Lista de personagens:");

        for (personagem p : rpgDAO.getPersonagem()) {
            System.out.println("ID: " + p.getId() + " - Nome: " + p.getNome() + " - Level: " + p.getLevel() + " - Ataque: " + p.getAtaque() + " - Defesa: " + p.getDefesa() + " - Data de cadastro: " + p.getDataCadastro());
        }

    }

    private static void adicionarPersonagem() {

        personagem personagem = new personagem();

        System.out.println("Digite o nome do personagem:");
        personagem.setNome(scanner.nextLine());

        System.out.println("Digite o level do personagem:");
        personagem.setLevel(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Digite o ataque do personagem:");
        personagem.setAtaque(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Digite a defesa do personagem:");
        personagem.setDefesa(scanner.nextInt());
        scanner.nextLine();

        personagem.setDataCadastro(new Date());

        rpgDAO.save(personagem);

        System.out.println("Personagem adicionado com sucesso!");

    }
    
    public static void batalhar() {
        System.out.println("Digite o ID do primeiro personagem:");
        int id1 = scanner.nextInt();
        System.out.println("Digite o ID do segundo personagem:");
        int id2 = scanner.nextInt();
        String resultado = rpgDAO.batalhar(id1, id2);
        System.out.println(resultado);
    }


}

/*public class Main {
	
    private static rpgDAO rpgDAO = new rpgDAO();
    private static Scanner scanner = new Scanner(System.in);

  

	public static void main(String[] args) {
		
		listarPersonagens();
		
		rpgDAO rpgDAO = new rpgDAO();
		
		personagem personagem = new personagem();
		personagem.setId(1);
		personagem.setNome("Mark");
		personagem.setLevel(1);
		personagem.setAtaque(4);
		personagem.setDefesa(2);
		personagem.setDataCadastro(new Date());

		rpgDAO.save(personagem);
		
	    
	}
	
	private static void listarPersonagens() {

        for (personagem p : rpgDAO.getPersonagem()) {
            System.out.println("Contato Salvo: ID " + p.getId() + " - " + p.getNome());
        }
    }

}
*/
