drop database if exists buy_things;

 create database if not exists buy_things;
  use buy_things;
  
  drop table if exists adresa;
   
   create table if not exists adresa(
    ID int auto_increment primary key,
	Oras varchar(20) not null,
    Strada varchar(30) not null,
    Numar varchar(10) );
    
   describe adresa;
  
  
 drop table if exists client;
 
 create table if not exists client(
 
  Nume varchar(15) not null,
  Prenume varchar(20) not null,
  CNP char(13) not null primary key,
  numar_de_telefon char(13) not null,
  ID_adresa_client int,
  foreign key (ID_adresa_client)
  references adresa(ID)
           on update cascade
           on delete cascade
  );
  
  describe client;
  
   drop table if exists comanda;
   
   create table comanda(
      
        ID_comanda int auto_increment primary key,
        Cantitate  int default 0,
        Cost_total float default 0,
        balance float default 0
   );
   
     describe comanda;  
 
    drop table if exists client_plaseaza_comanda;
    
    create table  client_plaseaza_comanda(
       
       ID_client_plaseaza_comanda int auto_increment primary key,
       ID_comanda int not null,
       ID_client char(13) not null ,
       foreign key (ID_comanda) references comanda (ID_comanda) on update cascade on delete cascade,
       foreign key (ID_client) references client(CNP) on update cascade on delete cascade
       
	);
    
    describe client_plaseaza_comanda;
    
    drop table if exists produs;
    
    create table produs (
      ID_produs int auto_increment primary key,
      pret float ,
      reducere float ,
      cantitate_reducere int,
      denumire_produs varchar(20)
	   );
      
      describe produs;
      
     alter table client_plaseaza_comanda
     add suma_achitata float default 0;
      
      describe client_plaseaza_comanda;
      
     drop table if exists comanda_contine_produs ;
     
     create table comanda_contine_produs(
       
           ID_comanda_contine_produs int auto_increment primary key,
           ID_produs int not null,
           ID_comanda int not null,
           data_achizitie date ,
           disponibilitate boolean ,
           achitare_produs boolean default false,
           foreign key (ID_produs) references produs (ID_produs) ,
           foreign key (ID_comanda) references comanda (ID_comanda) 
     );
     
     describe comanda_contine_produs;
    
  drop table if exists furnizor;
  
  create table furnizor(
  
    ID_furnizor int auto_increment primary key,
    Nume varchar(30),
    Cantitate int 
  );
  
  describe furnizor;
  
  alter table produs 
  add  furnizorul_produsului int not null;
  
  alter table produs
  add foreign key (furnizorul_produsului) references furnizor(ID_furnizor) on update cascade on delete cascade;

  describe produs;
  
  drop table if exists curier;
  
  create table curier(
  
      ID_curier int auto_increment primary key,
      Numar_de_telefon int not null
  
  );
  
   describe curier;
   
   alter table comanda
   add id_curier int not null ;
   
   alter table comanda
   add foreign key (id_curier) references curier(ID_curier) on update cascade on delete cascade;
   
   describe comanda;
