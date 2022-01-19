package com.spingboot.app.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spingboot.app.models.entity.Cliente;
import com.spingboot.app.models.service.IClienteService;
import com.spingboot.app.models.service.IUploadFileService;
import com.spingboot.app.util.paginator.PageRender;

@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	@Qualifier("clienteServiceJPA")
	private IClienteService clienteService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		//Cliente cliente = clienteService.findOne(id);
		
		//Consulta optimizada utilizando joins
		Cliente cliente = clienteService.fetchByIdWithFacturas(id);
		
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombres());
		return "ver";
	}
	
	@GetMapping("/listar")
	public String listar(@RequestParam(name ="page", defaultValue = "0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = clienteService.listarClientes(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/clientes/listar", clientes);
		model.addAttribute("titulo", "Listar de CLientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}
	
	@GetMapping("/form")
	public String formularioCliente(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		
		model.put("titulo", "Formulario de Clientes");
		model.put("cliente", cliente);
		return "form";
	}
	
	@PostMapping("/form")
	public String crearCliente(@Valid Cliente cliente, BindingResult result, @RequestParam("file") MultipartFile foto, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		if (!foto.isEmpty()) {

			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {

				uploadFileService.delete(cliente.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			cliente.setFoto(uniqueFilename);
		}

		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";

		clienteService.guardarCliente(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		
		if(id > 0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El id del cliente no existe en la DB");
			}
		}else {
			flash.addFlashAttribute("error", "ID NO ENCONTRADO");
			return "redirect:/clientes/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if(id > 0) {
			Cliente cliente = clienteService.findOne(id);
			clienteService.eliminar(id);
			flash.addFlashAttribute("success", "Cliente Eliminado con éxito");
			
			Path rootPath = Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
			File archio = rootPath.toFile();
			
			if(archio.exists() && archio.canRead()) {
				if(archio.delete()) {
					flash.addFlashAttribute("info", "Foto " + cliente.getFoto() + " eliminada con exito");
				}
			}
		}
		return "redirect:/clientes/listar";
	}
	
}
