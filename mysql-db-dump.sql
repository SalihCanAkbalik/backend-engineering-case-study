CREATE TABLE IF NOT EXISTS Users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    user_level INTEGER NOT NULL,
    coins INTEGER NOT NULL,
    country VARCHAR(255) NOT NULL,
    enter_tournament BOOLEAN,
    already_entered BOOLEAN,
    is_get_last_tournament_reward BOOLEAN,
    group_scores INTEGER,
    tournament_id BIGINT,
    group_leaderboard_id BIGINT,
    CONSTRAINT fk_tournament FOREIGN KEY (tournament_id) REFERENCES Tournament (id),
    CONSTRAINT fk_group_leaderboard FOREIGN KEY (group_leaderboard_id) REFERENCES GroupLeaderboard (id)
);

CREATE TABLE IF NOT EXISTS GroupLeaderboard (
    id SERIAL PRIMARY KEY,
    scores INTEGER,
    tournament_id BIGINT,
    CONSTRAINT fk_tournament_group FOREIGN KEY (tournament_id) REFERENCES Tournament (id)
);

CREATE TABLE IF NOT EXISTS tournament (
    id SERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL
);


INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (1,"Tanek","Cortez",33,182,31028,"France",1,0,20),
  (2,"Amir","Holman",0,131,43382,"France",0,0,1),
  (3,"Burke","Roberts",22,45,46200,"UnitedStates",0,0,23),
  (4,"Tobias","Byrd",5,77,45560,"Germany",1,0,28),
  (5,"Rahim","Vinson",16,20,36238,"UnitedKingdom",0,0,23),
  (6,"Zachery","Austin",38,36,41162,"UnitedKingdom",1,0,9),
  (7,"Kane","Velasquez",32,2,47654,"UnitedKingdom",0,0,10),
  (8,"Jane","Sims",19,46,34923,"Turkey",1,0,25),
  (9,"Rina","Burt",45,90,27998,"France",0,0,13),
  (10,"Madeline","Nichols",46,24,2932,"Germany",0,0,16);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (11,"Dana","Estes",10,180,17296,"UnitedKingdom",0,0,14),
  (12,"Leonard","Ingram",2,173,39042,"Turkey",1,0,2),
  (13,"Kai","Valentine",25,183,34922,"Turkey",1,0,0),
  (14,"Athena","Kaufman",32,68,39288,"UnitedKingdom",1,0,1),
  (15,"Macaulay","Montoya",40,196,37126,"Turkey",1,0,2),
  (16,"Aaron","Calhoun",3,45,29800,"Germany",1,0,24),
  (17,"Odette","Clemons",31,120,43028,"France",0,0,16),
  (18,"Caleb","Crosby",26,112,35823,"UnitedKingdom",0,0,1),
  (19,"Judith","Vinson",33,112,34369,"UnitedKingdom",0,0,24),
  (20,"Alfonso","Mueller",33,143,35834,"UnitedStates",0,0,22);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (21,"Colette","Riddle",36,98,21588,"UnitedStates",0,0,22),
  (22,"Oliver","Adams",32,40,15283,"UnitedStates",1,0,23),
  (23,"Gisela","Clemons",30,69,24657,"France",0,0,28),
  (24,"Jaquelyn","Lang",47,95,6748,"France",1,0,25),
  (25,"Cheryl","Dunlap",2,65,37642,"UnitedKingdom",1,0,24),
  (26,"Cassady","Witt",22,89,36441,"Turkey",1,0,11),
  (27,"Gregory","Joyce",31,147,3934,"UnitedKingdom",0,0,6),
  (28,"Amal","Heath",11,133,9247,"UnitedKingdom",0,0,1),
  (29,"Illiana","Duffy",0,96,40831,"UnitedStates",0,0,29),
  (30,"Quemby","Mckay",49,82,18896,"UnitedKingdom",1,0,11);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (31,"Elton","Foreman",28,132,34913,"UnitedStates",0,0,5),
  (32,"Deirdre","Newman",5,111,1576,"Germany",0,0,28),
  (33,"Candace","Cortez",28,105,12382,"UnitedStates",0,0,8),
  (34,"Carson","Bridges",44,90,8473,"France",0,0,7),
  (35,"Shay","Maynard",16,48,29936,"Germany",1,0,21),
  (36,"Savannah","Barber",11,58,20080,"France",1,0,21),
  (37,"Ezra","Matthews",27,106,13811,"France",1,0,20),
  (38,"Jin","Vargas",24,116,45124,"UnitedStates",1,0,8),
  (39,"Lacy","Herring",17,65,12202,"UnitedKingdom",0,0,29),
  (40,"Kieran","Smith",4,154,49858,"Turkey",1,0,3);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (41,"Aline","Brady",50,166,3940,"Germany",1,0,14),
  (42,"Burton","Hudson",12,53,6479,"Germany",1,0,27),
  (43,"Oren","Snyder",41,133,15681,"France",1,0,0),
  (44,"Alvin","Walls",46,83,38121,"France",1,0,28),
  (45,"Chadwick","Lowery",34,121,23835,"Turkey",1,0,11),
  (46,"Gisela","Pacheco",30,109,12123,"UnitedKingdom",0,0,23),
  (47,"Trevor","Conley",1,53,45747,"Germany",0,0,16),
  (48,"Elliott","Murphy",39,93,33177,"UnitedStates",1,0,6),
  (49,"Lareina","Lawson",26,102,15735,"France",1,0,10),
  (50,"Irene","Heath",7,187,34933,"UnitedStates",1,0,6);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (51,"Kirby","Ayala",24,105,28882,"UnitedStates",1,0,30),
  (52,"Blossom","Pitts",25,147,12006,"Germany",1,0,2),
  (53,"Kasimir","Thompson",40,76,48146,"UnitedKingdom",1,0,27),
  (54,"Rajah","Walton",38,35,29028,"UnitedStates",1,0,11),
  (55,"Hashim","Tate",21,41,15680,"UnitedKingdom",0,0,1),
  (56,"Raja","Tyson",17,148,44212,"Germany",1,0,29),
  (57,"Jack","Hale",24,1,14996,"Turkey",1,0,26),
  (58,"Jasmine","Hill",20,122,48578,"UnitedKingdom",1,0,6),
  (59,"Aiko","Kline",22,193,25843,"France",1,0,15),
  (60,"Imani","Flores",26,149,23137,"France",1,0,19);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (61,"Galvin","Frost",9,158,22906,"UnitedKingdom",0,0,18),
  (62,"Bianca","Ramsey",10,180,16452,"France",1,0,13),
  (63,"Plato","Woodward",28,63,12811,"Turkey",0,0,14),
  (64,"Zachary","Boyle",32,38,12883,"France",1,0,23),
  (65,"Rafael","Tillman",14,29,896,"France",1,0,8),
  (66,"Brenden","Lynch",16,118,9811,"France",0,0,15),
  (67,"Barry","Middleton",29,89,28577,"UnitedStates",1,0,2),
  (68,"Sawyer","Page",9,30,3399,"Turkey",1,0,25),
  (69,"Blossom","Maynard",25,117,33340,"Turkey",1,0,13),
  (70,"Xavier","Hensley",26,14,33278,"France",1,0,28);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (71,"Salvador","Edwards",28,104,14371,"UnitedStates",1,0,4),
  (72,"Chantale","Bean",27,130,24396,"UnitedKingdom",1,0,9),
  (73,"Dara","Puckett",34,115,42858,"France",0,0,13),
  (74,"Orla","Jennings",37,111,38138,"Turkey",0,0,3),
  (75,"Chiquita","Witt",43,126,44894,"UnitedStates",1,0,20),
  (76,"Maile","Cohen",8,134,19103,"UnitedKingdom",0,0,4),
  (77,"Price","Combs",37,165,42460,"UnitedKingdom",0,0,12),
  (78,"Madaline","Curtis",3,47,27505,"France",1,0,19),
  (79,"Jonah","Villarreal",46,107,3356,"Germany",1,0,1),
  (80,"Ronan","Bowers",23,145,41095,"France",1,0,10);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (81,"Natalie","Bridges",7,26,41515,"Turkey",0,0,5),
  (82,"Rowan","Keith",45,199,24812,"Germany",1,0,3),
  (83,"Tamara","Ramsey",31,97,37485,"Germany",0,0,10),
  (84,"Fay","Preston",38,2,25607,"France",0,0,25),
  (85,"Magee","Morin",10,184,38221,"Turkey",0,0,5),
  (86,"Moses","Duran",8,181,25682,"UnitedKingdom",0,0,11),
  (87,"Drake","Mendez",7,40,21565,"UnitedKingdom",0,0,3),
  (88,"Mufutau","Daniels",35,41,31420,"France",0,0,1),
  (89,"Stone","Williams",40,86,9941,"France",1,0,6),
  (90,"Nolan","Merritt",26,198,11933,"Germany",1,0,26);
INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (91,"Kane","Osborn",38,74,26534,"UnitedKingdom",0,0,12),
  (92,"Aristotle","Henson",43,123,29241,"UnitedKingdom",1,0,3),
  (93,"Jordan","Wallace",2,34,27791,"France",0,0,6),
  (94,"Kitra","Guzman",15,115,37660,"Germany",0,0,15),
  (95,"Dalton","Raymond",47,15,35688,"UnitedKingdom",1,0,13),
  (96,"Nicholas","Garrett",29,91,28696,"Germany",0,0,12),
  (97,"Gregory","Marsh",35,189,13634,"Germany",0,0,8),
  (98,"Bert","Mcdaniel",25,184,39951,"UnitedStates",0,0,16),
  (99,"Aladdin","Alston",22,121,26527,"UnitedKingdom",1,0,8),
  (100,"Willow","Acevedo",36,37,27676,"Turkey",1,0,16);

INSERT INTO `users` (`id`,`name`,`surname`,`age`,`user_level`,`coins`,`country`,`enter_tournament`,`already_entered`,`group_scores`)
VALUES
  (103,"Kane","Osborn",38,74,26534,"UnitedKingdom",0,0,12),
  (104,"Aristotle","Henson",43,123,29241,"UnitedKingdom",1,0,3),
  (105,"Jordan","Wallace",2,34,27791,"France",0,0,6),
  (106,"Kitra","Guzman",15,115,37660,"Germany",0,0,15),
  (107,"Dalton","Raymond",47,15,35688,"UnitedKingdom",1,0,13),
  (108,"Nicholas","Garrett",29,91,28696,"Germany",0,0,12),
  (109,"Gregory","Marsh",35,189,13634,"Germany",0,0,8),
  (110,"Bert","Mcdaniel",25,184,39951,"UnitedStates",0,0,16),
  (111,"Aladdin","Alston",22,121,26527,"UnitedKingdom",1,0,8),
  (112,"Willow","Acevedo",36,37,27676,"Turkey",1,0,16);