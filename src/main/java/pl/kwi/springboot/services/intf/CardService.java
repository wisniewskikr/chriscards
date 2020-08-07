package pl.kwi.springboot.services.intf;

import pl.kwi.springboot.db.entities.CardEntity;

public interface CardService {

	CardEntity save(CardEntity card);

}
