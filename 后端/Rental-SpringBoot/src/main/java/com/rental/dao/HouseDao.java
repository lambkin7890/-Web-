package com.rental.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rental.entity.House;

/**
 * 房屋dao
 * @author 张宝玲
 *
 */

@Repository
public interface HouseDao {
	public int add(House house);
	public int edit(House house);
	public int delete(Long id);
	public House findById(Long id);
	public List<House> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
