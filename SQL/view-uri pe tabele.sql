# views

drop view if exists vizualizare_rest_de_plata;
 
  create view vizualizare_rest_de_plata as 
  select c.ID_comanda, Cost_total - suma_achitata as Rest_de_plata
  from comanda as c join client_plaseaza_comanda as cpc on (c.ID_comanda = cpc.ID_comanda)
  group by c.ID_comanda;


drop view if exists info_clienti_v1;
   
   create view info_clienti_v1 as 
   select c.Nume,c.Prenume,c.CNP,denumire_produs as "Produsul Achizitonat",ccp.ID_comanda as "Numarul_comenzii"
   from client as c , client_plaseaza_comanda as cpc,comanda_contine_produs as ccp , produs as p
   where (c.CNP = cpc.ID_client) and (cpc.ID_comanda = ccp.ID_comanda) and (ccp.ID_produs = p.ID_produs);
   

   #view v2 = nume , prenume client + denumire produs achizitionat + pretul lui + rest de plata factura.
   drop view if exists info_clienti_v2;    
   
    create view  info_clienti_v2 as 
    select c.Nume,c.Prenume,c.CNP,p.denumire_produs as "Produs Achizitionat" , p.pret , co.Cost_total - cpc.suma_achitata as "Rest de plata"
    from client as c,client_plaseaza_comanda as cpc , comanda_contine_produs as ccp, produs as p , comanda as co
    where (c.CNP = cpc.ID_client) and (cpc.ID_comanda = co.ID_comanda)and (cpc.ID_comanda = ccp.ID_comanda) and (ccp.ID_produs = p.ID_produs);
   
    
    # la stergerea unui produs din comanda trebuie sa scada totalul de plata si numarul de produse achizitionate trebuie sa scada
    # mai trebuie si ca stocul sa creasca inapoi restul de plata sa se micsorat restul de plata
 