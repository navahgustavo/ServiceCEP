package br.unipar.consomecep.servicecep.service;

import br.unipar.consomecep.servicecep.dao.UsuarioDAO;
import br.unipar.consomecep.servicecep.dao.UsuarioDAOImpl;
import br.unipar.consomecep.servicecep.model.Usuario;
import br.unipar.consomecep.servicecep.util.EntityManagerUtil;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.unipar.consomecep.servicecep.service.UsuarioSEI")
public class UsuarioSIB implements UsuarioSEI {
    @Override
    public String boasVindas(String nome) {
        return "Bem Vindo" + nome + "!";
    }

    @Override
    public Usuario consultaUsuario(Long idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EntityManagerUtil.getManager());

        Usuario usuario = usuarioDAO.findById(idUsuario);

        return usuario;
    }
}
