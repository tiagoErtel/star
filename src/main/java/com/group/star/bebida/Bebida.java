package com.group.star.bebida;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "bebidas")
public class Bebida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotEmpty
	private String Name;
	@Enumerated(EnumType.STRING)
	private BebidaType Type;
	
    public Bebida() {
    }
	
	public Bebida(String name, BebidaType type) {
		this.Name = name;
		this.Type = type;
	}

	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
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
