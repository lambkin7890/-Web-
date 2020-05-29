package com.rental.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rental.entity.HouseCollection;
import com.rental.page.Page;
import com.rental.service.HouseCollectionService;

/**
 * 收藏管理控制器
 * @author 张宝玲
 *
 */
@Controller
@RequestMapping("/house_collection")
public class HouseCollectionController {
	
	
	@Autowired
	private HouseCollectionService houseCollectionService;
	

	
	/**
	 * 分页查询收藏信息
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(name="userId",required=false,defaultValue="") Long userId,
			@RequestParam(name="houseId",required=false,defaultValue="") Long houseId,
			Page page
			){
		Map<String, Object> ret=new HashMap<String,Object>();
		Map<String, Object> queryMap=new HashMap<String,Object>();
		queryMap.put("userId", userId);
		queryMap.put("houseId", houseId);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getPageSize());
		ret.put("pageSize", houseCollectionService.findList(queryMap));
		ret.put("totalCount", houseCollectionService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加收藏
	 * @param bookOrder
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Map<String, String> add(HouseCollection houseCollection){
		Map<String, String> ret=new HashMap<String, String>();
		if(houseCollection.getUserId()==null){
			ret.put("type", "error");
			ret.put("msg", "用户不能为空！");
			return ret;
		}
		if(houseCollection.getHouseId()==null){
			ret.put("type", "error");
			ret.put("msg", "房源不能为空！");
			return ret;
		}
		if(houseCollectionService.add(houseCollection)<=0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	/**
	 * 删除收藏
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
		if(houseCollectionService.delete(id)<=0){
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
}
