package com.rental.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rental.entity.House;
/**
 * 房屋service
 * @author 张宝玲
 *
 */
@Service
public interface HouseService {
	public int add(House house);
	public int edit(House house);
	public int delete(Long id);
	public House findById(Long id);
	public List<House> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
