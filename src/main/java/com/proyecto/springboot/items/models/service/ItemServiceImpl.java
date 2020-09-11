package com.proyecto.springboot.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/api/productos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map <String,String> pathVariable = new HashMap<String,String>();
		pathVariable.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://servicio-productos/api/productos/listar/{id}", Producto.class, pathVariable);
		return new Item(producto, cantidad);
	}

	//Metodo para guardar producto mediante API rest
	@Override
	public Producto save(Producto producto) {
		HttpHeaders headers = new HttpHeaders();
		//Se agrega header porque da mediatypeerror sin eso
		headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<Producto> body = new HttpEntity<Producto>(producto,headers);
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/api/productos/crear", HttpMethod.POST,body,Producto.class);
		return response.getBody();
	}

	@Override
	public Producto update(Producto producto, Long id) {
		HttpHeaders headers = new HttpHeaders();
		//Se agrega header porque da mediatypeerror sin eso
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto,headers);
		Map <String,String> pathVariable = new HashMap<String,String>();
		pathVariable.put("id", id.toString());
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/api/productos/editar/{id}", HttpMethod.PUT,body,Producto.class, pathVariable);
		
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map <String,String> pathVariable = new HashMap<String,String>();
		pathVariable.put("id", id.toString());
		clienteRest.delete("http://servicio-productos/api/productos/eliminar/{id}", pathVariable);
		
	}

}
