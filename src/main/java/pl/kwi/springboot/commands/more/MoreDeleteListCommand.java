package pl.kwi.springboot.commands.more;

import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.pagination.checkboxPagination.commands.AbstrCheckboxPagiantionCommand;

public class MoreDeleteListCommand extends AbstrCheckboxPagiantionCommand {
	
	
	private Iterable<DeckEntity> decks;

	
	public Iterable<DeckEntity> getDecks() {
		return decks;
	}
	public void setDecks(Iterable<DeckEntity> decks) {
		this.decks = decks;
	}
	

}
