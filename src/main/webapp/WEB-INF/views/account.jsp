<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <html>
<head>
<title>Spring MVC表单处理</title>
</head>
<body>
	<h2>Student Information</h2>
	<form:form method="POST" action="addAccount.do">
		<table>
			<tr>
				<td><form:label path="name">名字：</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="age">年龄：</form:label></td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td><form:label path="id">编号：</form:label></td>
				<td><form:input path="id" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交表单" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html> --%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>
<link rel="stylesheet" href="/js/layui-v2.2.45/layui/css/layui.css">
<style type="text/css">
/* .layui-btn {
          width: 120px;
        } */
.layui-upload-img {
	height: 100px;
	width: 100%;
}

.layui-upload {
	right: 20px;
	/* width: 100px; */
	/* margin-right: 0px; */
	position: absolute;
	width: 100px;
}

.layui-upload-list {
	margin: 0px 0px 10px 0px;
}

#btn-upload {
	width: 100%;
}

#userID, #userName {
	width: 514px;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div id="app" style="height: 100%; width: 100%;">
			<blockquote class="layui-elem-quote layui-text">
				Take care of your infomation！ <a
					href="Mailto:huangchao911@aliyun.com?Subject=Hello&Body=Hello"
					target="_blank">Any problem, contact jacky please？</a>
			</blockquote>

			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 20px;">
				<legend>Personal Infomation</legend>
			</fieldset>
			<form:form class="layui-form" action="addAccount.do" method="post">
				<form:input path="name" type="hidden" value="test" />
				<form:input path="id" type="hidden" value="1" />
				<form:input path="age" type="hidden" value="2" />
				<div class="layui-upload">
					<div class="layui-upload-list">
						<img class="layui-upload-img" name="imgdemo" id="imgdemo"
							src="/images/logoV1.png"> <input type="hidden" name="image"
							id="image">
					</div>
					<button type="button" class="layui-btn" id="btn-upload">Image</button>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">User ID</label>
					<div class="layui-input-block">
						<input type="text" name="userID" id="userID" value="{{ id }}"
							readonly="readonly" autocomplete="off" placeholder=""
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item" style="width: 514px;">
					<label class="layui-form-label">User Name</label>
					<div class="layui-input-block">
						<input type="text" name="userName" id="userName" value="abc"
							lay-verify="required" placeholder="" autocomplete="off"
							class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">Email</label>
						<div class="layui-input-inline">
							<input type="text" name="email" value="XX@QQ.COM"
								lay-verify="required|email" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">Telphone</label>
						<div class="layui-input-inline">
							<input type="tel" name="telphone" value="13111111111"
								lay-verify="required" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">Country</label>
					<div class="layui-input-block">
						<select name="country" id="country" value="{{ country }}"
							lay-filter="country" lay-verify="required" lay-search="">
							<option value="">Select your country</option>
							<option value="China"{% ifequalcountry 'China' %} selected {% endifequal %}>China</option>
							<option value="America"{% ifequalcountry 'America' %} selected {% endifequal %}>America</option>
						</select>
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">Address</label>
					<div class="layui-input-block">
						<input type="text" name="address" value="Chengdu" placeholder=""
							autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">Activated</label>
					<div class="layui-input-block">
						<input type="checkbox" name="isActivated" id="isActivated"
							lay-filter="isActivated" lay-skin="switch" lay-text="YES|NO"{% ifequalis_activeTrue %} checked {% endifequal %}>
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">Role</label>
					<div class="layui-input-block">
						<c:forEach items="${groups}" var="item">
							<input type="checkbox" style="background-color: #5FB878;"
								name="groupID" title="${item.name}" lay-filter="roleSelected"
								checked>
						</c:forEach>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">Remark</label>
					<div class="layui-input-block">
						<textarea placeholder="" name="remark" class="layui-textarea">{{ remark }}</textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" type="button"
							lay-filter="formDemo">AJAX</button>
						<button class="layui-btn" lay-submit="" type="submit">SUBMIT</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<script src="/js/layui-v2.2.45/layui/layui.js" charset="utf-8"></script>
	<script src="/js/jquery-1.8.3.min.js" charset="utf-8"></script>
	<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">Push</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">Edit</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">Delete</a>
</script>
	<script>
		$(function() {
			$.ajaxSetup({
				headers : {
					"X-CSRFToken" : getCookie("csrftoken")
				}
			});
		});

		function getCookie(name) {
			var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
			if (arr = document.cookie.match(reg))
				return unescape(arr[2]);
			else
				return null;
		}

		var vm = this;
		vm.datasource = {};
		// form表单及事件初始化
		layui.use([ "element", "upload", "form" ], function() {
			// layui自带的jquery
			// var $ = layui.jquery;
			var element = layui.element;
			var upload = layui.upload;
			var form = layui.form;

			var API_SERVER = "127.0.0.1";
			var token = window.localStorage.getItem("token");
			// 普通图片上传
			upload.render({
				// headers: {
				//     Authorization: "Token " + token
				// },
				elem : "#btn-upload",
				url : "/upload/",
				before : function(obj) {
					// 预读本地文件示例，不支持ie8
					obj.preview(function(index, file, result) {
						// imgBytes = result;
					});
				},
				done : function(res, index, upload) {
					// 如果上传失败
					if (res.code !== "000") {
						return layer.msg("上传失败");
					}

					vm.datasource.image = res.data;
					// 上传成功
					$("#imgdemo").attr("src", res.data);
				},
				error : function(index, upload) {
					layer.msg("上传失败！");
				}
			});

			// select、checkbox、radio、date类型都通过监听方式赋值
			// 监听checkbox开关
			$("input[name='isActivated']").removeAttr("checked");
			form.on("switch(isActivated)", function(obj) {
				//vm.datasource.isActivated = obj.elem.checked;
				$(obj.elem).removeAttr("checked")
			});

			// 取消checked属性，让其不能修改（小窍门？？？为什么会这样？？？）
			$("input[name='groupID']").removeAttr("checked");
			form.on("checkbox(roleSelected)", function(obj) {
				$(obj.elem).removeAttr("checked")
				//obj.elem.checked=!obj.elem.checked;
				var listRole = [];
				// if (vm.datasource.userRoles) {
				//     listRole = vm.datasource.userRoles.split(",");
				// }
				//
				// // 删除已存在但正在取消的元素
				// $.each(listRole, function (index, item) {
				//     if (item === obj.elem.name && !obj.elem.checked) {
				//         listRole.splice(index, 1);
				//     }
				// });

				// // 添加不存在但正在选定的元素
				// if ($.inArray(obj.elem.name, listRole) === -1 && obj.elem.checked) {
				//     listRole.push(obj.elem.name);
				// }
				//
				// // 重新赋值
				// //vm.datasource.userRoles = "";
				// $.each(listRole, function (index, item) {
				//     // vm.datasource.userRoles +=
				//     //     vm.datasource.userRoles === "" ? item : "," + item;
				// });

				//console.log(vm.datasource.userRoles);
			});

			// 监听select列表
			form.on("select(country)", function(obj) {
				//vm.datasource.country = obj.value;
			});

			// 监听提交
			form.on("submit(formDemo)", function(data) {
				if (vm.datasource.optType === "add") {
					// nothing todo
				} else {

				}

				return false;
			});
		});
	</script>
</body>
</html>