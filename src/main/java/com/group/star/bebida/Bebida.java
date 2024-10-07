package com.group.star.bebida;

import jakarta.validation.constraints.NotEmpty;

public class Bebida {
	
	private int Id;
	@NotEmpty
	private String Name;
	private BebidaType Type;
	
	public Bebida(int id, String name, BebidaType type) {
		if (id == 69) {
			throw new IllegalArgumentException("Ta de brinquetion with me?");
		}
		this.Id = id;
		this.Name = name;
		this.Type = type;
	}

	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public BebidaType getType() {
		return Type;
	}
	
	public void setType(BebidaType type) {
		Type = type;
	}

}
