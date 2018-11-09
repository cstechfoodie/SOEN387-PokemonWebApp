package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.connection.DbConnectionManager;
import domain.service.SingleAppUniqueIdFactory;

public class CardRDG {
	private int deckId;
	private int sequenceId;
	private int cardtypeId;

	public int getDeckId() {
		return deckId;
	}


	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}


	public int getSequenceId() {
		return sequenceId;
	}


	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}



	public int getCardtypeId() {
		return cardtypeId;
	}


	public void setCardtypeId(int cardtypeId) {
		this.cardtypeId = cardtypeId;
	}
	
	public CardRDG() {};

	public CardRDG(int deckId, int sequenceId, int cardtypeId) {
		this.deckId = deckId;
		this.sequenceId = sequenceId;
		this.cardtypeId = cardtypeId;
	}
	
	
	public int insert() throws SQLException {
		String sql = "INSERT INTO CARD (deckId, sequenceId, cardtypeId) VALUES (?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.deckId);
		ps.setInt(2, this.sequenceId);
		ps.setInt(3, this.cardtypeId);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
	
	public int delete() throws SQLException {
		String sql = "DELETE FROM CARD WHERE id = '" + this.deckId + "' AND sequenceId = '" + this.sequenceId + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
	
	//upload a new deck with a unique deckId and return the deckId to deck object
	public static int uploadDeck() throws SQLException{
		int deckId = SingleAppUniqueIdFactory.getMaxId("CARD", "deckId");		
		CardRDG card = new CardRDG();
		for(int i = 1; i <= 40; i++) {
			card = new CardRDG();
			card.setDeckId(deckId);
			card.setSequenceId(i);
			card.setCardtypeId((int) (Math.floor(Math.random()*3))+1);
			card.insert();
		}
		return deckId;	
	}
	
	//return a list of cards associate with the deckId
	public static List<CardRDG> viewDeck(int deckId) throws SQLException{
		ArrayList<CardRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM CARD WHERE deckId = '" + deckId + "';";
		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
		CardRDG card = null;
		while (res.next()) {
			card = new CardRDG();
			card.setDeckId(res.getInt("deckId"));
			card.setSequenceId(res.getInt("sequenceId"));
			card.setCardtypeId(res.getInt("cardtypeId"));
			list.add(card);
		}
		res.close();
		return list;
	}
}
