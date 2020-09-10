package com.proyecto.springboot.items.models.service;

import java.util.List;

import com.proyecto.springboot.items.models.Item;
import com.proyecto.springboot.items.models.Producto;

public interface ItemService {
	//Hay 2 implementaciones de esta interfaz ItemServiceImpl, ItemServiceFeignImpl
	//La que sea por de fecto le ponemos @Primary, si no en el controlador con
	//@Qualifier ponemos el nombre del servicio que queremos 
public List <Item> findAll();
public Item findById(Long id, Integer cantidad);
public Producto save(Producto producto);
public Producto update (Producto producto, Long id);
public void delete(Long id);
}
