<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
  <json:array name="players" var="item" items="${players}">
    <json:object>
      <json:property name="id" value="${item.id}"/>
      <json:property name="user" value="${item.username}"/>
    </json:object>
  </json:array>
</json:object>