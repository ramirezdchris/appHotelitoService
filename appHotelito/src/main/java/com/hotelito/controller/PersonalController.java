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

import com.hotelito.model.Personal;
import com.hotelito.repository.PersonalRepository;

@RestController
public class PersonalController {
	
	@Autowired
	PersonalRepository personalRepository;
	
	@GetMapping(path = "/personal")
	public ResponseEntity<?> getPersonals(){
		return new ResponseEntity<List<Personal>>((List<Personal>) personalRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/personal/{id}")
	public ResponseEntity<?> getPersonalById(@PathVariable Integer id){
		
		Personal personal = personalRepository.findById(id).orElse(null);
		if(personal == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Personal>(personal, HttpStatus.OK);
	}
	
	@PostMapping(path = "/personal")
	public ResponseEntity<?> savePersonal(@Valid @RequestBody Personal personal, BindingResult result){
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
		
		return new ResponseEntity<Personal>(personalRepository.save(personal), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/personal/{id}")
	public ResponseEntity<?> updatePersonal(@PathVariable Integer id,@Valid @RequestBody Personal personal, BindingResult result){
		
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
		
		if(personalRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		personal.setIdPersonal(id);		
		return new ResponseEntity<Personal>(personalRepository.save(personal), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "personal/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		personalRepository.deleteById(id);
	}

}
