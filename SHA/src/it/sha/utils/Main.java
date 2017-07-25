package it.sha.utils;

import java.security.NoSuchAlgorithmException;

import it.sha.beans.Amministratore;
import it.sha.beans.AmministratoreDAO;

public class Main 
{

	public static void main(String[] args) 
	{
		Amministratore a=new Amministratore();
		a.setPassword("cia");
		a.setEmail("futuredili@gmail");
		AmministratoreDAO ad=new AmministratoreDAO();
		try 
		{
			System.out.println(ad.verificaAccount(a));
		}
		catch (NoSuchAlgorithmException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
