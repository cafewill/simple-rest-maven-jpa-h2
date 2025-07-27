package com.cube.simple.repository.read;

import org.springframework.data.repository.CrudRepository;

import com.cube.simple.entity.Item;

public interface ReadItemRepository extends CrudRepository <Item, Long> {
}
