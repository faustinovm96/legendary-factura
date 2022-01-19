package com.spingboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.spingboot.app.models.entity.Cliente;

@Component("clienteDaoCRUD")
public interface IClienteDaoCrud extends PagingAndSortingRepository<Cliente, Long>{
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWithFacturas(Long id);
}
