package com.spingboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.spingboot.app.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
