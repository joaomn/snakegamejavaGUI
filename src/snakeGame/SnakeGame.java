package snakeGame;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import dao.SnakeDAO;

public class SnakeGame {

	public static void main(String[] args) {
		String USUARIO = "fuctura";
		String SENHA = "123";
		
			

		try {
			Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", USUARIO, SENHA);
			PreparedStatement ps;
			new GameFrame();
		
		
			SnakeDAO snk = new SnakeDAO();
		
			
			GamePanel gp = new GamePanel();
			
			int num = gp.setApplesEaten(gp.getApplesEaten(0));
	
				gp.setApplesEaten(num);
			
			snk.salvar(conexao, num);
			
		}
		
		catch (SQLException e) {
			System.out.println("Ocorreu um problema ao acassar o banco de dados");
			e.printStackTrace();
		}

		
		
		
		

		
		

	}

}
