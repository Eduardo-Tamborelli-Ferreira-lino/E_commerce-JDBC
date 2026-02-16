package org.example;

import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final ClienteDao CLIENTE_DAO = new ClienteDao();
    static final MotoristaDao MOTORISTA_DAO = new MotoristaDao();
    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }

    public static void inicio(){
        System.out.println("Olá seja bem-vindo ao nosso E-Commerce");
        while (true){
            System.out.println("""
                    1 - Cadastrar Cliente
                    2 - Cadastrar Motorista
                    3 - Criar Pedido
                    4 - Atribuir Pedido a Motorista (Gerar Entrega)
                    5 - Registrar Evento de Entrega (Histórico)
                    6 - Atualizar Status da Entrega
                    7 - Listar Todas as Entregas com Cliente e Motorista
                    8 - Relatório: Total de Entregas por Motorista
                    9 - Relatório: Clientes com Maior Volume Entregue
                    10 - Relatório: Pedidos Pendentes por Estado
                    11 - Relatório: Entregas Atrasadas por Cidade
                    12 - Buscar Pedido por CPF/CNPJ do Cliente
                    13 - Cancelar Pedido
                    14 - Excluir Entrega (com validação)
                    15 - Excluir Cliente (com verificação de dependência)
                    16 - Excluir Motorista (com verificação de dependência)
                    0 - Sair
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    cadastrarCliente();
                    break;
                }
                case 2:{
                    cadastrarMotorista();
                    break;
                }
            }
        }
    }

    public static void cadastrarCliente(){
        System.out.println("Insira o nome do cliente: ");
        SC.nextLine();
        String nome = SC.nextLine();
        System.out.println("Insira o CPF ou CNPJ do cliente: ");
        String cpfCnpj = SC.nextLine();
        System.out.println("Insira o endereço do cliente: ");
        String endereco = SC.nextLine();
        System.out.println("Insira o Cidade do cliente: ");
        String cidade = SC.nextLine();
        System.out.println("Insira o Estado do cliente: ");
        String estado = SC.nextLine();
        try {
            CLIENTE_DAO.cadastrarCliente(nome,cpfCnpj,endereco,cidade,estado);
            System.out.println("Cadastro realizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
    }

    public static void cadastrarMotorista(){
        System.out.println("Insira o nome do cliente: ");
        SC.nextLine();
        String nome = SC.nextLine();
        System.out.println("Insira o CNH do cliente: ");
        String cnh = SC.nextLine();
        System.out.println("Insira o veiculo do cliente: ");
        String veiculo = SC.nextLine();
        System.out.println("Insira o Cidade Base do cliente: ");
        String cidadeBase = SC.nextLine();
        try {
            MOTORISTA_DAO.cadastrarMotorista(nome,cnh,veiculo,cidadeBase);
            System.out.println("Cadastro realizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
    }
}