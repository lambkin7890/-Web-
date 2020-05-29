package com.rental.entity;


import org.springframework.stereotype.Component;

/**
 * 预定订单实体类
 * @author 张宝玲
 *
 */
@Component
public class BookOrder {
	private Long id;  //预定订单id
	private String sn;  //订单编号
	private Long houseId;  //所属房屋id
	private Long houseHolderId;//房东id
	private String checkinDay;//入住日期
	private String checkoutDay;//离店日期
	private Long userId;//下单人
	private String mobile;//联系人手机号
	private Integer checkinNum;//入住人数
	private String checkinName;//其中一位入住人姓名
	private String checkinIdCard;//入住人身份证号
	private Integer nightCount;//预定天数
	private float totalPrice;//订单总额
	private int status;//状态：0：待付款，1；已支付
	private String createTime;//下单时间
	
	
	private String tenantName;//预订人用户名
	private String name;//房源名称
	private String photo;//房源图片
	private String price;//房源价格
	private String address;//房源地址
	
	
	
	public Integer getNightCount() {
		return nightCount;
	}
	public void setNightCount(Integer nightCount) {
		this.nightCount = nightCount;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getHouseHolderId() {
		return houseHolderId;
	}
	public void setHouseHolderId(Long houseHolderId) {
		this.houseHolderId = houseHolderId;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCheckinDay() {
		return checkinDay;
	}
	public void setCheckinDay(String checkinDay) {
		this.checkinDay = checkinDay;
	}
	public String getCheckoutDay() {
		return checkoutDay;
	}
	public void setCheckoutDay(String checkoutDay) {
		this.checkoutDay = checkoutDay;
	}
	public Integer getCheckinNum() {
		return checkinNum;
	}
	public void setCheckinNum(Integer checkinNum) {
		this.checkinNum = checkinNum;
	}
	public String getCheckinName() {
		return checkinName;
	}
	public void setCheckinName(String checkinName) {
		this.checkinName = checkinName;
	}
	public String getCheckinIdCard() {
		return checkinIdCard;
	}
	public void setCheckinIdCard(String checkinIdCard) {
		this.checkinIdCard = checkinIdCard;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	
}
