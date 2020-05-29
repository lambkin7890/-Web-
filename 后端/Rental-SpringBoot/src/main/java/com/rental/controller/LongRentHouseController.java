package com.rental.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rental.entity.LongRentHouse;
import com.rental.page.Page;
import com.rental.service.LongRentHouseService;
@Controller
@RequestMapping("/long_rent_house")
public class LongRentHouseController {
	
	@Autowired
	private LongRentHouseService longRentHouseService;
	

	/**
	 * 根据id查询房间信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/detail",method= RequestMethod.GET)
	public LongRentHouse houseDetail(@RequestParam(name="id",defaultValue="") Long id){
		LongRentHouse longRentHouse=longRentHouseService.findById(id);
		return longRentHouse;
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
		ret.put("pageSize", longRentHouseService.findList(queryMap));
		ret.put("totalCount", longRentHouseService.getTotal(queryMap));
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
	public Map<String, String> add(LongRentHouse longRentHouse){
		Map<String, String> ret = new HashMap<String, String>();
		if(longRentHouse==null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的房源信息！");
			return ret;
		}
		if(StringUtils.isEmpty(longRentHouse.getName())){
			ret.put("type", "error");
			ret.put("msg", "房源名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(longRentHouse.getPhoto())){
			ret.put("type", "error");
			ret.put("msg", "房源图片不能为空！");
			return ret;
		}
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String createTime = dateformat.format(date);
		longRentHouse.setCreateTime(createTime);
		if(longRentHouseService.add(longRentHouse)<=0){
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
	public Map<String, String> edit(LongRentHouse longRentHouse){
		Map<String, String> ret=new HashMap<String, String>();
		if(longRentHouse==null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的房型信息！");
			return ret;
		}
		if(StringUtils.isEmpty(longRentHouse.getName())){
			ret.put("type", "error");
			ret.put("msg", "房源名称不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(longRentHouse.getPhoto())){
			ret.put("type", "error");
			ret.put("msg", "房源图片不能为空！");
			return ret;
		}
		if(longRentHouseService.edit(longRentHouse)<=0){
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
		if(longRentHouseService.delete(id)<=0){
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
	
}
