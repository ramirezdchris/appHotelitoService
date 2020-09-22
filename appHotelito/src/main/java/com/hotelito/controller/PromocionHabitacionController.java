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

import com.hotelito.model.PromocionHabitacion;
import com.hotelito.repository.PromocionHabitacionRepository;

@RestController
public class PromocionHabitacionController {
	
	@Autowired
	PromocionHabitacionRepository promocionHabitacionRepository;
	
	@GetMapping(path = "/promocionHabitacion")
	public ResponseEntity<?> getPromocionHabitacions(){
		return new ResponseEntity<List<PromocionHabitacion>>((List<PromocionHabitacion>) promocionHabitacionRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/promocionHabitacion/{id}")
	public ResponseEntity<?> getPromocionHabitacionById(@PathVariable Integer id){
		
		PromocionHabitacion promocionHabitacion = promocionHabitacionRepository.findById(id).orElse(null);
		if(promocionHabitacion == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<PromocionHabitacion>(promocionHabitacion, HttpStatus.OK);
	}
	
	@PostMapping(path = "/promocionHabitacion")
	public ResponseEntity<?> savePromocionHabitacion(@Valid @RequestBody PromocionHabitacion promocionHabitacion, BindingResult result){
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
		
		return new ResponseEntity<PromocionHabitacion>(promocionHabitacionRepository.save(promocionHabitacion), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/promocionHabitacion/{id}")
	public ResponseEntity<?> updatePromocionHabitacion(@PathVariable Integer id,@Valid @RequestBody PromocionHabitacion promocionHabitacion, BindingResult result){
		
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
		
		if(promocionHabitacionRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		promocionHabitacion.setIdPromHab(id);		
		return new ResponseEntity<PromocionHabitacion>(promocionHabitacionRepository.save(promocionHabitacion), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "promocionHabitacion/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		promocionHabitacionRepository.deleteById(id);
	}

}
