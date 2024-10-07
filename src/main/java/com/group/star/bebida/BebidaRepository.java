package com.group.star.bebida;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class BebidaRepository {

	private List<Bebida> bebidas = new ArrayList<>();
	
	public List<Bebida> findAll(){
		return bebidas;
	}
	
	public Optional<Bebida> findById(int id){
		return bebidas.stream()
				.filter(bebidas -> bebidas.getId() == id)
				.findFirst();
	}
	
	public void create(Bebida bebida) {
		bebidas.add(bebida);
	}
	
	public void update(Bebida bebida, int id) {
		Optional<Bebida> existingBebida = findById(id);
		if (existingBebida.isPresent()) {
			bebidas.set(bebidas.indexOf(existingBebida.get()), bebida);
		}
	}
	
	public void delete(int id) {
		bebidas.removeIf(bebida -> bebida.getId() == id);
	}
	
	@PostConstruct
	private void init(){
		bebidas
		.add(
			new Bebida(
			1,
			"vodka", 
			BebidaType.DRINK)
		);
		
		bebidas
		.add(
			new Bebida(
			2,
			"California Ale", 
			BebidaType.CHOPP)
		);
		
		bebidas
		.add(
			new Bebida(
			3,
			"whisky", 
			BebidaType.DRINK)
		);
	}
}
