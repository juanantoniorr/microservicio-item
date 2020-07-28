package com.proyecto.springboot.items.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyecto.springboot.items.models.Producto;

@FeignClient (name="servicio-productos", url="localhost:8001")
public interface ProductoClienteRest {
	
	@GetMapping ("/api/productos/listar")
	public List <Producto> listar();
	
	@GetMapping ("/api/productos/listar/{id}")
	public Producto detalle (@PathVariable (value = "id") Long id);

}
