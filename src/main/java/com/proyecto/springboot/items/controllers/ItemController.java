package com.proyecto.springboot.items.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.proyecto.springboot.items.models.Item;
import com.proyecto.springboot.items.models.Producto;
import com.proyecto.springboot.items.models.service.ItemService;
@RefreshScope
@RestController
public class ItemController {
	//Inyectando este bean puedes llamar propiedades y perfiles activos
	@Autowired
	private Environment env;
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	
	@GetMapping("/listar")
	public List <Item> listar(){
		
		return itemService.findAll();
		
	}
	
	@GetMapping("obtener-config")
	public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String puerto, @Value("${ambiente.info}") String texto) {
		Map<String,String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		json.put("texto", texto);
		if (env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("Autor", env.getProperty("autor.nombre"));
		}
		return new ResponseEntity<Map<String,String>> (json,HttpStatus.OK);
	}
	//Con este comando si hay un fallo llamara al otro metodo
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle (@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Nombre por defecto");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return item;
	}

}
