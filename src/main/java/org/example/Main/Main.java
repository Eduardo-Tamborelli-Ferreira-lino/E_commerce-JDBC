package org.example.Main;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;
import org.example.Classes.Cliente;
import org.example.Classes.Motorista;
import org.example.Classes.Historico;
import org.example.Classes.Pedido;
import org.example.Classes.Entrega;
import org.example.DAO.EntregaDao;
import org.example.DAO.MotoristaDao;
import org.example.DAO.ClienteDao;
import org.example.DAO.PedidoDao;
import org.example.DAO.HistoricoDao;
import org.example.DTO.EntregaDetalhadaDTO;
import org.example.DTO.MaiorVolumeEntregueDTO;
import org.example.DTO.TotalEntregasMotoristaDTO;
import org.example.Enum.StatusPedido;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final ClienteDao CLIENTE_DAO = new ClienteDao();
    static final MotoristaDao MOTORISTA_DAO = new MotoristaDao();
    static final PedidoDao PEDIDO_DAO = new PedidoDao();
    static final EntregaDao ENTREGA_DAO = new EntregaDao();
    static final HistoricoDao HISTORICO_DAO = new HistoricoDao();
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
                case 3:{
                    criarPedido();
                    break;
                }
                case 4:{
                    atribuirPedido();
                    break;
                }
                case 5:{
                    registrarEntrega();
                    break;
                }
                case 6:{
                    atualizarEntrega();
                    break;
                }
                case 7:{
                    listarECM();
                    break;
                }
                case 8:{
                    listarTEM();
                    break;
                }
                case 9:{
                    listarMV();
                    break;
                }
                case 10:{
                    listarPPE();
                    break;
                }
            }
        }
    }

    public static void cadastrarCliente(){
        while(true) {
            System.out.println("""
                    Você quer mesmo adicionar um novo cliente?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao) {
                case 1: {
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
                        CLIENTE_DAO.cadastrarCliente(nome, cpfCnpj, endereco, cidade, estado);
                        System.out.println("Cadastro realizado com sucesso");
                        return;
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados");
                        e.printStackTrace();
                        return;
                    }
                }
                case 2: {
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }
    }

    public static void cadastrarMotorista(){
        while (true){
            System.out.println("""
                    Você quer mesmo adicionar um novo Motorista?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    System.out.println("Insira o nome do Motorista: ");
                    SC.nextLine();
                    String nome = SC.nextLine();
                    System.out.println("Insira o CNH do Motorista: ");
                    String cnh = SC.nextLine();
                    System.out.println("Insira o veiculo do Motorista: ");
                    String veiculo = SC.nextLine();
                    System.out.println("Insira o Cidade Base do Motorista: ");
                    String cidadeBase = SC.nextLine();
                    try {
                        MOTORISTA_DAO.cadastrarMotorista(nome,cnh,veiculo,cidadeBase);
                        System.out.println("Cadastro realizado com sucesso");
                        return;
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados");
                        e.printStackTrace();
                        return;
                    }
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }
    }

    public static void criarPedido (){
        while (true){
            System.out.println("""
                    Você quer mesmo adicionar um novo Pedido?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1: {
                    ArrayList<Cliente> clientes = new ArrayList<>();
                    try {
                        clientes = CLIENTE_DAO.listarCliente();
                        if (clientes == null || clientes.isEmpty()) {
                            System.out.println("Nenhum Cliente Cadastrado no Momento");
                        } else {
                            for (Cliente cliente : clientes) {
                                System.out.println(cliente.toString());
                            }
                            System.out.println("Insira o ID do cliente que deseja usar para criar o pedido ou insira 0 para retornar");
                            int clienteId = SC.nextInt();
                            for (Cliente cliente : clientes) {
                                if (clienteId == 0) {
                                    System.out.println("Ok, vamos retornar ao menu principal.");
                                    return;
                                }
                                if (cliente.getId() == clienteId) {
                                    System.out.println("Cliente valido");

                                    System.out.println("Insira o volume do pedido em Metros cubicos:");
                                    Double volumeM3 = SC.nextDouble();
                                    System.out.println("Insira o peso do pedido em KG:");
                                    Double peso = SC.nextDouble();
                                    StatusPedido status = StatusPedido.values()[0];
                                    Timestamp dataPedido = new Timestamp(System.currentTimeMillis());
                                    Pedido pedido = new Pedido(clienteId, dataPedido, volumeM3, peso, status);
                                    PEDIDO_DAO.criarPedido(pedido);
                                    System.out.println("Pedido Criado com Sucesso!");
                                    return;
                                }
                                System.out.println("Id do cliente não encontrado. " +
                                        "Informe um Id valido.");
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados");
                        e.printStackTrace();
                        return;
                    }
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }
    }

    public static void atribuirPedido() {
        while (true){
            System.out.println("""
                    Você quer mesmo realizar essa ação?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    ArrayList<Pedido> pedidos = new ArrayList<>();
                    ArrayList<Motorista> motoristas = new ArrayList<>();
                    try {
                        pedidos = PEDIDO_DAO.listarPedido();
                        if (pedidos == null || pedidos.isEmpty()) {
                            System.out.println("Nenhum pedido Cadastrado no Momento");
                        } else {
                            for (Pedido pedido : pedidos) {
                                System.out.println(pedido.toString());
                            }
                            System.out.println("Insira o ID do pedido que deseja usar para gerar a entrega ou insira 0 para retornar");
                            int pedidoId = SC.nextInt();
                            for (Pedido pedido : pedidos) {
                                if (pedidoId == 0) {
                                    System.out.println("Ok, vamos retornar ao menu principal.");
                                    return;
                                }
                                if (pedido.getId() == pedidoId) {
                                    System.out.println("Pedido valido");
                                    motoristas = listarMotorista();
                                    if (motoristas == null || motoristas.isEmpty()){
                                        System.out.println("Nenhum motorista Cadastrado no Momento");
                                    }
                                    else {
                                        for (Motorista motorista : motoristas){
                                            System.out.println(motorista.toString());
                                        }
                                        System.out.println("Insira o ID do motorista que deseja usar para gerar a entrega ou insira 0 para retornar");
                                        int motoristaId = SC.nextInt();
                                        for (Motorista motorista : motoristas) {
                                            if (motoristaId == 0) {
                                                System.out.println("Ok, vamos retornar ao menu principal.");
                                                return;
                                            }
                                            if (motorista.getId() == motoristaId){
                                                System.out.println("Motorista valido");
                                                Timestamp dataSaida = new Timestamp(System.currentTimeMillis());
                                                String dataEntrega = null;
                                                int index = 1;
                                                for (StatusPedido status : StatusPedido.values()){
                                                    System.out.println(index + " - " + status);
                                                    index++;
                                                }
                                                System.out.println("Escolha um status para esse pedido");
                                                int opcaoStatus = SC.nextInt() - 1;
                                                StatusPedido status = StatusPedido.values()[opcaoStatus];
                                                Entrega entrega = new Entrega(pedidoId,motoristaId,dataSaida,dataEntrega,status);
                                                ENTREGA_DAO.gerarEntrega(entrega);
                                                System.out.println("Entrega gerada com sucesso");
                                                return;
                                            }
                                        }
                                        System.out.println("Id do cliente não encontrado. " +
                                                "Informe um Id valido.");
                                    }
                                }
                            }
                        }
                    }catch(SQLException e){
                        System.out.println("Erro ao acessar o banco de dados");
                        e.printStackTrace();
                        return;
                    }
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }

    }

    public static void registrarEntrega(){
        while (true){
            System.out.println("""
                    Você quer mesmo realizar essa ação?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    ArrayList<Entrega> entregas= new ArrayList<>();
                    entregas = listarEntregas();
                    if (entregas == null || entregas.isEmpty()){
                        System.out.println("Nenhuma Entrega Cadastrado no Momento");
                    }
                    else {
                        for (Entrega entrega: entregas){
                            System.out.println(entrega.toString());
                        }
                        System.out.println("Insira o ID da entrega que deseja usar para gerar o historico ou insira 0 para retornar");
                        int entregaId = SC.nextInt();
                        for (Entrega entrega : entregas){
                            if (entregaId == 0){
                                System.out.println("Ok, vamos retornar ao menu principal.");
                                return;
                            }
                            if (entrega.getId() == entregaId){
                                System.out.println("Entrega existe");
                                SC.nextLine();
                                Timestamp dataEvento = new Timestamp(System.currentTimeMillis());
                                System.out.println("Insira a descrição da entrega: ");
                                String descricao = SC.nextLine();
                                try {
                                    HISTORICO_DAO.gerarHistorico(entregaId, dataEvento, descricao);
                                    System.out.println("Historico Atualizado");
                                    return;
                                } catch (SQLException e) {
                                    System.out.println("Erro ao acessar o banco de dados");
                                    e.printStackTrace();
                                    return;
                                }
                            }
                        }
                        System.out.println("Id do cliente não encontrado. " +
                                "Informe um Id valido.");
                    }
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }
    }

    public static void atualizarEntrega(){
        while (true){
            System.out.println("""
                    Você quer mesmo realizar essa ação?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    ArrayList<Entrega> entregas = new ArrayList<>();
                    entregas = listarEntregas();
                    for (Entrega entrega: entregas){
                        System.out.println(entrega.toString());
                    }
                    System.out.println("Por favor Insira o ID da entrega que deseja atualizar");
                    int id = SC.nextInt();
                    for (Entrega entrega : entregas){
                        if (entrega.getId() == id){
                            System.out.println(entrega.toString());
                            System.out.println("Escolha valida, Entrega escolhida");
                            int index = 1;
                            for (StatusPedido status : StatusPedido.values()){
                                System.out.println(index + " - " + status);
                                index++;
                            }
                            System.out.println("Escolha um status para esse pedido");
                            int opcaoStatus = SC.nextInt() - 1;
                            StatusPedido status = StatusPedido.values()[opcaoStatus];
                            try {
                                ENTREGA_DAO.atualizarEntrega(status, id);
                                System.out.println("Status da entrega foi atualizado");
                                return;
                            }catch (SQLException e) {
                                System.out.println("Erro ao acessar o banco de dados");
                                e.printStackTrace();
                                return;
                            }
                        }
                    }
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }
    }

    public static void listarECM(){
        while (true){
            System.out.println("""
                    Você quer mesmo realizar essa ação?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    ArrayList<EntregaDetalhadaDTO> entrega = new ArrayList<>();
                    try {
                        entrega = ENTREGA_DAO.listarECM();
                        if (entrega == null || entrega.isEmpty()){
                            System.out.println("Nenhuma Entrega foi encontrada");
                            return;
                        }
                        for (EntregaDetalhadaDTO dto : entrega){
                            System.out.println(dto);
                        }
                        return;
                    }catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados");
                        e.printStackTrace();
                        return;
                    }
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }
    }

    public static void listarTEM(){
        while (true){
            System.out.println("""
                    Você quer mesmo realizar essa ação?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    ArrayList<TotalEntregasMotoristaDTO> dto = new ArrayList<>();
                    try {
                        dto = ENTREGA_DAO.totalEntregaMotorista();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados");
                        e.printStackTrace();
                    }
                    if (dto == null || dto.isEmpty()){
                        System.out.println("Nenhuma Entrega Atribuita a Motoristas");
                        return;
                    }
                    for (TotalEntregasMotoristaDTO motoristaDTO : dto){
                        System.out.println(motoristaDTO);
                    }
                    return;
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }
    }

    public static void listarMV(){
        while (true){
            System.out.println("""
                    Você quer mesmo realizar essa ação?
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    ArrayList<MaiorVolumeEntregueDTO> dtos = new ArrayList<>();
                    try {
                        dtos = ENTREGA_DAO.MaiorVolumeEntregue();
                    }catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados");
                        e.printStackTrace();
                    }
                    if (dtos == null || dtos.isEmpty()){
                        System.out.println("Nenhuma Entrega Realizada");
                        return;
                    }
                    for (MaiorVolumeEntregueDTO dto : dtos){
                        System.out.println(dto);
                    }
                    return;
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("Escolha uma opção valida.");
                }
            }
        }
    }

    public static void listarPPE(){

    }

    public static ArrayList<Motorista> listarMotorista(){
        ArrayList<Motorista> motoristas = new ArrayList<>();
        try {
            motoristas = MOTORISTA_DAO.listarMotorista();
            if (motoristas == null || motoristas.isEmpty()){
                System.out.println("Nenhum Motorista Cadastrado no Momento");
            }
            else {
                return motoristas;
            }
        }catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
        return motoristas;
    }

    public static ArrayList<Entrega> listarEntregas(){
        ArrayList<Entrega> entregas = new ArrayList<>();
        try {
            entregas = ENTREGA_DAO.listarEntregas();
            if (entregas == null || entregas.isEmpty()){
                System.out.println("Nenhuma Entrega Cadastrado no Momento");
            }
            else {
                return entregas;
            }
        }catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
        return entregas;
    }
}