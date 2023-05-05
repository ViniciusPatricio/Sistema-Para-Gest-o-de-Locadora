package Telas;

import ClassesBancoDeDados.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class DevolucaoTitulos extends JFrame{
    private JPanel PainelPrincipal;
    private JButton DEVOLUCAOButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton RESETARTABELAButton;
    private JTable table1;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JTextField textField4;
    private JButton BUSCAButton;
    private JTextField textField6;
    private JTextField textField7;

    public DevolucaoTitulos() throws SQLException, ClassNotFoundException {
        super("Devolução de Títulos");
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(1350,700));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();

        DEVOLUCAOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = textField6.getText().toUpperCase();
                String tipo = Objects.requireNonNull(comboBox1.getSelectedItem()).toString().toUpperCase();
                String CPFfuncionario = textField4.getText();
                String CPFcliente = textField5.getText();
                String data = textField2.getText()+"-"+textField3.getText()+"-"+textField1.getText();
                String numExemplares = textField7.getText();

                try {
                    TitulosAlugadosLocadora titulosAlugadosLocadora = new TitulosAlugadosLocadora();
                int flagCliente = 0;
                int flagFuncionario = 0;
                if(descricao.equals("") || numExemplares.equals("")){
                    JOptionPane.showMessageDialog(null,"DESCRICAO OU NÚMERO DE EXEMPLARES NÃO PREENCHIDO","TITULO",JOptionPane.INFORMATION_MESSAGE);
                }else if(titulosAlugadosLocadora.verificaTituloAlugadoExistente(descricao,tipo)) {
                    try {
                        ClientesLocadora clientesLocadora = new ClientesLocadora();
                        FuncionariosLocadora funcionariosLocadora = new FuncionariosLocadora();
                        TitulosLocadora titulosLocadora = new TitulosLocadora();
                        if(CPFcliente.equals("")){
                            JOptionPane.showMessageDialog(null,"CPF DO CLIENTE NÃO PREENCHIDO","TITULO",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            if (clientesLocadora.verificaClienteExistente(CPFcliente)) {
                                flagCliente = 1;
                            } else {
                                JOptionPane.showMessageDialog(null, "CLIENTE NÃO CADASTRADO", "CLIENTE", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }

                        if (flagCliente == 1) {
                            if(CPFfuncionario.equals("")){
                                JOptionPane.showMessageDialog(null, "CPF DO FUNCIONARIO NAO PREENCHIDO", "TITULO", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                if (funcionariosLocadora.verificaFuncionarioExistente(CPFfuncionario)) {
                                    flagFuncionario = 1;
                                } else {
                                    JOptionPane.showMessageDialog(null, "FUNCIONARIO NAO CADASTRADO", "FUNCIONARIO", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                        if(flagCliente == 1 && flagFuncionario == 1){
                            String dataEsperada = titulosAlugadosLocadora.getDataDevolucao(descricao,tipo,CPFcliente);
                            LocalDate d1 = LocalDate.parse(dataEsperada, DateTimeFormatter.ISO_LOCAL_DATE);
                            LocalDate d2 = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);

                            Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());

                            long diffDays = diff.toDays();

                            int numExemplaresRegistrado = Integer.parseInt(titulosAlugadosLocadora.getNumExemplares(descricao,tipo));
                            if(numExemplaresRegistrado<Integer.parseInt(numExemplares)){

                                JOptionPane.showMessageDialog(null, "NUMERO DE EXEMPLARES DEVOLVIDOS MAIOR QUE O NUMERO DE EXEMPLARES ALUGADOS", "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);

                            }else if(numExemplaresRegistrado>Integer.parseInt(numExemplares)){
                                JOptionPane.showMessageDialog(null, "NUMERO DE EXEMPLARES DEVOLVIDOS MENOR QUE A QUANTIDADE ALUGADA PELO CLIENTE", "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);
                                clientesLocadora.alterarQuantidadeFilmesDevolvidos(CPFcliente, Integer.parseInt(numExemplares));
                                clientesLocadora.alterarQuantidadeFilmesAlugados(CPFcliente, Integer.parseInt(numExemplares), 1);
                                funcionariosLocadora.alterarNumeroDevolucoes(CPFfuncionario,Integer.parseInt(numExemplares));
                                String idFuncionario = funcionariosLocadora.getIDfuncionario(CPFfuncionario);
                                String idCliente = clientesLocadora.getIDcliente(CPFcliente);
                                Registro registro = new Registro(0,"DEVOLUÇÃO PARCIAL",descricao.toUpperCase(),tipo.toUpperCase(), parseInt(numExemplares), parseInt(idFuncionario), parseInt(idCliente),data);
                                RegistrosLocadora registrosLocadora = new RegistrosLocadora();
                                registrosLocadora.adicionarRegistros(registro);
                                titulosAlugadosLocadora.alterarNumExemplaresAlugados(descricao,tipo,CPFcliente, Integer.parseInt(numExemplares));
                                titulosLocadora.alterarNumExemplares(descricao,tipo, Integer.parseInt(numExemplares),'+');
                                titulosLocadora.alterarNumExemplaresAlugados(descricao,tipo, Integer.parseInt(numExemplares),1);
                                if(diffDays<0){
                                    JOptionPane.showMessageDialog(null, "DEVOLUÇÃO ATRASADA\nCLIENTE MULTADO\nDIAS ATRASADOS: "+ Math.abs(diffDays), "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);
                                }else{
                                    JOptionPane.showMessageDialog(null, "DEVOLUÇÃO REALIZADA DENTRO DO PRAZO", "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);
                                }
                                JOptionPane.showMessageDialog(null, "DEVOLUÇÃO PARCIAL REALIZADA COM SUCESSO", "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                clientesLocadora.alterarQuantidadeFilmesDevolvidos(CPFcliente, Integer.parseInt(numExemplares));
                                clientesLocadora.alterarQuantidadeFilmesAlugados(CPFcliente, Integer.parseInt(numExemplares), 1);
                                funcionariosLocadora.alterarNumeroDevolucoes(CPFfuncionario,Integer.parseInt(numExemplares));
                                String idFuncionario = funcionariosLocadora.getIDfuncionario(CPFfuncionario);
                                String idCliente = clientesLocadora.getIDcliente(CPFcliente);
                                Registro registro = new Registro(0,"DEVOLUÇÃO COMPLETA",descricao.toUpperCase(),tipo.toUpperCase(), parseInt(numExemplares), parseInt(idFuncionario), parseInt(idCliente),data);
                                RegistrosLocadora registrosLocadora = new RegistrosLocadora();
                                int numExemplaresAtual;
                                numExemplaresAtual = titulosAlugadosLocadora.alterarNumExemplaresAlugados(descricao,tipo,CPFcliente, Integer.parseInt(numExemplares));
                                registrosLocadora.adicionarRegistros(registro);
                                titulosLocadora.alterarNumExemplares(descricao,tipo, Integer.parseInt(numExemplares),'+');
                                titulosLocadora.alterarNumExemplaresAlugados(descricao,tipo, Integer.parseInt(numExemplares),1);
                                if(numExemplaresAtual == 0){
                                    titulosAlugadosLocadora.excluirTitulosAlugados(descricao,tipo,CPFcliente);
                                }
                                if(diffDays<0){
                                    JOptionPane.showMessageDialog(null, "DEVOLUÇÃO ATRASADA\nCLIENTE MULTADO\nDIAS ATRASADOS: "+ Math.abs(diffDays), "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);
                                }else{
                                    JOptionPane.showMessageDialog(null, "DEVOLUÇÃO REALIZADA DENTRO DO PRAZO", "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);
                                }
                                JOptionPane.showMessageDialog(null, "DEVOLUÇÃO REALIZADA COM SUCESSO", "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "HOUVE ALGUMA ERRO NA DEVOLUÇÃO\nDEVE TER OCORRIDO ERRO DE PREENCHIMENTO", "DEVOLUÇÃO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "TITULO NÃO ENCONTRADO", "TITULO", JOptionPane.INFORMATION_MESSAGE);
                }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
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
        BUSCAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<String> pesquisa = new LinkedList<>();
                String pesquisaTabela = "";
                String descricao = textField6.getText().toUpperCase();
                String tipo = Objects.requireNonNull(comboBox1.getSelectedItem()).toString().toUpperCase();
                String CPFfuncionario = textField4.getText();
                String CPFcliente = textField5.getText();
                String numExemplares = textField7.getText();
                if(!descricao.equals("")){
                    pesquisa.add("descricao='"+descricao+"'");
                }
                if(!tipo.equals("")){
                    pesquisa.add("tipo='"+tipo+"'");
                }
                if(!CPFcliente.equals("")){
                    try {
                        ClientesLocadora clientesLocadora = new ClientesLocadora();
                        TitulosAlugadosLocadora titulosAlugadosLocadora = new TitulosAlugadosLocadora();
                        String idCliente ="";
                        if(clientesLocadora.getIDcliente(CPFcliente)!=null){
                            idCliente = clientesLocadora.getIDcliente(CPFcliente);
                        }

                        pesquisa.add("id_cliente ='"+idCliente+"'");
                    } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if(pesquisa.size()==0){
                    JOptionPane.showMessageDialog(null, "EXISTE(M) CAMPO(S) VAZIO(S)", "BUSCA", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    for(String i : pesquisa){
                        if(i.equals(pesquisa.getFirst())){
                            pesquisaTabela = pesquisaTabela.concat(i);
                        }else{
                            pesquisaTabela = pesquisaTabela.concat(" AND "+i);
                        }
                    }
                }

                try {
                    createTable(pesquisaTabela);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private  void createTable() throws SQLException, ClassNotFoundException {
        TitulosAlugadosLocadora titulosLocadora = new TitulosAlugadosLocadora();
        Object[][] data = titulosLocadora.gerarValoresTabelas();
        table1.setModel(new DefaultTableModel(data,new String[]{"idtitulosAlugados","descricao","tipo","nº exemplares","period max emprest","id_funcionario","id_cliente","data emprestimo","data devolucao"}));

    }
    private  void createTable(String pesquisa) throws SQLException, ClassNotFoundException {
        TitulosAlugadosLocadora titulosLocadora = new TitulosAlugadosLocadora();
        Object[][] data = titulosLocadora.gerarValoresTabelas(pesquisa);
        if(data == null){
            JOptionPane.showMessageDialog(null, "NÃO FOI ENCONTRADO TAIS ESPECIFICAÇÕES NO CONJUNTO DE DADOS", "BUSCA", JOptionPane.INFORMATION_MESSAGE);
        }
        table1.setModel(new DefaultTableModel(data,new String[]{"idtitulosAlugados","descricao","tipo","nº exemplares","period max emprest","id_funcionario","id_cliente","data emprestimo","data devolucao"}));

    }
}
