package Telas;

import ClassesBancoDeDados.Funcionarios;
import ClassesBancoDeDados.FuncionariosLocadora;
import ClassesBancoDeDados.Titulos;
import ClassesBancoDeDados.TitulosLocadora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Locale;

public class JanelaFuncionarios extends JFrame{
    private JPanel PainelPrincipal;
    private JButton REGISTRARButton;
    private JButton REMOVERButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTable table1;

    public  JanelaFuncionarios() throws SQLException, ClassNotFoundException {
        super("Funcionários");
        setContentPane(PainelPrincipal);
        setMinimumSize(new Dimension(900,650));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String primeiroNome = textField1.getText();
                String segundoNome = textField2.getText();
                String CPF = textField3.getText();
                if(CPF.equals("") || segundoNome.equals("") || primeiroNome.equals("")){
                    JOptionPane.showMessageDialog(null,"Existe(m) campo(s) vazio(s)","AVISO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    try {
                        double CPFint = Double.valueOf(CPF).doubleValue();
                        FuncionariosLocadora funcionariosLocadora = new FuncionariosLocadora();

                        if(funcionariosLocadora.verificaFuncionarioExistente(CPF)){
                            JOptionPane.showMessageDialog(null,"FUNCIONARIO JA CADASTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            Funcionarios funcionarios = new Funcionarios(primeiroNome.toUpperCase(),segundoNome.toUpperCase(),CPF);
                            if(funcionariosLocadora.adicionarFuncionarios(funcionarios)) JOptionPane.showMessageDialog(null,"FUNCIONARIO CADASTRADO COM SUCESSO","AVISO",JOptionPane.INFORMATION_MESSAGE);;
                        }
                        createTable();
                    }catch (Exception e1){
                        System.out.println(e1);
                        JOptionPane.showMessageDialog(null,"CPF É UM VALOR NUMÉRICO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                    };
                }
            }
        });

        REMOVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CPF = textField3.getText();
                if(CPF.equals("")){
                    JOptionPane.showMessageDialog(null,"Existe(m) campo(s) vazio(s)","AVISO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    try {
                        double CPFint = Double.valueOf(CPF).doubleValue();
                        FuncionariosLocadora funcionariosLocadora = new FuncionariosLocadora();

                        if(funcionariosLocadora.verificaFuncionarioExistente(CPF)){
                            if(funcionariosLocadora.excluirFucionario(CPF)){JOptionPane.showMessageDialog(null,"FUNCIONARIO REMOVIDO COM SUCESSO","AVISO",JOptionPane.INFORMATION_MESSAGE);}
                        }else{
                            JOptionPane.showMessageDialog(null,"FUNCIONARIO NAO ENCONTRADO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                        }
                        createTable();
                    }catch (Exception e1){
                        System.out.println(e1);
                        JOptionPane.showMessageDialog(null,"CPF E UM VALOR NUMERICO","AVISO",JOptionPane.INFORMATION_MESSAGE);
                    };
                }
            }
        });

    }
    public Object[][] gerarValoresTabelas() throws SQLException, ClassNotFoundException {
        FuncionariosLocadora funcionariosLocadora = new FuncionariosLocadora();
        String[] colunas = {"idfuncionarios","primeiro nome","segundo nome","CPF","nº locações tratadas","nº devoluções tratadas"};
        Object[][] data = new Object[funcionariosLocadora.numLinhaFuncionarios()][colunas.length];
        int i = 0;
        for(Funcionarios funcionarios:funcionariosLocadora.listarFuncionarios()){
            data[i][0] = funcionarios.getID();
            data[i][1] = funcionarios.getPrimeiroNome();
            data[i][2] = funcionarios.getSegundoNome();
            data[i][3] = funcionarios.getCPF();
            data[i][4] = funcionarios.getNumeroLocacoesTratadas();
            data[i][5] = funcionarios.getNumeroDevolucoesTratadas();
            i++;
        }
        return data;
    }

    private  void createTable() throws SQLException, ClassNotFoundException {
        Object[][] data = gerarValoresTabelas();
        table1.setModel(new DefaultTableModel(data,new String[]{"idfuncionarios","primeiro nome","segundo nome","CPF","nº locações tratadas","nº devoluções tratadas"}));
        //table1.setVisible(true);
    }

}
