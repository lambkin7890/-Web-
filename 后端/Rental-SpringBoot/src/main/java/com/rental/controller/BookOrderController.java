package com.rental.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.rental.entity.BookOrder;
import com.rental.page.Page;
import com.rental.service.BookOrderService;
import com.rental.util.AlipayUtil;

@Controller
@RequestMapping("/book_order")
public class BookOrderController {

	@Autowired
	private BookOrderService bookOrderService;
	
	
	/**
	 *	查询单个订单
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/detail",method= RequestMethod.GET)
	public BookOrder bookOrderDetail(@RequestParam(name="id",defaultValue="") Long id){
		BookOrder bookOrder=bookOrderService.findById(id);
		return bookOrder;
	}
	
	
	/**
	 * 分页查询订单列表
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(name="status",required=false) Integer status,
			@RequestParam(name="houseHolderId",required=false)Long houseHolderId,
			@RequestParam(name="userId",required=false)Long userId,
			Page page
			){
		Map<String, Object> ret=new HashMap<String,Object>();
		Map<String, Object> queryMap=new HashMap<String,Object>();
		queryMap.put("status", status);
		queryMap.put("houseHolderId", houseHolderId);
		queryMap.put("userId", userId);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getPageSize());
		ret.put("pageSize", bookOrderService.findList(queryMap));
		ret.put("totalCount", bookOrderService.getTotal(queryMap));
		return ret;
	}
	
	
	/**
	 * 预定订单信息添加操作
	 * @param bookOrder
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Map<String, String> add(BookOrder bookOrder){
		Map<String, String> ret=new HashMap<String, String>();
		if(bookOrder==null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的预定订单信息！");
			return ret;
		}
		if(bookOrder.getUserId()==null){
			ret.put("type", "error");
			ret.put("msg", "预订人不能为空！");
			return ret;
		}
		if(bookOrder.getHouseId()==null){
			ret.put("type", "error");
			ret.put("msg", "房间不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getMobile())){
			ret.put("type", "error");
			ret.put("msg", "联系人手机号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getCheckinDay())){
			ret.put("type", "error");
			ret.put("msg", "入住日期不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getCheckoutDay())){
			ret.put("type", "error");
			ret.put("msg", "退房日期不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getCheckinName())){
			ret.put("type", "error");
			ret.put("msg", "入住人姓名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getCheckinIdCard())){
			ret.put("type", "error");
			ret.put("msg", "入住人身份证号不能为空！");
			return ret;
		}
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateformat.format(date);
		bookOrder.setCreateTime(time);
		if(bookOrderService.add(bookOrder)<=0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "提交成功！");
		return ret;
	}
	

	/**
	 * 预定订单信息编辑操作
	 * @param floor
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(BookOrder bookOrder){
		Map<String, String> ret=new HashMap<String, String>();
		if(bookOrder==null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的预定订单信息！");
			return ret;
		}
		if(bookOrder.getUserId()==null){
			ret.put("type", "error");
			ret.put("msg", "预订人不能为空！");
			return ret;
		}
		if(bookOrder.getHouseId()==null){
			ret.put("type", "error");
			ret.put("msg", "预定房源不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getMobile())){
			ret.put("type", "error");
			ret.put("msg", "联系手机号不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getCheckinDay())){
			ret.put("type", "error");
			ret.put("msg", "入住日期不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(bookOrder.getCheckoutDay())){
			ret.put("type", "error");
			ret.put("msg", "退房日期不能为空！");
			return ret;
		}
		if(bookOrderService.edit(bookOrder)<=0){
			ret.put("type", "error");
			ret.put("msg", "编辑失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
	}
	
	/**
	 * 删除订单
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
		if(bookOrderService.delete(id)<=0){
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
	/**
	 * 在线支付订单
	 * @param floor
	 * @return
	 */
	@RequestMapping(value="/pay",method=RequestMethod.POST)
	public void toPay(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		String oid=request.getParameter("oid");
		String houseName=request.getParameter("houseName");
		Float totalPrice=Float.parseFloat(request.getParameter("totalPrice"));
		try {
			String s= AlipayUtil.generateAlipayTradePagePayRequestForm(oid, houseName, totalPrice);
			
			response.getWriter().print(s);

		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 支付成功后回调函数
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/pay_finished")
	@ResponseBody
	public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //首先先去验签名
        boolean check = AlipayUtil.check(request.getParameterMap());
        if (check){
            //获取哪个订单支付完成了
            String osn = request.getParameter("out_trade_no");

            //把这个订单的状态改了
            BookOrder bookOrder = bookOrderService.findBySn(osn);
            bookOrder.setStatus(1);//1代表已支付
            bookOrderService.edit(bookOrder);
            //更新完成


            //还让我们用户看看到他的订单 已经支付完成状态
            response.sendRedirect("http://localhost:8080/#/book_success?oid="+bookOrder.getId());

        }else{

            response.getWriter().print("验签失败!");
        }




    }
}
