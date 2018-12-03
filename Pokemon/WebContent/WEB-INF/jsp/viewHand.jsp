<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
<json:array name="hand" var="item" items="${hand}"/>
<%--   <json:array name="hand" var="item" items="${hand}">
    <json:object>
      <json:property name="handId" value="${item.handId}"/>
      <json:property name="sequenceId" value="${item.sequenceId}"/>
      <json:property name="type" value="${item.type}"/>
      <json:property name="name" value="${item.name}"/>
    </json:object>
  </json:array> --%>
</json:object>