package domain.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.rdg.BenchCardRDG;
import data.rdg.ChallengeRDG;
import data.rdg.DeckCardRDG;
import data.rdg.GameRDG;
import data.rdg.HandCardRDG;
import data.rdg.UserRDG;

public class Board {
	
	private int gameId;
	
	private int[] players;
	
	private int[] decks;
	
	private Map<String, State> play;
	
	public Board(int gameId) {
		this.gameId = gameId;
		play = new HashMap();
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int[] getPlayers() {
		return players;
	}

	public void setPlayers(int[] players) {
		this.players = players;
	}

	public int[] getDecks() {
		return decks;
	}

	public void setDecks(int[] decks) {
		this.decks = decks;
	}

	public Map<String, State> getPlay() {
		return play;
	}

	public void setPlay(Map<String, State> play) {
		this.play = play;
	}
	
	public void fillBoardData(int challengeId) throws SQLException {
		ChallengeRDG ch = ChallengeRDG.find(this.gameId);
		int challengerId = ch.getChallenger();
		int challengeeId = ch.getChallengee();
		this.players = new int[] {challengerId , challengeeId};
		this.decks = new int[] {challengerId , challengeeId};
		
		
		State s1 = new State();
		int handSize1 = this.findHandSize(challengerId);
		s1.setHandSize(handSize1);
		int deckSize1 = this.findDeckSize(challengerId);
		s1.setDeckSize(deckSize1);
		s1.setDiscardSize(40 - deckSize1 - handSize1);
		s1.setStatus(findPlayerStatus(challengerId));
		s1.setBench(findBenchCards(challengerId));
		play.put(challengerId+"", s1);
		
		State s2 = new State();
		int handSize2 = this.findHandSize(challengeeId);
		s2.setHandSize(handSize2);
		int deckSize2 = this.findDeckSize(challengeeId);
		s2.setDeckSize(deckSize2);
		s2.setDiscardSize(40 - deckSize2 - handSize2);
		s2.setStatus(findPlayerStatus(challengeeId));
		s2.setBench(findBenchCards(challengeeId));
		play.put(challengeeId+"", s2);
	}
	
	private String findPlayerStatus(int playerId) {
		UserRDG u = null;
		try {
			u = UserRDG.find(playerId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u.getStatus();
	}
	
	private int findHandSize(int playerId) throws SQLException {
			return HandCardRDG.handSize(playerId);
	}
	
	private int findDeckSize(int playerId) throws SQLException {
			return DeckCardRDG.deckSize(playerId);
	}
	
	private int[] findBenchCards(int playerId) throws SQLException {
		ArrayList<BenchCardRDG> cards = BenchCardRDG.viewBench(playerId);
		int[] cardsId = new int[cards.size()];
		for(int i = 0; i < cards.size(); i++) {
			cardsId[i] = cards.get(0).getSequenceId();
		}
		return cardsId;
	}
	
	public boolean isMyGame(int playerId) throws SQLException {
		GameRDG g = GameRDG.find(this.gameId);
		int[] players = g.getPlayers();
		if(playerId == players[0] || playerId == players[1]) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public HandCardRDG drawCard(int playerId) throws SQLException {
		List<DeckCardRDG> deckCards = DeckCardRDG.viewDeck(playerId);
		DeckCardRDG c = deckCards.get(0);
		HandCardRDG h = new HandCardRDG();
		h.setHandId(c.getDeckId());
		h.setSequenceId(c.getSequenceId());
		h.setType(c.getType());
		h.setName(c.getName());
		c.delete();
		h.insert();
		return h;
	}
	
	public boolean playCardToBench(int playerId, int cardId) throws SQLException {
		HandCardRDG c = HandCardRDG.find(playerId, cardId);
		if(c == null) {
			return false;
		}else {
			BenchCardRDG h = new BenchCardRDG();
			h.setHandId(c.getHandId());
			h.setSequenceId(c.getSequenceId());
			h.setType(c.getType());
			h.setName(c.getName());
			c.delete();
			h.insert();
			return true;
		}
	}

}
