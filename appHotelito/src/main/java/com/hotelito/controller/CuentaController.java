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

import com.hotelito.model.Cuenta;
import com.hotelito.repository.CuentaRepository;

@RestController
public class CuentaController {
	
	@Autowired
	CuentaRepository cuentaRepository;
	
	@GetMapping(path = "/cuentas")
	public ResponseEntity<?> getCuentas(){
		return new ResponseEntity<List<Cuenta>>((List<Cuenta>) cuentaRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/cuenta/{id}")
	public ResponseEntity<?> getCuentaById(@PathVariable Integer id){
		
		Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
		if(cuenta == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
	}
	
	@PostMapping(path = "/cuenta")
	public ResponseEntity<?> saveCuenta(@Valid @RequestBody Cuenta cuenta, BindingResult result){
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
		
		return new ResponseEntity<Cuenta>(cuentaRepository.save(cuenta), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/cuenta/{id}")
	public ResponseEntity<?> updateCuenta(@PathVariable Integer id,@Valid @RequestBody Cuenta cuenta, BindingResult result){
		
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
		
		if(cuentaRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		cuenta.setIdCuenta(id);		
		return new ResponseEntity<Cuenta>(cuentaRepository.save(cuenta), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "cuenta/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		cuentaRepository.deleteById(id);
	}

}
