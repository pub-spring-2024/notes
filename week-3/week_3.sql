use pub_elections;

create table generations (
	id int primary key auto_increment,
	number int,
	name varchar(30)
);

create table candidates (
	id int primary key auto_increment,
	name varchar(30),
	number int,
	generation_id int,   
	foreign KEY (generation_id) references generations(id)
);

generations
	
INSERT INTO candidates
	(NAME, NUMBER, generation_id)
VALUES
	('Hanif Fauzan Nurrahman', 1, 6),
	('Lira Rahmawati', 2, 6),
	('Sawaluddin Siregar', 3, 5);