package com.hotelito.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
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

import com.hotelito.entities.RespuestaGenerica;
import com.hotelito.model.Personal;
import com.hotelito.model.UsuarioEmpleado;
import com.hotelito.repository.UsuarioEmpleadoRepository;


@RestController
public class UsuarioEmpleadoController {
	
	@Autowired
	UsuarioEmpleadoRepository usuarioEmpleadoRepository;
	
	@GetMapping(path = "/usuarioEmpleado")
	public ResponseEntity<?> getUsuarioEmpleados(){
		return new ResponseEntity<List<UsuarioEmpleado>>((List<UsuarioEmpleado>) usuarioEmpleadoRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/usuarioEmpleado/{id}")
	public ResponseEntity<?> getUsuarioEmpleadoById(@PathVariable Integer id){
		
		UsuarioEmpleado usuarioEmpleado = usuarioEmpleadoRepository.findById(id).orElse(null);
		if(usuarioEmpleado == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<UsuarioEmpleado>(usuarioEmpleado, HttpStatus.OK);
	}
	
	@PostMapping(path = "/usuarioEmpleado")
	public ResponseEntity<?> saveUsuarioEmpleado(@Valid @RequestBody UsuarioEmpleado usuarioEmpleado, BindingResult result){
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
		
		return new ResponseEntity<UsuarioEmpleado>(usuarioEmpleadoRepository.save(usuarioEmpleado), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/usuarioEmpleado/{id}")
	public ResponseEntity<?> updateUsuarioEmpleado(@PathVariable Integer id,@Valid @RequestBody UsuarioEmpleado usuarioEmpleado, BindingResult result){
		
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
		
		if(usuarioEmpleadoRepository.findById(id).orElse(null) == null) {
			Map<String, Object> response = new HashMap<>();
			response.put("codigo", 1001);
			response.put("mensaje", "administrador no encontrado.");
			response.put("descripcion",
					"El administrador con el id ".concat(id.toString()).concat(" no se encontró en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		usuarioEmpleado.setUsuario(String.valueOf(id));		
		return new ResponseEntity<UsuarioEmpleado>(usuarioEmpleadoRepository.save(usuarioEmpleado), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "usuarioEmpleado/{id}")
	public void deleteAdministrador(@PathVariable Integer id) {
		usuarioEmpleadoRepository.deleteById(id);
	}
	
	@GetMapping(path = "/usuarioEmpleado/login/{usuario}/{clave}")
	public ResponseEntity<?> loginCliente(@PathVariable String usuario, @PathVariable String clave){
		List<UsuarioEmpleado> loginUsuarioEmpleado = usuarioEmpleadoRepository.loginEmpleado(usuario, clave);
		return new ResponseEntity<List<UsuarioEmpleado>>(loginUsuarioEmpleado, HttpStatus.OK);
		
	}
	
	@GetMapping(path ="/usuarioLogin/{dato}/{valor}")
	public ResponseEntity<?> validarUsuario(@PathVariable String dato,@PathVariable String valor){
		UsuarioEmpleado usuario = usuarioEmpleadoRepository.usarioVal(dato);
		System.out.println(" ==== "+dato+" "+valor);
		//util = new Utilidades();
		RespuestaGenerica respuesta = new RespuestaGenerica(); 
		HttpStatus httpStatus = null;
		if(usuario == null) {
			respuesta.setCodigo("1");
			respuesta.setMensaje("Error inicio sesion");
			respuesta.setDescripcion("Usuario no existe");
			httpStatus = httpStatus.BAD_REQUEST;
			
		}else if(usuario.getClave().equalsIgnoreCase(valor.trim()) && usuario.getUsuario().equalsIgnoreCase(dato.trim())) {
			if(usuario != null) {
				System.out.println(usuario.getUsuario()+" 3 "+usuario.getClave());
				respuesta.setCodigo("3");
				respuesta.setMensaje(String.valueOf(usuario.getId_usuario_empleado()));
				respuesta.setDescripcion("Login exitoso");
				httpStatus = httpStatus.ACCEPTED;
			}
		}else {
			System.out.println(usuario.getUsuario()+" 2 "+usuario.getClave());
			respuesta.setCodigo("2");
			respuesta.setMensaje("Usuario/Contraseña incorrectos");
			respuesta.setDescripcion("Login error");
			httpStatus = httpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<RespuestaGenerica>(respuesta, httpStatus);
		}	

}
