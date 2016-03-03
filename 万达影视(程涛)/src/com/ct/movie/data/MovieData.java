package com.ct.movie.data;

import com.ct.movie.R;

public class MovieData {
	private String[] movieName;
	private String[] movieSimpleContent;
	private String[] movieCommentNumber;
	private String[] movieScore;
	private String[] movieExactContent;
	private String[] movieInfoContent;
	private String[] movieInfoTime;
	private int[] movieImage;
	private int[] movieContentImages;
	private int[] movieTag;
	public MovieData() {
		super();
		initMovieData();
	}
	public MovieData(int n) {
		super();
		movieName = new String[n];
		movieSimpleContent = new String[n];
		movieCommentNumber = new String[n];
		movieScore = new String[n];
		movieExactContent = new String[n];
		movieInfoContent = new String[n];
		movieInfoTime = new String[n];
		movieImage = new int[n];
		movieTag = new int[n];
	}
	public String[] getMovieName() {
		return movieName;
	}

	public void setMovieName(String[] movieName) {
		this.movieName = movieName;
	}

	public String[] getMovieSimpleContent() {
		return movieSimpleContent;
	}

	public void setMovieSimpleContent(String[] movieSimpleContent) {
		this.movieSimpleContent = movieSimpleContent;
	}

	public int[] getMovieImage() {
		return movieImage;
	}

	public void setMovieImage(int[] movieImage) {
		this.movieImage = movieImage;
	}

	public String[] getMovieCommentNumber() {
		return movieCommentNumber;
	}

	public void setMovieCommentNumber(String[] movieCommentNumber) {
		this.movieCommentNumber = movieCommentNumber;
	}

	public String[] getMovieScore() {
		return movieScore;
	}

	public void setMovieScore(String[] movieScore) {
		this.movieScore = movieScore;
	}

	public String[] getMovieExactContent() {
		return movieExactContent;
	}

	public void setMovieExactContent(String[] movieExactContent) {
		this.movieExactContent = movieExactContent;
	}

	public String[] getMovieInfoContent() {
		return movieInfoContent;
	}

	public void setMovieInfoContent(String[] movieInfoContent) {
		this.movieInfoContent = movieInfoContent;
	}

	public String[] getMovieInfoTime() {
		return movieInfoTime;
	}

	public void setMovieInfoTime(String[] movieInfoTime) {
		this.movieInfoTime = movieInfoTime;
	}

	public int[] getMovieContentImages() {
		return movieContentImages;
	}

	public void setMovieContentImages(int[] movieContentImages) {
		this.movieContentImages = movieContentImages;
	}

	public int[] getMovieTag() {
		return movieTag;
	}

	public void setMovieTag(int[] movieTag) {
		this.movieTag = movieTag;
	}

	public void initMovieData() {
		movieName = new String[] { "钢的琴", "郭明义", "功夫熊猫2", "终极快递", "迷途追凶",
				"建党伟业" };
		movieSimpleContent = new String[] { "小人物的酸甜苦辣", "当代雷锋的崇高品质", "阿宝身世大揭秘",
				"法国喜剧浪漫奢华", "黄秋生父子荧幕合作", "豪华巨制 众星云集" };
		movieScore = new String[] { "3.8", "4.1", "3.2", "3.8", "2.5", "4.8" };
		movieCommentNumber = new String[] { "79", "31", "103", "26", "5", "326" };
		movieExactContent = new String[] {
				"\\n\\n《钢的琴》讲述了一位父亲为了女儿的音乐梦想而不断艰苦努力，最后通过身边朋友的帮助用钢铁为女儿打造出一架钢琴的故事，通过小人物幽默与艰辛，展露一段感人至深的亲情和友情。",
				"\\n\\n《郭明义》由陈国星、王竞联合执导，侯勇、李沁等主演的传记电影。该电影主要讲述的是郭明义的成长历程、工作经历和生活片段再现了其助人为乐的事迹，向观众展现了其平凡而丰富的内心世界。",
				"\\n\\n《功夫熊猫2》（Kung Fu Panda 2）是一部2011年美国3D动画电影，是2008年动画电影《功夫熊猫》的续集。由詹妮弗·余执导，杰克·布莱克、安吉丽娜·朱莉、成龙、塞斯·罗根、加里·奥德曼等配音",
				"\\n\\n该片讲述了山姆（迈克尔·扬 Michaël Youn 饰）每日飞车穿过巴黎，常常无视交通的基本规则。他必须出席婚礼以试图挽救与女友纳迪娅（杰拉尔丁·纳卡什 Géraldine Nakache 饰）的关系，然而这天他没有选择地只能接受他的老板紧急差事，去送一个快件。为了完成任务他不得不扮演罪犯的帮凶，将价值连城的珠宝送至巴黎市中心的豪华酒店。",
				"\\n\\n《迷途追凶》由寰亚影视出品的警匪片，由罗永昌执导、杜琪峰监制，黄秋生、任贤齐、冯志强、林逢等人主演。电影讲述固执霸道的富商黄秋生在爱女绑架被撕票后，派出贴身保镖任贤齐不惜一切手段追杀绑匪。",
				"\\n\\n《建党伟业》（Beginning of the Great Revival）是为庆祝中国共产党建党九十周年而制作的献礼影片。[1]  该片由韩三平、黄建新执导（海外部分由李少红执导，五四时期部分由陆川执导，战争场面由沈东执导），由刘烨、陈坤、张嘉译、冯远征等108位明星出演。" };
		movieInfoContent = new String[] { "导演:张猛\\t编剧:张猛\\t制片人:崔光石、甘蕙茵",
				"导演:陈国星、王竞\\t编剧:高满堂\\t制片人:陈国星、王竞",
				"导演:詹妮弗·余\\t编剧:乔纳森·阿贝尔，格伦·伯杰\\t制片人:乔纳森·阿贝尔，格伦·伯杰",
				"导演:埃尔韦·雷诺阿\\t编剧:埃尔韦·雷诺阿\\t制片人:埃尔韦·雷诺阿",
				"导演:罗永昌\\t编剧:冯志强、林逢\\t制片人:庄澄 、杜琪峰",
				"导演:韩三平、黄建新\\t编剧:黄欣、郭俊立、董哲\\t制片人:韩三平" };
		movieInfoTime = new String[] { "国家:中国\\t片长:107分钟\\t上映时间:2011年",
				"国家:中国\\t片长:90分钟\\t上映时间:2011年", "国家:美国\\t片长:90分钟\\t上映时间:2011年",
				"国家:法国\\t片长:98分钟\\t上映时间:2011年",
				"国家:中国香港\\t片长:94分钟\\t上映时间:2011年",
				"国家:中国\\t片长:140分钟\\t上映时间:2011年" };
		movieImage = new int[] { R.drawable.movie_pic_a,
				R.drawable.movie_pic_b, R.drawable.movie_pic_c,
				R.drawable.movie_pic_d, R.drawable.movie_pic_e,
				R.drawable.movie_pic_f };
		movieContentImages = new int[] { R.drawable.gallery_photo_1,
				R.drawable.gallery_photo_2, R.drawable.gallery_photo_3,
				R.drawable.gallery_photo_4, R.drawable.gallery_photo_5,
				R.drawable.gallery_photo_6, R.drawable.gallery_photo_7,
				R.drawable.gallery_photo_8 };
		movieTag = new int[] { 1, 2, 3, 4, 5, 6 };
	}
}
