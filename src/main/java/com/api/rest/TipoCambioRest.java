package com.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.dao.TipoCambioDAO;
import com.api.model.TipoCambio;
import com.api.model.TipoCambioOperacion;
import com.api.model.TipoCambioRespuesta;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("tipocambio")
public class TipoCambioRest {

	@Autowired
	private TipoCambioDAO tipoCambioDAO;

	@PostMapping("/calcular")
	public ResponseEntity<?> guardar(@Valid @RequestBody TipoCambioOperacion tipoCambioOperacion,
			BindingResult result) throws NoSuchMethodException, SecurityException {

		Optional<TipoCambio> tipoCambio = tipoCambioDAO.findById(1);
	

		if (result.hasErrors()) {
			List<String> message = new ArrayList<>();
			result.getAllErrors().forEach((error) -> {
				FieldError fieldError = (FieldError) error;
				String field = fieldError.getField();
				message.add("" + fieldError.getField() + ":" + fieldError.getDefaultMessage() + "");
			});

			return ResponseEntity.badRequest().body(message);
		}

		TipoCambioRespuesta tipoCambioRespuesta = tipoCambioOperacion.calcularTipoCambio(tipoCambioOperacion,
				tipoCambio.get().getTipocambio());
		return ResponseEntity.ok(tipoCambioRespuesta);

	}
	
	

	@PutMapping("/actualizar")
	public void actualizar(@RequestBody TipoCambio tipoCambio) {
		tipoCambioDAO.save(tipoCambio);
	}
	@GetMapping("/listar")
	public List<TipoCambio> listar() {
		return tipoCambioDAO.findAll();
	}

}
