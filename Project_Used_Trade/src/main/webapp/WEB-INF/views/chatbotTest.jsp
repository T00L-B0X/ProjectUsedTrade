<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "./include/header.jsp" %>

<h1> chatbotTest (챗봇 테스트) </h1>

<script>
    var ht = null;
    (function(id, scriptSrc, callback) {
        var d = document,
            tagName = 'script',
            $script = d.createElement(tagName),
            $element = d.getElementsByTagName(tagName)[0];

        $script.id = id;
        $script.async = true;
        $script.src = scriptSrc;

        if (callback) { $script.addEventListener('load', function (e) { callback(null, e); }, false); }
        $element.parentNode.insertBefore($script, $element);
    })('happytalkSDK', 'https://chat-static.happytalkio.com/sdk/happytalk.chat.v2.min.js', function() {
        ht = new Happytalk({
          siteId: '1000425764',
          siteName: '중고거래서비스',
          categoryId: '180394',
          divisionId: '180395'
      });
    });
</script>

<%@ include file = "./include/footer.jsp" %>