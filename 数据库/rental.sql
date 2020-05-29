/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : rental

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2020-05-11 22:04:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book_order
-- ----------------------------
DROP TABLE IF EXISTS `book_order`;
CREATE TABLE `book_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(128) DEFAULT NULL,
  `checkinDay` varchar(32) DEFAULT NULL,
  `checkoutDay` varchar(32) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `mobile` varchar(32) DEFAULT NULL,
  `checkinNum` int(2) DEFAULT NULL,
  `checkinName` varchar(16) DEFAULT NULL,
  `checkinIdCard` varchar(32) DEFAULT NULL,
  `houseId` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `createTime` datetime(4) DEFAULT NULL,
  `houseHolderId` int(11) NOT NULL DEFAULT '0',
  `nightCount` int(5) DEFAULT NULL,
  `totalPrice` float(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_order_ibfk_1` (`userId`),
  KEY `book_order_ibfk_2` (`houseId`),
  CONSTRAINT `book_order_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `book_order_ibfk_2` FOREIGN KEY (`houseId`) REFERENCES `house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_order
-- ----------------------------
INSERT INTO `book_order` VALUES ('45', '1587202561154', '2020-05-11', '2020-06-17', '14', '638721683', '1', '哈哈哈', '1111111111111', '7', '1', '2020-04-18 17:36:01.0000', '1', '36', '31680');
INSERT INTO `book_order` VALUES ('46', '1587202681747', '2020-05-01', '2020-05-02', '14', '13213113', '1', '萨达', '1111111111111111', '7', '0', '2020-04-18 17:38:01.0000', '1', '1', '880');
INSERT INTO `book_order` VALUES ('47', '1587203791826', '2020-4-18', '2020-4-19', '14', '1111111111111', '1', '省事多撒', '1111111111111111', '1', '0', '2020-04-18 17:56:31.0000', '1', '1', '272');
INSERT INTO `book_order` VALUES ('48', '1587301151989', '2020-05-01', '2020-05-02', '15', '23214134254', '1', '张三', '22222222', '6', '0', '2020-04-19 20:59:12.0000', '1', '1', '1380');
INSERT INTO `book_order` VALUES ('50', '1587306017276', '2020-4-19', '2020-4-20', '14', '1212222222222222', '1', '请问', '1111111111111', '4', '1', '2020-04-19 22:20:17.0000', '1', '1', '580');
INSERT INTO `book_order` VALUES ('52', '1587361959424', '2020-4-20', '2020-4-21', '14', '22222211111', '1', '呜呜', '1111111111111111', '16', '1', '2020-04-20 13:52:39.0000', '1', '1', '12');
INSERT INTO `book_order` VALUES ('53', '1587362362880', '2020-4-20', '2020-4-21', '14', '1111111111', '1', '阿达', '11111111111', '16', '1', '2020-04-20 13:59:23.0000', '0', '1', '12');
INSERT INTO `book_order` VALUES ('54', '1587372568548', '2020-4-20', '2020-4-21', '14', '11111111111', '1', 'sadasfa', '11111', '13', '1', '2020-04-20 16:49:28.0000', '0', '1', '12');
INSERT INTO `book_order` VALUES ('55', '1587885903895', '2020-4-26', '2020-4-27', '14', '23213143', '1', '额鹅鹅鹅', '2222222222', '2', '0', '2020-04-26 15:25:03.0000', '0', '1', '350');
INSERT INTO `book_order` VALUES ('56', '1587977438269', '2020-04-27', '2020-04-28', '14', '2132143242', '1', '张三', '123231423', '16', '1', '2020-04-27 16:50:38.0000', '0', '1', '12');
INSERT INTO `book_order` VALUES ('57', '1587984583273', '2020-4-27', '2020-4-28', '14', '2313143242', '1', '张三', '1213333333', '10', '0', '2020-04-27 18:49:43.0000', '0', '1', '123');
INSERT INTO `book_order` VALUES ('58', '1587984645112', '2020-4-27', '2020-4-28', '14', '2222222', '1', '问问', '22222222222222', '10', '0', '2020-04-27 18:50:45.0000', '0', '1', '123');
INSERT INTO `book_order` VALUES ('59', '1587984800384', '2020-05-01', '2020-05-02', '14', '33333333333', '1', '发给他', '3333333333333', '12', '1', '2020-04-27 18:53:20.0000', '1', '1', '123');
INSERT INTO `book_order` VALUES ('60', '1588137490076', '2020-4-29', '2020-4-30', '14', '46546521312', '1', '张三', '3253241321', '7', '1', '2020-04-29 13:18:10.0000', '1', '1', '12');
INSERT INTO `book_order` VALUES ('61', '1588154510165', '2020-4-29', '2020-4-30', '14', '1111111111', '1', 'dafa', '11111111111111', '6', '0', '2020-04-29 18:01:50.0000', '1', '1', '880');
INSERT INTO `book_order` VALUES ('62', '1588428224292', '2020-5-2', '2020-5-3', '14', '11111111', '1', '大萨达', '111111111111', '4', '1', '2020-05-02 22:03:44.0000', '1', '1', '580');
INSERT INTO `book_order` VALUES ('63', '1588500386186', '2020-05-03', '2020-05-04', '14', '33333333333', '1', 'vdha', '7829372128648732', '7', '1', '2020-05-03 18:06:26.0000', '1', '1', '880');
INSERT INTO `book_order` VALUES ('64', '1588657678308', '2020-5-5', '2020-5-6', '14', '18262038738', '1', '张三', '4103782018927398', '4', '0', '2020-05-05 13:47:58.0000', '1', '1', '580');
INSERT INTO `book_order` VALUES ('65', '1588657927741', '2020-5-5', '2020-5-6', '14', '15567321873', '1', '张三', '2128109382901943', '30', '1', '2020-05-05 13:52:07.0000', '1', '1', '143');
INSERT INTO `book_order` VALUES ('66', '1588658445877', '2020-5-5', '2020-5-6', '14', '12132422222', '1', 'eew', '2222222222222222', '30', '0', '2020-05-05 14:00:45.0000', '1', '1', '143');
INSERT INTO `book_order` VALUES ('68', '1589181688155', '2020-05-11', '2020-05-12', '14', '23234344444', '1', 'ewew', 'ww22222222222222', '30', '1', '2020-05-11 15:21:28.0000', '1', '1', '143');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(64) DEFAULT NULL,
  `landlordId` int(11) DEFAULT NULL,
  `photo` varchar(256) DEFAULT NULL,
  `price` float(8,2) DEFAULT NULL,
  `name` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `liveNum` int(2) DEFAULT '2',
  `introduce` varchar(1024) DEFAULT NULL,
  `toiletNum` int(2) DEFAULT '1',
  `bedRoomNum` int(2) DEFAULT '1',
  `isRealPhoto` int(1) DEFAULT '1',
  `isCooking` int(1) DEFAULT '1',
  `isParkFree` int(1) DEFAULT '1',
  `isProject` int(1) DEFAULT '1',
  `sittingRoomNum` int(2) DEFAULT '1',
  `bedNum` int(2) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `landlordId` (`landlordId`),
  CONSTRAINT `house_ibfk_1` FOREIGN KEY (`landlordId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('1', null, '1', '/upload/file/20200504215139201911021928514585_700_467.jpg', '588.00', '三里屯大使馆工体东直门高端日式家庭房', '北京市首开·铂郡北区', '2', '房间整体为日式装修，全铺榻榻米垫，樱花树盘满整墙，浓浓的日式气息', '1', '1', '1', '1', '1', '1', '2', '1');
INSERT INTO `house` VALUES ('2', null, '1', '/upload/file/2020050510114420180829113444306_700_467.jpg', '350.00', '沐言阁·北京世园会 （75温馨整租房)', '背景三里屯', '2', '彼爱客栈位于厦门市最繁华的中山路上，位置超级好！！！ 是各大电影取景地。而且去步行到轮渡码头只需要8分钟，去鼓浪屿很方便哦！', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `house` VALUES ('4', null, '1', '/upload/file/2020050510121220180829113450727_700_467.jpg', '580.00', '遇见·世园 温馨舒适北欧风', '江苏省南京市复地活力广场(S2栋二单元22楼大厅（韩笑口腔旁边物业大门进）)', '2', '房屋配有落地窗、蓝牙环绕音响、品牌家电家具、温馨舒适床品。开放式厨房、24小时安保、设计轻奢简约风，无论公差还是居家，都能感受到同样温馨的体验，相信会给您留下温馨美好的回忆！', '1', '1', '1', '0', '1', '0', '1', '1');
INSERT INTO `house` VALUES ('6', null, '33', '/upload/file/20200505101255201808291123436324_700_467.jpg', '1380.00', '玉渡山脚下—乡涧山舍号院（含早餐）', '江苏省南京市阳光聚宝山庄一期红豆街区理想家酒店公寓', '2', '此房型为22平左右精装公寓，独立卫生间，品牌家电，箭牌卫浴，24小时热水。4楼顶有一个80平米的超大屋顶花园，一楼湖边有一个专属咖啡厅。独立晾晒区。', '1', '1', '1', '1', '0', '0', '1', '1');
INSERT INTO `house` VALUES ('7', null, '1', '/upload/file/20200505101303201607012304213648_700_467.jpg', '880.00', '慧心社【伍】可长租 短租 瑞金医院田子坊', '上海黄浦区复兴坊(复兴中路553弄)', '3', '房间设计简约但不简单、大方不失雅致，室内宽敞明亮、清静幽雅、干净整洁，是您度假的首选之地。', '2', '2', '0', '0', '0', '0', '1', '2');
INSERT INTO `house` VALUES ('10', null, '18', '/upload/file/20200505101311201705090301001216_700_467.jpg', '543.00', '千岛湖欢墅归耕园（耕系亲子房）', '浙江省杭州市茶园村里胡家', '3', '房间配备两张床，一个大床一个小床，非常适合一家居住，即宽敞又温馨。', '1', '1', '1', '0', '1', '0', '0', '2');
INSERT INTO `house` VALUES ('11', null, '1', '/upload/file/20200505101319201706142303374258_700_467.jpg', '280.00', '【清风苑】紫光灯消毒 地铁零距 黄金城道', '上海市长宁区虹桥路1111弄', '3', '热情喜玩，好待客！ 您住此处便是家！ 地铁出行零距！公交周围绕！出入方便尽无忧！ 机场过来没一会，楼下超市，菜场 便利多！ 旅游景点皆是通，富人住在路隔壁！', '1', '1', '1', '1', '0', '0', '1', '2');
INSERT INTO `house` VALUES ('12', null, '1', '/upload/file/20200505101326201712091038074230_700_467.jpg', '249.00', '3号线东宝兴路/近虹口足球场/上海火车站', '上海市虹口区华昌路9号', '2', '作为一名土生土长的上海人，我很愿意和每位来到上海的游客成为朋友，让他们了解真正的上海是什么模样，同他们分享上海的美食，上海的景点，避免一些景点的雷区', '1', '1', '1', '0', '1', '1', '1', '1');
INSERT INTO `house` VALUES ('13', null, '1', '/upload/file/20200505101400201801042051377756_700_467.jpg', '527.00', '罗马夏日-意式轻奢投影套房，近西湖河坊街', '浙江省杭州市通润银座', '2', '环游了一圈世界，走过世界上美好的角角落落，看过了最美的星空，回国第一件事就是在西湖边精心打造了这个房子，希望入住的你可以一起感受这份温暖与心意。晚饭过后逛逛街，可以散步去西湖吹吹风，看看对面的雷峰塔夕', '1', '1', '0', '0', '0', '1', '1', '2');
INSERT INTO `house` VALUES ('14', null, '1', '/upload/file/20200505101410201805292146044849_700_467.jpg', '368.00', '知春路地铁站北影北航', '北京市太月园(太月园小区4号楼)', '2', '雨巷采用现代简约古典空间设计，整个房间以青 灰 瓦红为主，配以青竹 以及灯笼等古典装饰物，清新淡雅。', '1', '4', '1', '1', '0', '0', '1', '2');
INSERT INTO `house` VALUES ('15', null, '1', '/upload/file/20200505101617201808040141504676_700_467.jpg', '199.00', '喜舍*禅园 两居投影带茶台 丽景门店', '义勇前街住宅楼(义勇前街48号孙师傅开锁的月亮门进入)', '2', '我的房子是两室一厅，一厨一卫, 70平米。地处老城区繁华的地段。距离当地的连锁超市只有227米。距离拥有1400年历史的丽景门只有377米。距离老街十字街(步行街)只有570米。距离天堂明堂和洛邑古城', '1', '1', '1', '1', '0', '0', '1', '1');
INSERT INTO `house` VALUES ('16', null, '18', '/upload/file/20200505101631201808291134354800_700_467.jpg', '438.00', '望京悠乐汇温馨一居室ABBA', '北京市悠乐汇-a座', '2', 'Hi,欢迎您入住奇幻暖屋——暖屋家人用心全新打造的温馨一居，让您感受不一样的生活魅力!', '1', '1', '1', '0', '0', '1', '1', '1');
INSERT INTO `house` VALUES ('17', null, '18', '/upload/file/20200505102341201808291134405358_700_467.jpg', '298.00', '云深•长安 潭柘寺门头沟简欧loft投影', '北京市西长安壹号30号院(永定镇长安壹号30号院)', '2', '云深·长安坐落于北京潭柘寺附近的西长安壹号，房屋面积60平，房型为loft飘窗阳光房（采光巨好）房屋整体北欧风，全套全新家具。', '1', '1', '1', '0', '1', '1', '1', '2');
INSERT INTO `house` VALUES ('18', null, '33', '/upload/file/20200505102350201809211323443864_700_467.jpg', '539.00', '巨幕投影 浴缸loft复式夜景三里屯国展', '北京市左家庄北里', '2', '一应俱全，马路对面左侧路口有一个“燕丰商场”里面蔬菜价格实惠，海鲜什么都很新鲜。', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `house` VALUES ('20', null, '33', '/upload/file/20200505102359201810241417301004_700_467.jpg', '710.00', '全息光影概念民宿4K巨幕国贸', '北京市苹果社区北区2号楼C座(苹果社区北区2C)', '2', '【逅来·未来系列 全息光影】概念4K巨幕影院·约会主题民宿', '1', '1', '1', '1', '0', '1', '1', '1');
INSERT INTO `house` VALUES ('27', null, '1', '/upload/file/20200505102426201903111840011857_700_467.jpg', '206.00', '小猪佩奇～亲子度假公寓（润川丽景）', '四川省成都市润川·丽景(建设中)(君山度假公寓)', '2', '三星级精品装修，2019年5月份新装修交房。房间宽大舒适，居住舒适。一张1.8米大床，一张1.2米单人床，温馨舒适。 公寓对面是栾川最大的山体公园东岭植物公园，往西500米为老君山景区游客服务中心。', '1', '1', '1', '1', '1', '0', '1', '1');
INSERT INTO `house` VALUES ('30', null, '1', '/upload/file/20200505102435201904072258066215_700_467.png', '143.00', '洛阳罗曼蒂克精品公寓法式浪漫', '四川省成都市南昌路建业壹号城邦9号楼', '2', '集住宅、SOHO、公寓、商业中心为一体。大型成熟小区、南北通透、空中花园、大型停车场，配备五星级私人定制物管，24小时全方位管家服务！独立空间，免受打扰，设施齐全，自由自在。尊享丹尼斯百货、万达广场醇', '1', '1', '1', '0', '0', '0', '1', '1');
INSERT INTO `house` VALUES ('31', null, '1', '/upload/file/20200505102442201905192016541141_700_467.jpg', '598.00', '漫步老城遇见洛邑古城丽景门十字街夜市4室', '四川省成都市图书馆街安居小区(北门)', '3', '房源位于洛阳老城核心区域青年宫,步行可至洛阳最经久不衰的小吃街——“十字街夜市”,古城门丽景门,鼓楼,文创古镇洛邑古城北门等Top景点,非常适合旅游者自由探索,房间安静不临街,旅游交通极为方便,附近5', '1', '2', '1', '1', '0', '0', '1', '1');
INSERT INTO `house` VALUES ('32', null, '1', '/upload/file/20200505102450201906232320324533_700_467.jpg', '239.00', '【花会民宿】高层电梯两居室景区河景房。', '四川省成都市柳林南街/柳林街(路口)', '2', '高层电梯景区房，河景房。洛黾古城，丽景门。十字街夜市。十分钟步行。两居室大床房。停车方便，早中晚饭店应有尽有。都是免费景区网红打卡地。 周边有洛阳民俗博物馆。八路军驻洛阳办事处。{红色教育基地。}洛浦', '1', '1', '1', '0', '0', '0', '1', '1');
INSERT INTO `house` VALUES ('33', null, '33', '/upload/file/20200505102503201906240119005322_700_467.jpg', '160.00', '【丽景门】洛邑古城/金屏苑舒适一居19', '四川省成都市金屏苑', '2', '针对近期新型肺炎疫情，为保障房客安全，途家自营将加强房屋消毒措施，与您一起，共克时艰：', '1', '1', '1', '0', '1', '0', '1', '1');
INSERT INTO `house` VALUES ('40', null, '18', '/upload/file/20200505102541201906241253091566_700_467.jpg', '383.00', '华景新城 复式大床房', '广东省广州市叠翠台', '2', '位于市中心繁华的天河区，临近华南师范大学，周边配套非常成熟，3号线岗顶地铁站，通达全城。', '1', '1', '1', '1', '1', '0', '1', '1');
INSERT INTO `house` VALUES ('42', null, '33', '/upload/file/20200505102559201908081517433436_700_467.jpg', '806.00', '广州塔广交会兴盛路珠江新城标准豪华大套房', '广东省广州市领峰园', '2', '领峰园W豪华套房位于广州珠江新城核心地段。五星级的配套,优越的地理位置,适合情侣、商务旅行者、有小孩的家庭。', '1', '1', '1', '1', '0', '0', '1', '1');
INSERT INTO `house` VALUES ('43', null, '18', '/upload/file/20200505102608201908201233046779_700_467.jpg', '528.00', '上下九荔枝湾·老西关·花坞·青花', '广东省广州市逢源北街逢源北街/龙津西路(路口)', '2', '藏匿老城核心街区的尘心归宿，寻踪一种正在消失的历史记忆，斜躺棉床，随风入眠，侧耳聆听老城夜话，享受老城慢生活。', '1', '1', '1', '1', '1', '0', '1', '1');
INSERT INTO `house` VALUES ('51', null, '33', '/upload/file/20200505102619201909121132408757_700_467.jpg', '476.00', '上下九荔枝湾·老西关·花坞·余香', '广东省广州市逢源北街逢源北街/龙津西路(路口)', '2', '藏匿老城核心街区的尘心归宿，寻踪一种正在消失的历史记忆，斜躺棉床，随风入眠，侧耳聆听老城夜话，享受老城慢生活。', '1', '1', '1', '0', '1', '0', '1', '1');
INSERT INTO `house` VALUES ('53', null, '33', '/upload/file/20200505102629201909251840346243_700_467.jpeg', '888.00', '长隆美雅复式双床房（汉溪长隆地铁站）', '广东省广州市奥园城市天地9区-1栋(钟村街奥园城市天地)', '2', '房间采用数字化电子配置，超大落地窗，后现代式设计，简约但不失奢华，房间配有两台超大数码电视，极速网络，分体式中央空调，微信付费式按摩椅，茶座榻榻米，尽享旅途的乐趣!', '1', '1', '1', '1', '1', '0', '1', '2');
INSERT INTO `house` VALUES ('54', null, '18', '/upload/file/20200505102641201910010044445108_700_467.jpg', '368.00', '【夏日狂想】广州塔/花城广场/暨大/长隆', '广东省广州市甲子花园', '2', '房子在珠江新城CBD高尚住宅区，靠近地铁，有独立的大客厅、大露台和餐厅，阳台能看见楼下整个珠江公园的景观', '1', '1', '1', '0', '1', '0', '0', '1');
INSERT INTO `house` VALUES ('56', null, '33', '/upload/file/20200505102734201910090652097572_700_467.jpg', '800.00', '天河区珠江新城广交会兴盛路五装修豪华', '广东省广州市领峰东二门', '2', '领峰园W豪华套房位于广州珠江新城核心地段。五星级的配套,优越的地理位置,适合情侣、商务旅行者、有小孩的家庭。', '1', '1', '1', '0', '1', '0', '1', '1');

-- ----------------------------
-- Table structure for house_collection
-- ----------------------------
DROP TABLE IF EXISTS `house_collection`;
CREATE TABLE `house_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `houseId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `house_collection_ibfk_1` (`userId`),
  KEY `house_collection_ibfk_2` (`houseId`),
  CONSTRAINT `house_collection_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `house_collection_ibfk_2` FOREIGN KEY (`houseId`) REFERENCES `house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of house_collection
-- ----------------------------
INSERT INTO `house_collection` VALUES ('1', '15', '10');
INSERT INTO `house_collection` VALUES ('16', '14', '2');
INSERT INTO `house_collection` VALUES ('18', '14', '4');
INSERT INTO `house_collection` VALUES ('21', '14', '15');
INSERT INTO `house_collection` VALUES ('22', '14', '40');

-- ----------------------------
-- Table structure for long_rent_collection
-- ----------------------------
DROP TABLE IF EXISTS `long_rent_collection`;
CREATE TABLE `long_rent_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `longRentHouseId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of long_rent_collection
-- ----------------------------
INSERT INTO `long_rent_collection` VALUES ('13', '14', '1');
INSERT INTO `long_rent_collection` VALUES ('14', '14', '26');
INSERT INTO `long_rent_collection` VALUES ('15', '14', '13');
INSERT INTO `long_rent_collection` VALUES ('16', '14', '10');

-- ----------------------------
-- Table structure for long_rent_house
-- ----------------------------
DROP TABLE IF EXISTS `long_rent_house`;
CREATE TABLE `long_rent_house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(64) DEFAULT NULL,
  `photo` varchar(256) DEFAULT NULL,
  `price` float(8,0) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `area` int(8) DEFAULT NULL,
  `floorRange` varchar(4) DEFAULT NULL,
  `introduce` varchar(256) DEFAULT NULL,
  `bedRoomNum` int(2) NOT NULL DEFAULT '1',
  `sittingRoomNum` int(2) NOT NULL DEFAULT '1',
  `toiletNum` int(2) NOT NULL DEFAULT '1',
  `landlordId` int(11) DEFAULT NULL,
  `totalFloor` int(3) DEFAULT '1',
  `isEntire` int(1) DEFAULT '1',
  `isSouth` int(1) DEFAULT '1',
  `isElevator` int(1) DEFAULT '1',
  `createTime` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `landlordId` (`landlordId`),
  CONSTRAINT `long_rent_house_ibfk_1` FOREIGN KEY (`landlordId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of long_rent_house
-- ----------------------------
INSERT INTO `long_rent_house` VALUES ('1', null, '/upload/file/20200505110817u=420840375,3235077107&fm=26&gp=0.jpg', '2100', '爱在诚创 大学城旁诚城紫钰 精装三室家电家具齐全拎包入住', '河南省新乡市诚城紫钰(南区)  (红旗 东站十字)', '110', '低层', '精心装修，宽敞明亮，交通便利，周边环境好，购物，医疗等都很方便', '3', '2', '1', '1', '23', '1', '1', '1', '2020-03-19');
INSERT INTO `long_rent_house` VALUES ('2', null, '/upload/file/20200505110830u=473689596,3008926100&fm=26&gp=0.jpg', '1000', '常青藤|紫钰大学城近|精装公寓|租期灵活|高级物业管理', '河南省新乡市诚城常青藤  (红旗 红旗周边)', '45', '低层', '小区自带花园绿化好，有健身房，门口世纪联华大型超市电影院，距离大学城一路之隔。', '1', '1', '5', '1', '6', '1', '0', '0', '2020-02-19');
INSERT INTO `long_rent_house` VALUES ('5', null, '/upload/file/20200505110838u=559940726,3826211548&fm=26&gp=0.jpg', '888', '新房源 福利价位 好房源 正光路 晖达 金鹏 金水东路 地铁', '河南省郑州市金庄社区东十一社区  (郑东新区 祭城)', '30', '低层', '您正好有需求，我正好专业。找我租房能给你一个舒适像家一样的房子。 龤.还能给你个优惠打折惊喜！ 閏.我们不求租房能挣多少钱，我们做的是服务！ 驋.可以这么说：同样的产品比价格，同样的价格比服务', '2', '2', '1', '1', '32', '0', '1', '0', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('9', null, '/upload/file/20200505110847u=696775513,2362412719&fm=26&gp=0.jpg', '900', '绿地新都汇 楷琳雅宝 升龙广场行署国际双子塔 金水东路', '河南省郑州市祭城社区36号院  (郑东新区 祭城)', '34', '中层', '【配套设施】房间内配备品牌家具家电、配套床垫、抱枕、台灯、壁灯、减轻疲劳桌椅、衣柜、挂衣钩、壁画、带插板、空调、洗衣机、冰箱和厨房。房管员及时带看，售后维修及时。不收取中介费：押一付一，免中介费用售后维修及时：驋龥h都可保修及当天维护，让住户省心省事，非故意损坏，不会收取任何费用。', '2', '1', '1', '18', '12', '0', '1', '0', '2020-02-19');
INSERT INTO `long_rent_house` VALUES ('10', null, '/upload/file/20200505110855u=950243827,5139838&fm=26&gp=0.jpg', '2710', '南湖中园一区 家具齐全 上班方便 定金无忧退 干净简洁', '北京市南湖中园一区(东区)  (朝阳 望京)', '10', '低层', '小区环境好，安全系数有保障，有保安值守，家具家电齐全，可拎包入住。床电脑桌衣柜空调热水器洗衣机宽带网络。不能养宠物，有厨房可以做饭。 就在附近上班要看房提前打电话。', '2', '1', '1', '33', '6', '1', '1', '0', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('11', null, '/upload/file/20200505110905u=1305570338,352174946&fm=26&gp=0.jpg', '2500', '奥北南区 北京北小区 正规次卧 落地飘窗 随时入住 屋杂费', '北京市奥北中心南区  (昌平 立水桥)', '26', '中层', '禁止养宠物，精装修装修：北欧风格精装修，精装大主卧，干净卫生，阳光充足家配套：配备品牌家具家电、配套床垫、抱枕、台灯、壁灯、减轻疲劳桌椅、衣柜、空调、洗衣机、冰箱和厨房，高速宽带。环境：小区环境优美，安静祥和，地铁该房源，一客一锁，安全性高', '3', '2', '2', '18', '30', '0', '1', '1', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('13', null, '/upload/file/20200505110913u=1389429055,1623462610&fm=26&gp=0.jpg', '1900', '大山子北里 宽敞南卧 有阳台可晾衣 急速维修 随时看房', '北京市大山子北里  (朝阳 大山子)', '12', '低层', '精装修主卧出租，屋内有空调、大衣柜、桌子、床垫。厨房冰箱共享，客厅电视也共享。卫生间有淋浴，干净整洁； 龒.步行十就到地铁站，小区门口多公交车，出行十分便利； 麣.商品房小区物业到位，环境优美，绿化宜人，公共健身休闲设施齐全； 驋.楼层中间不高，有电梯。小区安保好，安全问题有保障。水电费平摊、网速快。', '2', '1', '1', '1', '23', '0', '1', '1', '2020-04-10');
INSERT INTO `long_rent_house` VALUES ('14', null, '/upload/file/20200505110937u=1395704313,2322260857&fm=26&gp=0.jpg', '3300', '望京西园三区精装大次卧 紧邻望京SOHO 绿地中心', '北京市望京西园三区  (朝阳 望京)', '20', '低层', '房源情况30平大次卧，精装，家具家电齐全。下楼方便，随时看房，有钥匙！', '3', '1', '2', '18', '20', '0', '1', '1', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('15', null, '/upload/file/20200505111230u=1583081045,1775251762&fm=26&gp=0.jpg', '4600', '本六区低层板楼精装一居室 家具家电齐全 随时看房 拎包入住', '北京市天通苑老六区(天通苑本六区)  (昌平 天通苑)', '70', '低层', '楼层好，采光特别好，在小区中间，没遮挡，之前一直是业主自住，家电家具齐全。', '1', '1', '1', '18', '6', '1', '1', '0', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('18', null, '/upload/file/20200505111240u=1769562692,2991838310&fm=26&gp=0.jpg', '960', '民建里 近6号线 三潭医院 低楼层 主卧带阳台 家具全齐月付', '天津市民建里  (南开 万兴街)', '23', '中层', '全国品牌连锁公寓，全新家具电器，手绘油画装点你的梦想空间；支持异地、同城免费换房，工作调动住房零损失。', '2', '1', '1', '1', '23', '0', '1', '1', '2020-04-18');
INSERT INTO `long_rent_house` VALUES ('19', null, '/upload/file/20200505111250u=2110909667,300193603&fm=26&gp=0.jpg', '920', '双港近地铁 隔音好 月月返现，可省2月租金 手快有慢无', '天津市柳景家园  (津南 双港)', '14', '中层', '我们为您提供的不仅是一间房，更是一个家！希望您入住的同时，我们也成为了长久的朋友。', '2', '1', '1', '33', '12', '1', '0', '1', '2020-04-02');
INSERT INTO `long_rent_house` VALUES ('20', null, '/upload/file/20200505111258u=2112243885,2118463892&fm=11&gp=0.jpg', '970', '隔音好 双港毕业生月付龒押金 送免费换租 手快有慢无 双', '天津市柳景家园  (津南 双港)', '15', '中层', '配备品牌家具家电、配套床垫、抱枕、台灯、桌椅、衣柜、空调、洗衣机、冰箱和宽带。', '2', '1', '1', '1', '26', '0', '0', '1', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('21', null, '/upload/file/20200505111309u=2177211334,1222659005&fm=11&gp=0.jpg', '1230', '首月立减600，房源押一付一，无中介，智能门锁 近地铁 品牌', '天津市 海天馨苑德信园  (津南 双港)', '14', '高层', '配备品牌家具家电、配套床垫、抱枕、台灯、桌椅、衣柜、空调、洗衣机、冰箱和宽带。', '3', '1', '1', '18', '20', '1', '1', '1', '2020-04-03');
INSERT INTO `long_rent_house` VALUES ('22', null, '/upload/file/20200505111322u=2253762377,3827903868&fm=26&gp=0.jpg', '890', '双港可短租，精装修隔音好，无物业费，拎包入住，带阳台', '天津市津铁惠苑  (津南 双港)', '13', '低层', '电器齐全，洗衣机，热水器,空调等一应俱全(一线大品牌）；室内家具配套，大床，衣柜，电脑桌。龤餼餼M高速宽带，一切等你来享。 ', '3', '1', '1', '33', '26', '0', '1', '1', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('23', null, '/upload/file/20200505111335u=2519705007,3259993775&fm=26&gp=0.jpg', '930', '毕业租房季 月付无押金 免费换租 福松源庄 家电齐全', '天津市 福松源庄  (津南 双港)', '14', '低层', '全新家具家电公寓所使用家具为全新宜家家具，家电也为全新品牌家电。 ', '3', '1', '1', '1', '20', '0', '1', '1', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('24', null, '/upload/file/20200505111350u=2856735940,1418015360&fm=26&gp=0.jpg', '1840', '西丽 文光村 家电齐全 采光好 近茶光地铁 出行方便', '广东省深圳市文光村  (南山 西丽)', '18', '低层', '房屋采光好，整洁明亮，家具家电配置齐全，可拎包入住！采光通风效果好，安静无噪音！交通方便，公交地铁站步行可达！周边配套齐全，可满足基本生活需求。', '3', '1', '1', '33', '6', '1', '0', '1', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('25', null, '/upload/file/20200505111412u=3063859212,1663574451&fm=26&gp=0.jpg', '1300', '包宽带 精装单配 轻轨旁 家私齐全 拎包入住 随时看房', '重庆市富力城繁华里(商住楼)  (沙坪坝 大学城)', '45', '低层', '步行可到达轻轨站。出行很方便。', '2', '1', '1', '18', '31', '1', '1', '1', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('26', null, '/upload/file/20200505111427u=3833994970,1002771127&fm=26&gp=0.jpg', '680', '南稍门近地铁 隔音好 月月返现，可省2月租金 手快有慢无', '陕西省西安市 泛渼大厦  (碑林 南稍门)', '19', '低层', '配备品牌家具家电、配套床垫、抱枕、台灯、桌椅、衣柜、空调、洗衣机、冰箱和宽带。', '2', '1', '1', '33', '4', '1', '1', '0', '2020-04-19');
INSERT INTO `long_rent_house` VALUES ('27', null, '/upload/file/20200505111438u=4064927604,1925434338&fm=26&gp=0.jpg', '1890', '10个月租金住一年 斜土路 家电齐全 有阳台 交通便利 精装', '上海市清真小区  (徐汇 斜土路)', '23', '低层', '配备品牌家具家电、配套床垫、抱枕、台灯、壁灯、减轻疲劳桌椅、衣柜、空调、洗衣机、冰箱和厨房，高速宽带。可以直接拎包入住', '3', '1', '2', '1', '6', '0', '1', '0', '2020-04-28');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '房客');
INSERT INTO `role` VALUES ('2', '房东');
INSERT INTO `role` VALUES ('3', '管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT '1',
  `photo` varchar(128) DEFAULT NULL,
  `realPhoto` varchar(128) DEFAULT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nickName` varchar(32) DEFAULT NULL,
  `realName` varchar(8) DEFAULT NULL,
  `idCard` varchar(32) DEFAULT NULL,
  `sex` int(1) DEFAULT '0',
  `email` varchar(32) DEFAULT NULL,
  `mobile` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2', '/upload/file/20200504215233u=596044868,1285208380&fm=11&gp=0.jpg', '/upload/file/20200505102807u=3687561876,1541714816&fm=26&gp=0.jpg', 'lambkin', '123456', '王五五', '孙悟空', '32222222221', '2', '268312386@qq.com', '13452678222');
INSERT INTO `user` VALUES ('13', '1', '/upload/file/20200505102818u=1202331995,1156322891&fm=26&gp=0.jpg', null, '猿来入此', '123456', '裸奔的小嫦', null, null, '1', null, null);
INSERT INTO `user` VALUES ('14', '1', '/upload/file/20200505102832u=15545507,3008681345&fm=11&gp=0.jpg', null, '张三', '123', '小牧五', '张三', '124538182199872', '1', '453543251@qq.com', '');
INSERT INTO `user` VALUES ('15', '1', '/upload/file/20200505102843u=1246207470,1516116328&fm=11&gp=0.jpg', null, '李四', '123', '小栗子', null, null, '0', null, null);
INSERT INTO `user` VALUES ('17', '1', '/upload/file/20200505102856u=1932359049,4261028892&fm=26&gp=0.jpg', null, 'a', '123', 'A饱食度', null, '5737612573131', '0', null, null);
INSERT INTO `user` VALUES ('18', '2', '/upload/file/20200505102906u=2134820583,4188516657&fm=15&gp=0.jpg', '/upload/file/20200505102914u=552372378,540428458&fm=26&gp=0.jpg', '小天使', '123456', '凸(艹皿艹 )', '李海军', null, '1', null, null);
INSERT INTO `user` VALUES ('19', '1', '/upload/file/20200505102931u=2182692804,4093356707&fm=26&gp=0.jpg', null, 'abc', '123456', '吴博士那', null, null, '0', null, null);
INSERT INTO `user` VALUES ('23', '3', '/upload/file/20200505102941u=2601900707,917050054&fm=26&gp=0.jpg', null, 'admin', 'admin', 'admin', null, null, '0', '', null);
INSERT INTO `user` VALUES ('24', '1', '/upload/file/20200505102951u=2648708203,2903957431&fm=11&gp=0.jpg', null, 'ssssss', 'ssssss', '啊啊啊啊啊啊', '', '', '1', '', '');
INSERT INTO `user` VALUES ('26', '1', '/upload/file/20200505103000u=2670322214,1593882070&fm=11&gp=0.jpg', null, 'fsfsdfds', 'dsffdsfs', '', '', '', '1', '', '');
INSERT INTO `user` VALUES ('27', '1', '/upload/file/20200505103019u=2942344383,676339444&fm=11&gp=0.jpg', null, 'aaa', 'aaa', '', '', '', '1', '', '');
INSERT INTO `user` VALUES ('28', '1', '/upload/file/20200505103032u=3374416169,262924133&fm=11&gp=0.jpg', null, 'sssss', 'sssss', '', '', '', '2', '', '');
INSERT INTO `user` VALUES ('29', '1', '/upload/file/20200505103040u=3434686726,1651926452&fm=11&gp=0.jpg', null, 'www', 'www', 'sssssss', '', '', '0', '', '');
INSERT INTO `user` VALUES ('30', '1', '/upload/file/20200505103055u=3629371921,2420870156&fm=26&gp=0.jpg', null, 'aaass', '21312', '', '', '', '1', '', '');
INSERT INTO `user` VALUES ('31', '1', '/upload/file/20200505103108下载 (1).jpg', null, '小火锅底', '123456', '小火锅底', '张珊珊', '410882199710284759', '2', '23123143@163.com', '13123333333');
INSERT INTO `user` VALUES ('32', '1', '/upload/file/20200505103118下载 (2).jpg', '/upload/file/20200505103132u=3434686726,1651926452&fm=11&gp=0.jpg', 'wewrew', '111', '', '', '', '1', '', '');
INSERT INTO `user` VALUES ('33', '2', '/upload/file/20200505103141下载 (3).jpg', '/upload/file/20200505103209u=3231437715,2626478449&fm=26&gp=0.jpg', '笔记本', '1234', '笔记本', '王红军', '326819263672681', '1', '11111111', '');
INSERT INTO `user` VALUES ('34', '1', '/upload/file/20200505103157下载.jpg', null, '12312', '111', 'vvdhavd', null, null, '1', null, null);
INSERT INTO `user` VALUES ('35', '1', '/upload/file/20200505140812u=2182692804,4093356707&fm=26&gp=0.jpg', null, 'ddd', '123', 'hhh', null, null, '2', null, null);
