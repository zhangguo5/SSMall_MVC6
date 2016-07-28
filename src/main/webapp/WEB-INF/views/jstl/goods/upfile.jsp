<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link href="<c:url value="/styles/main.css" />" type="text/css"
	rel="stylesheet" />
<title>上传图片</title>
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>上传图片</span>
		</h2>
		<form action="<c:url value="/goods/upPictureSave/${entity.id}" />" method="post"
			enctype="multipart/form-data">
			<fieldset>
				<legend>商品</legend>
				<p>
					<label for="name">商品名称：</label> ${entity.name}
				</p>
				<p>
					<label for="price">商品价格：</label>${entity.price}
				</p>
				<p>
					<label for="title">商品图片：</label> <input type="file" name="picFile" />
				</p>

				<p>
					<input type="submit" value="上传" class="btn out">
				</p>
			</fieldset>
			</form>
			<p style="color: red">${message}</p>
			<p>
				<a href="<c:url value="/goods/list" />" class="abtn out">返回列表</a>
			</p>
	</div>
</body>
</html>