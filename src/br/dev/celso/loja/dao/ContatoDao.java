package br.dev.celso.loja.dao;

import br.dev.celso.loja.data.Conexao;
import br.dev.celso.loja.model.Contato;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {

    private Contato contato;

    public ContatoDao(){}

    public ContatoDao(Contato contato){
        this.contato = contato;
    }

    public int salvar(){

        String sql = "INSERT INTO tbl_contatos " +
                "(nome, email, salario) output inserted.id " +
                "values(?,?,?)";

        try {
            PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
            stm.setString(1, contato.getNome());
            stm.setString(2, contato.getEmail());
            stm.setDouble(3, contato.getSalario());
            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public Contato buscarContatoPeloId(int id){

        String sql = "SELECT * FROM tbl_contatos WHERE id = ?";

        try{
            PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                return new Contato(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                );
            }

            return null;

        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public List<Contato> listar(){
        String sql = "SELECT * FROM tbl_contatos ORDER BY nome ASC";
        List<Contato> contatos = new ArrayList<>();
        try{

            PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Contato c = new Contato(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                );
                contatos.add(c);
            }

            return contatos;

        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

}