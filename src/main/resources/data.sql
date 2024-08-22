-- study_card 데이터 초기화
insert into study_card (id, korean_title, russian_title, korean_description, russian_description) values
(1, '마트 장보기', 'покупки в супермаркете', '나는 저녁 재료를 사러 슈퍼마켓에 갔다.', 'Я пошел в супермаркет, чтобы купить ингредиенты для ужина.');
insert into study_card (id, korean_title, russian_title, korean_description, russian_description) values
(2, '119 전화하기', 'Позвоните 119', '사람이 넘어졌다. 119에 전화해 보세요.', 'Человек упал. Попробуйте позвонить 119.');
insert into study_card (id, korean_title, russian_title, korean_description, russian_description) values
(3, '분식집에서 주문하기', 'Заказ из закусочной', '친구들과 함께 분식집에 갔어요. 메뉴 주문을 해보세요.', 'Я пошел в закусочную с друзьями. Заказать меню.');

--  word 데이터 초기화
insert into word (id, korean_word, russian_word, audio_url, study_card_id) values
(1, '긴급(긴급상황)', 'Чрезвычайная ситуация', 'https://papago.naver.com/apis/tts/c_lt_kyuri_2.2.29.0.3.32_197-nvoice_kyuri_2.2.29.0.3.32_247f69938df7abb5bcb7197b53acbd99-1724339774505', 2);
insert into word (id, korean_word, russian_word, audio_url, study_card_id) values
(2, '의식(의식이 없다)', 'Сознание (без сознания)', 'https://papago.naver.com/apis/tts/c_lt_kyuri_2.2.29.0.3.32_261-nvoice_kyuri_2.2.29.0.3.32_2f75b48223f48f53e80ac38f023b0715-1724339809766', 2);

-- dialogue 데이터 초기화
insert into dialogue (id, korean_sentence, russian_sentence, audio_url, study_card_id) values
(1, '119입니다. 무엇을 도와드릴까요?', '— 119 с. Чем вам помочь?', 'https://papago.naver.com/apis/tts/c_lt_kyuri_2.2.29.0.3.32_206-nvoice_kyuri_2.2.29.0.3.32_bd8cba2021f795f840db7d33ca24f34a-1724339849637', 2);
insert into dialogue (id, korean_sentence, russian_sentence, audio_url, study_card_id) values
(2, '우리 집에서 불이 났어요!', 'В нашем доме начался пожар!', 'https://papago.naver.com/apis/tts/c_lt_kyuri_2.2.29.0.3.32_414-nvoice_kyuri_2.2.29.0.3.32_c7017673dc70e752c53259ea57995118-1724339877622', 2);