insert into DECK (id, name) values (1,'Talia numer 1');
insert into DECK (id, name) values (2,'Talia numer 2');

insert into CARD (id, deck_id) values (1,1);
insert into CARD (id, deck_id) values (2,1);
insert into CARD (id, deck_id) values (3,2);
insert into CARD (id, deck_id) values (4,2);

insert into WORD (id, word, sentence, language, card_id) values (1,'Word 1 PL', 'Sentence 1 PL', 'POLISH', 1);
insert into WORD (id, word, sentence, language, card_id) values (2,'Word 1 EN', 'Sentence 1 EN', 'ENGLISH', 1);
insert into WORD (id, word, sentence, language, card_id) values (3,'Word 1 RU', 'Sentence 1 RU', 'RUSSIAN', 1);
insert into WORD (id, word, sentence, language, card_id) values (4,'Word 1 SP',  'Sentence 1 SP', 'SPAIN', 1);

insert into WORD (id, word, sentence, language, card_id) values (5,'Word 2 PL', 'Sentence 2 PL', 'POLISH', 2);
insert into WORD (id, word, sentence, language, card_id) values (6,'Word 2 EN', 'Sentence 2 EN', 'ENGLISH', 2);
insert into WORD (id, word, sentence, language, card_id) values (7,'Word 2 RU', 'Sentence 2 RU', 'RUSSIAN', 2);
insert into WORD (id, word, sentence, language, card_id) values (8,'Word 2 SP', 'Sentence 2 SP', 'SPAIN', 2);

insert into WORD (id, word, sentence, language, card_id) values (9,'Word 3 PL', 'Sentence 3 PL', 'POLISH', 3);
insert into WORD (id, word, sentence, language, card_id) values (10,'Word 3 EN', 'Sentence 3 EN', 'ENGLISH', 3);
insert into WORD (id, word, sentence, language, card_id) values (11,'Word 3 RU', 'Sentence 3 RU', 'RUSSIAN', 3);
insert into WORD (id, word, sentence, language, card_id) values (12,'Word 3 SP', 'Sentence 3 SP', 'SPAIN', 3);

insert into WORD (id, word, sentence, language, card_id) values (13,'Word 4 PL', 'Sentence 4 PL', 'POLISH', 4);
insert into WORD (id, word, sentence, language, card_id) values (14,'Word 4 EN', 'Sentence 4 EN', 'ENGLISH', 4);
insert into WORD (id, word, sentence, language, card_id) values (15,'Word 4 RU', 'Sentence 4 RU', 'RUSSIAN', 4);
insert into WORD (id, word, sentence, language, card_id) values (16,'Word 4 SP', 'Sentence 4 SP', 'SPAIN', 4);
