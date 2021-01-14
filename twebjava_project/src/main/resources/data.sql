/*insert into rallies values (null,'Raliul Argesului');
insert into rallies values (null,'Raliul Perla Harghitei');
insert into rallies values (null,'Raliul Sibiului');
insert into rallies values (null,'Raliul Iasului');
insert into rallies values (null,'Raliul Aradului');


insert into editions values (null,1,'Editie 2020');
insert into editions values (null,2,'Editie 2019');
insert into editions values (null,2,'Editie 2020');
insert into editions values (null,2,'Editie 2021');
insert into editions values (null,3,'Editie 2020');
insert into editions values (null,5,'Editie 2020');



insert into stages values (null,3,'PS 1 – Poiana Brasov',10.9,'asfalt','2020/07/17 14:53');
insert into stages values (null,3,'PS 2 – Paraul Rece',14.3,'asfalt','2020/07/17 15:31');
insert into stages values (null,3,'PS 3 – Rasnov',7.9,'asfalt','2020/07/17 18:04');
insert into stages values (null,3,'PS 4 – Paraul Rece',14.3,'asfalt','2020/07/17 18:27');
insert into stages values (null,3,'PS 5 – Leuta',12.75,'asfalt','2020/07/18 10:53');
insert into stages values (null,3,'PS 6 – Craciunel',6.7,'asfalt','2020/07/18 11:41');


insert into stages values (null,1,'PS1 Godeni 1',12.4,'asfalt','2020/06/26 17:28');
insert into stages values (null,1,'PS2 Godeni 2',12.4,'asfalt','2020/06/26 18:57');
insert into stages values (null,1,'PS3 Lacul Vidraru',27,'asfalt','2020/06/27 11:43');
insert into stages values (null,1,'PS4 Transfagarasan',15.1,'asfalt','2020/06/27 12:21');



insert into teams values (null, 'Napoca Rally Academy', 'Romania');
insert into teams values (null, 'BCR Rally Team', 'Romania');
insert into teams values (null, 'Danny Ungur Racing', 'Romania');
insert into teams values (null, 'Sibiu Racing Team', 'Romania');
insert into teams values (null, 'Harghita Racing Team', 'Romania');
insert into teams values (null, 'SIBIU RALLY TEAM', 'Romania');
insert into teams values (null, 'LYON RALLY TEAM', 'Franta');



insert into team_members values (null, 'GHEORGHE','ALEXANDRU','1996/05/22','driver', 1);
insert into team_members values (null, 'SOLOMON','CIPRIAN','1998/09/01','coDriver', 1);
insert into team_members values (null, 'POPESCU','MIHAI','1997/11/22','staff', 1);
insert into team_members values (null, 'VASILESCU','GABRIEL','1996/02/14','staff', 1);

insert into team_members values (null, 'ANGHEL','RENADO','1998/03/03','staff', 2);
insert into team_members values (null, 'DARAGIU','BOGDAN','2000/08/14','staff', 2);
insert into team_members values (null, 'POTOCEAN','RAZVAN','1995/10/05','staff', 2);

insert into team_members values (null, 'MINEA','ADRIAN','2001/06/21','driver', 3);
insert into team_members values (null, 'RADUCIOIU','CONSTANTIN','1999/09/21','coDriver', 3);
insert into team_members values (null, 'CONSTANTIN','SILVIU','1999/11/22','staff', 3);
insert into team_members values (null, 'STANCIU','MIHAI','2000/02/27','staff', 3);

insert into team_members values (null, 'TESLOVAN','ADRIAN','2001/04/28','driver', 4);
insert into team_members values (null, 'ANDREOIU','MARCUS','2003/06/11','coDriver', 4);
insert into team_members values (null, 'MARINESCU','GEORGE','1999/10/03','staff', 4);
insert into team_members values (null, 'SIMON','ALEXANDRU','2000/08/15','staff', 4);

insert into team_members values (null, 'MICLAUSU','GABRIEL','2001/01/14','driver', 5);
insert into team_members values (null, 'GHIGOLEA','DRAGOS','2003/04/15','coDriver', 5);
insert into team_members values (null, 'BUTNARIU','ALIN','1998/11/13','staff', 5);
insert into team_members values (null, 'CAITANU','ARNOLD','2001/05/25','staff', 5);

insert into team_members values (null, 'GHEORGHIU','BOGDAN','1998/12/03','driver', 6);
insert into team_members values (null, 'GHEORGHIU','ANDREI','2001/09/15','coDriver', 6);
insert into team_members values (null, 'BOTEZATU','ROMEO','1999/07/23','staff', 6);

insert into team_members values (null, 'BERNARDI','NICOLAS','1995/04/03','driver', 7);
insert into team_members values (null, 'CAMILLI','ERIC','1993/12/16','coDriver', 7);
insert into team_members values (null, 'CAMPANA','PIERRE','1996/11/17','staff', 7);



insert into participations values (null,2,1,'2019/04/05');
insert into participations values (null,2,2,'2019/04/06');
insert into participations values (null,2,3,'2019/04/05');


insert into participations values (null,3,2,'2020/03/05');
insert into participations values (null,3,3,'2020/03/15');
insert into participations values (null,3,4,'2020/03/14');
insert into participations values (null,3,5,'2020/03/15');
insert into participations values (null,3,6,'2020/03/13');


insert into participations values (null,4,2,curdate());
insert into participations values (null,4,4,curdate());
insert into participations values (null,4,5,curdate());
insert into participations values (null,4,6,curdate());



insert into results values (null,1,2,5,300);
insert into results values (null,1,3,4.24,400);
insert into results values (null,1,4,6.23,200);
insert into results values (null,1,5,10.66,0);
insert into results values (null,1,6,7.24,100);


insert into results values (null,2,2,7.33,400);
insert into results values (null,2,3,10.26,100);
insert into results values (null,2,4,8.3,200);
insert into results values (null,2,5,13.5,0);
insert into results values (null,2,6,7.55,300);


insert into results values (null,3,2,4.01,300);
insert into results values (null,3,3,3.54,400);
insert into results values (null,3,4,6.32,100);
insert into results values (null,3,5,5.51,200);
insert into results values (null,3,6,7.3,0);


insert into results values (null,4,2,7.24,400);
insert into results values (null,4,3,9.5,100);
insert into results values (null,4,4,8.56,200);
insert into results values (null,4,5,11.51,0);
insert into results values (null,4,6,7.42,300);


insert into results values (null,5,2,5.14,400);
insert into results values (null,5,3,5.36,300);
insert into results values (null,5,4,8.25,100);
insert into results values (null,5,5,11.5,0);
insert into results values (null,5,6,6.35,200);


insert into results values (null,6,2,2.55,400);
insert into results values (null,6,3,3.06,300);
insert into results values (null,6,4,7.22,0);
insert into results values (null,6,5,6.02,100);
insert into results values (null,6,6,4.54,200);*/