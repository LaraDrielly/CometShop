// Declarando Package
package DAO;

// Importando a classe Usuario do pacote Models
import Models.Usuario;

// Importando bibliotecas do jakarta.persistence
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

// Importando bibliotecas do java.util
import java.util.List;
import java.util.Scanner;

// Classe UsuarioDAO
public class UsuarioDAO {

    //    Declarando e Criando um EntityManagerFactory
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    //    Declarando o Scanner
    Scanner sc = new Scanner(System.in);

    //    Função addUsuario
    public String addUsuario(Usuario user) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do addUsuario
        try {

//            Instanciando um novo Produto para o addUsuario
            Usuario newUser = new Usuario();

//            Processo de adição de um novo Usuario
            System.out.println("\nInsira o ID do novo Usuário: ");
            int novoId = sc.nextInt();
            newUser.setId_usuario(novoId);
            System.out.println("\nInsira o nome do Perfil do novo Usuário: ");
            String novoNomePerfil = sc.next();
            newUser.setUserNomePerfil(novoNomePerfil);
            System.out.println("\nInsira a senha do novo Usuário: ");
            String novaSenha = sc.next();
            newUser.setUserSenha(novaSenha);
            System.out.println("\nInsira o e-mail do novo Usuário: ");
            String novoEmail = sc.next();
            newUser.setUserEmail(novoEmail);
            System.out.println("\nInsira o nome completo do novo Usuário: ");
            String novoNome= sc.next();
            newUser.setUserNome(novoNome);

//            Finalização da adição e enviando novo Usuario para o BD com mensagem de sucesso
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();

            return "\nUsuário Adicionado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao adicionar Usuário: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getUsuarios
    public void getUsuarios() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getUsuarios
        try {

//            Query para pegar usuarios
            Query readUsers = em.createQuery("SELECT user1 FROM usuario user1 ORDER BY user1.id_usuario");
            List<Usuario> user1 = readUsers.getResultList();
            for (Usuario user2 : user1) {
                System.out.println(user2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter Usuários: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getUsuariosNomePerfil
    public void getUsuariosNomePerfil() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getUsuariosNomePerfil
        try {

//            Solicitando Nome de Perfil
            System.out.println("\nInsira o Nome de Perfil que quer procurar: ");
            String nomePerfilSolicitado = sc.next();

//            Query para pegar apenas usuários de nome de perfil especificado
            Query readUsers = em.createQuery("SELECT user1 FROM usuario user1 WHERE user1.userNomePerfil = :nomePerfil ORDER BY user1.id_usuario");
            readUsers.setParameter("nomePerfil", nomePerfilSolicitado);
            List<Usuario> user1 = readUsers.getResultList();
            for (Usuario user2 : user1) {
                System.out.println(user2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter usuário pelo Perfil informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getUsuariosEmail
    public void getUsuariosEmail() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getUsuariosEmail
        try {

//            Solicitando E-mail
            System.out.println("\nInsira o e-mail que quer procurar: ");
            String emailSolicitado = sc.next();

//            Query para pegar apenas usuários de e-mail especificado
            Query readUsers = em.createQuery("SELECT user1 FROM usuario user1 WHERE user1.userEmail = :email ORDER BY user1.id_usuario");
            readUsers.setParameter("email", emailSolicitado);
            List<Usuario> user1 = readUsers.getResultList();
            for (Usuario user2 : user1) {
                System.out.println(user2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter usuário pelo e-mail informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função alterarUsuarios
    public String alterarUsuarios(Usuario user) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do alterarUsuarios
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual usuário alterar
            System.out.println("\nInforme o ID do usuário que deseja alterar: ");
            int idAlterador = sc.nextInt();

//            Tenta procurar o Usuário com ID igual ao fornecido
            user = em.find(Usuario.class, idAlterador);

//            Caso não encontrado, fornece mensagem de erro
            if (user == null) {
                return "\nUsuário não encontrado!";
            }

//            Caso encontrado, começa o processo de alteração de Usuário
            System.out.println("\nInforme o ID que deseja substituir: ");
            int idSub = sc.nextInt();
            user.setId_usuario(idSub);
            System.out.println("\nInforme o nome de perfil que deseja substituir: ");
            String nomePerfilSub = sc.next();
            user.setUserNomePerfil(nomePerfilSub);
            System.out.println("\nInforme a senha que deseja substituir: ");
            String senhaSub = sc.next();
            user.setUserSenha(senhaSub);
            System.out.println("\nInforme o e-mail que deseja substituir: ");
            String emailSub = sc.next();
            user.setUserEmail(emailSub);
            System.out.println("\nInforme o nome completo que deseja substituir: ");
            String nomeSub = sc.next();
            user.setUserNome(nomeSub);
            System.out.println("\nInforme o telefone completo que deseja substituir: ");
            String telSub = sc.next();
            user.setUserTel(telSub);

//            Finalização da adição e enviando Usuário alterado para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nUsuário Alterado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao alterar Usuário: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função excluirUsuario
    public String excluirUsuario(Usuario user) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do excluirUsuario
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Usuario excluir
            System.out.println("\nInforme o ID do Usuário que deseja excluir: ");
            int idEx = sc.nextInt();

//            Tenta procurar o Usuário com ID igual ao fornecido
            user = em.find(Usuario.class, idEx);

//            Caso não encontrado, fornece mensagem de erro
            if (user == null) {
                return "\nUsuário não encontrado!";
            }

//            Caso encontrado, exlui o Usuário
            em.remove(user);

//            Finalização da exclusão e enviando Usuário excluído para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nUsuário Excluído com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao excluir Usuário: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}