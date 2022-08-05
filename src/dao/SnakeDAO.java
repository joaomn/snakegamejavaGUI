package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import snakeGame.GamePanel;

public class SnakeDAO {

	
	public void salvar (Connection conexao, int applesEaten) {
		String sql = "insert into SCORE (pontos)" + "values(?)";
		
		 GamePanel gp = new GamePanel();
		 
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1,gp.getApplesEaten(0));
			
		

			ps.execute();

		} catch (SQLException e) {
			System.out.println("erro");

			e.printStackTrace();
		}

		
		
		
		
		
	
		
		
	}

	
	
}
