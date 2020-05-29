package com.rental.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rental.dao.HouseCollectionDao;
import com.rental.entity.HouseCollection;
import com.rental.service.HouseCollectionService;
/**
 * 用户实现类
 * @author 张宝玲
 *
 */
@Service
public class HouseCollectionServiceImpl implements HouseCollectionService {
	
	@Autowired
	private HouseCollectionDao collectionDao;

	@Override
	public int add(HouseCollection collection) {
		// TODO Auto-generated method stub
		return collectionDao.add(collection);
	}

	@Override
	public List<HouseCollection> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return collectionDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return collectionDao.getTotal(queryMap);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return collectionDao.delete(id);
	}

	

}
