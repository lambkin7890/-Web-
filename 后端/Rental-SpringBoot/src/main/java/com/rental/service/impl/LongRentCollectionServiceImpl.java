package com.rental.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rental.dao.LongRentCollectionDao;
import com.rental.entity.LongRentCollection;
import com.rental.service.LongRentCollectionService;
/**
 * 用户实现类
 * @author 张宝玲
 *
 */
@Service
public class LongRentCollectionServiceImpl implements LongRentCollectionService {
	
	@Autowired
	private LongRentCollectionDao longRentCollectionDao;

	@Override
	public int add(LongRentCollection longRentCollection) {
		// TODO Auto-generated method stub
		return longRentCollectionDao.add(longRentCollection);
	}

	

	@Override
	public List<LongRentCollection> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return longRentCollectionDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return longRentCollectionDao.getTotal(queryMap);
	}



	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return longRentCollectionDao.delete(id);
	}

	
	

}
