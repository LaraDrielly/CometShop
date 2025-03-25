// Declarando Package
package DAO;

// Importando a classe Categoria do pacote Models
import Models.Estoque;

// Importando bibliotecas do jakarta.persistence
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

// Importando bibliotecas do java.util
import java.util.List;
import java.util.Scanner;

// Classe EstoqueDAO
public class EstoqueDAO {

    //    Declarando e Criando um EntityManagerFactory
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    //    Declarando o Scanner
    Scanner sc = new Scanner(System.in);

    //    Função addEstoque
    public String addEstoque(Estoque est) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do addEstoque
        try {

//            Instanciando um novo Estoque para o addEstoque
            Estoque newEst = new Estoque();

//            Processo de adição de um novo Estoque
            System.out.println("\nInsira o ID do novo Estoque: ");
            int novoId = sc.nextInt();
            newEst.setId_estoque(novoId);
            System.out.println("\nInsira o código de produto do novo Estoque: ");
            int novoCodProd = sc.nextInt();
            newEst.setCod_produto(novoCodProd);
            System.out.println("\nInsira a Quantidade em Estoque desse Produto: ");
            int novoQuant = sc.nextInt();
            newEst.setQuantEstoque(novoQuant);

//            Finalização da adição e enviando novo Estoque para o BD com mensagem de sucesso
            em.getTransaction().begin();
            em.persist(newEst);
            em.getTransaction().commit();

            return "\nEstoque Adicionado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao adicionar estoque: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getEstoque
    public void getEstoque() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getEstoque
        try {

//            Query para pegar estoque
            Query readEst = em.createQuery("SELECT est1 FROM estoque est1 ORDER BY est1.id_estoque");
            List<Estoque> est1 = readEst.getResultList();
            for (Estoque est2 : est1) {
                System.out.println(est2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter estoque: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getEstoqueId
    public void getEstoqueId() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getEstoqueId
        try {

//            Solicitando Id do Estoque
            System.out.println("\nInsira o Id do Estoque que quer procurar: ");
            int idEstSolicitado = sc.nextInt();

//            Query para pegar apenas estoque de Id especificado
            Query readEst = em.createQuery("SELECT est1 FROM estoque est1 WHERE est1.id_estoque = :idEst ORDER BY est1.id_estoque");
            readEst.setParameter("idEst", idEstSolicitado);
            List<Estoque> est1 = readEst.getResultList();
            for (Estoque est2 : est1) {
                System.out.println(est2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter estoque pelo Id informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    //    Função getEstoqueCodProd
    public void getEstoqueCodProd() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getEstoqueCodProd
        try {

//            Solicitando Código do Produto
            System.out.println("\nInsira o Código do Produto que quer procurar no Estoque: ");
            int codProdSolicitado = sc.nextInt();

//            Query para pegar apenas estoque que contenha o código do produto especificado
            Query readEst = em.createQuery("SELECT est1 FROM estoque est1 WHERE est1.cod_produto = :codProd ORDER BY est1.cod_produto");
            readEst.setParameter("codProd", codProdSolicitado);
            List<Estoque> est1 = readEst.getResultList();
            for (Estoque est2 : est1) {
                System.out.println(est2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter estoque pelo código de produto informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função alterarEstoque
    public String alterarEstoque(Estoque est) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do alterarEstoque
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Estoque alterar
            System.out.println("\nInforme o ID do Estoque que deseja alterar: ");
            int idAlterador = sc.nextInt();

//            Tenta procurar o Estoque com ID igual ao fornecido
            est = em.find(Estoque.class, idAlterador);

//            Caso não encontrado, fornece mensagem de erro
            if (est == null) {
                return "\nEstoque não encontrado!";
            }

//            Caso encontrado, começa o processo de alteração do Estoque
            System.out.println("\nInforme o ID que deseja substituir: ");
            int idSub = sc.nextInt();
            est.setId_estoque(idSub);
            System.out.println("\nInforme o código do Produto que deseja substituir: ");
            int codProdSub = sc.nextInt();
            est.setCod_produto(codProdSub);
            System.out.println("\nInforme a quantidade do Estoque que deseja substituir: ");
            int estQuant = sc.nextInt();
            est.setQuantEstoque(estQuant);

//            Finalização da adição e enviando Estoque alterado para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nEstoque Alterado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao alterar Estoque: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função diminuirEstoque
    public String diminuirEstoque(Estoque est) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do diminuirEstoque
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Estoque diminuir
            System.out.println("\nInforme o ID do Estoque que deseja diminuir: ");
            int idAlterador = sc.nextInt();

//            Tenta procurar o Estoque com ID igual ao fornecido
            est = em.find(Estoque.class, idAlterador);

//            Caso não encontrado, fornece mensagem de erro
            if (est == null) {
                return "\nEstoque não encontrado!";
            }

//            Caso encontrado, começa o processo de decrementação do Estoque
            System.out.println("\nInforme a quantidade do Estoque que deseja diminuir: ");
            int estQuant = sc.nextInt();
            est.setQuantEstoque(est.getQuantEstoque() - estQuant);

//            Finalização da subtração e enviando Estoque decrementando para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nEstoque decrementado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao decrementar Estoque: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função aumentarEstoque
    public String aumentarEstoque(Estoque est) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do aumentarEstoque
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Estoque aumentar
            System.out.println("\nInforme o ID do Estoque que deseja aumentar: ");
            int idAlterador = sc.nextInt();

//            Tenta procurar o Estoque com ID igual ao fornecido
            est = em.find(Estoque.class, idAlterador);

//            Caso não encontrado, fornece mensagem de erro
            if (est == null) {
                return "\nEstoque não encontrado!";
            }

//            Caso encontrado, começa o processo de incrementação do Estoque
            System.out.println("\nInforme a quantidade do Estoque que deseja aumentar: ");
            int estQuant = sc.nextInt();
            est.setQuantEstoque(est.getQuantEstoque() + estQuant);

//            Finalização da adição e enviando Estoque incrementado para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nEstoque incrementado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao incrementar Estoque: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função excluirEstoque
    public String excluirEstoque(Estoque est) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do excluirEstoque
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Estoque excluir
            System.out.println("\nInforme o ID do Estoque que deseja excluir: ");
            int idEx = sc.nextInt();

//            Tenta procurar o Estoque com ID igual ao fornecido
            est = em.find(Estoque.class, idEx);

//            Caso não encontrado, fornece mensagem de erro
            if (est == null) {
                return "\nEstoque não encontrado!";
            }

//            Caso encontrado, exlui o Estoque
            em.remove(est);

//            Finalização da exclusão e enviando Estoque excluído para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nEstoque Excluído com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao excluir Estoque: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}