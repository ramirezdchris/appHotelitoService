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

import com.hotelito.model.TipoHabitacion;
import com.hotelito.repository.TipoHabitacionRepository;

@RestController
public class TipoHabitacionController {
	
	@Autowired
	TipoHabitacionRepository tipoHabitacionRepository;
	
	@GetMapping(path = "/tipoHabitacion")
	public ResponseEntity<?> getTipoHabitacions(){
		return new ResponseEntity<List<TipoHabitacion>>((List<TipoHabitacion>) tipoHabitacionRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/tipoHabitacion/{id}")
	public ResponseEntity<?> getTipoHabitacionById(@PathVariable Integer id){
		
		TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(id).orElse(null);
		if(tipoHabitacion == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<TipoHabitacion>(tipoHabitacion, HttpStatus.OK);
	}
	
	@PostMapping(path = "/tipoHabitacion")
	public ResponseEntity<?> saveTipoHabitacion(@Valid @RequestBody TipoHabitacion tipoHabitacion, BindingResult result){
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
		
		return new ResponseEntity<TipoHabitacion>(tipoHabitacionRepository.save(tipoHabitacion), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/tipoHabitacion/{id}")
	public ResponseEntity<?> updateTipoHabitacion(@PathVariable Integer id,@Valid @RequestBody TipoHabitacion tipoHabitacion, BindingResult result){
		
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
		
		if(tipoHabitacionRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		tipoHabitacion.setIdTipoHabitacion(id);		
		return new ResponseEntity<TipoHabitacion>(tipoHabitacionRepository.save(tipoHabitacion), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "tipoHabitacion/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		tipoHabitacionRepository.deleteById(id);
	}

}
