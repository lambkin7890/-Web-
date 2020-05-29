package com.rental.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rental.entity.LongRentHouse;
/**
 * 房屋service
 * @author 张宝玲
 *
 */
@Service
public interface LongRentHouseService {
	public int add(LongRentHouse longRentHouse);
	public int edit(LongRentHouse longRentHouse);
	public int delete(Long id);
	public LongRentHouse findById(Long id);
	public List<LongRentHouse> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
