package domain.model;

public class State {
	private String status;
	
	private int handSize;
	
	private int deckSize;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getHandSize() {
		return handSize;
	}

	public void setHandSize(int handSize) {
		this.handSize = handSize;
	}

	public int getDeckSize() {
		return deckSize;
	}

	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}

	public int getDiscardSize() {
		return discardSize;
	}

	public void setDiscardSize(int discardSize) {
		this.discardSize = discardSize;
	}

	public int[] getBench() {
		return bench;
	}

	public void setBench(int[] bench) {
		this.bench = bench;
	}

	private int discardSize;
	
	private int[] bench;
}
