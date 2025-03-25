// Declarando Package
package DAO;

// Importando a classe Categoria do pacote Models
import Models.Categoria;

// Importando bibliotecas do jakarta.persistence
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

// Importando bibliotecas do java.util
import java.util.List;
import java.util.Scanner;

// Classe CategoriaDAO
public class CategoriaDAO {

    //    Declarando e Criando um EntityManagerFactory
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    //    Declarando o Scanner
    Scanner sc = new Scanner(System.in);

    //    Função addCategoria
    public String addCategoria(Categoria cat) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do addCategoria
        try {

//            Instanciando uma nova Categoria para o addCategoria
            Categoria newCat = new Categoria();

//            Processo de adição de um novo Produto
            System.out.println("\nInsira o ID da nova Categoria: ");
            int novoId = sc.nextInt();
            newCat.setId_categoria(novoId);
            System.out.println("\nInsira o nome da nova Categoria: ");
            String novoNome = sc.next();
            newCat.setCatCategoria(novoNome);

//            Finalização da adição e enviando nova Categoria para o BD com mensagem de sucesso
            em.getTransaction().begin();
            em.persist(newCat);
            em.getTransaction().commit();

            return "\nCategoria Adicionada com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao adicionar categoria: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getCategoria
    public void getCategoria() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getCategoria
        try {

//            Query para pegar categoria
            Query readCat = em.createQuery("SELECT cat1 FROM categoria cat1 ORDER BY cat1.id_categoria");
            List<Categoria> cat1 = readCat.getResultList();
            for (Categoria cat2 : cat1) {
                System.out.println(cat2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter categoria: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getCategoriaId
    public void getCategoriaId() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getCategoriaId
        try {

//            Solicitando Id da Categoria
            System.out.println("\nInsira o Id da Categoria que quer procurar: ");
            int idCatSolicitado = sc.nextInt();

//            Query para pegar apenas categoria de Id especificado
            Query readCat = em.createQuery("SELECT cat1 FROM categoria cat1 WHERE cat1.id_categoria = :idCat ORDER BY cat1.id_categoria");
            readCat.setParameter("idCat", idCatSolicitado);
            List<Categoria> cat1 = readCat.getResultList();
            for (Categoria cat2 : cat1) {
                System.out.println(cat2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter categoria pelo Id informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função alterarCategoria
    public String alterarCategoria(Categoria cat) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do alterarCategoria
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Categoria alterar
            System.out.println("\nInforme o ID da Categoria que deseja alterar: ");
            int idAlterador = sc.nextInt();

//            Tenta procurar a Categoria com ID igual ao fornecido
            cat = em.find(Categoria.class, idAlterador);

//            Caso não encontrado, fornece mensagem de erro
            if (cat == null) {
                return "\nCategoria não encontrada!";
            }

//            Caso encontrado, começa o processo de alteração da Categoria
            System.out.println("\nInforme o ID que deseja substituir: ");
            int idSub = sc.nextInt();
            cat.setId_categoria(idSub);
            System.out.println("\nInforme o nome da Categoria que deseja substituir: ");
            String catSub = sc.nextLine();
            cat.setCatCategoria(catSub);

//            Finalização da adição e enviando Categoria alterada para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nCategoria Alterada com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao alterar Categoria: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função excluirCategoria
    public String excluirCategoria(Categoria cat) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do excluirCategoria
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Categoria excluir
            System.out.println("\nInforme o ID da Categoria que deseja excluir: ");
            int idEx = sc.nextInt();

//            Tenta procurar a Categoria com ID igual ao fornecido
            cat = em.find(Categoria.class, idEx);

//            Caso não encontrado, fornece mensagem de erro
            if (cat == null) {
                return "\nCategoria não encontrada!";
            }

//            Caso encontrado, exlui a Categoria
            em.remove(cat);

//            Finalização da exclusão e enviando Categoria excluída para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nCategoria Excluída com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao excluir Categoria: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}