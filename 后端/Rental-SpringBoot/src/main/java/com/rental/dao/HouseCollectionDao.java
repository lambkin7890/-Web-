package com.rental.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rental.entity.HouseCollection;
/**
 * 收藏dao
 * @author 张宝玲
 *
 */
@Repository
public interface HouseCollectionDao {
	public int add(HouseCollection collection);
	public int delete(Long id);
	public List<HouseCollection> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
