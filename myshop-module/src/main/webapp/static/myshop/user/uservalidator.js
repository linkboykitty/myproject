var UserValidator = function () {

    var formId = "inputForm";

    /**
     * 用户信息验证
     */
    var handleValidate = function () {
        $("#" + formId).validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
                element.parent().parent().attr("class", "form-group has-error");
            },

            rules: {
                username: {
                    required: true,
                    remote: {
                        url: "/user/check",
                        type: "post",
                        dataType: "json",
                        data: {
                            username: function () {
                                return $("#username").val();
                            }
                        }
                    }
                },
                phone: {
                    required: true,
                    remote: {
                        url: "/user/check",
                        type: "post",
                        dataType: "json",
                        data: {
                            phone: function () {
                                return $("#phone").val();
                            }
                        }
                    }
                },
                email: {
                    required: true,
                    remote: {
                        url: "/user/check",
                        type: "post",
                        dataType: "json",
                        data: {
                            email: function () {
                                return $("#email").val();
                            }
                        }
                    }
                }
            },
            messages: {
                username: {
                    required: "请输入用户名",
                    remote: "用户名已存在，请重新输入"
                },
                phone: {
                    required: "请输入手机号",
                    remote: "手机号已存在，请重新输入"
                },
                email: {
                    required: "请输入邮箱",
                    remote: "邮箱已存在，请重新输入"
                }
            }
        });

        // 调用检测函数
        checkSuccess();
    };

    /**
     * 检查验证是否成功
     */
    var checkSuccess = function () {
        var inputs = $("#" + formId).find("input");
        $(inputs).each(function () {
           var type = $(this).attr("type");
           if (type === "text") {
               $(this).bind("blur", function () {
                   var errorSpan = $(this).parent().find("span")[0];
                   var errorHtml = $(errorSpan).html();
                   if (errorHtml != null && errorHtml.length == 0) {
                       $(this).parent().parent().attr("class", "form-group has-success");
                       $(errorSpan).remove();
                   }
               });
           }
        });
    };

    return {
        validate: function () {
            handleValidate();
        }
    }
}();