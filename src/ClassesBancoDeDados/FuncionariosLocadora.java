package ClassesBancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class FuncionariosLocadora extends BancoDeDados{

    public FuncionariosLocadora() throws ClassNotFoundException, SQLException {
        super();
    }

    public boolean adicionarFuncionarios(Funcionarios funcionario){
        try{
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO funcionarios VALUES (NULL, '"+funcionario.getPrimeiroNome()+"', '"+funcionario.getSegundoNome()+"', '"+funcionario.getCPF()+"', '"+funcionario.getNumeroLocacoesTratadas()+"', '"+funcionario.getNumeroDevolucoesTratadas()+"')");
            return true;
        }catch (SQLException e){ return false;}
    }
    public boolean verificaFuncionarioExistente(String CPF){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM funcionarios WHERE "+"CPF='"+ CPF+"'");
            if(rs.next()){
                return true;
            }else{
                return  false;
            }
        }catch (SQLException e){ return false;}
    }

    public String getIDfuncionario(String CPF) {
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM funcionarios WHERE " + "CPF='" + CPF + "'");

            if (rs.next()) {
                return rs.getString(1);
            } else {
                return null;
            }

        } catch (SQLException e) {
            return null;
        }
    }
    public boolean alterarNumeroLocacoes(String CPF,int numExemplaresLocacao){
        try{
            int numeroExemplaresAlugadosAntigos=0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM funcionarios WHERE "+"CPF='"+CPF+"'");
            if(rs.next()){
                numeroExemplaresAlugadosAntigos = Integer.parseInt(rs.getString(5));
                int numeroExemplaresAtualizado = numeroExemplaresAlugadosAntigos+numExemplaresLocacao;
                st.executeUpdate("UPDATE funcionarios SET "+"numero_locações_tratadas='"+numeroExemplaresAtualizado+"' WHERE idfuncionarios='"+getIDfuncionario(CPF)+"'");
                return true;
            }else{
                return false;
            }

        }catch (SQLException e){System.out.println(e);return false;}
    }
    public boolean alterarNumeroDevolucoes(String CPF,int numExemplaresDevolucao){
        try{
            int numeroExemplaresDevolucaoAntigos=0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM funcionarios WHERE "+"CPF='"+CPF+"'");
            if(rs.next()){
                numeroExemplaresDevolucaoAntigos = Integer.parseInt(rs.getString(6));
                int numeroExemplaresAtualizado = numeroExemplaresDevolucaoAntigos+numExemplaresDevolucao;
                st.executeUpdate("UPDATE funcionarios SET "+"numero_devoluções_tratadas='"+numeroExemplaresAtualizado+"' WHERE idfuncionarios='"+getIDfuncionario(CPF)+"'");
                return true;
            }else{
                return false;
            }

        }catch (SQLException e){System.out.println(e);return false;}
    }
    public boolean excluirFucionario(String CPF){
        try {
            Statement st = conexao.createStatement();
            String idfuncionario = getIDfuncionario(CPF);
            st.executeUpdate("DELETE FROM funcionarios WHERE "+"idfuncionarios='"+idfuncionario+"'");
        return true;

        }catch (SQLException e){return false;}
    }

    public int numLinhaFuncionarios(){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from funcionarios");

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }
    public LinkedList<Funcionarios> listarFuncionarios(){
        try {

            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from funcionarios");

            LinkedList<Funcionarios> listaFuncionarios= new LinkedList<>();
            while ((rs.next())){
                Funcionarios funcionarios = new Funcionarios(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6)));
                listaFuncionarios.add(funcionarios);
            }
            return listaFuncionarios;
        }catch (SQLException e) { return null;}

    }
}
