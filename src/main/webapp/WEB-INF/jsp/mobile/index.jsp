<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
dt,dd {
	line-height: 2em;
}

dt {
	color: orange;
}
</style>
</head>
<body>

<div id="youkuplayer"></div>
<script type="text/javascript" src="http://player.youku.com/jsapi">
    player = new YKU.Player('youkuplayer',{
        client_id: '0bc97c92de7d05b1',
        vid: 'XNjI1MjExNzEy',
        autoplay: true,
        width: '100%',
        height: '100%',
        embsig: '1_1382593648604_C931C6602E560E4CBD70BF14F4EB17FF'
    });
    function playVideo() {
        player.playVideo();
    }
    function pauseVideo() {
        player.pauseVideo();
    }
</script>
	<dl>
		<dt>用户登录：</dt>
		<dd>
			<a
				href="http://27.154.234.10:8888/video-server/user/login?username=String&password=String">http://27.154.234.10:8888/video-server/user/login?username={String}&password={String}</a>
			{用户名密码随便写，还没有过滤}
		</dd>
		<dt>用户注册：</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/user/register">http://27.154.234.10:8888/video-server/user/register</a>
			{用户名，密码 必填，邮箱，用户头像不是必须的
		</dd>
		<dd>POST参数：username:{String},password:{String},email:{223@xx.com},headImage:{file}}</dd>

		<dt>修改用户信息：</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/user/modify">http://27.154.234.10:8888/video-server/user/modify</a>
			{用户id号，是必须，性别，生日，简述，邮箱不是必须的}
		</dd>
		<dd>POST参数：personId:{Long},sex:{boolean},birthday:{String},desc:{String},email:{String}(邮箱格式必须正确)</dd>
		<dt>播放记录列表：</dt>
		<dd>
			<a
				href="http://27.154.234.10:8888/video-server/user/record?personId=1">http://27.154.234.10:8888/video-server/user/record?personId={Long}</a>
		</dd>
		<dt>提交播放记录：</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/user/commitRecord">http://27.154.234.10:8888/video-server/user/commitRecord</a>
		</dd>
		<dd>POST参数：personId:{Long} 人物id 必须 videoId:{Long}视频id号，必须
			historyId{Long} 历史记录ID，不是必须 record：{String} 播放记录 必须recordImg：{file}
			必须，播放记录图片</dd>
		<dt>用户反馈</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/user/feedback">http://27.154.234.10:8888/video-server/user/feedback</a>
		</dd>
		<dd>POST参数：personId:{Long} 人物id 必须 content：{String} 内容 必须</dd>
		<dt>2.频道列表：</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/channel/list">http://27.154.234.10:8888/video-server/channel/list</a>
		</dd>
		<dt>频道搜索条件：</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/channel/videoType">http://27.154.234.10:8888/video-server/channel/videoType</a>
		</dd>
		<dt>3.视频详细信息：</dt>
		<dd>
			<a
				href="http://27.154.234.10:8888/video-server/detail/video?videoId=1">http://27.154.234.10:8888/video-server/detail/video?videoid={videoid}</a>
			{id为具体的视频id}
		</dd>
		<dt>评论列表：</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/detail/commentList">http://27.154.234.10:8888/video-server/detail/commentList</a>
		</dd>
		<dt>4.搜索页面热词：</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/search/hotWord">http://27.154.234.10:8888/video-server/search/hotWord</a>
		</dd>
		<dt>搜索</dt>
		<dd>
			<a
				href="http://27.154.234.10:8888/video-server/search/video?keyword=蓝色情人节?typeID=1?offset=1?fetchSize=10">http://27.154.234.10:8888/video-server/search/video?keyword={keyword}?typeID={typeId}?offset={offset}?fetchSize={fetchSize}</a>
		</dd>
		<dd>关键词：keyword是必须的，typeID（类型编号）不是必要，默认为1；offset(开始行数)，默认0，不是必要；fetchSize(每页记录数)，不是必要，默认10;</dd>
		<dd>PS：{搜索“蓝色情人节”有返回结果，没有就返回猜您喜欢}</dd>
		<dt>5.业界资讯</dt>
		<dd>
			<a href="http://27.154.234.10:8888/video-server/news/list">http://27.154.234.10:8888/video-server/news/list</a>
		</dd>
		<dt>收藏</dt>
		<dd>
			<a href="">http://27.154.234.10:8888/video-server/person/collection/add</a>
		</dd>
		<dd>POST参数：personId videoId</dd>
		<dt>取消收藏</dt>
		<dd>http://27.154.234.10:8888/video-server/person/collection/del</dd>
		<dd>POST参数： collectionId 收藏id号</dd>
		<dt>收藏列表</dt>
		<dd>
			http://27.154.234.10:8888/video-server/person/collection/del?personId={personId}</dd>
		<dd>get参数 personId 用户id</dd>
	</dl>
</body>
</html>