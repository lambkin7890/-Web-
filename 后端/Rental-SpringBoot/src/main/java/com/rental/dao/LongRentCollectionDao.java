package com.rental.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rental.entity.LongRentCollection;
/**
 * 收藏dao
 * @author 张宝玲
 *
 */
@Repository
public interface LongRentCollectionDao {
	public int add(LongRentCollection longRentCollection);
	public int delete(Long id);
	public List<LongRentCollection> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
