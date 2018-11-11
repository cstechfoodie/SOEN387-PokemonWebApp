<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
  <json:array name="games" var="item" items="${games}">
    <json:object>
      <json:property name="id" value="${item.id}"/>
      <json:array name="players" items="${item.players}"/>
    </json:object>
  </json:array>
</json:object>