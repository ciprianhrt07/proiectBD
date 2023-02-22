package mysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class WindowInfo {

	private JFrame frmBuythingslayout;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { WindowInfo window = new WindowInfo();
	 * window.frmBuythingslayout.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the application.
	 */
	public WindowInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBuythingslayout = new JFrame();
		frmBuythingslayout.getContentPane().setBackground(Color.CYAN);
		frmBuythingslayout.setTitle("Buy_things_Layout");
		frmBuythingslayout.setForeground(Color.CYAN);
		frmBuythingslayout.setFont(new Font("Century Schoolbook", Font.PLAIN, 19));
		frmBuythingslayout.setBounds(100, 100, 965, 736);
		frmBuythingslayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBuythingslayout.getContentPane().setLayout(null);

		JTable table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column"
//			}
//		));
		table.setBounds(22, 186, 743, 503);
		frmBuythingslayout.getContentPane().add(table);
		frmBuythingslayout.setVisible(true);

		JLabel lblNewLabel = new JLabel("Welcome to buy_things aplication");
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 38));
		lblNewLabel.setBounds(22, 35, 743, 57);
		frmBuythingslayout.getContentPane().add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("VizualizareFurnizori");
		btnNewButton_1.setBounds(775, 186, 168, 21);
		frmBuythingslayout.getContentPane().add(btnNewButton_1);

		JButton butonInserare = new JButton("InserareProdus");
		butonInserare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String str = textField.getText();
				StringTokenizer st = new StringTokenizer(str, " , .;?[");
				int a = -1, b = -2;

				try {
					int count = 0;

					while (st.hasMoreTokens()) {
						++count;
						String s = st.nextToken().toString();

						if (count == 1)
							a = Integer.parseInt(s);
						else if (count == 2)
							b = Integer.parseInt(s);

						//System.out.println(s);

					}

					try {
						// connect
						Connection Mycon = (Connection) DriverManager
								.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");

						// creating a statement:
						Statement myStmt = Mycon.createStatement();

						// execute sql query
						String sql1 = " insert into comanda_contine_produs "
								+ " ( ID_produs , ID_comanda , data_achizitie ) " + " values (" + a + " , " + b + " , "
								+ " ' " + java.time.LocalDate.now() + " ' " + " ); ";
						myStmt.executeUpdate(sql1);
						System.out.println(" Insertion OK ");

					} catch (Exception ex1) {
						ex1.printStackTrace();
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmBuythingslayout, "One of the input is wrong");
				}
			}
		});
		butonInserare.setBounds(775, 251, 168, 21);
		frmBuythingslayout.getContentPane().add(butonInserare);

		JButton butonAdaugacomanda = new JButton("Adauga Comanda");
		butonAdaugacomanda.setBounds(775, 218, 168, 21);
		frmBuythingslayout.getContentPane().add(butonAdaugacomanda);

		textField = new JTextField();
		textField.setBounds(22, 125, 743, 47);
		frmBuythingslayout.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("VizualizareProduse");
		btnNewButton.setBounds(775, 155, 168, 21);
		frmBuythingslayout.getContentPane().add(btnNewButton);
		
		JButton btnModPret = new JButton("ModificaPretProdus");
		btnModPret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = textField.getText();
				StringTokenizer st = new StringTokenizer(str, " , ;?[");
				
				//str ul format din numele produsului si noul pret
				int a = -1;
				float b = (float)(-1);

				try {
					int count = 0;

					while (st.hasMoreTokens()) {
						++count;
						String s = st.nextToken().toString();

						if (count == 1)
							a = Integer.parseInt(s);
						else if (count == 2)
							b = Float.parseFloat(s);

						//System.out.println(s);

					}
					
					 // System.out.println(a+" "+b);

					try {
						// connect
						Connection Mycon = (Connection) DriverManager
								.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");

						// creating a statement:
						Statement myStmt = Mycon.createStatement();

						// execute sql query
						/*String sql1 = " insert into comanda_contine_produs "
								+ " ( ID_produs , ID_comanda , data_achizitie ) " + " values (" + a + " , " + b + " , "
								+ " ' " + java.time.LocalDate.now() + " ' " + " ); ";
						*/
						
						 String sql1 = " update produs "
							       +"set pret = "+b
							       +" where ID_produs = "+a;
						
						myStmt.executeUpdate(sql1);
						 System.out.println(" Update pret OK ");
						
						 CallableStatement mySt = null;
						 
						 mySt=Mycon.prepareCall(" {call pr2() }"); 
						 mySt.execute(); 
					      
                         System.out.println("Totalul din comenzi a fost actualizat"); 
						 
					} catch (Exception ex1) {
						ex1.printStackTrace();
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmBuythingslayout, "Pentru actualiarea pretului este nevoie de ID-ul produsului si pretul nou");
				}
			}
		});
		btnModPret.setBounds(775, 282, 165, 21);
		frmBuythingslayout.getContentPane().add(btnModPret);
		
		JButton button = new JButton("New button");
		button.setBounds(740, 313, -2, 11);
		frmBuythingslayout.getContentPane().add(button);
		
		JButton btnInserareClient = new JButton("Adauga Client");
		btnInserareClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = textField.getText();
				StringTokenizer st = new StringTokenizer(str, " , .;?[");
				
				// cand se insereaza un client nou este nevoie de Nume Prenume Cnp numar de telefon si ID_adresa
				int count = 0;
             
                String nume = null , prenume = null,cnp=null,numar_telefon=null;
                int id_adresa=-1;
				
				try {
                
				while (st.hasMoreTokens()) {
					++count;
					String s = st.nextToken().toString();
				
					if(count == 1)
					  nume = s;
					else
					if(count == 2)
					  prenume = s;
					else
					if(count == 3)
					  cnp = s;
					else
					if(count == 4)
					  numar_telefon=s;
					else
					 if(count == 5)
						 id_adresa = Integer.parseInt(s); 
					
					//System.out.println(s); 
				}
				
				 if(count!=5||cnp.length()!=13||numar_telefon.length()>13)
				 {
					 JOptionPane.showMessageDialog(frmBuythingslayout,"Nu ati introdus argumente potrivite , clientul nu poate fi inserat");
				 }else
				 {
						try {
							// connect
							Connection Mycon = (Connection) DriverManager
									.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");

							// creating a statement:
							Statement myStmt = Mycon.createStatement();

							// execute sql query
							String sql1 = " insert into client " 
							            + " (Nume , Prenume , Cnp , numar_de_telefon ,ID_adresa_client) " 
							          +" values (" + "'" +nume+"'" +" , "+ "'"+prenume+"'" +" , " 
							            + "'"+cnp+"'" +" , " + "'"+numar_telefon+"'" +" , "+ id_adresa + "); ";
							
							
							myStmt.executeUpdate(sql1);
							
							System.out.println(" Insertion OK ");

						    } catch (Exception ex1) {
							ex1.printStackTrace();
						  }
 
					 
				 }
	  
				
				}catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(frmBuythingslayout, "Input incorect mai incearca o data :)");
				}
				
				
				
			}
		});
		btnInserareClient.setBounds(775, 313, 166, 21);
		frmBuythingslayout.getContentPane().add(btnInserareClient);
		
		JButton btnVizClsiCom = new JButton("Vizualizare Clienti si Comenzi");
		btnVizClsiCom.addActionListener(new ActionListener() {
			
			DefaultTableModel initialModel = (DefaultTableModel) table.getModel();
			
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Connection Mycon = (Connection) DriverManager
							.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");
					
					Statement myStmt = Mycon.createStatement();
					
					ResultSet myRs = myStmt.executeQuery("select* from info_clienti_v2");
					
					DefaultTableModel model = new DefaultTableModel(0, 6);
					
					model.addRow(new String[] { "Nume", "Prenume", "CNP", "Produs Achizitionat","Pret","Rest de plata" });
					
					while (myRs.next()) 
					{
						Object[] row = { myRs.getString("Nume"), myRs.getString("Prenume"),
								myRs.getString("CNP"), myRs.getString("Produs Achizitionat"),myRs.getString("pret"), myRs.getString("Rest de plata")  };
						
						model.addRow(row);
					}
					
					table.setModel(model);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
				
				
			
		});
		btnVizClsiCom.setBounds(775, 344, 166, 21);
		frmBuythingslayout.getContentPane().add(btnVizClsiCom);
		
		JButton btnAtrcom = new JButton("Atribuire Comanda Clientului ");
		btnAtrcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		 
				String str = textField.getText();
				StringTokenizer st = new StringTokenizer(str, " , .;?[");
				int a = -1;
				String b = null;
				//9 0231231231231
				try {
					int count = 0;

					while (st.hasMoreTokens()) {
						++count;
						String s = st.nextToken().toString();

						if (count == 1)
							a = Integer.parseInt(s);
						else if (count == 2)
							b = s;
						//System.out.println(s);

					}
					
					System.out.println(a+"\n"+b);
					
					if(b.length()!=13||count!=2)
						System.out.println("ID_client invalid");
					else
					{
					try {
						// connect
						Connection Mycon = (Connection) DriverManager
								.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");

						// creating a statement:
						Statement myStmt = Mycon.createStatement();

						// execute sql query
						String sql1 = " insert into client_plaseaza_comanda " +
						    " ( ID_comanda , ID_client ) " + 
							" values (" + a +" , "+"'"+b+"'" + " ); ";
						myStmt.executeUpdate(sql1);
						System.out.println(" Insertion CCP OK ");

					} catch (Exception ex1) {
						ex1.printStackTrace();
					}
					
				   }
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmBuythingslayout, "One of the input is wrong");
				}
				

			}
		});
	 
		btnAtrcom.setBounds(775, 374, 168, 21);
		frmBuythingslayout.getContentPane().add(btnAtrcom);
		
		JButton btnAchitare = new JButton("Achitare_produs");
		btnAchitare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				
				String str = textField.getText();
				StringTokenizer st = new StringTokenizer(str, " , ;?[");
				
				//str ul format din numele produsului si noul pret
				
				String[] inputUtilizator = new String [20];
				int loc =0;
			    String id_utilizator=null;
			    String nume_produs=null;
			    String firma=null;
			    float pret=-1;

				 
					int count = 0;

					while (st.hasMoreTokens()) {
						++count;
						String s = st.nextToken().toString();

						 inputUtilizator[loc++]=s;
						 
						//System.out.println(s);

					}
					
				    /* for(int i=0;i<loc;i++)
						System.out.println(inputUtilizator[i]);
					
					   System.out.println(loc);
					   */
			              
				 
					   
					     if(loc==4)
					     {
					    	 id_utilizator = inputUtilizator[0];
					    	 nume_produs = inputUtilizator[1];
					    	 firma= inputUtilizator[2];
					    	 
					    	  try {	
					    		  
					    		  pret = Float.parseFloat(inputUtilizator[3]);
					    		  
					    	  }catch (NumberFormatException ex) {
					    		
					    		 JOptionPane.showMessageDialog(frmBuythingslayout,"Pretul trebuie sa fie un numar valid");  
					    		  
					    	  }
					    	 
					     }else
					    	 
					    	 if(loc==5)
						     {
						    	 id_utilizator = inputUtilizator[0];
						    	 nume_produs = inputUtilizator[1].concat(" ").concat(inputUtilizator[2]);
						    	 firma= inputUtilizator[3];
						    	 
						    	  try {	
						    		  
						    		  pret = Float.parseFloat(inputUtilizator[4]);
						    		  
						    	  }catch (NumberFormatException ex) {
						    		
						    		 JOptionPane.showMessageDialog(frmBuythingslayout,"Pretul trebuie sa fie un numar valid");  
						    		  
						    	  }
						    	 
						     }
					    	 else
					    		 JOptionPane.showMessageDialog(frmBuythingslayout,"Input invalid pentru Achitare");  
					    		 
					   
					   if(loc==4||loc==5)
					   {  
						 //  System.out.println(id_utilizator+" "+nume_produs+" "+firma+" "+pret);
					 // System.out.println(a+" "+b);
                 
				 	 try {
						// connect
						Connection Mycon = (Connection) DriverManager
								.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");

						// creating a statement:
						Statement myStmt = Mycon.createStatement();

						// execute sql query
					/*	 String sql1 = " insert into comanda_contine_produs "
								+ " ( ID_produs , ID_comanda , data_achizitie ) " + " values (" + a + " , " + b + " , "
								+ " ' " + java.time.LocalDate.now() + " ' " + " ); ";
					
			        */
				 
						
						 CallableStatement mySt = null;
						 // 5012505523242 Televizor Samsung 2000.0 //
						 mySt=Mycon.prepareCall(" {call achitare_prod("+"'"+id_utilizator+"'"+" , "+"'"+nume_produs+"'"+" , "+"'"+firma+"'"+" , "+pret+" ) }"); 
						 mySt.execute(); 
					      
                         System.out.println("Achitare produs "+nume_produs+" s-a efectuat cu succes"); 
						 
					} catch (Exception ex1) {
						ex1.printStackTrace();
					}
                   	
				} 
					 
			}
		
		});
				
			
 
		btnAchitare.setBounds(775, 405, 168, 21);
		frmBuythingslayout.getContentPane().add(btnAchitare);
		
		JButton btnStergereComanda = new JButton("Sterge Comanda");
		btnStergereComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = textField.getText();
				int id_comanda= -1;
				
				try {
					
				   id_comanda = Integer.parseInt(str);	
					
				}catch(NumberFormatException ex)
				{
				  JOptionPane.showMessageDialog(frmBuythingslayout,"Introduceti un numar valid ");	
				}
				
				
				try {
					// connect
					Connection Mycon = (Connection) DriverManager
							.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");

					// creating a statement:
					Statement myStmt = Mycon.createStatement();

					// execute sql query
					String sql1 = " delete from comanda " + " where ID_comanda = " + id_comanda;
							 
					myStmt.executeUpdate(sql1);
					System.out.println(" Stergere comanda: OK ");

				} catch (Exception ex1) {
					ex1.printStackTrace();
				}
				
				
			}
		});
		btnStergereComanda.setBounds(775, 446, 166, 21);
		frmBuythingslayout.getContentPane().add(btnStergereComanda);
		btnNewButton.addActionListener(new ActionListener() {
			DefaultTableModel initialModel = (DefaultTableModel) table.getModel();

			public void actionPerformed(ActionEvent e) {

				try {
					Connection Mycon = (Connection) DriverManager
							.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");
					Statement myStmt = Mycon.createStatement();
					ResultSet myRs = myStmt.executeQuery("select* from produs");
					DefaultTableModel model = new DefaultTableModel(0, 4);
					model.addRow(new String[] { "ID produs", "Denumire", "Pret", "Reducere" });
					while (myRs.next()) {
						Object[] row = { myRs.getString("ID_produs"), myRs.getString("denumire_produs"),
								myRs.getString("pret"), myRs.getString("reducere") };
						model.addRow(row);
					}
					table.setModel(model);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			DefaultTableModel initialModel = (DefaultTableModel) table.getModel();

			public void actionPerformed(ActionEvent e) {

				try {
					Connection Mycon = (Connection) DriverManager
							.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");
					Statement myStmt = Mycon.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from furnizor");
					DefaultTableModel model = new DefaultTableModel(0, 3);
					model.addRow(new String[] { "ID furnizor", "Nume", "Cantitate" });
					while (myRs.next()) {
						Object[] row = { myRs.getString("ID_furnizor"), myRs.getString("Nume"),
								myRs.getString("Cantitate") };
						model.addRow(row);
					}
					table.setModel(model);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		butonAdaugacomanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String str = textField.getText();
				StringTokenizer st = new StringTokenizer(str, " , .;?[");
				int a = -1, b;
				try {
					int count = 0;

					while (st.hasMoreTokens()) {
						++count;
						String s = st.nextToken().toString();

						if (count == 1)
							a = Integer.parseInt(s);
						else if (count == 2)
							b = Integer.parseInt(s);
						//System.out.println(s);

					}

					try {
						// connect
						Connection Mycon = (Connection) DriverManager
								.getConnection("jdbc:mysql://localhost:3306/buy_things", "root", "root");

						// creating a statement:
						Statement myStmt = Mycon.createStatement();

						// execute sql query
						String sql1 = " insert into comanda " + " ( id_curier) " + " values (" + a + " ); ";
						myStmt.executeUpdate(sql1);
						System.out.println(" Insertion OK ");

					} catch (Exception ex1) {
						ex1.printStackTrace();
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmBuythingslayout, "One of the input is wrong");
				}

			}
		});

	}
}
