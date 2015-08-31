CREATE KEYSPACE IF NOT EXISTS movie_ks WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE movie_ks;

CREATE TABLE IF NOT EXISTS movies (
  id uuid,
  title text,
  year varchar,
  description text,
  picture varchar, 
  genres set<text>,PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS title_key ON movies (title);
CREATE INDEX IF NOT EXISTS genres_key ON movies (genres);

INSERT INTO movies (id,title,year,description,picture,genres)
VALUES (now(),'Fantastic Four','2015','Four young outsiders teleport to an alternate and dangerous universe which alters their physical form in shocking ways. The four must learn to harness their new abilities and work together to save Earth from a former friend turned enemy.','fantastic_four_2015.jpg',{'Action','Adventure','Sci-Fi'});

INSERT INTO movies (id,title,year,description,picture,genres)
VALUES (now(),'Ant-Man','2015','Armed with a super-suit with the astonishing ability to shrink in scale but increase in strength, cat burglar Scott Lang must embrace his inner hero and help his mentor, Dr. Hank Pym, plan and pull off a heist that will save the world.','ant_man_2015.jpg',{'Action','Adventure','Sci-Fi'});

INSERT INTO movies (id,title,year,description,picture,genres)
VALUES (now(),'Minions','2015','Minions Stuart, Kevin and Bob are recruited by Scarlet Overkill, a super-villain who, alongside her inventor husband Herb, hatches a plot to take over the world.','minions_2015.jpg',{'Animation','Comedy','Family'});

INSERT INTO movies (id,title,year,description,picture,genres)
VALUES (now(),'Southpaw','2015','Boxer Billy Hope turns to trainer Tick Willis to help him get his life back on track after losing his wife in a tragic accident and his daughter to child protection services.','southpaw_2015.jpg',{'Action','Drama','Sport','Thriller'});

INSERT INTO movies (id,title,year,description,picture,genres)
VALUES (now(),'Star Wars','1977','Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a wookiee and two droids to save the universe from the Empires world-destroying battle-station, while also attempting to rescue Princess Leia from the evil Darth Vader.','star_wars_1977.jpg',{'Action','Adventure','Fantasy'});

INSERT INTO movies (id,title,year,description,picture,genres)
VALUES (now(),'The Pianist','2002','A Polish Jewish musician struggles to survive the destruction of the Warsaw ghetto of World War II.','the_pianist_2002.jpg',{'Biography','Drama','War'});

INSERT INTO movies (id,title,year,description,picture,genres)
VALUES (now(),'The Godfather','1972','The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.','the_godfather_1972.jpg',{'Crime','Drama'});
