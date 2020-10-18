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

import com.hotelito.model.Promocion;
import com.hotelito.repository.PromocionRepository;

@RestController
public class PromocionController {
	
	@Autowired
	PromocionRepository promocionRepository;
	
	@GetMapping(path = "/promocion")
	public ResponseEntity<?> getPromocions(){
		return new ResponseEntity<List<Promocion>>((List<Promocion>) promocionRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/promocion/{id}")
	public ResponseEntity<?> getPromocionById(@PathVariable Integer id){
		
		Promocion promocion = promocionRepository.findById(id).orElse(null);
		if(promocion == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Promocion>(promocion, HttpStatus.OK);
	}	
	
	@PostMapping(path = "/promocion")
	public ResponseEntity<?> savePromocion(@Valid @RequestBody Promocion promocion, BindingResult result){
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
		
		return new ResponseEntity<Promocion>(promocionRepository.save(promocion), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/promocion/{id}")
	public ResponseEntity<?> updatePromocion(@PathVariable Integer id,@Valid @RequestBody Promocion promocion, BindingResult result){
		
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
		
		if(promocionRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		promocion.setIdPromocion(id);		
		return new ResponseEntity<Promocion>(promocionRepository.save(promocion), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "promocion/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		promocionRepository.deleteById(id);
	}

}
