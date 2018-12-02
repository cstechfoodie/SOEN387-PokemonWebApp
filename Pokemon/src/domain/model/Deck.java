package domain.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.rdg.DeckCardRDG;

public class Deck {
	
	private int id;
	private List<DeckCardRDG> cards;
	
	public Deck() {
		cards = new ArrayList<DeckCardRDG>();
	}
	
	public Deck(int id) {
		this.id = id;
		cards = new ArrayList<DeckCardRDG>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<DeckCardRDG> getCards() {
		return cards;
	}

	public void setCards(ArrayList<DeckCardRDG> cards) {
		this.cards = cards;
	}
	
	
	//initialize a deck with 
	public boolean uploadDeck() {
		this.cards.forEach(i -> {
			try {
				i.insert();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return true;
	}
	
	public static ArrayList<DeckCardRDG> viewDeck(int deckId) {
		ArrayList<DeckCardRDG> cards = null;
		try {
			cards = DeckCardRDG.viewDeck(deckId);
		} catch (SQLException e) {
			System.out.println("viewDeck method error from Deck Class");
			e.printStackTrace();
		}
		return cards;
	}
	
	public ArrayList<DeckCardRDG> viewDeck() {
		try {
			this.cards = DeckCardRDG.viewDeck(this.id);
		} catch (SQLException e) {
			System.out.println("viewDeck method error from Deck Class");
			e.printStackTrace();
		}
		return (ArrayList<DeckCardRDG>) this.cards;
	}
	
	public static ArrayList<Integer> getDeckIds() {
		try {
			return DeckCardRDG.viewDecks();
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ArrayList<Integer> getDeckIdsByPlayer(int playerId) {
		try {
			return DeckCardRDG.viewDecksByPlayer(playerId);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static boolean isMyOwnDeck(int playerId, int deckId) {
		ArrayList<Integer> deckIds = getDeckIdsByPlayer(playerId);
		for(int i = 0; i < deckIds.size(); i++) {
			if(deckIds.get(i) == deckId) {
				return true;
			}
		}
		return false;
	}
	

}
