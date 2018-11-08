package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import data.connection.DbConnectionManager;
import domain.service.SingleAppUniqueIdFactory;

public class DeckRDG {
	private int id;
	private int sequenceId;
	private int cardId;
	
	private List<CardRDG> cards;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSequenceId() {
		return sequenceId;
	}


	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}


	public int getCardId() {
		return cardId;
	}


	public void setCardId(int cardId) {
		this.cardId = cardId;
	}


	public DeckRDG(int id) {
		this.id = id;
		this.cardId = (int) (Math.floor(Math.random()*3))+1;
		try {
			sequenceId = SingleAppUniqueIdFactory.getMaxId("DECK", "sequenceId");
			if(sequenceId == 40) {
				SingleAppUniqueIdFactory.reset("DECK", "sequenceId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int insert() throws SQLException {
		String sql = "INSERT INTO Deck (id, sequenceId, cardId) VALUES (?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setInt(2, this.sequenceId);
		ps.setInt(3, this.cardId);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
	
	public int delete() throws SQLException {
		String sql = "DELETE FROM Deck WHERE id = '" + this.id + "' AND sequenceId = '" + this.sequenceId + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
	
	public static List<CardRDG> uploadDeck(){
		return null;	
	}
	
	public static List<CardRDG> viewDeck(int id){
		return null;
	}
	
	public List<CardRDG> viewDeck(){
		return null;
	}


	public List<CardRDG> getCards() {
		return cards;
	}


	public void setCards(List<CardRDG> cards) {
		this.cards = cards;
	}
	
	
}
