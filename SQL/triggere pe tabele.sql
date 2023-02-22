# creare triggere pentru actualizare cantitate din cosul de cumparaturi:
# tr1: creste cantitatea la inserare
# tr2: scade cantitatea la inserare

# de facut triggere pentru fiecare noua comanda_contine_produs 
#sa se actualizeze automat suma de plata rest de plata si disponibilitatea.
# select ID_, pret,... INTO variabila declarata anterior cu declare. 
#from table
#where conditie.

   drop function if exists find_price;
    
    delimiter //
    
    create function find_price(price float , reducere float) returns float
     deterministic
      begin
      
         return price * (1-reducere);
        
      end //
      
     delimiter ; 


  drop trigger if exists determinare_cantitate_din_cos;
  
    delimiter //
    
       create trigger determinare_cantitate_din_cos after insert on comanda_contine_produs
          for each row begin
            
                update comanda
                set Cantitate = Cantitate+1
                where ID_comanda = new.ID_comanda;
 

          end //
   delimiter ;    

  
 drop trigger if exists trig_stergere_produs_executa_actualizari;
      
       delimiter //
       
        create trigger trig_stergere_produs_executa_actualizari after delete on comanda_contine_produs
           for each row begin
           
                update comanda
                set cantitate = cantitate -1
                where ID_comanda = old.ID_comanda;
             
           end //
      delimiter ;     
   
drop trigger if exists trigger_for_ccp;

delimiter //

create trigger trigger_for_ccp before insert on comanda_contine_produs
    for each row begin
    
        
         declare produs_id int ;
         declare furnizor_id int;
         declare cant int ;
         declare pret_produs float ;
         declare reducere_produs float;
        
         select furnizorul_produsului into furnizor_id
         from produs
         where ID_produs = new.ID_produs;
         
         select Cantitate into cant
         from furnizor
         where ID_furnizor = furnizor_id;
         
         select pret into pret_produs
         from produs
         where ID_produs = new.ID_produs;
         
         select reducere into reducere_produs
         from produs
         where ID_produs = new.ID_produs;
         
         if(cant > 0 )
           then 
              begin
                
                   update furnizor
                   set Cantitate = Cantitate -1
                   where ID_furnizor= furnizor_id;
                 
				   set new.disponibilitate =1;
                   
                   update comanda
                   set Cost_total = Cost_total + find_price(pret_produs,reducere_produs)
                   where ID_comanda = new.ID_comanda;
              
              
              end ;
          end if;   
             
         
        
    
    
    end //
    