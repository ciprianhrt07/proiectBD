# procedures and functions:
# select (ID,pret,...) into (variabila de acelasi tip declarata cu declare inainte)

 drop procedure if exists pr1;
    
    delimiter //
      
        create procedure pr1( in produs_id int, in comanda_id int)
            begin
            
                
                
               select @copie_cantitate := Cantitate,@furnizor_prod :=ID_furnizor
               from furnizor
               where ID_furnizor = (select furnizorul_produsului
                                    from produs
                                    where ID_produs=produs_id);
            
               if(@copie_cantitate>0)
                  then 
                     
                     # select concat(@copie_cantitate," este mai mare decat 0"); 
                      update comanda_contine_produs
                      set disponibilitate = 1
                      where ID_comanda = comanda_id and ID_produs = produs_id;
                      
                      update furnizor
                      set cantitate = cantitate - 1
                      where ID_furnizor = @furnizor_prod;
                      
                  else
                      #select concat(@copie_cantitate," nu este mai mare decat 0"); 
					  update comanda_contine_produs
                      set disponibilitate = 0
                      where ID_comanda = comanda_id and ID_produs = produs_id;
                      
                       
               end if   ;
               
            
            end //
          
      delimiter ;    
      
      #call pr1(2,1);
      
     #2 pentru fiecare comanda se determina produsele din ea si se verifica disponibilitatea din stoc.
     
     drop procedure if exists pr3;
       
         delimiter //
         
            create procedure pr3(in a int )
              begin
              
			 declare c,b int;
             declare cursor1 cursor for 
             select ID_produs 
             from comanda_contine_produs
             where ID_comanda = a;
              
             declare continue handler for not found 
             
             set b = 1;
               
			 open cursor1;
             repeat 
               fetch cursor1 into c;
                   
                   call pr1(c,a);
                   
               until b =1
             end repeat ;
             close cursor1;
           
               
              end //
        delimiter ;     
     drop procedure if exists pr2;
     
      delimiter //
         
         create procedure pr2()
          begin
           
             declare a,b int;
             declare cursor1 cursor for select ID_comanda from comanda;
             declare continue handler for not found 
             
             set b = 1;
               
			 open cursor1;
             repeat 
               fetch cursor1 into a;
                 call pr3(a);
                 until b=1
             end repeat ;
             close cursor1;
           
           
          end //
       delimiter ;   
       
    call pr2();
    
    drop function if exists find_price;
    
    delimiter //
    
    create function find_price(price float , reducere float) returns float
     deterministic
      begin
      
         return price * (1-reducere);
        
      end //
      
     delimiter ; 
     
     #select find_price(100,0.1);


drop procedure if exists pr4;
 delimiter //
 
    create procedure pr4(in comanda_id int)
         begin
         
             select @cost_total := sum(find_price(pret,reducere)*disponibilitate)
             from comanda_contine_produs as ccp join produs as p on (ccp.ID_produs = p.ID_produs)
             where ID_comanda = comanda_id;
             
             update comanda 
             set Cost_total = @cost_total
             where ID_comanda = comanda_id;
         
         end //
 delimiter ;         
 
 drop procedure if exists pr5;
 
  delimiter //
      
        create procedure pr5()
           begin 
           
			 declare a,b int;
             declare cursor1 cursor for select ID_comanda from comanda;
             declare continue handler for not found 
             
             set b = 1;
               
			 open cursor1;
             repeat 
             
             fetch cursor1 into a;
                 call pr4(a);
                 until b=1
             
             end repeat ;
             close cursor1;
           
           end //
     delimiter ;      
 
   call pr5();
 
 
 drop procedure if exists achitare_prod;
 delimiter //
 
    create procedure achitare_prod(in CNP char(13) , in nume_produs varchar(30) ,in nume_furnizor varchar(30), in valoare float)
       begin
  
           set @comanda_id = NULL;
           set @furnizor_id = NULL;
           set @produs_id = NULL;
           set @pret_lista = NULL;
           set @discount_lista = NULL;
           
           
            select @comanda_id := ID_comanda
            from client_plaseaza_comanda
            where ID_client = CNP;
            
            select @furnizor_id :=ID_furnizor
            from furnizor
            where Nume = nume_furnizor;
            
            
            select @produs_id := ID_produs
            from produs
            where denumire_produs = nume_produs and furnizorul_produsului = @furnizor_id;
            
            select @pret_lista := pret,@discount_lista := reducere
            from produs
            where ID_produs = @produs_id;
            
            select @disp := disponibilitate 
            from comanda_contine_produs 
            where ID_produs = @produs_id and ID_comanda = @comanda_id;
            
            select @plus :=balance
            from comanda
            where ID_comanda = @comanda_id;
            
            set @pret_dupa_reducere :=find_price(@pret_lista,@discount_lista);
            
            if( @comanda_id is not null and @furnizor_id is not null and @produs_id is not null and @pret_lista is not null and @disp = 1)
               then  begin
                  
                    if(valoare + @plus >= @pret_dupa_reducere)
                         then  begin
                         
                                 update comanda_contine_produs
                                 set achitare_produs =1
                                 where ID_produs = @produs_id and ID_comanda =@comanda_id;
                                 
                                 update comanda
                                 set balance = valoare + @plus - @pret_dupa_reducere
                                 where ID_comanda = @comanda_id;
                         
                                 update client_plaseaza_comanda
                                 set suma_achitata = valoare+@plus
                                 where ID_comanda = @comanda_id;
                         
                         end ;
                       end if;  
						
               end;  
                   
           end if;
  
       end // 
   
  delimiter ; 
  
   call achitare_prod('4020403456567','Obiectiv camera','Sony',3400.0);
   
   
   drop procedure if exists proc_stergere_produs_executa_actualizari ;
  
     delimiter //
     
         create procedure proc_stergere_produs_executa_actualizari(in comanda_id int ,in prod_id int)
           begin
           
                 select @fnz_id := furnizorul_produsului
                 from produs
                 where ID_produs = prod_id;
           
                 select @cost := pret ,@reducere := reducere
                 from produs
                 where ID_produs = prod_id;
                 
                 set @pret_dupa_reducere :=find_price(@cost,@reducere);
                 
                 update comanda 
                 set Cost_total = Cost_total - @pret_dupa_reducere
                 where ID_comanda = comanda_id ;
                 
                 select @verificare_achitat := achitare_produs
                 from comanda_contine_produs
                 where ID_comanda = comanda_id and ID_produs = prod_id;
                 
                 if(@verificare_achitat = 1)
                   then  
                      begin
                      
                            update comanda
                            set balance = balance + @pret_dupa_reducere
                            where ID_comanda = comanda_id;
                            
                            update client_plaseaza_comanda
                            set suma_achitata = suma_achitata - @pret_dupa_reducere
                            where ID_comanda =comanda_id; 
                            
                            update furnizor
                            set Cantitate = Cantitate + 1
                            where ID_furnizor = @fnz_id; 
  
                      end ;
                      
                end if;      
                 
                 
           
           end //
      delimiter      
      
      call proc_stergere_produs_executa_actualizari(1,2);
      delete from comanda_contine_produs
      where ID_comanda = 1 and ID_produs=2;
