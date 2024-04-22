package br.unipar.consomecep.servicecep.service;

import br.unipar.consomecep.servicecep.model.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface UsuarioSEI {

    @WebMethod
    String boasVindas(@WebParam(name = "nome") String nome);

    Usuario consultaUsuario(@WebParam(name = "idUsuario") Long idUsuario);
}
