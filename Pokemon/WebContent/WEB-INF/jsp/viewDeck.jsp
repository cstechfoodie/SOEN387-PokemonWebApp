<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
<%-- <json:object name="deck">
<json:property name="id" value="${id}"/> --%>
  <json:array name="cards" var="item" items="${cards}">
    <json:object>
      <json:property name="t" value="${item.type}"/>
      <json:property name="n" value="${item.name}"/>
      <json:property name="b" value="${item.basic}"/>
    </json:object>
  </json:array>
<%--   </json:object> --%>
</json:object>