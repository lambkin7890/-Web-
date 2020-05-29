package com.rental.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rental.entity.House;
import com.rental.page.Page;
import com.rental.service.HouseService;

@Controller
@RequestMapping("/house")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	

	/**
	 * 根据id查询房间信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/detail",method= RequestMethod.GET)
	public House houseDetail(@RequestParam(name="id",defaultValue="") Long id){
		House house=houseService.findById(id);
		return house;
	}
	
	/**
	 * 分页查询房源信息
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(name="name",defaultValue="") String name,
			@RequestParam(name="address",defaultValue="") String address,
			@RequestParam(name="landlordId",defaultValue="")Long landlordId,
			Page page
			){
		Map<String, Object> ret=new HashMap<String,Object>();
		Map<String, Object> queryMap=new HashMap<String,Object>();
		queryMap.put("name", name);
		queryMap.put("address", address);
		queryMap.put("landlordId", landlordId);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getPageSize());
		ret.put("pageSize", houseService.findList(queryMap));
		ret.put("totalCount", houseService.getTotal(queryMap));
		return ret;
	}
	
	
	/**
	 * 上传房源
	 * @param photo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(House house){
		Map<String, String> ret = new HashMap<String, String>();
		if(house == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的房源信息！");
			return ret;
		}
		if(StringUtils.isEmpty(house.getName())){
			ret.put("type", "error");
			ret.put("msg", "房源名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(house.getPhoto())){
			ret.put("type", "error");
			ret.put("msg", "房源图片不能为空！");
			return ret;
		}
		if(houseService.add(house)<=0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	
	
	/**
	 * 编辑房源信息
	 * @param floor
	 * @return
	 */
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(House house){
		Map<String, String> ret=new HashMap<String, String>();
		if(house == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的房源信息！");
			return ret;
		}
		if(StringUtils.isEmpty(house.getName())){
			ret.put("type", "error");
			ret.put("msg", "房源名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(house.getPhoto())){
			ret.put("type", "error");
			ret.put("msg", "房源图片不能为空！");
			return ret;
		}
		if(houseService.edit(house)<=0){
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
	}
	
	/**
	 * 删除房源
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
		if(houseService.delete(id)<=0){
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
	
}
