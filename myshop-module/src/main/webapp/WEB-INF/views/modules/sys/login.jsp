<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<!-- head begin -->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>商城管理 - 登录</title>

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/static/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/static/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<!-- ./head end -->

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="login.jsp"><b>商城管理</b></a>
    </div>

    <div class="login-box-body">
        <p class="login-box-msg">欢迎使用商城管理</p>

        <c:if test="${message != null}">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                ${message}
            </div>
        </c:if>

        <form action="/login" method="post">
            <div class="form-group has-feedback">
                <input id="loginId" name="loginId" type="text" class="form-control" placeholder="用户名" value="${dto.loginId}">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <input id="loginPwd" name="loginPwd" type="password" class="form-control" placeholder="密码" value="${dto.loginPwd}">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <div class="form-group">
                <input id="validateCode" name="validateCode" type="text" class="form-control pull-left" placeholder="验证码" style="width: 170px;">
                <img id="imgValidateCode" src="/validate/code" style="cursor: pointer;" alt="点击刷新验证码" title="点击刷新验证码" />
            </div>

            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input id="isRemember" name="isRemember" type="checkbox" ${"on" eq dto.isRemember ? "checked" : ""}> 记住我
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button id="btnLogin" type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <a href="#">忘记密码？</a>
    </div>
</div>

<div class="modal fade" id="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">温馨提示</h4>
            </div>
            <div class="modal-body">
                <p>用户名或密码不能为空，请重新输入</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">知道了</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- jQuery 3 -->
<script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="/static/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });

        // 绑定事件
        bindEvent();
    });

    // 绑定事件
    function bindEvent() {
        // 绑定登录按钮的点击事件
        $("#btnLogin").bind("click", function () {
            return checkLogin();
        });

        // 验证码切换
        $("#imgValidateCode").bind("click", function () {
            $(this).attr("src", "/validate/code?" + Math.random());
        });
    }

    // 检查登录
    function checkLogin() {
        var email = $("#loginId").val();
        var password = $("#loginPwd").val();

        if (email.length == 0 || password.length == 0) {
            $('#modal').modal("show");
            return false;
        }
    }
</script>
</body>
</html>