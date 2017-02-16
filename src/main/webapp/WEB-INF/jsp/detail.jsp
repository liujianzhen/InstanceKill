<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>秒杀详情页</title>
<%@include file="common/head.jsp"%>
</head>

<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>${instanceKill.name}</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<!-- 显示time图标 -->
					<span class="glyphicon glyphicon-time"></span>
					<!-- 展示倒计时 -->
					<span class="glyphicon" id="instanceKill-box"></span>
				</h2>
			</div>
		</div>
	</div>
	<!-- 登录弹出层 -->
	<div id="instanceKillPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>
					</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="instanceKillPhone"
								id="instanceKillPhoneKey" placeholder="填写手机号^o^"
								class="form-control">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- 验证信息 -->
					<span id="instanceKillPhoneMessage" class="glyphicon"></span>
					<button type="button" id="instanceKillPhoneBtn"
						class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span> submit
					</button>
				</div>
			</div>
		</div>
	</div>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- 使用CDN获取公共js http:www.bootcdn.cn -->
	<!-- jquery cookie操作插件 -->
	<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	<!-- jquery countdown倒计时插件 -->
	<script src="//cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
	<!-- 开始编写交互逻辑 -->
	<script type="text/javascript" src="/resource/script/instanceKill.js"></script>
	<script type="text/javascript">
		$(function() {
			//使用EL表达式传入参数
			instanceKill.detail.init({
				instanceKillId : ${instanceKill.instanceKillId},
				startTime : ${instanceKill.startTime.time},//毫秒
				endTime : ${instanceKill.endTime.time}
			})
		})
	</script>
</body>
</html>
