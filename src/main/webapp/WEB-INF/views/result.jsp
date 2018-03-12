<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC表单处理</title>
</head>
<body>
	<h2>Result Information</h2>
	<!-- 使用Spring的form标签主要有两个作用，第一是它会自动的绑定来自Model中的一个属性值到当前form对应的实体对象，默认是command属性(一般都用modelAttribute,两者本质上没有区别)，这样我们就可以在form表单体里面方便的使用该对象的属性了；第二是它支持我们在提交表单的时候使用除GET和POST之外的其他方法进行提交，包括DELETE和PUT等。 -->
	<form:form method="POST" action="addAccount.do" modelAttribute="user">
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
</html>