package it.sha.utils;

import java.util.*;
import it.sha.beans.Amministratore;

import java.sql.*;

public class DBManager 
{
	String DbDriver;
	String DbURL;
	String username;
	String password;	
	Connection conn;
	/**
	 * Metododo che restituisce true se la connessione e' aperta.
	 */
	public boolean isOpen()
	{
		if (this.conn == null)
			return false;
		else
			return true;
	}

	/**
	 * Costruttore della classe. Riceve l'url del driver jdbc, la username e la password
	 * necessari per la connessione al database.
	 */
	public DBManager()
	{
		this.DbDriver = "com.mysql.jdbc.Driver";
		this.DbURL = "jdbc:mysql://localhost:3306/progetto"; 
		this.username = "root";
		this.password = "SIlavora1!ok";		
	}
	
	public DBManager(String url, String usn, String psw)
	{
		this.DbDriver = "com.mysql.jdbc.Driver";
		this.DbURL = url;
		this.username = usn;
		this.password = psw;	
	}
	
	private boolean startConnection()
	{
		if ( isOpen() )
			return true;
		try 
		{
			Class.forName(DbDriver);// Carica il Driver del DBMS
			conn = DriverManager.getConnection(DbURL, username, password);// Apertura connessione
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean closeConnection()
	{
		if ( !isOpen() )
			return true;
		try 
		{
			conn.close();
			conn = null;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo che esegue una query di update o delete. Riceve la stringa sql e
	 * restituisce true se la query viene eseguita correttamente, false altrimenti.
	 */
	public boolean executeUpdate(String sql)
	{
		startConnection();  //avvia una connessione
		Statement st;
		try 
		{
			st = conn.createStatement();
			st.executeUpdate(sql);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		closeConnection();
		return true;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Metodo che esegue una query di select. Riceve la stringa sql e la stringa che
	 * indica la tipologia di classe su cui si vuol restituire il vettore dei risultati.
	 * Restituisce il vettore dei risultati.
	 */
	public Vector<Object> executeSelect(String sql, String type)
	{
		startConnection();
		Vector<Object> v = new Vector<Object>();
		Statement st;
		try 
		{
			st = conn.createStatement();
		
			ResultSet rs = st.executeQuery(sql);		
			while( rs.next() )
			{
				if ( type.equals("Amministratore") )
				{
					Amministratore a;
					a= new Amministratore();
					a.setNome( rs.getString("nome") );
					a.setPassword(rs.getString("password") );
					a.setIdAdmin(rs.getInt("idAmministratore"));
					a.setEmail(rs.getString("email") );
					v.add(a);
				}
			}
		}
		
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		closeConnection();
		return v;
	}
}

