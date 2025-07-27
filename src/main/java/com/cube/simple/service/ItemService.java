package com.cube.simple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cube.simple.entity.Item;
import com.cube.simple.repository.read.ReadItemRepository;
import com.cube.simple.repository.write.WriteItemRepository;

@Service
public class ItemService {

	@Autowired
    private ReadItemRepository readItemRepository;

	@Autowired
    private WriteItemRepository writeItemRepository;

    public long count() {
        return readItemRepository.count();
    }

    public Iterable<Item> findAll() {
        return readItemRepository.findAll();
    }

    public Optional<Item> findById(Long id) {
        return readItemRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return readItemRepository.existsById(id);
    }

    public Item save(Item item) {
        return writeItemRepository.save(item);
    }

    public void delete(Item item) {
        writeItemRepository.delete(item);
    }

    public void deleteById(Long id) {
        writeItemRepository.deleteById(id);
    }
}
