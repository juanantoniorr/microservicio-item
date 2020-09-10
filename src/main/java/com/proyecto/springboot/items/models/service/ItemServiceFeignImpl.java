package com.proyecto.springboot.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.proyecto.springboot.items.clientes.ProductoClienteRest;
import com.proyecto.springboot.items.models.Item;
import com.proyecto.springboot.items.models.Producto;

@Service ("serviceFeign")
public class ItemServiceFeignImpl implements ItemService {
	private static final Logger log = LoggerFactory.getLogger(ItemServiceFeignImpl.class);
	@Autowired
	private ProductoClienteRest clienteRest;

	@Override
	public List<Item> findAll() {
		log.info("Usando API Feign");
		//regresa una lista de productos por eso lo convierto a item
		return clienteRest.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		return new Item(clienteRest.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	

}
