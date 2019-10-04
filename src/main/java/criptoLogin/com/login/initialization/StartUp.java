package criptoLogin.com.login.initialization;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import criptoLogin.com.login.crypto.CryptoUtil;


@SuppressWarnings("serial")
public class StartUp extends HttpServlet{

	public void init() throws ServletException{
		System.out.println("Initializing database connection");
		try
		{
			FactoryManager.IniciateEntityFactory();
//			FactoryManager.getEntityManager().per;
			CryptoUtil.generateKey();
			CryptoUtil.carregaSinonimos();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
