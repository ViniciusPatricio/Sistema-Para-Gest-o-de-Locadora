package ClassesBancoDeDados;

import Telas.PainelClientes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
/**
 *  A classe {@code ClientesLocadora} foi criada acessar tabela de clientes do conjunto de dados da locadora
 */
public class ClientesLocadora extends BancoDeDados{
    /**
     * Tenta realizar a coneccao com conjunto de dados
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ClientesLocadora() throws ClassNotFoundException, SQLException {
        super();
    }

    /**
     * Realiza a insercao do novo cliente na tabela de clientes do conjunto de dados
     * @param cliente Cliente que esta sendo cadastrado
     * @return verdadeiro se a insercao foi realizada com sucesso, e falso caso nao
     */
    public boolean adicionarClientes(Clientes cliente){
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO clientes VALUES (NULL, '"+cliente.getPrimeiro_nome()+"', '"+cliente.getSegundo_nome()+"', '"+cliente.getSenha()+"', '"+cliente.getQuantidade_de_filmes_atualmente_alugados()+"', '"+cliente.getQuanitdade_de_filmes_devolvidos()+"', '"+cliente.getCPF()+"')");
            return true;
        }catch (SQLException e){return false;}
    }

    /**
     * Verifica se o cliente ja esta cadastrado
     * @param CPF CPF do cliente
     * @return verdadeiro se o cliente ja esta cadastrado e falso caso nao
     */
    public boolean verificaClienteExistente(String CPF){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes WHERE "+"CPF='"+ CPF+"'");

            if(rs.next()){
                return true;
            }else{
                return  false;
            }

        } catch (SQLException e){ return false;}
    }

    /**
     * Acessa a tabela de dados e retorna o indice do cliente
     * @param CPF CPF cliente
     * @return indice do cliente na tabela de clientes
     */
    public String getIDcliente(String CPF){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes WHERE "+"CPF='"+CPF+"'");

            if(rs.next()){
                return rs.getString(1);
            }else{
                return  null;
            }

        } catch (SQLException e){ return null;}
    }

    /**
     * Exclui o Cliente da tabela do conjunto de dados
     * @param CPF CPF DO cliente
     * @param senha SENHA do cliente
     * @return verdadeiro caso a remocao ocorra com sucesso e falso caso contrario
     */
    public boolean excluirCliente(String CPF,String senha){
        try {
            Statement st = conexao.createStatement();
            String senhaRegistrada;
            ResultSet rs = st.executeQuery("SELECT * FROM clientes WHERE "+"CPF='"+ CPF+"'");
            if(rs.next()){
                senhaRegistrada = rs.getString(4);
            }else {return false;}

            if(senhaRegistrada.equals(senha)){
                String idcliente = getIDcliente(CPF);
                st.executeUpdate("DELETE FROM clientes WHERE "+"idclientes='"+idcliente+"'");
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){return false;}
    }

    public boolean alterarQuantidadeFilmesAlugados(String CPF,int numExemplaresAlugados) {

        try{
            int numeroExemplaresAlugadosAntigos=0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes WHERE "+"CPF='"+CPF+"'");

            if(rs.next()){
                numeroExemplaresAlugadosAntigos = Integer.parseInt(rs.getString(5));
            }
            int numeroExemplaresAtualizado = numeroExemplaresAlugadosAntigos+numExemplaresAlugados;
            st.executeUpdate("UPDATE clientes SET "+"quantidade_de_filmes_atualmente_alugados='"+numeroExemplaresAtualizado+"' WHERE idclientes='"+getIDcliente(CPF)+"'");
            return true;
        }catch (SQLException e){System.out.println(e);return false;}
    }
    public boolean alterarQuantidadeFilmesAlugados(String CPF,int numExemplaresAlugados,int op) {

        try{
            int numeroExemplaresAlugadosAntigos=0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes WHERE "+"CPF='"+CPF+"'");

            if(rs.next()){
                numeroExemplaresAlugadosAntigos = Integer.parseInt(rs.getString(5));
            }
            int numeroExemplaresAtualizado = numeroExemplaresAlugadosAntigos-numExemplaresAlugados;
            st.executeUpdate("UPDATE clientes SET "+"quantidade_de_filmes_atualmente_alugados='"+numeroExemplaresAtualizado+"' WHERE idclientes='"+getIDcliente(CPF)+"'");
            return true;
        }catch (SQLException e){System.out.println(e);return false;}
    }

    public boolean alterarQuantidadeFilmesDevolvidos(String CPF,int numExemplaresDevolvidos) {

        try{
            int numeroExemplaresDevolvidosAntigos=0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes WHERE "+"CPF='"+CPF+"'");

            if(rs.next()){
                numeroExemplaresDevolvidosAntigos = Integer.parseInt(rs.getString(5));
            }
            int numeroExemplaresAtualizado = numeroExemplaresDevolvidosAntigos+numExemplaresDevolvidos;
            st.executeUpdate("UPDATE clientes SET "+"quantidade_de_filmes_alugados_devolvidos='"+numeroExemplaresAtualizado+"' WHERE idclientes='"+getIDcliente(CPF)+"'");
            return true;
        }catch (SQLException e){System.out.println(e);return false;}
    }
    public String getSenha(String CPF) {
        try {
            Statement st = conexao.createStatement();
            String senhaResitrada;
            ResultSet rs = st.executeQuery("SELECT * FROM clientes WHERE CPF='" + CPF + "'");
            if (rs.next()) {
                senhaResitrada = rs.getString(4);
                return senhaResitrada;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public LinkedList<Clientes> listarClientes(){
        try {

            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from clientes");

            LinkedList<Clientes> listaclintes = new LinkedList<>();
            while ((rs.next())){
                Clientes clientes = new Clientes(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6)),rs.getString(7));
                listaclintes.add(clientes);
            }
            return listaclintes;
        }catch (SQLException e) { return null;}
    }
    public LinkedList<Clientes> listarClientes(String pesquisa){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from clientes WHERE "+pesquisa);
            LinkedList<Clientes> listaclintes = new LinkedList<>();
            while ((rs.next())){
                Clientes clientes = new Clientes(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6)),rs.getString(7));
                listaclintes.add(clientes);
            }
            return listaclintes;
        }catch (SQLException e) { return null;}
    }
    public int numLinhaClintes(){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from clientes");

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }
    public int numLinhaClintes(String pesquisa){
        try {
            int numLinhas = 0;
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from clientes WHERE "+pesquisa);

            while ((rs.next())){
                numLinhas++;
            }
            return numLinhas;
        }catch (SQLException e) { return 0;}
    }

    public Object[][] gerarValoresTabelas() throws SQLException, ClassNotFoundException {
        String[] colunas = {"idclientes","primeiro_nome_cliente","segundo_nome_cliente","CPF","quantidade_de_filmes_atualmente_alugados","quantidade_de_filmes_devolvidos"};
        Object[][] data = new Object[numLinhaClintes()][colunas.length];
        int i = 0;
        for(Clientes clientes:listarClientes()){
            data[i][0] = clientes.getId();
            data[i][1] = clientes.getPrimeiro_nome();
            data[i][2] = clientes.getSegundo_nome();
            data[i][3] = clientes.getCPF();
            data[i][4] = clientes.getQuanitdade_de_filmes_devolvidos();
            data[i][5] = clientes.getQuantidade_de_filmes_atualmente_alugados();

            i++;
        }
        return data;
    }

    public Object[][] gerarValoresTabelas(String pesquisa) throws SQLException, ClassNotFoundException {
        String[] colunas = {"idclientes","primeiro_nome_cliente","segundo_nome_cliente","CPF","quantidade_de_filmes_atualmente_alugados","quantidade_de_filmes_devolvidos"};
        Object[][] data = new Object[numLinhaClintes(pesquisa)][colunas.length];
        int i = 0;
        for(Clientes clientes:listarClientes(pesquisa)){
            data[i][0] = clientes.getId();
            data[i][1] = clientes.getPrimeiro_nome();
            data[i][2] = clientes.getSegundo_nome();
            data[i][3] = clientes.getCPF();
            data[i][4] = clientes.getQuanitdade_de_filmes_devolvidos();
            data[i][5] = clientes.getQuantidade_de_filmes_atualmente_alugados();

            i++;
        }
        return data;
    }


}
