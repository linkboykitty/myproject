<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>移动商城管理 | 首页</title>
    <jsp:include page="/WEB-INF/views/includes/header.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <sys:nav />
    <sys:menu />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                首页<small id="small">来自移动端</small>
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

        </section>
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="/WEB-INF/views/includes/copyright.jsp" />
</div>

<jsp:include page="/WEB-INF/views/includes/mobile/footer.jsp" />
<script>
    $(function () {
       var isWeChat = UserAgent.isWeChat();
       if (isWeChat) {
           // 调用微信内置 JS 相关操作...
           $("#small").html("来自微信内置浏览器");
       }
    });
</script>
</body>
</html>
