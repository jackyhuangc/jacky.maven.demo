<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Monitor Log</title>
<link rel="stylesheet" href="/js/layui-v2.2.45/layui/css/layui.css">
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div id="app" style="height: 100%; width: 100%;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
				<legend>INPUT CONDITION</legend>
			</fieldset>
			<div class="layui-form">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">Start Time</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="bgtm" v-model="bgtm" placeholder="">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">End Time</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="edtm" v-model="edtm" placeholder="">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">Log Content</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="content" v-model="content" @keyup="enterKeyup($event)">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn" onclick="btnClick();">
							<i class="layui-icon">&#xe615;</i>
							SEARCH
						</button>
					</div>
				</div>
			</div>
			<table class="layui-hide" id="table" lay-filter="tableEvent"></table>
			<div id="pager"></div>
		</div>
	</div>
	<script src="/js/layui-v2.2.45/layui/layui.js" charset="utf-8"></script>
	<script src="/js/jquery-1.8.3.min.js" charset="utf-8"></script>
	<script>
		$(function() {
			initPage();

			$('input').keydown(function(ev) {
				if (ev.keyCode == 13) {
					btnClick();
				}
			});
		});

		var btnClick = function() {
			initPage();
		};

		var initPage = function() {
			var vm = this;
			vm.count = -1;
			vm.curr = 1;

			layui.use([ "layer", "table", "laypage", "laydate" ], function() {
				vm.layer = layui.layer;
				vm.table = layui.table;
				var laypage = layui.laypage;
				var laydate = layui.laydate;

				// 墨绿主题
				laydate.render({
					elem : "#bgtm",
					theme : "molv",
					type : "datetime",
					done : function(value, date, endDate) {
						//vm.bgtm = value;
					}
				});

				laydate.render({
					elem : "#edtm",
					theme : "molv",
					type : "datetime",
					done : function(value, date, endDate) {
						//vm.edtm = value;
					}
				});

				var token = window.localStorage.getItem("token");
				$.ajax({
					// Token都是需要先登录后才获取到的，Token认证一般用于第三方软件，比如APP
					// headers: {
					//     Authorization: "Token " + token
					// },
					type : "get",
					url : "/monitor/log/?" + "bgtm=" + $("#bgtm").val()
							+ "&edtm=" + $("#edtm").val() + "&content="
							+ $("#content").val() + "&count=-1" + "&page=1"
							+ "&size=20",
					dataType : "json",
					success : function(result) {
						if (result.code === "000") {
							vm.count = result.extra;
							vm.limit = 10;

							// 完整功能
							laypage.render({
								elem : "pager",
								count : vm.count,
								limit : vm.limit,
								layout : [ "count", "prev", "page", "next",
										"limit" ],
								jump : function(obj) {
									vm.curr = obj.curr;
									vm.limit = obj.limit;
									if (result) {
										// 展示已知数据
										bindDataSource(result.data);
										result = null;
									} else {
										bindDataSource(null);
									}
								}
							});
						} else {
							layer.msg(result.msg);
						}
					},
					error : function(xhr, status, statusText) {
						if (xhr.status == 401) {
							// top.location.href = "/login/";
						}
					}
				});
			});
		}

		var bindDataSource = function(data) {
			var vm = this;
			if (data) {
				var cnt = 1;
				data.forEach(function(item) {
					item.ID = (vm.curr - 1) * vm.limit + cnt;
					cnt++;
				});
				vm.table.render({
					elem : "#table",
					cols : [ [
					// ID
					{
						field : "ID",
						title : "ID",
						sort : true,
						width : 100,
						event : "setSign"
					},
					// 标题栏
					{
						field : "userID",
						title : "User ID",
						sort : true,
						width : 150,
						event : "setSign"
					}, {
						field : "optModule",
						title : "Module",
						minWidth : 150,
						event : "setSign"
					}, {
						field : "optType",
						title : "Operation",
						minWidth : 100,
						event : "setSign"
					}, {
						field : "optContent",
						title : "Log Content",
						minWidth : 300,
						event : "setSign"
					}, {
						field : "optTime",
						title : "Opt Time",
						sort : true,
						width : 250,
						event : "setSign"
					}, {
						field : "remark",
						title : "Remark",
						width : 300,
						event : "setSign"
					} ] ],
					limit : vm.limit, // 每页显示的条数（默认：10）。值务必对应 limits 参数的选项。优先级低于 page 参数中的 limit 参数
					data : data,
					skin : "line", // 表格风格
					even : true
				});
			} else {
				var token = window.localStorage.getItem("token");
				$.ajax({
					// Token都是需要先登录后才获取到的，Token认证一般用于第三方软件，比如APP
					// headers: {
					//     Authorization: "Token " + token
					// },
					type : "get",
					url : "/monitor/log/?bgtm=" + $("#bgtm").val() + "&edtm="
							+ $("#edtm").val() + "&content="
							+ $("#content").val() + "&page=" + vm.curr
							+ "&size=" + vm.limit,
					dataType : "json",
					success : function(result) {
						if (result.code === "000") {
							bindDataSource(result.data);
						} else {
							layer.msg(result.msg);
						}
					}
				});
			}
		};
	</script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
