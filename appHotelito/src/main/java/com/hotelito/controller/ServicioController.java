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

import com.hotelito.model.Servicio;
import com.hotelito.repository.ServicioRepository;

@RestController
public class ServicioController {
	
	@Autowired
	ServicioRepository servicioRepository;
	
	@GetMapping(path = "/servicio")
	public ResponseEntity<?> getServicios(){
		return new ResponseEntity<List<Servicio>>((List<Servicio>) servicioRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/servicio/{id}")
	public ResponseEntity<?> getServicioById(@PathVariable Integer id){
		
		Servicio servicio = servicioRepository.findById(id).orElse(null);
		if(servicio == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
	}
	
	@PostMapping(path = "/servicio")
	public ResponseEntity<?> saveServicio(@Valid @RequestBody Servicio servicio, BindingResult result){
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
		
		return new ResponseEntity<Servicio>(servicioRepository.save(servicio), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/servicio/{id}")
	public ResponseEntity<?> updateServicio(@PathVariable Integer id,@Valid @RequestBody Servicio servicio, BindingResult result){
		
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
		
		if(servicioRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		servicio.setIdServicio(id);		
		return new ResponseEntity<Servicio>(servicioRepository.save(servicio), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "servicio/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		servicioRepository.deleteById(id);
	}

}
