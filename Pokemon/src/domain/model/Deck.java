package domain.model;

import java.sql.SQLException;
import java.util.List;

import data.rdg.CardRDG;

public class Deck {
	
	private int id;
	private List<CardRDG> cards;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CardRDG> getCards() {
		return cards;
	}

	public void setCards(List<CardRDG> cards) {
		this.cards = cards;
	}
	
	
	//initialize a deck with 
	public static Deck uploadDeck() {
		Deck deck = new Deck();
		try {
			deck.setId(CardRDG.uploadDeck());
		} catch (SQLException e) {
			System.out.println("uploadDeck method error from Deck Class");
			e.printStackTrace();
		}
		return deck;
	}
	
	public static List<CardRDG> viewDeck(int deckId) {
		List<CardRDG> cards = null;
		try {
			cards = CardRDG.viewDeck(deckId);
		} catch (SQLException e) {
			System.out.println("viewDeck method error from Deck Class");
			e.printStackTrace();
		}
		return cards;
	}
	
	public List<CardRDG> viewDeck() {
		try {
			this.cards = CardRDG.viewDeck(this.id);
		} catch (SQLException e) {
			System.out.println("viewDeck method error from Deck Class");
			e.printStackTrace();
		}
		return this.cards;
	}
	

}
