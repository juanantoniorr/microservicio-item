package com.proyecto.springboot.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.springboot.items.models.Item;
import com.proyecto.springboot.items.models.Producto;

@Service ("serviceRest")
public class ItemServiceImpl implements ItemService {
	//Para poder usarlo lo registre como bean de spring en la clase config
	@Autowired
	RestTemplate clienteRest;
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Override
	public List<Item> findAll() {
		log.info("Usando RestTemplate");
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/api/productos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map <String,String> pathVariable = new HashMap<String,String>();
		pathVariable.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://localhost:8001/api/productos/listar/{id}", Producto.class, pathVariable);
		return new Item(producto, cantidad);
	}

}
