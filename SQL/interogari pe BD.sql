# Select From Where :

#1 Determinare pentru fiecare comanda pretul total de plata:
 
 select c.ID_comanda,sum(find_price(pret,reducere)* disponibilitate) as cost_total_per_comanda
 from comanda as c join comanda_contine_produs as ccp on (c.ID_comanda = ccp.ID_comanda) join produs as p on (ccp.ID_produs = p.ID_produs)
 group by c.ID_comanda;
 
 #2
 # Comanda cu costul maxim
 select max(Cost_total) as "Comanda cu costul cel mai mare"
 from comanda;
 
 #3
 #Numarul de orase distincte
 select  count(distinct(Oras)) as "Numarul de orase distincte unde se vor face livrarile este:"
 from adresa;
 
 #4
  #cate comenzi vor fi livrate in orasul X
  select count(ID_client_plaseaza_comanda) as "Numar de comenzi din orasul:", Oras
  from   client_plaseaza_comanda join client on (ID_client = CNP) join adresa on (ID_adresa_client = ID)
  group by Oras
  order by count(ID_client_plaseaza_comanda) desc;
 
 #5
  select p.denumire_produs as Numele_produsului , f.Nume as Furnizorul
  from produs as p , furnizor as f
  where p.furnizorul_produsului = f.ID_furnizor;
  
 #6
  select Rest_de_plata as "Total cont in plus" , ID_comanda as Contul_Comenzii_Asociat
  from vizualizare_rest_de_plata
  where Rest_de_plata <0;
  
 #7
  select Nume,Prenume,ID_comanda as "Comanda"
  from  client join client_plaseaza_comanda on(ID_client= CNP);
  
 #8 Orasele care au prima litera C iar numele strazii incepe cu o litera >=C; 
  select Oras,Strada
  from adresa 
  where Oras like "C%" and substr(Strada,1,1)>='C';
  
 #9 Strazile care au numere cuprinse intre 7 si 40
  select Strada,Numar
  from adresa
  where Numar >= 7 and Numar <=40;
  
 #10 
  select pret as "Pret fara reducere", find_price(pret,reducere) as "Pret cu reducere"
  from produs;

#11 
  
  select denumire_produs , pret , reducere , find_price(pret,reducere)
  from produs 
  where pret - find_price(pret,reducere) = (select max(pret-find_price(pret,reducere)) 
                                           from produs);
#12
  select max(pret-find_price(pret,reducere)) 
  from produs;
  
#13  
  select denumire_produs , pret , reducere , find_price(pret,reducere)
  from produs 
  where pret - find_price(pret,reducere) = (select min(pret-find_price(pret,reducere)) 
                                           from produs);

#14                                           
  select min(pret-find_price(pret,reducere)) 
  from produs  ;       
 
#15 
  select avg(find_price(pret,reducere)) as "Pretul mediu al produselor"
  from produs;

#16
  select Numarul_comenzii,count(*)
  from info_clienti_v1
  group by Numarul_comenzii;
 
#17
  select ID_comanda , Cost_total 
  from comanda
  where Cost_total is not null ;
  
#18
  select ID_comanda, balance as "Suma introdusa"
  from comanda
  where balance > 0 ;
  
#19
  select ID_comanda ,count(*) as "Numarul de persoane care participa la plasarea comenzii"
  from client_plaseaza_comanda
  group by ID_comanda;
  
#20
  
  select Nume,Prenume,ID_comanda
  from   client join client_plaseaza_comanda on (ID_client = CNP) join comanda using (ID_comanda)
  where Cost_total is null ;
  
  
  
  