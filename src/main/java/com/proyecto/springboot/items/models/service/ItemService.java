package com.proyecto.springboot.items.models.service;

import java.util.List;

import com.proyecto.springboot.items.models.Item;

public interface ItemService {
	//Hay 2 implementaciones de esta interfaz ItemServiceImpl, ItemServiceFeignImpl
	//La que sea por de fecto le ponemos @Primary
public List <Item> findAll();
public Item findById(Long id, Integer cantidad);
}
