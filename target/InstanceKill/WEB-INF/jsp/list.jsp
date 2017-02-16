<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>秒杀列表页</title>
    <%@include file="common/head.jsp" %>
  </head>
  
  <body>
    <div class="container">
    	<div class="panel panel-default text-center">
        <div class="panel-heading">
          <h2>秒杀列表</h2>
        </div>
        <div class="panel-body">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>名称</th>
                <th>库存</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>创建时间</th>
                <th>详情页</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="IK" items="${ list}">
              	<tr>
              		<td>${IK.name}</td>
              		<td>${IK.number }</td>
              		<td>
              			<fmt:formatDate value="${IK.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
              		</td>
              		<td>
              			<fmt:formatDate value="${IK.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
              		</td>
              		<td>
              			<fmt:formatDate value="${IK.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
              		</td>
              		<td>
              			<a class="btn btn-info" href="/instanceKill/${IK.instanceKillId}/detail" target="_blank">link</a>
              		</td>
              	</tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>
