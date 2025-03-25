// Declarando Package
package DAO;

// Importando a classe ADM do pacote Models
import Models.ADM;

// Importando bibliotecas do jakarta.persistence
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

// Importando bibliotecas do java.util
import java.util.List;
import java.util.Scanner;

// Classe ADMDAO
public class ADMDAO {

//    Declarando e Criando um EntityManagerFactory
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

//    Declarando o Scanner
    Scanner sc = new Scanner(System.in);

//    Função addAdmin
    public String addAdmin(ADM adm) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do addAdmin
        try {

//            Instanciando um novo ADM para o addAdmin
            ADM newAdm = new ADM();

//            Processo de adição de um novo ADM
            System.out.println("\nInsira o ID do novo Administrador: ");
            int novoId = sc.nextInt();
            newAdm.setId_adm(novoId);
            System.out.println("\nInsira o nome completo do novo Administrador: ");
            String novoNome = sc.nextLine();
            newAdm.setAdmNome(novoNome);
            System.out.println("\nInsira o E-mail do novo Administrador: ");
            String novoEmail = sc.next();
            newAdm.setAdmEmail(novoEmail);
            System.out.println("\nInsira a senha do novo Administrador: ");
            String novaSenha = sc.nextLine();
            newAdm.setAdmSenha(novaSenha);
            newAdm.setAdmStatus(true);

//            Finalização da adição e enviando novo ADM para o BD com mensagem de sucesso
            em.getTransaction().begin();
            em.persist(newAdm);
            em.getTransaction().commit();

            return "\nAdministrador Adicionado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao adicionar administrador: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


//    Função getAdminsStatusTrue
    public void getAdminsStatusTrue() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getAdminsStatusTrue
        try {

//            Query para pegar apenas admins que estão ativos (status = true)
            Query readAdmin = em.createQuery("SELECT admin1 FROM administrador admin1 WHERE admin1.status = true ORDER BY admin1.id_administador");
            List<ADM> admin1 = readAdmin.getResultList();
            for (ADM admin2 : admin1) {
                System.out.println(admin2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter administradores ativos: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


//    Função getAdminsStatusFalse
    public void getAdminsStatusFalse() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getAdminsStatusFalse
        try {

//            Query para pegar apenas admins que estão inativos (status = false)
            Query readAdmin = em.createQuery("SELECT admin1 FROM administrador admin1 WHERE admin1.status = false ORDER BY admin1.id_administador");
            List<ADM> admin1 = readAdmin.getResultList();
            for (ADM admin2 : admin1) {
                System.out.println(admin2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter administradores inativos: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


//    Função alterarAdmin
    public String alterarAdmin(ADM adm) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do alterarAdmin
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Adm alterar
            System.out.println("\nInforme o ID do Administrador que deseja alterar: ");
            int idAlterador = sc.nextInt();

//            Tenta procurar o ADM com ID igual ao fornecido
            adm = em.find(ADM.class, idAlterador);

//            Caso não encontrado, fornece mensagem de erro
            if (adm == null) {
                return "\nAdministrador não encontrado!";
            }

//            Caso encontrado, começa o processo de alteração de ADM
            System.out.println("\nInforme o ID que deseja substituir: ");
            int idSub = sc.nextInt();
            adm.setId_adm(idSub);
            System.out.println("\nInforme o nome completo que deseja substituir: ");
            String nomeSub = sc.nextLine();
            adm.setAdmNome(nomeSub);
            System.out.println("\nInforme o e-mail que deseja substituir: ");
            String emailSub = sc.next();
            adm.setAdmEmail(emailSub);
            System.out.println("\nInforme a senha que deseja substituir: ");
            String senhaSub = sc.nextLine();
            adm.setAdmSenha(senhaSub);
            System.out.println("\nInforme o status que deseja substituir: ");
            boolean statusSub = sc.nextBoolean();
            adm.setAdmStatus(statusSub);

//            Finalização da adição e enviando ADM alterado para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nAdministrador Alterado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao alterar administrador: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


//    Função excluirAdmin
    public String excluirAdmin(ADM adm) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do excluirAdmin
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Adm excluir
            System.out.println("\nInforme o ID do Administrador que deseja excluir: ");
            int idEx = sc.nextInt();

//            Tenta procurar o ADM com ID igual ao fornecido
            adm = em.find(ADM.class, idEx);

//            Caso não encontrado, fornece mensagem de erro
            if (adm == null) {
                return "\nAdministrador não encontrado!";
            }

//            Caso encontrado, exlui o ADM
            em.remove(adm);

//            Finalização da exclusão e enviando ADM excluído para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nAdministrador Excluído com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao excluir administrador: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}