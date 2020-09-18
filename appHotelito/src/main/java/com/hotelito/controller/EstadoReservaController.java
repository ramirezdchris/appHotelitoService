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

import com.hotelito.model.EstadoReserva;
import com.hotelito.repository.EstadoReservaRepository;

@RestController
public class EstadoReservaController {
	
	@Autowired
	EstadoReservaRepository estadoReservaRepository;
	
	@GetMapping(path = "/estadoReserva")
	public ResponseEntity<?> getEstadoReservas(){
		return new ResponseEntity<List<EstadoReserva>>((List<EstadoReserva>) estadoReservaRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/estadoReserva/{id}")
	public ResponseEntity<?> getEstadoReservaById(@PathVariable Integer id){
		
		EstadoReserva estadoReserva = estadoReservaRepository.findById(id).orElse(null);
		if(estadoReserva == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<EstadoReserva>(estadoReserva, HttpStatus.OK);
	}
	
	@PostMapping(path = "/estadoReserva")
	public ResponseEntity<?> saveEstadoReserva(@Valid @RequestBody EstadoReserva estadoReserva, BindingResult result){
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
		
		return new ResponseEntity<EstadoReserva>(estadoReservaRepository.save(estadoReserva), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/estadoReserva/{id}")
	public ResponseEntity<?> updateEstadoReserva(@PathVariable Integer id,@Valid @RequestBody EstadoReserva estadoReserva, BindingResult result){
		
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
		
		if(estadoReservaRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		estadoReserva.setIdEstadoReserva(id);		
		return new ResponseEntity<EstadoReserva>(estadoReservaRepository.save(estadoReserva), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "estadoReserva/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		estadoReservaRepository.deleteById(id);
	}

}
