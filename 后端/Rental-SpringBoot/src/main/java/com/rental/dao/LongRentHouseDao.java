package com.rental.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rental.entity.LongRentHouse;

/**
 * 房屋dao
 * @author 张宝玲
 *
 */

@Repository
public interface LongRentHouseDao {
	public int add(LongRentHouse longRentHouse);
	public int edit(LongRentHouse longRentHouse);
	public int delete(Long id);
	public LongRentHouse findById(Long id);
	public List<LongRentHouse> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
