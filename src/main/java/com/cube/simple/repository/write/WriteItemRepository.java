package com.cube.simple.repository.write;

import org.springframework.data.repository.CrudRepository;

import com.cube.simple.entity.Item;

public interface WriteItemRepository extends CrudRepository <Item, Long> {
}
