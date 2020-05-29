package com.rental.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rental.entity.LongRentCollection;
/**
 * 收藏service
 * @author 张宝玲
 *
 */
@Service
public interface LongRentCollectionService {
	public int add(LongRentCollection longRentCollection);
	public int delete(Long id);
	public List<LongRentCollection> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
