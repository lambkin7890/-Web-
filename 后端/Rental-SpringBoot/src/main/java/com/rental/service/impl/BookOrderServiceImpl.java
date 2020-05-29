package com.rental.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rental.dao.BookOrderDao;
import com.rental.entity.BookOrder;
import com.rental.service.BookOrderService;
/**
 * 预定订单实现类
 * @author 张宝玲
 *
 */
@Service
public class BookOrderServiceImpl implements BookOrderService {
	
	@Autowired
	private BookOrderDao bookOrderDao;
	
	@Override
	public int add(BookOrder bookOrder) {
		// TODO Auto-generated method stub
		return bookOrderDao.add(bookOrder);
	}

	@Override
	public int edit(BookOrder bookOrder) {
		// TODO Auto-generated method stub
		return bookOrderDao.edit(bookOrder);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return bookOrderDao.delete(id);
	}

	@Override
	public List<BookOrder> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return bookOrderDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return bookOrderDao.getTotal(queryMap);
	}

	@Override
	public BookOrder findById(Long id) {
		// TODO Auto-generated method stub
		return bookOrderDao.findById(id);
	}

	@Override
	public BookOrder findBySn(String sn) {
		// TODO Auto-generated method stub
		return bookOrderDao.findBySn(sn);
	}

}
