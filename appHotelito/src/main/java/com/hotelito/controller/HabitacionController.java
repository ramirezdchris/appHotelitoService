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

import com.hotelito.model.Habitacion;
import com.hotelito.repository.HabitacionRepository;

@RestController
public class HabitacionController {
	
	@Autowired
	HabitacionRepository habitacionRepository;
	
	@GetMapping(path = "/habitacion")
	public ResponseEntity<?> getHabitacions(){
		return new ResponseEntity<List<Habitacion>>((List<Habitacion>) habitacionRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/habitacion/{id}")
	public ResponseEntity<?> getHabitacionById(@PathVariable Integer id){
		
		Habitacion habitacion = habitacionRepository.findById(id).orElse(null);
		if(habitacion == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Habitacion>(habitacion, HttpStatus.OK);
	}
	
	@PostMapping(path = "/habitacion")
	public ResponseEntity<?> saveHabitacion(@Valid @RequestBody Habitacion habitacion, BindingResult result){
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
		
		return new ResponseEntity<Habitacion>(habitacionRepository.save(habitacion), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/habitacion/{id}")
	public ResponseEntity<?> updateHabitacion(@PathVariable Integer id,@Valid @RequestBody Habitacion habitacion, BindingResult result){
		
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
		
		if(habitacionRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		habitacion.setIdHabitacion(id);		
		return new ResponseEntity<Habitacion>(habitacionRepository.save(habitacion), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "habitacion/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		habitacionRepository.deleteById(id);
	}

}
