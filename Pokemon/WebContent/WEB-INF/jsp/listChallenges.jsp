<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
  <json:array name="challenges" var="item" items="${challenges}">
    <json:object>
      <json:property name="id" value="${item.id}"/>
      <json:property name="challenger" value="${item.challenger}"/>
      <json:property name="challengee" value="${item.challengee}"/>
      <json:property name="status" value="${item.status}"/>
    </json:object>
  </json:array>
</json:object>