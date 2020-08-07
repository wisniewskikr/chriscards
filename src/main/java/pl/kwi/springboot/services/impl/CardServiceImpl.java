package pl.kwi.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.repositories.CardRepository;
import pl.kwi.springboot.services.intf.CardService;

@Service
public class CardServiceImpl implements CardService {
	
	
	@Autowired
	private CardRepository cardRepository;
	
	
	@Override
	public CardEntity save(CardEntity card) {
		return cardRepository.save(card);
	}

}
