<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="message" type="java.lang.String" required="true" description="提示信息" %>

<c:if test="${message != null && message.contains('成功')}">
    <div class="alert alert-success alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${message}
    </div>
</c:if>

<c:if test="${message != null && message.contains('失败')}">
    <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${message}
    </div>
</c:if>