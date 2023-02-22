#inserare valori in tabele

   insert into adresa(Oras,Strada,Numar)
   values ("Arad","Domnita Maria",22),
          ("Arad","Oituz",7),
          ("Bistrita","Carol I",19),
          ("Brasov","Comarnic",9),
          ("Timisoara","Alexandru cel Bun",10),
          ("Iasi","Somes",76),
          ("Pitesti","Principala",42),
          ("Cluj Napoca","Calea Dorobantilor",112),
          ("Cluj Napoca","Piata Marasti",102),
          ("Cluj Napoca","Bistrita",29);
      
   insert into client (Nume,Prenume,CNP,numar_de_telefon,ID_adresa_client)
   values ("Marinescu","Constantin","4020905023142","0256447901",3),
          ("Adrian","Vizitiu","5030906043742","0764123984",2),
          ("Sebastian","Crisan","5011803033222","0754338765",6),
          ("Adoinitei","Cristian","3040203433232","0246212312",1),
          ("Valentin","Lupu","4123725154133","0236547991",4),
          ("Ciubotariu","Aurelian","6223514245241","0765342897",5),
          ("Neica","Marius","5012505523242","0784028445",9),
          ("Groza","Darius","4020403456567","0754218954",7),
          ("Malinescu","George","3055673234199","0743219784",10),
          ("Luminita","Camelia","3426234563451","0798546732",8);
     
describe curier;     
          
  insert into curier(Numar_de_telefon)
  values ("0776543209"),
         ("0754673219"),
         ("0755422134"),
         ("0778652098"),
         ("0789564989"),
         ("0886455221");

 describe furnizor;        
    
  insert into furnizor(Nume,Cantitate)
  values ("Indesit",20),
         ("Siemens",30),
         ("Bosch",17),
         ("LG",21),
         ("Sony",33),
         ("Samsung",50),
         ("Asus",9),
         ("Acer",3),
         ("Apple",15),
         ("Beko",12);
          
 describe furnizor;   
 
 describe produs;
  
   insert into produs(pret,reducere,cantitate_reducere,denumire_produs,furnizorul_produsului)
   values 
   (4299.99,0.10,1,"Laptop de gaming",7),
   (3400,0.07,1,"Obiectiv camera",5),
   (2500,0.12,1,"Masina de spalat",10),
   (1250,0,0,"Casti",9),
   (3000,0.05,1,"Televizor",6),
   (560,0.22,1,"Aspirator",4),
   (450,0.13,1,"Flex",3),
   (1400,0,0,"Sistem audio 5.1",6);
    
    
  describe comanda;  
  
  insert into comanda(id_curier)
   values (1),
		  (3),
          (6),
          (2),
          (1),
          (4); 
          
  describe comanda_contine_produs;    
  # insert into comanda_contine_produs(ID_produs,ID_comanda,data_achizitie,disponibilitate)
    insert into comanda_contine_produs(ID_comanda,ID_produs,data_achizitie)
  values 
    (1,2,"2008.03.04"),
    (2,2,"2009.05.04"),
    (3,5,"2011.04.07"),
    (3,8,"2011.04.07"),
    (3,6,"2021.11.29"),
    (4,5,"2021.12.12"),
    (4,5,"2021.12.12"),
    (5,2,"2019.09.07"),
    (5,4,"2019.09.07"),
    (6,2,"2018.07.14"),
    (6,5,"2018.07.14"),
    (6,4,"2018.07.14"),
    (6,3,"2018.07.14"),
    (6,8,"2018.07.14");
    
 describe client_plaseaza_comanda; 
 
  insert into client_plaseaza_comanda(ID_comanda,ID_client)
  values
    (1,'4020403456567'),
    (2,'5030906043742'),
    (3,'5012505523242'),
    (4,'5011803033222'),
    (4,'4123725154133'),
    (5,'3040203433232'),
    (6,'3055673234199'),
    (6,'3426234563451'),
    (6,'6223514245241');

## 
 