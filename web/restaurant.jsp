<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: JUSTIN
  Date: 2015/7/30
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>我排商家管理页面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>

<body>
<h4>订单列表</h4>
<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <td>ID</td>
        <td>订单号</td>
        <td>订餐时间</td>
        <td>订单状态</td>
        <td>桌子型号</td>
        <td>用户</td>
        <td>处理订单</td>
    </tr>
    <s:iterator value="#request.order">
        <tr>
            <td><s:property value="id"/></td>
            <td><s:property value="ordernumber"/> </td>
            <td><s:date name="date" format="yyyy-MM-dd hh:mm:ss"/> </td>
            <td><s:property value="state"/></td>
            <td><s:property value="desk"/></td>
            <td><s:property value="user.nickname"/></td>
            <td>
                <s:if test="state==0">
                     <a href="change.action?id=${id}">下一位</a>
                </s:if>
                <s:else>
                    下一位
                </s:else>
            </td>
        </tr>
    </s:iterator>
</table>
</body>
</html>

