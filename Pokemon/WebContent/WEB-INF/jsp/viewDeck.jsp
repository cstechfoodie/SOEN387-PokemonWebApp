<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
<json:object name="deck">
  <json:property name="id" value="${d.getId()}"/>
  <json:array name="cards" var="item" items="${cards}">
    <json:object>
      <json:property name="t" value="${item.type}"/>
      <json:property name="n" value="${item.name}"/>
    </json:object>
  </json:array>
  </json:object>
</json:object>