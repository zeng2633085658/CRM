<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	session.removeAttribute("user");
	session.invalidate();
%>
<script type="text/javascript">
	top.location.href="login.jsp";
</script>
