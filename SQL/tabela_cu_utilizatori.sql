drop table if exists tabela_cu_utilizatori_si_parole;


create table tabela_cu_utilizatori_si_parole(

   ID varchar(30) not null ,
   pass varchar(30) not null,
   
   primary key (ID,pass)
   );
   
 insert into tabela_cu_utilizatori_si_parole (ID,pass)
 values ('root','root');
 
 