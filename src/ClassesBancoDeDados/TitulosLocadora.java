package ClassesBancoDeDados;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.LinkedList;

public class TitulosLocadora  extends BancoDeDados {

    public TitulosLocadora()  throws ClassNotFoundException, SQLException{
        super();
    }

    public boolean adicionarTitulo(Titulos p)  {
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO titulos VALUES (NULL, '" +p.getDescricao() + "'," + " '" + p.getTipo()+ "', '" + p.getNumeroExemplares()+ "', '" + p.getPeriodoMaximoEmprestimo()+"', '"+p.getNumeroExemplaresAlugados()+"')");
            return true;
        }
        catch (SQLException e) { return false; }
    }

    public boolean verificaTituloExistente(String descricaoTitulo){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulos WHERE "+"descricao='"+ descricaoTitulo+"'");

            if(rs.next()){
                return true;
            }else{
                return  false;
            }

        } catch (SQLException e){ return false;}
    }
    public  boolean verificaTituloExistente(String descricaoTitulo, String tipoTitulo){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulos WHERE "+"descricao='"+ descricaoTitulo+"' AND tipo='"+tipoTitulo+"'");

            if(rs.next()){
                return true;
            }else{
                return  false;
            }

        } catch (SQLException e){ return false;}
    }
    public int alterarNumExemplares(String descricaoTitulo,String tipoTitulo, int numExemplaresNovo,char operacao){

        try {
            int numeroExemplaresAtualizado;
            int numeroExemplaresAntigo = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulos WHERE "+"descricao='"+ descricaoTitulo+"' AND tipo='"+tipoTitulo+"'");
            if(rs.next()){
                String numExemplaresAtual = rs.getString(4);
                numeroExemplaresAntigo = Integer.parseInt(numExemplaresAtual);

                if(operacao=='+'){

                    numeroExemplaresAtualizado = numeroExemplaresAntigo + numExemplaresNovo;
                    st.executeUpdate("UPDATE titulos SET "+"numeros_de_exemplares='"+numeroExemplaresAtualizado+"' WHERE idtitulos='"+getIDTitulo(descricaoTitulo,tipoTitulo)+"'");
                    return numeroExemplaresAtualizado;
                }else{
                    numeroExemplaresAtualizado = numeroExemplaresAntigo - numExemplaresNovo;
                    if(numeroExemplaresAtualizado<0)numeroExemplaresAtualizado = 0;
                    st.executeUpdate("UPDATE titulos SET "+"numeros_de_exemplares='"+numeroExemplaresAtualizado+"' WHERE idtitulos='"+getIDTitulo(descricaoTitulo,tipoTitulo)+"'");
                    return numeroExemplaresAtualizado;
                }
            }else{ return 0;}

        } catch (SQLException e){System.out.println(e);return 0;}
    }
    public int alterarNumExemplaresAlugados(String descricao,String tipo, int numExemplares){

        try {
            String numExemplaresAtualmenteAlugados;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulos WHERE "+"descricao='"+ descricao+"' AND tipo='"+tipo+"'");
            if(rs.next()){
               numExemplaresAtualmenteAlugados = rs.getString(6);

            }else{ return 0;}
            int numAtualizados = Integer.parseInt(numExemplaresAtualmenteAlugados) + numExemplares;
            st.executeUpdate("UPDATE titulos SET "+"numeros_de_exemplares_alugados='"+numAtualizados+"' WHERE idtitulos='"+getIDTitulo(descricao,tipo)+"'");
            return numAtualizados;
        } catch (SQLException e){System.out.println(e);return 0;}
    }
    public int alterarNumExemplaresAlugados(String descricao,String tipo, int numExemplares, int op){

        try {
            String numExemplaresAtualmenteAlugados;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulos WHERE "+"descricao='"+ descricao+"' AND tipo='"+tipo+"'");
            if(rs.next()){
                numExemplaresAtualmenteAlugados = rs.getString(6);

            }else{ return 0;}
            int numAtualizados = Integer.parseInt(numExemplaresAtualmenteAlugados) - numExemplares;
            st.executeUpdate("UPDATE titulos SET "+"numeros_de_exemplares_alugados='"+numAtualizados+"' WHERE idtitulos='"+getIDTitulo(descricao,tipo)+"'");
            return numAtualizados;
        } catch (SQLException e){System.out.println(e);return 0;}
    }

    public LinkedList<String> getTipoTitulo(String descricaoTitulo){
        LinkedList<String> tiposTitulo = new LinkedList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulos WHERE "+"descricao='"+descricaoTitulo+"'");
            while (rs.next()){
                tiposTitulo.add(rs.getString(3));
            }

        }catch (SQLException e){ System.out.println(e);return null;}
        return tiposTitulo;
    }
    public String getIDTitulo(String descricaoTitulo,String tipoTitulo){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM titulos WHERE "+"descricao='"+ descricaoTitulo+"' AND tipo='"+tipoTitulo+"'");

            if(rs.next()){
                return rs.getString(1);
            }else{
                return  null;
            }

        } catch (SQLException e){ return null;}
    }
    public boolean removerTitulo(String descricao, String tipo){
        String idtitulo = getIDTitulo(descricao,tipo);

        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM titulos WHERE "+"idtitulos='"+idtitulo+"'");
            return true;
        }catch (SQLException e){return false;}
    }
    public LinkedList<Titulos> listarTitulos(){
        try {

            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulos");

            LinkedList<Titulos> listaTitulos = new LinkedList<>();
            while ((rs.next())){
                Titulos titulos = new Titulos(Integer.parseInt(rs.getString(1)),rs.getString(3), Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)),rs.getString(2),Integer.parseInt(rs.getString(6)));
                listaTitulos.add(titulos);
            }
            return listaTitulos;
        }catch (SQLException e) { return null;}
    }

    public LinkedList<Titulos> listarTitulos(String descricao, String tipo){
        try {

            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulos WHERE descricao='"+descricao+"' AND tipo='"+tipo+"'");

            LinkedList<Titulos> listaTitulos = new LinkedList<>();
            while ((rs.next())){
                Titulos titulos = new Titulos(Integer.parseInt(rs.getString(1)),rs.getString(3), Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)),rs.getString(2),Integer.parseInt(rs.getString(6)));
                listaTitulos.add(titulos);
            }
            return listaTitulos;
        }catch (SQLException e) { return null;}
    }

    public int getTempoMaximoEmprestimo(String descricao, String tipo){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulos WHERE descricao='"+descricao+"' AND tipo='"+tipo+"'");
            if(rs.next()){
                return Integer.parseInt(rs.getString(5));
            }else{
                return -1;
            }
        }catch (SQLException e){ return -1;}
    }

    public int numLinhaTitulos(){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulos");

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }

    public int numLinhaTitulos(String descricao,String tipo){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulos WHERE descricao='"+descricao+"' AND tipo='"+tipo+"'");

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }
    public String getNumExemplares(String descricao,String tipo){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulos WHERE descricao='"+descricao+"' AND tipo='"+tipo+"'");

            if((rs.next())){
                return rs.getString(4);
            }else{
                return null;
            }

        }catch (SQLException e) { return null;}
    }

    public Object[][] gerarValoresTabelas() throws SQLException, ClassNotFoundException {
        String[] colunas = {"idtitulos","descricao","tipo","numero_maximo_exemplares","período_máximo_emprestimo","numeros_de_exemplares_alugados"};
        Object[][] data = new Object[numLinhaTitulos()][colunas.length];
        int i = 0;
        for(Titulos titulos:listarTitulos()){
            data[i][0] = titulos.getId();
            data[i][1] = titulos.getDescricao();
            data[i][2] = titulos.getTipo();
            data[i][3] = titulos.getNumeroExemplares();
            data[i][4] = titulos.getPeriodoMaximoEmprestimo();
            data[i][5] = titulos.getNumeroExemplaresAlugados();
            i++;
        }
        return data;
    }
    public Object[][] gerarValoresTabelas(String descricao,String tipo) throws SQLException, ClassNotFoundException {
        String[] colunas = {"idtitulos","descricao","tipo","numero_maximo_exemplares","período_máximo_emprestimo","numeros_de_exemplares_alugados"};
        Object[][] data = new Object[numLinhaTitulos(descricao,tipo)][colunas.length];
        int i = 0;
        for(Titulos titulos:listarTitulos(descricao,tipo)){
            data[i][0] = titulos.getId();
            data[i][1] = titulos.getDescricao();
            data[i][2] = titulos.getTipo();
            data[i][3] = titulos.getNumeroExemplares();
            data[i][4] = titulos.getPeriodoMaximoEmprestimo();
            data[i][5] = titulos.getNumeroExemplaresAlugados();
            i++;
        }
        return data;
    }
    public Titulos getLinhaTitulos(String descricao,String tipo){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from titulos WHERE descricao='"+descricao+"' AND tipo='"+tipo+"'");

            LinkedList<Titulos> listaTitulos = new LinkedList<>();
            if((rs.next())){
                Titulos titulos = new Titulos(Integer.parseInt(rs.getString(1)),rs.getString(3), Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)),rs.getString(2),Integer.parseInt(rs.getString(6)));
                return titulos;
            }else{
                return null;
            }
        }catch (SQLException e) { return null;}
    }
}
