package br.dev.celso.loja.data;

import java.sql.*;

public class Conexao {

    // teste
    private static Connection conexao = null;

    public static Connection getConexao(){

        String usuario = "celso";
        String senha = "1234567890";
        //String connectionUrl = "jdbc:sqlserver://localhost;databaseName=db_loja;encrypt=true;trustServerCertificate=true";
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=db_loja;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

        try {

            if (conexao == null){
                conexao = DriverManager.getConnection(connectionUrl);
//                conexao = DriverManager.getConnection(
//                        connectionUrl,
//                        usuario,
//                        senha);
            }

            System.out.println("Conexão efetuada com sucesso!");
            return conexao;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void fecharConexao() {
        try{
            conexao.close();
            System.out.println("Conexão fechada com sucesso!");
        }catch (Exception erro){
            System.out.println("Ocorreu o seguinte erro: " + erro.getMessage());
        }
    }

}
