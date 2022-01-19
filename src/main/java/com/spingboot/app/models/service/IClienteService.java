package com.spingboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spingboot.app.models.entity.Cliente;
import com.spingboot.app.models.entity.Factura;
import com.spingboot.app.models.entity.Producto;

public interface IClienteService {
	public List<Cliente> listarClientes();

	public Page<Cliente> listarClientes(Pageable pageable);

	public void guardarCliente(Cliente cliente);

	public Cliente findOne(Long id);

	public void eliminar(Long id);

	public List<Producto> findByNombre(String term);

	public void saveFactura(Factura factura);

	public Producto findProductoById(Long id);
	
	public Factura findFacturaById(Long id);
	
	public void deleteFactura(Long id);
	
	public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id);
	
	public Cliente fetchByIdWithFacturas(Long id);
}
