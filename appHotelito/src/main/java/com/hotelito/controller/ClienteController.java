package com.hotelito.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelito.model.Cliente;
import com.hotelito.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping(path = "/cliente")
	public ResponseEntity<?> getClientes(){
		return new ResponseEntity<List<Cliente>>((List<Cliente>) clienteRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/cliente/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Integer id){
		
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		if(cliente == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping(path = "/cliente")
	public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
		if(result.hasErrors()) {
			Map<String, Object> response = new HashMap<>();
			List<HashMap<String, Object>> errors = new ArrayList<>();
			result.getFieldErrors().forEach(err ->{
				Map<String, Object> error = new HashMap<>();
				error.put("campo", err.getField());
				error.put("mensaje", err.getDefaultMessage());
				errors.add((HashMap<String, Object>) error);
			});
			
			response.put("codigo", 1000);
			response.put("mensaje", "Error de validacion.");
			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Cliente>(clienteRepository.save(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/cliente/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable Integer id,@Valid @RequestBody Cliente cliente, BindingResult result){
		
		if(result.hasErrors()) {
			Map<String, Object> response = new HashMap<>();
			List<HashMap<String, Object>> errors = new ArrayList<>();
			result.getFieldErrors().forEach(err ->{
				Map<String, Object> error = new HashMap<>();
				error.put("campo", err.getField());
				error.put("mensaje", err.getDefaultMessage());
				errors.add((HashMap<String, Object>) error);
			});
			
			response.put("codigo", 1000);
			response.put("mensaje", "Error de validacion.");
			response.put("errores", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(clienteRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		cliente.setIdCliente(id);		
		return new ResponseEntity<Cliente>(clienteRepository.save(cliente), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "cliente/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		clienteRepository.deleteById(id);
	}

}
