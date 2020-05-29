package com.rental.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rental.entity.BookOrder;

/**
 * 预定订单service
 * @author 张宝玲
 *
 */
@Service
public interface BookOrderService {
	public int add(BookOrder bookOrder);
	public int edit(BookOrder bookOrder);
	public int delete(Long id);
	public List<BookOrder> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public BookOrder findById(Long id);
	public BookOrder findBySn(String sn);
}
