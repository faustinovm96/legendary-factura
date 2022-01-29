package com.spingboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="fac_descripcion")
	private String descripcion;

	// @NotEmpty
	@Column(name="fac_observacion")
	private String observacion;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")
	public List<DetallesFactura> detalles;

	public Factura() {
		detalles = new ArrayList<DetallesFactura>();
	}
	
	public Long getId() {
		return id;
	}

	public List<DetallesFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallesFactura> detalles) {
		this.detalles = detalles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public void addDetalleFactura(DetallesFactura detalle) {
		this.detalles.add(detalle);
	}
	
	public Double getTotal() {
		Double total = 0.0;
		int size = detalles.size();
		for (int i = 0; i < size; i++) {
			total += detalles.get(i).calcularImporte();
		}
		return total;
	}

	private static final long serialVersionUID = 4502029805123866593L;

}
