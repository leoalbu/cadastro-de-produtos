import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Produto> produtos = new ArrayList<>(); //instancia sempre tem o NEW


    public static void main(String[] args) {
        menu();

    }

    public static void cadastroProd() {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o produto: ");
        String nome = leitor.next();
        System.out.println("Digite o preço do produto");
        Double preco = leitor.nextDouble();
        System.out.println("Digite a cor");
        String cor = leitor.next();
        System.out.println("Digite a descrição");
        String descricao = leitor.next();
        System.out.println("Digite o estoque");
        int estoque = leitor.nextInt();


        // criar o produto com os dados do usuario

        Produto produto = new Produto(nome, preco, cor, descricao, estoque);// instancia do produto
        produtos.add(produto);



        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void deletar() {
        visualizar();
        Scanner leitor = new Scanner(System.in);
        System.out.println("Qual o indice do item que deseja cancelar?");
        int indice = leitor.nextInt();
        produtos.remove(indice);
        System.out.println("Produto deletado");

    }

    public static void visualizar() {
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(i + " " + produtos.get(i).getNome() + ", " + produtos.get(i).getPreco() + ", " +  produtos.get(i).getCor() + ", " +  produtos.get(i).getEstoque() + "," + produtos.get(i).getDescricao());
        }
    }

    public static void editar() {
        visualizar();
        Scanner leitor = new Scanner(System.in);
        System.out.println("Qual o indice do item que deseja editar?");
        int indice = leitor.nextInt();

        System.out.println("Qual será o novo produto?");
        String novoNome = leitor.next();
        System.out.println("Qual será o novo preço?");
        double novoPreco = leitor.nextDouble();
        System.out.println("Qual será a nova cor?");
        String novaCor = leitor.next();
        System.out.println("Qual será a nova descrição");
        String novaDescricao = leitor.next();
        System.out.println("Qual será a novo estoque");
        int novoEstoque = leitor.nextInt();
        Produto produto = new Produto(novoNome, novoPreco, novaCor, novaDescricao, novoEstoque);
        produtos.set(indice, produto);
        System.out.println("Produto editado" );

    }

    public static void menu() { //metodo recursivo
        Scanner leitor = new Scanner(System.in);
        System.out.println("1 - Cadastrar produto \n 2 - Deletar produto\n 3- Editar produto\n 4- Visualizar produto\n 5 - Sair");
        int opcao = leitor.nextInt();
        if (opcao == 1) {
            cadastroProd();
            menu();
        } else if (opcao == 2) {
            deletar();
            menu();
        } else if (opcao == 3) {
            editar();
            menu();
        } else if (opcao == 4) {
            visualizar();
            menu();
        } else if (opcao == 5) {
            System.out.println("Encerrando programa");
        } else {
            System.out.println("Opção inválida");
        }
    }
}

//exercicio - criar um cadastro de aluno, professor, funcionario, medicamentos..etc


