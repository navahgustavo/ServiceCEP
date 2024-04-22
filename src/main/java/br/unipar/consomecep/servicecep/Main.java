package br.unipar.consomecep.servicecep;

import br.unipar.consomecep.servicecep.dao.EnderecoDAO;
import br.unipar.consomecep.servicecep.dao.EnderecoDAOImpl;
import br.unipar.consomecep.servicecep.dao.UsuarioDAO;
import br.unipar.consomecep.servicecep.dao.UsuarioDAOImpl;
import br.unipar.consomecep.servicecep.model.Endereco;
import br.unipar.consomecep.servicecep.model.Usuario;
import br.unipar.consomecep.servicecep.util.EntityManagerUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {



        try {
            EntityManagerUtil.getEntityManagerFactory();
            //salvarUsuario();
            //editarUsuario();
            //deletarUsuario();
            //buscarUsuarioPorID();
            //buscarTodosUsuarios();
            salvarEndereco();

            System.out.println("endereço: " + getViaCep("85802190"));

            EntityManagerUtil.closeEntityManagerFactory();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    private static void salvarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

        Usuario usuario = new Usuario();

        usuario.setNome("Fulaninha");
        usuario.setLogin("fulaninha123");
        usuario.setSenha("1234567");

        usuarioDAO.save(usuario);
    }

    private static void editarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

        Usuario usuario = usuarioDAO.findById(1L);

        usuario.setSenha("654321");

        usuarioDAO.update(usuario);
    }

    private static void deletarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

        Usuario usuario = usuarioDAO.findById(1L);

        usuarioDAO.delete(usuario);
    }

    private static void buscarUsuarioPorID() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

        Usuario usuario = usuarioDAO.findById(1L);

        System.out.println("Usuário " + usuario.getNome() + " encontrado com sucesso!");
    }

    //imprimir todos os usuários
    private static void buscarTodosUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

        List<Usuario> usuarios = usuarioDAO.findAll();

        for (Usuario usuario : usuarios) {
            System.out.println("Usuário " + usuario.getNome() + " encontrado com sucesso!");
        }
    }

    private static Endereco getViaCep(String cep) throws Exception{
        URL url = new URL("http://viacep.com.br/ws/" +  cep.replace("-",
                "").replace(".", "") + "/xml/");

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        String result = "";

        while ((inputLine = in.readLine()) != null)
            result += inputLine;

        in.close();

        return Endereco.unmarshalFromString(result);
    }

    private static void salvarEndereco() {

        try {
            EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EntityManagerUtil.getManager());

            enderecoDAO.save(getViaCep("85802190"));
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
