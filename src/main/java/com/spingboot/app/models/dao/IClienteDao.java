package com.spingboot.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spingboot.app.models.entity.Cliente;

@Repository
public interface IClienteDao {
	public List<Cliente> listarClientes();
	public void guardarCliente(Cliente cliente);
	public Cliente findOne(Long id);
	public void eliminar(Long id);
}
