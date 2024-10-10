package com.group.star.bebida;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.JdbcClient.MappedQuerySpec;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class BebidaRepository {

	private static final Logger log = LoggerFactory.getLogger(BebidaRepository.class);
	private final JdbcClient jdbcClient;
	
	public BebidaRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}
	
	public List<Bebida> findAll(){
		return jdbcClient.sql("select * from bebidas")
				.query(Bebida.class)
				.list();
	}
	
	public Optional<Bebida> findById(int id){
		return jdbcClient.sql("select * from bebidas where id = :id")
				.param("id", id)
				.query(Bebida.class)
				.optional();
	}
	
	public void create(Bebida bebida) {
		var updated = jdbcClient.sql("insert into bebidas (id, name, type) values (:id, :name, :type)")
				.param("id", bebida.getId())
				.param("name", bebida.getName())
				.param("type", bebida.getType().toString())
				.update();
		
		Assert.state(updated == 1, "Failed to create bebida " + bebida.getName());
	}
	
	public void update(Bebida bebida, int id) {
		var updated = jdbcClient.sql("update bebidas set id = :newId, name = :name, type = :type where id = :currentId")
				.param("newId", bebida.getId())
				.param("name", bebida.getName())
				.param("type", bebida.getType().toString())
				.param("currentId", id)
				.update();
		
		Assert.state(updated == 1, "Failed to update the bebida " + bebida.getName());
	}
	
	public void delete(int id) {
		var updated = jdbcClient.sql("delete from bebidas where id = :id")
				.param("id", id)
				.update();
		
		Assert.state(updated == 1, "Failed to update the bebida " + id);
	}
	
	public int count() {
	    return jdbcClient.sql("SELECT COUNT(*) FROM bebidas")
	            .query(int.class)
	            .single();
	}
	
	public void saveAll(List<Bebida> bebidas) {
		bebidas.stream().forEach(this::create);
	}
}
