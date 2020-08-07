package pl.kwi.springboot.services.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.db.repositories.DeckRepository;
import pl.kwi.springboot.services.intf.DeckService;

@Service
public class DeckServiceImpl implements DeckService {
	
	
	@Autowired
	private DeckRepository deckRepository;
	
	
	@Override
	public DeckEntity save(DeckEntity deck) {
		deck.setModificationTimestamp(Calendar.getInstance().getTime());
		return deckRepository.save(deck);
	}
	
	@Override
	public Long getMaxId() {
		return deckRepository.getMaxId();
	}
	
	@Override
	public Page<DeckEntity> findLastDecks(Pageable pageable) {
		return deckRepository.findLastDecks(pageable);
	}
	

}
