BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO genre (genre_name) VALUES ('Comedy');
INSERT INTO genre (genre_name) VALUES ('Horror');
INSERT INTO genre (genre_name) VALUES ('Action');
INSERT INTO genre (genre_name) VALUES ('Sci-Fi');
INSERT INTO genre (genre_name) VALUES ('Fantasy');


INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Dumb and Dumber', '107', '1994', 'Filmed and completed during one of the great runs in any actors; career, Jim Carrey is joined by Jeff Daniels in an absolutely ridiculous pair of performances, as two well-intentioned imbeciles who just want to do what’s right. When Lloyd Carrey intercepts a briefcase left behind by a woman he drives to the airport, he and his friend Harry Daniels go on an unnecessarily difficult journey halfway across the country in the hopes of winning her heart and returning her briefcase, and the adventure only escalates when they discover the contents of said luggage.', '1');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Dodgeball: A True Underdog Story', '92', '2004', 'A group of misfits enter a Las Vegas dodgeball tournament in order to save their cherished local gym from the onslaught of a corporate health fitness chain.', '1');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('The Hangover', '100', '2009','“Three buddies wake up from a bachelor party in Las Vegas, with no memory of the previous night and the bachelor missing. They make their way around the city in order to find their friend before his wedding.', '1');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('21 Jump Street', '109', '2012','A pair of underachieving cops are sent back to a local high school to blend in and bring down a synthetic drug ring.', '1');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Step Brothers', '98', '2008','Two aimless middle-aged losers still living at home are forced against their will to become roommates when their parents marry.', '1');


INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Talk to Me', '95', '2022','When a group of friends discover how to conjure spirits using an embalmed hand, they become hooked on the new thrill, until one of them goes too far and unleashes terrifying supernatural forces.', '2');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Hereditary', '127', '2018','A grieving family is haunted by tragic and disturbing occurrences.', '2');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('The Conjuring', '112', '2013','Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence.', '2');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Get Out', '104', '2017','A young African-American visits his White girlfriends parents for the weekend, where his simmering uneasiness about their reception of him eventually reaches a boiling point.', '2');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Scream', '111', '1996','A year after the murder of her mother, a teenage girl is terrorized by a masked killer who targets her and her friends by using scary movies as part of a deadly game.', '2');

INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Lone Survivor', '121', '2013','Lone Survivor is a 2013 American biographical war film based on the 2007 nonfiction book of the same name by Marcus Luttrell with Patrick Robinson. Set during the war in Afghanistan, it dramatizes the unsuccessful United States Navy SEALs counter-insurgent mission Operation Red Wings, during which a four-man SEAL reconnaissance and surveillance team was given the task of tracking down the Taliban leader Ahmad Shah. The film was written and directed by Peter Berg and stars Mark Wahlberg, Taylor Kitsch, Emile Hirsch, Ben Foster, and Eric Bana.', '3');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('American Sniper', '132', '2014','American Sniper is a 2014 American biographical war drama film directed by Clint Eastwood and written by Jason Hall. It is loosely based on the memoir American Sniper: The Autobiography of the Most Lethal Sniper in U.S. Military History 2012 by Chris Kyle, with Scott McEwen and Jim DeFelice. The film follows the life of Kyle, who became the deadliest marksman in U.S. military history with 255 kills from four tours in the Iraq War, 160 of which were officially confirmed by the Department of Defense. While Kyle was celebrated for his military successes, his tours of duty took a heavy toll on his personal and family life. The film was produced by Eastwood, Robert Lorenz, Andrew Lazar, Bradley Cooper, and Peter Morgan. It stars Cooper as Kyle and Sienna Miller as his wife Taya, with Luke Grimes, Jake McDorman, Cory Hardrict, Kevin Lacz, Navid Negahban, and Keir ODonnell in supporting roles.', '3');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Lawless', '115', '2012','In 1931, the Bondurant brothers — middle brother Forrest, eldest brother Howard, and youngest brother Jack — are running a successful moonshine business in Franklin County, Virginia. The brothers use their gas station and restaurant as a front for dealing with the assistance of Cricket, Jacks mechanically gifted friend who builds and maintains their stills. Jack witnesses rum-runner Floyd Banner shoot dead two federal agents in broad daylight for trying to arrest him.', '3');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('American Gangster', '157', '2007','American Gangster is a 2007 American biographical crime film directed and produced by Ridley Scott and written by Steven Zaillian. The film is fictionally based on the criminal career of Frank Lucas, a gangster from La Grange, North Carolina who smuggled heroin into the United States on American service planes returning from the Vietnam War, before being detained by a task force led by Newark Detective Richie Roberts.', '3');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('The Equalizer', '132', '2014','The Equalizer is a 2014 American vigilante action film directed by Antoine Fuqua. It is the first installment of The Equalizer trilogy, loosely based on the 1980s TV series of the same title. Written by Richard Wenk, it stars Denzel Washington in the lead role, along with Marton Csokas, Chloë Grace Moretz, David Harbour, Bill Pullman and Melissa Leo. The film focuses on a former U.S. Marine turned DIA intelligence officer who reluctantly returns to action to protect a teenage prostitute from members of the Russian mafia.', '3');

INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('War Of The Worlds', '116', '2005','War of the Words has all the elements to create a blockbuster hit. Superstar actor Tom Cruise in the lead? Check. Oscar-winning director Steven Spielberg behind the camera? Check. Terrifying aliens with tentacles hell-bent to destroy humankind? Check. Throw in an adorable kid Dakota Fanning and its movie magic.', '4');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('The Flash', '144', '2023','Barry Allen uses his super speed to change the past, but his attempt to save his family creates a world without super heroes, forcing him to race for his life in order to save the future.', '4');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Interstellar', '169', '2014','When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.', '4');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Inception', '148', '2010','A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.', '4');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Dune', '155', '2021','A noble family becomes embroiled in a wathar for control over the galaxys most valuable asset while its heir becomes troubled by visions of a dark future.', '4');

INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('The Super Mario Bros. Movie', '92', '2023','A plumber named Mario travels through an underground labyrinth with his brother, Luigi, trying to save a captured princess.', '5');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Avatar: The Way of Water', '192', '2022','Jake Sully lives with his newfound family formed on the extrasolar moon Pandora. Once a familiar threat returns to finish what was previously started, Jake must work with Neytiri and the army of the Navi race to protect their home.', '5');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('The Lord of the Rings: The Fellowship of the Ring', '178', '2001','A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.', '5');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Twilight', '122', '2008','When Bella Swan moves to a small town in the Pacific Northwest, she falls in love with Edward Cullen, a mysterious classmate who reveals himself to be a 108-year-old vampire.', '5');
INSERT INTO movie (movie_title, movie_duration, movie_release, movie_description, genre_id)
VALUES
('Suicide Squad', '123', '2016','A secret government agency recruits some of the most dangerous incarcerated super-villains to form a defensive task force. Their first mission: save the world from the apocalypse.', '5');

COMMIT TRANSACTION;