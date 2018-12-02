<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
<json:object name="game">
	<json:property name = "id" value="${id}"/>
  <json:array name="players" items="${players}"/>
  <json:array name="decks" items="${decks}"/>
  <json:object name ="play">
      <json:object name ="${player1}">
      <json:property name = "status" value="${s1.status}"/>
      <json:property name = "handsize" value="${s1.handSize}"/>
      <json:property name = "decksize" value="${s1.deckSize}"/>
      <json:property name = "discardsize" value="${s1.discardSize}"/>
       <json:array name="bench" items="${si.bench}"/>
  </json:object>
  <json:object name ="${player2}">
      <json:property name = "status" value="${s2.status}"/>
      <json:property name = "handsize" value="${s2.handSize}"/>
      <json:property name = "decksize" value="${s2.deckSize}"/>
      <json:property name = "discardsize" value="${s2.discardSize}"/>
       <json:array name="bench" items="${s2.bench}"/>
   </json:object>
  </json:object>
  </json:object>
</json:object>