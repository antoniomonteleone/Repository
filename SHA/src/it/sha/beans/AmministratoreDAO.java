package it.sha.beans;

import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import it.sha.controlSha.ControlSha;
import it.sha.utils.DBManager;

public class AmministratoreDAO 
{
private DBManager db;
	
	public AmministratoreDAO()
	{
		db=new DBManager();
	}
	
	public AmministratoreDAO(DBManager db)
	{
		this.db=db;
	}
	
	public Amministratore getAmministratore(Amministratore admin)
	{
		String query = "SELECT * FROM amministratore WHERE idAmministratore = "+ admin.getIdAdmin();
		Vector<Object> v =db.executeSelect(query, "Amministratore");
		admin = (Amministratore)v.get(0); // primo elemento del vettore e faccio il cast
		return admin;
	}
	
	
	public boolean modificaPassAdmin(Amministratore a) //restituisce se l'operazione è avvenuta o no
	{
		boolean esito;
		
		String query = "UPDATE amministratore SET password='?' WHERE idAmministratore= "+a.getIdAdmin();
		query=query.replaceFirst("[?]",a.getPassword());
		esito = db.executeUpdate(query); // accesso in scrittura
		return esito;
	}
	
	public boolean verificaAccount(Amministratore a) throws NoSuchAlgorithmException
	{
		boolean esito;
		Vector<Object> res=new Vector<Object>();
		String query= "SELECT * FROM amministratore WHERE password= '?' AND email= '?' ";
		String password=ControlSha.sha256(a.getPassword().toString());
		query=query.replaceFirst("[?]", password);
		query=query.replaceFirst("[?]", a.getEmail().toString());
		res = db.executeSelect(query, "Amministratore"); // accesso in scrittura
		if(res.size()==1)
			return esito = true;
		return false;
	}
	
	public Amministratore getPassword(Amministratore admin)
	{
		String query = "SELECT * FROM amministratore WHERE idAmministratore = "+ admin.getIdAdmin();
		Vector<Object> v =db.executeSelect(query, "Amministratore");
		admin = (Amministratore)v.get(0); // primo elemento del vettore e faccio il cast
		return admin;
	}
}


