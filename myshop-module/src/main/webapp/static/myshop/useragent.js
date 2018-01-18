/**
 * 用户代理工具
 */
var UserAgent = function () {
    var ua = window.navigator.userAgent.toLowerCase();

    /**
     * 判断是否是微信内置浏览器
     * @returns {boolean}
     */
    var handleIsWeChat = function () {
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            return true;
        } else {
            return false;
        }
    };

    return {
        isWeChat: function () {
            return handleIsWeChat();
        }
    }
}();