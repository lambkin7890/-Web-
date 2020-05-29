package com.rental.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rental.entity.User;
import com.rental.page.Page;
import com.rental.service.UserService;

/**
 * 用户操作控制器
 * @author 张宝玲
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	

	
	/**
	 * 登录表单提交处理控制器
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public User loginAct(HttpServletRequest request){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=userService.findByUsername(username);
		if(user!=null){
			if(password.compareTo(user.getPassword())==0){
				return user;
			}
		}
		return user;
	}
	
	/**
	 * 添加用户
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Map<String, String> registerAct(User user){
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		if(user.getRoleId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属角色！");
			return ret;
		}
		if(isExist(user.getUsername(), 0l)){
			ret.put("type", "error");
			ret.put("msg", "该用户名已经存在，请重新输入！");
			return ret;
		}
		if(userService.add(user)<=0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
		
		
	}
	
	/**
	 * 删除用户
	 * @param floor
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> remove(Long id){
		Map<String, String> ret=new HashMap<String, String>();
		if(id==null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息！");
			return ret;
		}
		if(userService.delete(id)<=0){
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/update",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(User user){
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的个人信息！");
			return ret;
		}
		if(userService.edit(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "编辑失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
	}
	/**
	 * 修改密码
	 * @param newpassword
	 * @param oldpassword
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit_password",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editPasswordAct(String newpassword, String oldpassword, HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		String username=request.getParameter("username");
		User user=userService.findByUsername(username);
		if(!user.getPassword().equals(oldpassword)){
			ret.put("type", "error");
			ret.put("msg", "原密码错误,如忘记密码可联系管理员找回密码！");
			return ret;
		}
		user.setPassword(newpassword);
		if(userService.editPassword(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "密码修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "密码修改成功！");
		return ret;
	} 
	
	/**
	 * 根据用户名查询用户
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/query",method= RequestMethod.GET)
	public User findByUsername(String username){
		User user=userService.findByUsername(username);
		if(user!=null){
			return user;
		}
		return null;
	}
	
	/**
	 * 根据id查询用户
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/find_by_id",method= RequestMethod.GET)
	public User findById(@RequestParam(name="id",defaultValue="") Long id){
		User user=userService.findById(id);
		return user;
	}
	
	/**
	 * 分页查询用户信息
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(name="username",required=false,defaultValue="") String username,
			@RequestParam(name="roleId",required=false) Long roleId,
			Page page
			){
		Map<String, Object> ret=new HashMap<String,Object>();
		Map<String, Object> queryMap=new HashMap<String,Object>();
		queryMap.put("username", username);
		queryMap.put("roleId", roleId);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getPageSize());
		ret.put("pageSize", userService.findList(queryMap));
		ret.put("totalCount", userService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 判断该用户名是否存在
	 * @param username
	 * @param id
	 * @return
	 */
	private boolean isExist(String username,Long id){
		User user = userService.findByUsername(username);
		if(user == null){
			return false;    
		}
		if(user.getId().longValue() == id.longValue()){
			return false;  
		}
		return true;
	}
	
}
