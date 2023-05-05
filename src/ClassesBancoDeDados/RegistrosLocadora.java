package ClassesBancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class RegistrosLocadora extends BancoDeDados {

    public RegistrosLocadora() throws ClassNotFoundException, SQLException {
        super();
    }
    public void adicionarRegistros(Registro registro){
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO registros VALUES (NULL, '" +registro.getTipoRegistro() + "'," + " '" + registro.getDescricaoTitulo()+ "', '" + registro.getTipoTitulo()+ "', '" + registro.getNumExemplares()+"', '"+registro.getIdFuncionario()+"', '"+registro.getIdCliente()+"', '"+registro.getData()+"')");
        }
        catch (SQLException e) { }
    }

    public int numLinhaRegistros(){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from registros");

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }

    public LinkedList<Registro> listarRegistros(){
        try {

            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from registros");
            LinkedList<Registro> listaRegistro = new LinkedList<>();

            while ((rs.next())){
                Registro registro = new Registro(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(7)),rs.getString(8));
                listaRegistro.add(registro);
            }
            return listaRegistro;
        }catch (SQLException e) { return null;}
    }
    public LinkedList<Registro> listarRegistros(String pesquisa){
        try {

            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from registros WHERE "+pesquisa);
            LinkedList<Registro> listaRegistro = new LinkedList<>();

            while ((rs.next())){
                Registro registro = new Registro(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(7)),rs.getString(8));
                listaRegistro.add(registro);
            }
            return listaRegistro;
        }catch (SQLException e) { return null;}
    }

    public Object[][] gerarValoresTabelas() throws SQLException, ClassNotFoundException {

        Object[][] data = new Object[numLinhaRegistros()][8];
        int i = 0;
        for(Registro registro:listarRegistros()){
            data[i][0] = registro.getId();
            data[i][1] = registro.getTipoRegistro();
            data[i][2] = registro.getDescricaoTitulo();
            data[i][3] = registro.getTipoTitulo();
            data[i][4] = registro.getNumExemplares();
            data[i][5] = registro.getIdFuncionario();
            data[i][6] = registro.getIdCliente();
            data[i][7] = registro.getData();
            i++;
        }
        return data;
    }

    public int numLinhaRegistros(String pesquisa){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from registros WHERE "+pesquisa);

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }

    public Object[][] gerarValoresTabelas(String pesquisa) throws SQLException, ClassNotFoundException {

        Object[][] data = new Object[numLinhaRegistros(pesquisa)][8];
        int i = 0;
        for(Registro registro:listarRegistros(pesquisa)){
            data[i][0] = registro.getId();
            data[i][1] = registro.getTipoRegistro();
            data[i][2] = registro.getDescricaoTitulo();
            data[i][3] = registro.getTipoTitulo();
            data[i][4] = registro.getNumExemplares();
            data[i][5] = registro.getIdFuncionario();
            data[i][6] = registro.getIdCliente();
            data[i][7] = registro.getData();
            i++;
        }
        return data;
    }

}
