var Validator = function () {

    /**
     * 初始化验证规则
     */
    var handleInit = function () {
        // 手机号验证
        addMethodForRegex("mobile", /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/, "手机号码格式错误");
        // 邮政编码验证
        addMethodForRegex("zipCode", /^[0-9]{6}$/, "请正确填写您的邮政编码");

        /**
         * 自定义正则表达式验证
         * @param methodName
         * @param regexExpression
         * @param message
         */
        function addMethodForRegex(methodName, regexExpression, message) {
            $.validator.addMethod(methodName, function(value, element) {
                var regex =  regexExpression;
                return this.optional(element) || (regex.test(value));
            }, message);
        }
    };

    /**
     * 本地验证
     * @param formId
     */
    var handleValidate = function (formId) {
        $("#" + formId).validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
                element.parent().parent().attr("class", "form-group has-error");
            }
        });
    };

    return {
        init: function () {
            handleInit();
        },

        validate: function (formId) {
            handleValidate(formId);
        }
    }
}();

$(function () {
   Validator.init();
});