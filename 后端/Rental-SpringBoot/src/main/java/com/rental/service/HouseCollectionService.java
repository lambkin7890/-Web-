package com.rental.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rental.entity.HouseCollection;
/**
 * 收藏service
 * @author 张宝玲
 *
 */
@Service
public interface HouseCollectionService {
	public int add(HouseCollection collection);
	public int delete(Long id);
	public List<HouseCollection> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
