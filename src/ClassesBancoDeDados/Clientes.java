package ClassesBancoDeDados;

/**
 *  A classe {@code Clientes} foi criada para auxiliar o uso da tabela de clientes do conjunto de dados da locadora
 */

public class Clientes {
    private int id;
    private String primeiro_nome;
    private String segundo_nome;
    private String CPF;
    private String senha;
    private int quantidade_de_filmes_atualmente_alugados;
    private int quanitdade_de_filmes_devolvidos;

    /**
     * Cria a estrutura de dados do Cliente novo da locadora
     * @param primeiro_nome Primeiro nome do Cliente
     * @param segundo_nome Segundo nome do Cliente
     * @param senha Senha criada pelo Cliente
     * @param CPF CPF do Cliente
     */
    public Clientes(String primeiro_nome, String segundo_nome, String senha, String CPF){

        this.primeiro_nome = primeiro_nome;
        this.segundo_nome = segundo_nome;
        this.senha = senha;
        this.quanitdade_de_filmes_devolvidos = 0;
        this.quantidade_de_filmes_atualmente_alugados = 0;
        this.CPF = CPF;
    }

    /**
     * O método é utilizado quando a necessidade de acessar o banco de dados, neste caso faz-se necessário conhecer o id do Cliente
     * @param id id do cliente na tabela do conjunto de dados
     * @param primeiro_nome  Primeiro nome do Cliente
     * @param segundo_nome Segundo nome do Cliente
     * @param senha Senha criada pelo Cliente
     * @param quanitdade_de_filmes_devolvidos Quantidade de filmes devolvidos
     * @param quantidade_de_filmes_atualmente_alugados Quantidade de filmes atualmente alugados
     * @param CPF  CPF do Cliente
     */

    public Clientes(int id,String primeiro_nome, String segundo_nome, String senha,int quanitdade_de_filmes_devolvidos,int quantidade_de_filmes_atualmente_alugados, String CPF){
        this.id = id;
        this.primeiro_nome = primeiro_nome;
        this.segundo_nome = segundo_nome;
        this.senha = senha;
        this.CPF = CPF;
        this.quanitdade_de_filmes_devolvidos = quanitdade_de_filmes_devolvidos;
        this.quantidade_de_filmes_atualmente_alugados = quantidade_de_filmes_atualmente_alugados;
    }

    /**
     * Acessa o id do cliente
     * @return id do cliente
     */
    public int getId(){
        return id;
    }

    /**
     * Acessa o primeiro nome do cliente
     * @return Primeiro nome do cliente
     */
    public String getPrimeiro_nome(){
        return primeiro_nome;
    }

    /**
     * Acessa o segundo nome do cliente
     * @return Segundo nome do cliente
     */
    public String getSegundo_nome(){
        return segundo_nome;
    }

    /**
     * Acessa a senha do cliente
     * @return Senha do cliente
     */
    public String getSenha(){
        return senha;
    }

    /**
     * Acessa a Quantidade de filmes atualmente alugados do cliente
     * @return Quantidade de filmes atualmente alugados do cliente
     */
    public int getQuantidade_de_filmes_atualmente_alugados(){
        return quantidade_de_filmes_atualmente_alugados;
    }

    /**
     * Acessa Quanitdade de filmes devolvidos do cliente
     * @return Quanitdade de filmes devolvidos do cliente
     */
    public int getQuanitdade_de_filmes_devolvidos(){
        return quanitdade_de_filmes_devolvidos;
    }

    /**
     * Acessa o CPF do cliente
     * @return CPF do cliente
     */
    public String getCPF(){
        return CPF;
    }
}
