<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>p-admin</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" href="/js/layui-v2.2.45/layui/css/layui.css" media="all">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">J-Mvc System</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<c:forEach var="menu" items="${menu_nav}" varStatus="status">
					<li class="layui-nav-item ${menu.code==prnt_nav?'layui-this':'' }">
						<a href="${menu.url}">${menu.title}</a>
						<c:if test="${fn:length(menu.child)>0}">
							<dl class="layui-nav-child">
								<c:forEach var="child" items="${menu.child}" varStatus="status1">
									<dd class="${child.code==curr_nav?'layui-this':'' }">
										<a href="${child.url}">${child.title}</a>
									</dd>
								</c:forEach>
							</dl>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href="javascript:;">
						<img src="/images/logoV1.png" class="layui-nav-img">
						admin
					</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="me/" target="right_frame">About Me</a>
						</dd>
						<dd>
							<a href="javascript:;" id="modifypass">Change Password</a>
						</dd>
						<dd>
							<a href="javascript:;" id="download">Download Center</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<form id="_form" action="/auth/logout" method="post">

						<!-- 当开启了csrf时，需要用post方法且传入csrf.token才能正确注销 -->
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
					<a href="#" onclick="document.getElementById('_form').submit();">Exit</a>
				</li>
			</ul>
		</div>


		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">

				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<c:forEach var="menu" items="${menu_side}" varStatus="status">
						<li class="layui-nav-item ${status.index==0?'layui-nav-itemed':''}">
							<a href="${menu.url}">${menu.title}</a>
							<dl class="layui-nav-child">
								<c:forEach var="child" items="${menu.child}" varStatus="status1">
									<dd class="${status.index==0&&status1.index==0?'layui-this':''}">
										<a href="${child.url}" target="right_frame">${child.title}</a>
									</dd>
								</c:forEach>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!--内容主体区域-->
			<div style="padding: 0px; height: 100%; overflow: hidden;">

				<iframe name="right_frame" src="${menu_side[0].child[0].url}" frameborder="0" height="100%" width="100%"></iframe>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© Jacky Huang - huangchao911@aliyun.com
		</div>
	</div>
	<script src="/js/layui-v2.2.45/layui/layui.js" charset="utf-8"></script>
	<script src="/js/md5.js" charset="utf-8"></script>
	<script type="text/javascript">
		//JavaScript代码区域
		//JavaScript代码区域
		layui.use([ 'element', 'layer' ], function() {
			var element = layui.element;
			var $ = layui.jquery;
			var layer = layui.layer;

			$("#modifypass").click(function() {

				var vm = this;
				layer.prompt({
					title : "Current Password:",
					formType : 1
				}, function(pass, index) {
					var token = window.localStorage.getItem("token");

					// 获取用户
					$.ajax({
						type : "get",
						url : "excute/",
						data : {
							username : "admin",
						},
						dataType : "json",
						success : function(result) {

							if (result.code !== "000") {
								layer.msg(result.msg);
							} else {
								// 比较原密码
								if (result.data.password == hex_md5(pass)) {
									layer.close(index);
									// 重新设置新密码
									layer.prompt({
										title : "Your New Password:",
										formType : 1
									}, function(newPass, index) {
										layer.close(index);
										$.ajax({
											type : "put",
											url : "excute/",
											data : {
												username : "admin",
												password : newPass
											},
											dataType : "json",
											success : function(result) {
												layer.msg(result.msg);
											}
										});
									});
								} else {
									layer.msg("error password!")
								}
							}
						}
					});
				});
			});

			$("#download").click(function() {
				layer.open({
					type : 2,
					title : 'Download Center',
					shadeClose : true,
					shade : false,
					maxmin : false, //开启最大化最小化按钮
					area : [ '600px', '390px' ],
					content : [ 'download/', 'no' ]
				});
			});
		});
	</script>
</body>
</html>