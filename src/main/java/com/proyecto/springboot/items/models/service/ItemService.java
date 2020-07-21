package com.proyecto.springboot.items.models.service;

import java.util.List;

import com.proyecto.springboot.items.models.Item;

public interface ItemService {
public List <Item> findAll();
public Item findById(Long id, Integer cantidad);
}
