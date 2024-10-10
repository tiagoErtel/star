package com.group.star.bebida;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/bebidas")
public class BebidaController {
	
	private final BebidaRepository bebidaRepository;	
	
	public BebidaController(BebidaRepository bebidaRepository) {
		this.bebidaRepository = bebidaRepository;
	}
	
	@GetMapping("")
	List<Bebida> findAll(){
		return bebidaRepository.findAll();
	}
	
	@GetMapping("count")
	int count(){
		return bebidaRepository.count();
	}
	
	@GetMapping("{id}")
	Bebida findById(@PathVariable int id) {
		Optional<Bebida> bebida = bebidaRepository.findById(id);
		
		if (bebida.isEmpty()) {
			throw new BebidaNotFoundException();
		}
		
		return bebida.get();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	void create(@Valid @RequestBody Bebida bebida) {
		bebidaRepository.create(bebida);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("{id}")
	void update(@Valid @RequestBody Bebida bebida, @PathVariable int id) {
		bebidaRepository.update(bebida, id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	void delete(@PathVariable int id) {
		bebidaRepository.delete(id);
	}
}
