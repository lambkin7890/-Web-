package com.rental.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rental.dao.HouseDao;
import com.rental.entity.House;
import com.rental.service.HouseService;
/**
 * 房屋实现类
 * @author 张宝玲
 *
 */
@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao houseDao;

	@Override
	public House findById(Long id) {
		// TODO Auto-generated method stub
		return houseDao.findById(id);
	}

	@Override
	public List<House> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return houseDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return houseDao.getTotal(queryMap);
	}

	@Override
	public int add(House house) {
		// TODO Auto-generated method stub
		return houseDao.add(house);
	}

	@Override
	public int edit(House house) {
		// TODO Auto-generated method stub
		return houseDao.edit(house);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return houseDao.delete(id);
	}
	
	

}
