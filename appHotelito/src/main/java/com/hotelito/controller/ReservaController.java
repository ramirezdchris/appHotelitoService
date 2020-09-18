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

import com.hotelito.model.Reserva;
import com.hotelito.repository.ReservaRepository;

@RestController
public class ReservaController {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@GetMapping(path = "/reserva")
	public ResponseEntity<?> getReservas(){
		return new ResponseEntity<List<Reserva>>((List<Reserva>) reservaRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/reserva/{id}")
	public ResponseEntity<?> getReservaById(@PathVariable Integer id){
		
		Reserva reserva = reservaRepository.findById(id).orElse(null);
		if(reserva == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
	}
	
	@PostMapping(path = "/reserva")
	public ResponseEntity<?> saveReserva(@Valid @RequestBody Reserva reserva, BindingResult result){
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
		
		return new ResponseEntity<Reserva>(reservaRepository.save(reserva), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/reserva/{id}")
	public ResponseEntity<?> updateReserva(@PathVariable Integer id,@Valid @RequestBody Reserva reserva, BindingResult result){
		
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
		
		if(reservaRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		reserva.setIdReserva(id);		
		return new ResponseEntity<Reserva>(reservaRepository.save(reserva), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "reserva/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		reservaRepository.deleteById(id);
	}

}
