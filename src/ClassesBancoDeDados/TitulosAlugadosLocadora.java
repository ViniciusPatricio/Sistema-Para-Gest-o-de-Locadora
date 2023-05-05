package ClassesBancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class TitulosAlugadosLocadora extends BancoDeDados{

    public TitulosAlugadosLocadora() throws ClassNotFoundException, SQLException {
        super();
    }
    public boolean adicionarTitulosAlugados(TitulosAlugados p){
        try {

            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO titulosalugados VALUES (NULL, '" +p.getDescricao() + "'," + " '" + p.getTipo()+ "', '" + p.getNumeroExemplares()+ "', '"+p.getPeriodoMaximoEmprestimo()+"', '"+ p.getIdFuncionario()+"', '"+p.getIdCliente()+"', '"+p.getDataEmprestimo()+"', '"+p.getDataDevolucao()+"')");
            return true;
        }
        catch (SQLException e) { return false; }
    }
    public LinkedList<TitulosAlugados> listarTitulosAlugados(){
        try {

            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulosalugados");

            LinkedList<TitulosAlugados> listaTitulosAlugados = new LinkedList<>();
            while ((rs.next())){
                TitulosAlugados titulosAlugados = new TitulosAlugados(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(7)),rs.getString(8),rs.getString(9));
                listaTitulosAlugados.add(titulosAlugados);
            }
            return listaTitulosAlugados;
        }catch (SQLException e) { return null;}

    }

    public LinkedList<TitulosAlugados> listarTitulosAlugados(String pesquisa){
        try {

            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulosalugados WHERE "+pesquisa);

            LinkedList<TitulosAlugados> listaTitulosAlugados = new LinkedList<>();
            while ((rs.next())){
                TitulosAlugados titulosAlugados = new TitulosAlugados(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(7)),rs.getString(8),rs.getString(9));
                listaTitulosAlugados.add(titulosAlugados);
            }
            return listaTitulosAlugados;
        }catch (SQLException e) { return null;}

    }

    public int numLinhaTitulosAlugados(){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulosalugados");

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }

    public int numLinhaTitulosAlugados(String pesquisa){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulosalugados WHERE "+pesquisa);

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }

    public Object[][] gerarValoresTabelas() throws SQLException, ClassNotFoundException {
        Object[][] data = new Object[numLinhaTitulosAlugados()][9];
        int i = 0;
        for(TitulosAlugados titulos:listarTitulosAlugados()){
            data[i][0] = titulos.getId();
            data[i][1] = titulos.getDescricao();
            data[i][2] = titulos.getTipo();
            data[i][3] = titulos.getNumeroExemplares();
            data[i][4] = titulos.getPeriodoMaximoEmprestimo();
            data[i][5] = titulos.getIdFuncionario();
            data[i][6] = titulos.getIdCliente();
            data[i][7] = titulos.getDataEmprestimo();
            data[i][8] = titulos.getDataDevolucao();
            i++;
        }
        return data;
    }
    public Object[][] gerarValoresTabelas(String pesquisa) throws SQLException, ClassNotFoundException {
        try {
            Object[][] data = new Object[numLinhaTitulosAlugados(pesquisa)][9];
            int i = 0;
            for (TitulosAlugados titulos : listarTitulosAlugados(pesquisa)) {
                data[i][0] = titulos.getId();
                data[i][1] = titulos.getDescricao();
                data[i][2] = titulos.getTipo();
                data[i][3] = titulos.getNumeroExemplares();
                data[i][4] = titulos.getPeriodoMaximoEmprestimo();
                data[i][5] = titulos.getIdFuncionario();
                data[i][6] = titulos.getIdCliente();
                data[i][7] = titulos.getDataEmprestimo();
                data[i][8] = titulos.getDataDevolucao();
                i++;
            }
            return data;
        }catch (Exception e){ return null;}
    }


    public  boolean verificaTituloAlugadoExistente(String descricaoTitulo, String tipoTitulo){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulosalugados WHERE "+"descricao='"+ descricaoTitulo+"' AND tipo='"+tipoTitulo+"'");

            return rs.next();

        } catch (SQLException e){ return false;}
    }
    public  boolean verificaTituloAlugadoExistente(String descricaoTitulo){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulosalugados WHERE "+"descricao='"+ descricaoTitulo+"'");
            return rs.next();

        } catch (SQLException e){ return false;}
    }

    public String getIDTituloAlugado(String descricao, String tipo, String CPFcliente) {
        try {
            ClientesLocadora clientesLocadora = new ClientesLocadora();
            String idCliente = clientesLocadora.getIDcliente(CPFcliente);
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulosalugados WHERE " + "id_cliente='" + idCliente + "'" + " AND descricao='" + descricao + "' AND tipo='"+tipo+"'");

            if (rs.next()) {
                return rs.getString(1);
            } else {
                return null;
            }

        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); return null;
        }
    }

    public String getDataDevolucao(String descricao, String tipo, String CPFcliente) {
        try {
            ClientesLocadora clientesLocadora = new ClientesLocadora();
            String idCliente = clientesLocadora.getIDcliente(CPFcliente);
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulosalugados WHERE " + "id_cliente='" + idCliente + "'" + " AND descricao='" + descricao + "' AND tipo='"+tipo+"'");

            if (rs.next()) {
                return rs.getString(9);
            } else {
                return null;
            }

        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); return null;
        }
    }

    public int alterarNumExemplaresAlugados(String descricao, String tipo, String CPFcliente, int numExemplares){

        try {
            ClientesLocadora clientesLocadora = new ClientesLocadora();
            String idCliente = clientesLocadora.getIDcliente(CPFcliente);
            String numExemplaresAtualmenteAlugados;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulosalugados WHERE "+"descricao='"+ descricao+"' AND tipo='"+tipo+"'"+" AND id_cliente='"+idCliente+"'");
            if(rs.next()){
                numExemplaresAtualmenteAlugados = rs.getString(4);

            }else{ return 0;}
            int numAtualizados = Integer.parseInt(numExemplaresAtualmenteAlugados) - numExemplares;
            st.executeUpdate("UPDATE titulosalugados SET "+"numero_exemplares='"+numAtualizados+"' WHERE idtitulosAlugados='"+getIDTituloAlugado(descricao,tipo,CPFcliente)+"'");
            return numAtualizados;
        } catch (SQLException e){System.out.println(e);return 0;} catch (ClassNotFoundException e) {
            e.printStackTrace(); return 0;
        }
    }

    public String getNumExemplares(String descricao,String tipo){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulosalugados WHERE descricao='"+descricao+"' AND tipo='"+tipo+"'");

            if((rs.next())){
                return rs.getString(4);
            }else{
                return null;
            }

        }catch (SQLException e) { return null;}
    }
    public boolean excluirTitulosAlugados(String descricao, String tipo, String CPFcliente){
        try {
            ClientesLocadora clientesLocadora = new ClientesLocadora();
            String idCliente = clientesLocadora.getIDcliente(CPFcliente);
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM titulosalugados WHERE "+"id_cliente='"+idCliente+"'");
            return true;

        }catch (SQLException | ClassNotFoundException e){return false;}
    }
}
