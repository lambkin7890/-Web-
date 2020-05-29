package com.rental.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rental.entity.User;
/**
 * 用户service
 * @author 张宝玲
 *
 */
@Service
public interface UserService {
	public User findByUsername(String username);
	public User findById(Long id);
	public int add(User user);
	public int edit(User user);
	public int editPassword(User user);
	public int delete(Long id);
	public List<User> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
}
