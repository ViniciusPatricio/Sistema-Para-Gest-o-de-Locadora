package Telas;

import ClassesBancoDeDados.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class AlugarTitulos extends JFrame{
    private JPanel PainelPrincipal;
    private JTextField textField1;
    private JButton ALUGARButton;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JTable table1;
    private JTextField MESXXTextField;
    private JTextField DIAXXTextField;
    private JTextField CPFFUNCIONARIOTextField;
    private JTextField textField5;
    private JPasswordField passwordField1;
    private JButton RESETARTABELAButton;

    public AlugarTitulos() throws SQLException, ClassNotFoundException {

        super("Alugar Títulos");
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(1100,800));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();

        ALUGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int flagUsuario = 0;
            int flagTitulo = 0;
            int flagFuncionario = 0;
            String cpfCliente = textField1.getText();
            String senha = passwordField1.getText();
            if(cpfCliente.equals("") || senha.equals("")){
                JOptionPane.showMessageDialog(null,"SENHA OU CPF NÃO PREENCHIDO","DADOS CLIENTES",JOptionPane.INFORMATION_MESSAGE);
            }else{
                try{
                    double CPFint = Double.valueOf(cpfCliente).doubleValue();
                    ClientesLocadora clientesLocadora = new ClientesLocadora();

                    if(clientesLocadora.verificaClienteExistente(cpfCliente)){
                        String senhaRegistrada = clientesLocadora.getSenha(cpfCliente);
                        if(senhaRegistrada.equals(senha)){
                            flagUsuario = 1;
                        }else{
                            JOptionPane.showMessageDialog(null,"SENHA INCORRETA","AVISO",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"CLIENTE NÃO CADASTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                        int confirm = JOptionPane.showConfirmDialog(null,"DESEJA CADASTRAR CLIENTE?");
                        if(confirm == 0){
                            PainelClientes painelClientes = new PainelClientes();
                            painelClientes.setVisible(true);
                        }
                    }
                    }catch (Exception e1){
                        System.out.println(e1);
                        JOptionPane.showMessageDialog(null,"CPF É UM VALOR NUMÉRICO","DADOS CLIENTES",JOptionPane.INFORMATION_MESSAGE);
                    }

                String descricao = textField4.getText();
                String tipo = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();
                String numeroExemplares = textField3.getText();
                TitulosLocadora titulosLocadora = null;
                try {
                    titulosLocadora = new TitulosLocadora();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

                if(descricao.equals("") || numeroExemplares.equals("")){
                    JOptionPane.showMessageDialog(null,"DESCRICAO OU NÚMERO DE EXEMPLARES NÃO PREENCHIDO","TITULO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(titulosLocadora.verificaTituloExistente(descricao,tipo)){
                        if(parseInt(numeroExemplares)> parseInt(titulosLocadora.getNumExemplares(descricao,tipo))){
                            JOptionPane.showMessageDialog(null,"NUMERO DE EXEMPLARES SOLICITADOS\nMAIOR QUE O VALOR DISPONIVEL\nVALOR ATUAL: "+titulosLocadora.getNumExemplares(descricao,tipo),"AVISO",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            flagTitulo = 1;
                        }
                        JOptionPane.showMessageDialog(null,"TITULO ENCONTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                        try {
                            createTable(descricao,tipo);
                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"TITULO NAO ENCONTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                String cpfFuncionario = CPFFUNCIONARIOTextField.getText();
                try {
                    FuncionariosLocadora funcionariosLocadora = new FuncionariosLocadora();
                    if(funcionariosLocadora.verificaFuncionarioExistente(cpfFuncionario)){
                        flagFuncionario = 1;
                    }else{
                        JOptionPane.showMessageDialog(null,"FUNCIONARIO NAO ENCONTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
                String dia = DIAXXTextField.getText();
                String mes = MESXXTextField.getText();
                String ano = textField5.getText();

                if(dia.equals("") || mes.equals("") || ano.equals("")){
                    JOptionPane.showMessageDialog(null,"DATA NAO ESTA PREENCHIDA COMPLETAMENTE","AVISO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(flagFuncionario==1 && flagTitulo==1 && flagUsuario==1){
                        try {
                            ClientesLocadora clientesLocadora = new ClientesLocadora();
                            String data = ano+"-"+mes+"-"+dia;
                            Titulos titulos = titulosLocadora.getLinhaTitulos(descricao,tipo);
                            int periodoMaximoEmprestimo = titulos.getPeriodoMaximoEmprestimo();
                            String idFuncionario;
                            FuncionariosLocadora funcionariosLocadora = new FuncionariosLocadora();
                            idFuncionario = funcionariosLocadora.getIDfuncionario(cpfFuncionario);
                            String idCliente = clientesLocadora.getIDcliente(cpfCliente);
                            int diasEmprestimos = titulosLocadora.getTempoMaximoEmprestimo(descricao,tipo);
                            LocalDate date = LocalDate.of(parseInt(ano), parseInt(mes), parseInt(dia));
                            date = date.plusDays(diasEmprestimos);

                            String dataDevolucao = date.toString();

                            TitulosAlugados titulosAlugados = new TitulosAlugados(0,descricao.toUpperCase(),tipo.toUpperCase(), parseInt(numeroExemplares),periodoMaximoEmprestimo, parseInt(idFuncionario), parseInt(idCliente),data,dataDevolucao);
                            TitulosAlugadosLocadora titulosAlugadosLocadora = new TitulosAlugadosLocadora();

                            if(titulosAlugadosLocadora.adicionarTitulosAlugados(titulosAlugados)){
                                Registro registro = new Registro(0,"LOCAÇÃO",descricao.toUpperCase(),tipo.toUpperCase(), parseInt(numeroExemplares), parseInt(idFuncionario), parseInt(idCliente),data);
                                RegistrosLocadora registrosLocadora = new RegistrosLocadora();
                                registrosLocadora.adicionarRegistros(registro);
                                titulosLocadora.alterarNumExemplares(descricao,tipo, parseInt(numeroExemplares),'-');
                                clientesLocadora.alterarQuantidadeFilmesAlugados(cpfCliente, parseInt(numeroExemplares));
                                funcionariosLocadora.alterarNumeroLocacoes(cpfFuncionario, parseInt(numeroExemplares));
                                titulosLocadora.alterarNumExemplaresAlugados(descricao,tipo, parseInt(numeroExemplares));
                                JOptionPane.showMessageDialog(null,"TITULO ALUGADO COM SUCESSO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                                createTable(descricao,tipo);
                            }else{
                                JOptionPane.showMessageDialog(null,"FALHA AO ALUGAR TITULO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            }
            }
        });
        RESETARTABELAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createTable();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    private  void createTable() throws SQLException, ClassNotFoundException {
        TitulosLocadora titulosLocadora = new TitulosLocadora();
        Object[][] data = titulosLocadora.gerarValoresTabelas();
        table1.setModel(new DefaultTableModel(data,new String[]{"idtitulos","descricao","tipo","nº exemplares","period max emprest","nº exemp alugados"}));
        table1.setVisible(true);
    }

    private  void createTable(String descicao, String tipo) throws SQLException, ClassNotFoundException {
        TitulosLocadora titulosLocadora = new TitulosLocadora();
        Object[][] data = titulosLocadora.gerarValoresTabelas(descicao,tipo);
        table1.setModel(new DefaultTableModel(data,new String[]{"idtitulos","descricao","tipo","nº exemplares","period max emprest","nº exemp alugados"}));
        table1.setVisible(true);
    }



}
