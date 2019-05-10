package com.sky.mongo.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sky.mongo.entity.Book;
import com.sky.mongo.entity.service.impl.BookServiceImpl;

@RestController
public class BookController
{
	@Autowired
	private BookServiceImpl mongoDbService;

	@PostMapping("/mongo/save")
	public String saveObj(@RequestBody Book book)
	{
		return mongoDbService.saveObj(book);
	}

	@GetMapping("/mongo/findAll")
	public List<Book> findAll()
	{
		return mongoDbService.findAll();
	}

	@GetMapping("/mongo/findOne")
	public Book findOne(@RequestParam String id)
	{
		return mongoDbService.getBookById(id);
	}

	@GetMapping("/mongo/findOneByName")
	public Book findOneByName(@RequestParam String name)
	{
		return mongoDbService.getBookByName(name);
	}

	@PostMapping("/mongo/update")
	public String update(@RequestBody Book book)
	{
		return mongoDbService.updateBook(book);
	}

	@PostMapping("/mongo/delOne")
	public String delOne(@RequestBody Book book)
	{
		return mongoDbService.deleteBook(book);
	}

	@GetMapping("/mongo/delById")
	public String delById(@RequestParam String id)
	{
		return mongoDbService.deleteBookById(id);
	}
}
