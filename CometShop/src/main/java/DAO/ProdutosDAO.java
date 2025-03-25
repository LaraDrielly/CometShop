// Declarando Package
package DAO;

// Importando a classe Produtos do pacote Models
import Models.Produtos;

// Importando bibliotecas do jakarta.persistence
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

// Importando bibliotecas do java.util
import java.util.List;
import java.util.Scanner;

// Classe ProdutosDAO
public class ProdutosDAO {

    //    Declarando e Criando um EntityManagerFactory
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    //    Declarando o Scanner
    Scanner sc = new Scanner(System.in);

    //    Função addProdutos
    public String addProdutos(Produtos prod) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do addProdutos
        try {

//            Instanciando um novo Produto para o addProdutos
            Produtos newProd = new Produtos();

//            Processo de adição de um novo Produto
            System.out.println("\nInsira o ID do novo Produto: ");
            int novoId = sc.nextInt();
            newProd.setId_produto(novoId);
            System.out.println("\nInsira o nome do novo Produto: ");
            String novoNome = sc.next();
            newProd.setProdNome(novoNome);
            System.out.println("\nInsira a descrição do novo Produto: ");
            String novaDesc = sc.next();
            newProd.setProdDesc(novaDesc);
            System.out.println("\nInsira o preço do novo Produto: ");
            float novoPreco = sc.nextFloat();
            newProd.setProdPreco(novoPreco);
            System.out.println("\nInsira o código de Usuário: ");
            int novoCodUser = sc.nextInt();
            newProd.setCod_user(novoCodUser);

//            Finalização da adição e enviando novo Produto para o BD com mensagem de sucesso
            em.getTransaction().begin();
            em.persist(newProd);
            em.getTransaction().commit();

            return "\nProduto Adicionado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao adicionar produto: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getProdutos
    public void getProdutos() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getProdutos
        try {

//            Query para pegar produtos
            Query readProds = em.createQuery("SELECT prod1 FROM produtos prod1 ORDER BY prod1.id_produto");
            List<Produtos> prod1 = readProds.getResultList();
            for (Produtos prod2 : prod1) {
                System.out.println(prod2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter produtos: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getProdutosCodUser
    public void getProdutosCodUser() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getProdutosCodUser
        try {

//            Solicitando Código de Usuário
            System.out.println("\nInsira o Código de Usuário que quer procurar: ");
            int codUserSolicitado = sc.nextInt();

//            Query para pegar apenas produtos de código especificado
            Query readProd = em.createQuery("SELECT prod1 FROM produtos prod1 WHERE prod1.cod_user = :codUser ORDER BY prod1.cod_user");
            readProd.setParameter("codUser", codUserSolicitado);
            List<Produtos> prod1 = readProd.getResultList();
            for (Produtos prod2 : prod1) {
                System.out.println(prod2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter produtos pelo código informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função alterarProdutos
    public String alterarProdutos(Produtos prod) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do alterarProdutos
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Produto alterar
            System.out.println("\nInforme o ID do Produto que deseja alterar: ");
            int idAlterador = sc.nextInt();

//            Tenta procurar o Produto com ID igual ao fornecido
            prod = em.find(Produtos.class, idAlterador);

//            Caso não encontrado, fornece mensagem de erro
            if (prod == null) {
                return "\nProduto não encontrado!";
            }

//            Caso encontrado, começa o processo de alteração de Produto
            System.out.println("\nInforme o ID que deseja substituir: ");
            int idSub = sc.nextInt();
            prod.setId_produto(idSub);
            System.out.println("\nInforme o código de usuário que deseja substituir: ");
            int codSub = sc.nextInt();
            prod.setCod_user(codSub);
            System.out.println("\nInforme a descrição que deseja substituir: ");
            String descSub = sc.next();
            prod.setProdDesc(descSub);
            System.out.println("\nInforme o nome que deseja substituir: ");
            String nomeSub = sc.next();
            prod.setProdNome(nomeSub);
            System.out.println("\nInforme o preço que deseja substituir: ");
            float precoSub = sc.nextFloat();
            prod.setProdPreco(precoSub);

//            Finalização da adição e enviando Produto alterado para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nProduto Alterado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao alterar Produto: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função excluirProduto
    public String excluirProduto(Produtos prod) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do excluirProduto
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Produto excluir
            System.out.println("\nInforme o ID do Produto que deseja excluir: ");
            int idEx = sc.nextInt();

//            Tenta procurar o Produto com ID igual ao fornecido
            prod = em.find(Produtos.class, idEx);

//            Caso não encontrado, fornece mensagem de erro
            if (prod == null) {
                return "\nProduto não encontrado!";
            }

//            Caso encontrado, exlui o Produto
            em.remove(prod);

//            Finalização da exclusão e enviando Produto excluído para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nProduto Excluído com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao excluir Produto: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}