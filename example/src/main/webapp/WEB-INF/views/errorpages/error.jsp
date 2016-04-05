<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>错误界面</title>
	</head>
	
	<script type="text/javascript">
	</script>  
	<body>
		<form name="form" method="post">
			错误信息：${exp.message}
		</form>
	</body>
</html>
