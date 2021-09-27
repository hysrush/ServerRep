<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>postForm</title>
</head>
<body>
	<form action="/postSubmit" method="post">
		<input type="text" name="param1" /><br/>
		<input type="text" name="param2" /><br/>
		<input type="submit" value="POST전송" /><br/>
	</form>
</body>
</html>