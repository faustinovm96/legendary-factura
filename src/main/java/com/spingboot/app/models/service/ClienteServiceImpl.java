package com.spingboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spingboot.app.models.dao.IClienteDao;
import com.spingboot.app.models.dao.IClienteDaoCrud;
import com.spingboot.app.models.domain.Cliente;

@Service("clienteServiceJPA")
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	@Qualifier("clienteDaoCRUD")
	private IClienteDaoCrud clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void guardarCliente(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).get();
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional
	public Page<Cliente> listarClientes(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

}
