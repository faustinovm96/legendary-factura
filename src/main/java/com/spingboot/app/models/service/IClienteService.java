package com.spingboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spingboot.app.models.domain.Cliente;

public interface IClienteService {
	public List<Cliente> listarClientes();
	public Page<Cliente> listarClientes(Pageable pageable);
	public void guardarCliente(Cliente cliente);
	public Cliente findOne(Long id);
	public void eliminar(Long id);
}
