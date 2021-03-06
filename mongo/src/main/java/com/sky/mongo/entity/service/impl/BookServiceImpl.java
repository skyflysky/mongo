package com.sky.mongo.entity.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.sky.mongo.entity.Book;

@Service
public class BookServiceImpl
{
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 保存对象
	 *
	 * @param book
	 * @return
	 */
	public String saveObj(Book book)
	{
		book.setCreateTime(new Date());
		book.setUpdateTime(new Date());
		mongoTemplate.save(book);
		return "添加成功";
	}

	/**
	 * 查询所有
	 *
	 * @return
	 */
	public List<Book> findAll()
	{
		return mongoTemplate.findAll(Book.class);
	}

	/***
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Book getBookById(String id)
	{
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, Book.class);
	}

	/**
	 * 根据名称查询
	 *
	 * @param name
	 * @return
	 */
	public Book getBookByName(String name)
	{
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, Book.class);
	}

	/**
	 * 更新对象
	 *
	 * @param book
	 * @return
	 */
	public String updateBook(Book book)
	{
		Query query = new Query(Criteria.where("_id").is(book.getId()));
		Update update = new Update().set("publish", book.getPublish()).set("info", book.getInfo()).set("updateTime",new Date()).set("price",book.getPrice());
		// updateFirst 更新查询返回结果集的第一条
		mongoTemplate.updateFirst(query, update, Book.class);
		// updateMulti 更新查询返回结果集的全部
		// mongoTemplate.updateMulti(query,update,Book.class);
		// upsert 更新对象不存在则去添加
		// mongoTemplate.upsert(query,update,Book.class);
		return "success";
	}

	/***
	 * 删除对象
	 * 
	 * @param book
	 * @return
	 */
	public String deleteBook(Book book)
	{
		mongoTemplate.remove(book);
		return "success";
	}

	/**
	 * 根据id删除
	 *
	 * @param id
	 * @return
	 */
	public String deleteBookById(String id)
	{
		// findOne
		Book book = getBookById(id);
		// delete
		deleteBook(book);
		return "success";
	}
}
