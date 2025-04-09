// Declarando Package
package DAO;

// Importando a classe Pedido do pacote Models
import Models.Pedido;

// Importando bibliotecas do jakarta.persistence
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

// Importando bibliotecas do java.util
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.SimpleDateFormat;

// Classe PedidoDAO
public class PedidoDAO {

    //    Declarando e Criando um EntityManagerFactory
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    //    Declarando o Scanner
    Scanner sc = new Scanner(System.in);

    //    Função addPedido
    public String addPedido(Pedido ped) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do addPedido
        try {

//            Instanciando um novo Produto para o addPedido
            Pedido newPedido = new Pedido();

//            Processo de adição de um novo Pedido
            System.out.println("\nInsira o ID do novo Pedido: ");
            int novoId = sc.nextInt();
            newPedido.setId_pedido(novoId);
            System.out.println("\nInsira o código do carrinho do novo Pedido: ");
            int novoCodCar = sc.nextInt();
            newPedido.setCod_carrinho(novoCodCar);
            System.out.println("\nInsira o código do tipo de pagamento do novo Pedido: ");
            int novoCodPagamento = sc.nextInt();
            newPedido.setCod_tipo_pagamento(novoCodPagamento);
            Date novaData = new Date();
            newPedido.setPedDtPedido(novaData);

            // Buscando a soma dos preços dos produtos no carrinho
            Query searchTotal = em.createQuery(
                    "SELECT SUM(p.preco) FROM carrinho_produtos cp JOIN produtos p ON cp.cod_produto = p.id_produto WHERE cp.cod_carrinho = :cod"
            );
            searchTotal.setParameter("cod", novoCodCar);
            float total = (float) searchTotal.getSingleResult();
            newPedido.setPedValorTotal(total);

            System.out.println("\nInsira o CEP do novo Pedido: ");
            String novoCEP = sc.next();
            newPedido.setPedCepUser(novoCEP);
            System.out.println("\nInsira o bairro do novo Pedido: ");
            String novoBairro = sc.next();
            newPedido.setPedBairro(novoBairro);
            System.out.println("\nInsira a rua/avenida do novo Pedido: ");
            String novoRuaAvenida = sc.next();
            newPedido.setPedRuaAvenida(novoRuaAvenida);
            System.out.println("\nInsira algum complemento do novo Pedido: ");
            String novoComp = sc.next();
            newPedido.setPedComp(novoComp);
            System.out.println("\nInsira o Número do novo Pedido: ");
            int novoNum = sc.nextInt();
            newPedido.setPedNum(novoNum);
            System.out.println("\nInsira o telefone do novo Pedido: ");
            long novoTel = sc.nextLong();
            newPedido.setPedTel(novoTel);

//            Finalização da adição e enviando novo Pedido para o BD com mensagem de sucesso
            em.getTransaction().begin();
            em.persist(newPedido);
            em.getTransaction().commit();

            return "\nPedido Adicionado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao adicionar Pedido: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getPedidos
    public void getPedidos() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getPedidos
        try {

//            Query para pegar pedidos
            Query readPeds = em.createQuery("SELECT ped1 FROM pedido ped1 ORDER BY ped1.id_pedido");
            List<Pedido> ped1 = readPeds.getResultList();
            for (Pedido ped2 : ped1) {
                System.out.println(ped2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter Pedidos: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getPedidosCodCar
    public void getPedidosCodCar() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getPedidosCodCar
        try {

//            Solicitando Código do Carrinho
            System.out.println("\nInsira o Código do Carrinho que quer procurar: ");
            int codigoCarrinhoSolicitado = sc.nextInt();

//            Query para pegar apenas pedidos com código de carrinho especificado
            Query readPeds = em.createQuery("SELECT ped1 FROM usuario ped1 WHERE ped1.cod_carrinho = :codCar ORDER BY ped1.id_usuario");
            readPeds.setParameter("codCar", codigoCarrinhoSolicitado);
            List<Pedido> ped1 = readPeds.getResultList();
            for (Pedido ped2 : ped1) {
                System.out.println(ped2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter pedido pelo carrinho informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getPedidosTipoPag
    public void getPedidosTipoPag() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getPedidosTipoPag
        try {

//            Solicitando Tipo de Pagamento
            System.out.println("\nInsira o código do tipo de pagamento que quer procurar: ");
            int tipoPagSolicitado = sc.nextInt();

//            Query para pegar apenas pedidos com tipo de pagamento especificado
            Query readPeds = em.createQuery("SELECT ped1 FROM pedido ped1 WHERE ped1.codTipoPag = :codTipoPag ORDER BY ped1.cod_tipo_pagamento");
            readPeds.setParameter("codTipoPag", tipoPagSolicitado);
            List<Pedido> ped1 = readPeds.getResultList();
            for (Pedido ped2 : ped1) {
                System.out.println(ped2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter pedidos pelo tipo de pagamento informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função getPedidosDt
    public void getPedidosDt() {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do getPedidosDt
        try {

//            Solicitando Data do Pedido
            System.out.println("\nInsira a data de pedido que quer procurar (ATENÇÃO: A data informada deve ser no formato AAAA-MM-DD): ");
            String dtPed = sc.next();

//            Convertendo Data, de String para Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dtPedSolicitado = sdf.parse(dataInput);

//            Query para pegar apenas pedidos com tipo de pagamento especificado
            Query readPeds = em.createQuery("SELECT ped1 FROM pedido ped1 WHERE ped1.pedDtPedido = :dtPed ORDER BY ped1.pedDtPedido");
            readPeds.setParameter("dtPed", dtPedSolicitado);
            List<Pedido> ped1 = readPeds.getResultList();
            for (Pedido ped2 : ped1) {
                System.out.println(ped2);
            }

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao obter pedidos pelo tipo de pagamento informado: " + e.getMessage());

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função alterarPedidos
    public String alterarPedidos(Pedido ped) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do alterarPedidos
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual pedido alterar
            System.out.println("\nInforme o ID do pedido que deseja alterar: ");
            int idAlterador = sc.nextInt();

//            Tenta procurar o pedido com ID igual ao fornecido
            ped = em.find(Pedido.class, idAlterador);

//            Caso não encontrado, fornece mensagem de erro
            if (ped == null) {
                return "\nPedido não encontrado!";
            }

//            Caso encontrado, começa o processo de alteração de pedido
            System.out.println("\nInforme o ID que deseja substituir: ");
            int idSub = sc.nextInt();
            ped.setId_pedido(idSub);
            System.out.println("\nInforme o código do carrinho que deseja substituir: ");
            int codCarSub = sc.nextInt();
            ped.setCod_carrinho(codCarSub);
            System.out.println("\nInforme o código do tipo de pagamento que deseja substituir: ");
            int codTipoPagSub = sc.nextInt();
            ped.setCod_tipo_pagamento(codTipoPagSub);
            Date novaData = new Date();
            ped.setPedDtPedido(novaData);

            // Buscando a soma dos preços dos produtos no carrinho
            Query searchTotal = em.createQuery(
                    "SELECT SUM(p.preco) FROM carrinho_produtos cp JOIN produtos p ON cp.cod_produto = p.id_produto WHERE cp.cod_carrinho = :cod"
            );
            searchTotal.setParameter("cod", codCarSub);
            float total = (float) searchTotal.getSingleResult();
            ped.setPedValorTotal(total);

            System.out.println("\nInforme o CEP que deseja substituir: ");
            String cepSub = sc.next();
            ped.setPedCepUser(cepSub);
            System.out.println("\nInforme o bairro que deseja substituir: ");
            String bairroSub = sc.next();
            ped.setPedBairro(bairroSub);
            System.out.println("\nInforme o Rua/Avenida que deseja substituir: ");
            String ruaAvSub = sc.next();
            ped.setPedRuaAvenida(ruaAvSub);
            System.out.println("\nInforme o complemento que deseja substituir: ");
            String compSub = sc.next();
            ped.setPedComp(compSub);
            System.out.println("\nInforme o número de endereço que deseja substituir: ");
            int numSub = sc.nextInt();
            ped.setPedNum(numSub);
            System.out.println("\nInforme o telefone que deseja substituir: ");
            int telSub = sc.nextInt();
            ped.setPedTel(telSub);

//            Finalização da adição e enviando Pedido alterado para o BD com mensagem de sucesso
            em.getTransaction().commit();
                return "\nPedido Alterado com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao alterar Pedido: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    //    Função excluirPedido
    public String excluirPedido(Pedido ped) {

//        Iniciando o EntityManager com o emf criado anteriormente
        EntityManager em = emf.createEntityManager();

//        Try-catch do excluirPedido
        try {

//            Começando a Transação
            em.getTransaction().begin();

//            Pedindo ID ao usuário para saber qual Pedido excluir
            System.out.println("\nInforme o ID do pedido que deseja excluir: ");
            int idEx = sc.nextInt();

//            Tenta procurar o Pedido com ID igual ao fornecido
            ped = em.find(Pedido.class, idEx);

//            Caso não encontrado, fornece mensagem de erro
            if (ped == null) {
                return "\nPedido não encontrado!";
            }

//            Caso encontrado, exlui o Pedido
            em.remove(ped);

//            Finalização da exclusão e enviando Pedido excluído para o BD com mensagem de sucesso
            em.getTransaction().commit();
            return "\nPedido Excluído com Sucesso!";

//            Exceptions que podem ocorrer sendo tratadas
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return "\nErro ao excluir Pedido: " + e.getMessage();

//            Fechando o try-catch e o EntityManager
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}