package com.cube.simple.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cube.simple.common.ItemRequest;
import com.cube.simple.common.ItemResponse;
import com.cube.simple.entity.Item;
import com.cube.simple.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping ("/api/items")
	public ItemResponse insert (@RequestBody (required = true) ItemRequest request) {
		ItemResponse response = ItemResponse.builder ().build ();
		boolean status = true;
		String message = "";
		
		try {
			if (!Objects.isNull (request) && !Objects.isNull (request.getData ())) {
				ObjectMapper objectMapper = new ObjectMapper ();
				Item candidate = objectMapper.readValue (objectMapper.writeValueAsString (request.getData ()), Item.class);
				itemService.save (candidate);
				response.setData (candidate);
				log.info ("Insert success : {}", candidate);
				message = String.format ("Insert success : %s", candidate);
			} else {
				status = false;
				log.info ("Insert error : {}", request);
				message = String.format ("Insert error : %s", request);
			}
		} catch (Exception e) {
			status = false;
			log.info ("Insert error : {}", e.getMessage ());
			message = String.format ("Insert error : %s", e.getMessage ());
		}
		
		log.info (message);
		response.setStatus (status);
		response.setMessage (message);
		
		return response;
	}

	@GetMapping ("/api/items")
	public ItemResponse select (@RequestParam (required = false) Long id) {
		ItemResponse response = ItemResponse.builder ().build ();
		boolean status = true;
		String message = "";
		
		try {
			if (!Objects.isNull (id)) {
				Optional <Item> optional = itemService.findById (id);
				if (optional.isPresent ()) {
					Item item = optional.get ();
					response.setData (item);
					log.info ("Select item : {}", item);
					message = String.format ("Select success : %s", item);
				} else {
					status = false;
					log.info ("Select error : {}", optional);
					message = String.format ("Select error : %s", optional);
				}
			} else {
				int count = 0;
				List <Item> items = (List <Item>) itemService.findAll ();
				if (!Objects.isNull (items)) {
					response.setData (items);
					for (Item item : items) {
						count++;
						log.info ("Select item #{} : {}", count, item);
					}
					log.info ("Select success : {}", items);
					message = String.format ("Select success : %d", count);
				} else {
					status = false;
					log.info ("Select error #{} : {}", count, items);
					message = String.format ("Select error : %s", items);
				}
			}
		} catch (Exception e) {
			status = false;
			log.info ("Select error : {}", e.getMessage ());
			message = String.format ("Select error : %s", e.getMessage ());
		}
		
		log.info (message);
		response.setStatus (status);
		response.setMessage (message);
		
		return response;
	}

	@PutMapping ("/api/items")
	public ItemResponse update (@RequestBody (required = true) ItemRequest request) {
		ItemResponse response = ItemResponse.builder ().build ();
		boolean status = true;
		String message = "";

		try {
			if (!Objects.isNull (request) && !Objects.isNull (request.getData ())) {
				ObjectMapper objectMapper = new ObjectMapper ();
				Item candidate = objectMapper.readValue (objectMapper.writeValueAsString (request.getData ()), Item.class);
				itemService.save (candidate);
				Optional <Item> optional = itemService.findById (candidate.getId ());
				if (optional.isPresent ()) {
					Item item = optional.get ();
					response.setData (item);
					log.info ("Update item : {}", item);
					message = String.format ("Update success : %s", item);
				} else {
					status = false;
					log.info ("Update error : {}", optional);
					message = String.format ("Update error : %s", optional);
				}
			} else {
				status = false;
				log.info ("Update error : {}", request);
				message = String.format ("Update error : %s", request);
			}
		} catch (Exception e) {
			status = false;
			log.info ("Update error : {}", e.getMessage ());
			message = String.format ("Update error : %s", e.getMessage ());
		}
		
		log.info (message);
		response.setStatus (status);
		response.setMessage (message);
		
		return response;
	}

	@DeleteMapping ("/api/items")
	public ItemResponse delete (@RequestBody (required = true) ItemRequest request) {
		ItemResponse response = ItemResponse.builder ().build ();
		boolean status = true;
		String message = "";

		try {
			if (!Objects.isNull (request) && !Objects.isNull (request.getData ())) {
				ObjectMapper objectMapper = new ObjectMapper ();
				Item candidate = objectMapper.readValue (objectMapper.writeValueAsString (request.getData ()), Item.class);
				itemService.delete (candidate);
				response.setData (candidate);
				log.info ("Delete item : {}", candidate);
				message = String.format ("Delete success : %s", candidate);
			} else {
				status = false;
				log.info ("Delete error : {}", request);
				message = String.format ("Delete error : %s", request);
			}
		} catch (Exception e) {
			status = false;
			log.info ("Delete error : {}", e.getMessage ());
			message = String.format ("Delete error : %s", e.getMessage ());
		}
		
		log.info (message);
		response.setStatus (status);
		response.setMessage (message);
		
		return response;
	}
}
