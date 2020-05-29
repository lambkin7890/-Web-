package com.rental.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rental.dao.LongRentHouseDao;
import com.rental.entity.LongRentHouse;
import com.rental.service.LongRentHouseService;
/**
 * 房屋实现类
 * @author 张宝玲
 *
 */
@Service
public class LongRentHouseServiceImpl implements LongRentHouseService {

	@Autowired
	private LongRentHouseDao longRentHouseDao;


	@Override
	public LongRentHouse findById(Long id) {
		// TODO Auto-generated method stub
		return longRentHouseDao.findById(id);
	}

	@Override
	public List<LongRentHouse> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return longRentHouseDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return longRentHouseDao.getTotal(queryMap);
	}

	@Override
	public int add(LongRentHouse longRentHouse) {
		// TODO Auto-generated method stub
		return longRentHouseDao.add(longRentHouse);
	}

	@Override
	public int edit(LongRentHouse longRentHouse) {
		// TODO Auto-generated method stub
		return longRentHouseDao.edit(longRentHouse);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return longRentHouseDao.delete(id);
	}
	
	

}
