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

import com.hotelito.model.MenuDia;
import com.hotelito.repository.MenuDiaRepository;

@RestController
public class MenuDiaController {
	
	@Autowired
	MenuDiaRepository menuDiaRepository;
	
	@GetMapping(path = "/menuDia")
	public ResponseEntity<?> getMenuDias(){
		return new ResponseEntity<List<MenuDia>>((List<MenuDia>) menuDiaRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/menuDia/{id}")
	public ResponseEntity<?> getMenuDiaById(@PathVariable Integer id){
		
		MenuDia menuDia = menuDiaRepository.findById(id).orElse(null);
		if(menuDia == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<MenuDia>(menuDia, HttpStatus.OK);
	}
	
	@PostMapping(path = "/menuDia")
	public ResponseEntity<?> saveMenuDia(@Valid @RequestBody MenuDia menuDia, BindingResult result){
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
		
		return new ResponseEntity<MenuDia>(menuDiaRepository.save(menuDia), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/menuDia/{id}")
	public ResponseEntity<?> updateMenuDia(@PathVariable Integer id,@Valid @RequestBody MenuDia menuDia, BindingResult result){
		
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
		
		if(menuDiaRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		menuDia.setIdMenuDia(id);		
		return new ResponseEntity<MenuDia>(menuDiaRepository.save(menuDia), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "menuDia/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		menuDiaRepository.deleteById(id);
	}

}
