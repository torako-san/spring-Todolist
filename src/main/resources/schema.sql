CREATE TABLE todo
(
	id						SERIAL PRIMARY KEY,
	title					TEXT,
	importance		INTEGER,
	urgency			INTEGER,
	deadline			DATE,
	done					TEXT
);
