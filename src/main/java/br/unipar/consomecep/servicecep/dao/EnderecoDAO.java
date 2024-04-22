package br.unipar.consomecep.servicecep.dao;

import br.unipar.consomecep.servicecep.model.Endereco;

import java.util.List;

public interface EnderecoDAO {

    void save(Endereco endereco);
    void update(Endereco endereco);
    void delete(Endereco endereco);

    Endereco findById(Long id);
    List<Endereco> findAll();

}
