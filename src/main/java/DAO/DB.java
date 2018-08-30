package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import random.Dice;
//hello
public class DB {

	private String url = "jdbc:mysql://localhost:3306/country?autoReconnect=true&useSSL=false";
	private String username = "root";
	private String password = "root";

	public void addDice(List<Dice> dice) throws SQLException {
		Connection myConn = DriverManager.getConnection(url, username, password);
		String sql = "INSERT INTO `random_number`.`random_number` (`dice1`, `dice2`, `dice3`, `timeStamp`) VALUES (?, ?, ?, ?);";
		PreparedStatement prep = myConn.prepareStatement(sql);

		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			prep.setInt(1, dice.get(0).getNumber());
			prep.setInt(2, dice.get(1).getNumber());
			prep.setInt(3, dice.get(2).getNumber());
			prep.setString(4, sdf.format(date));
			prep.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			myConn.close();
			prep.close();
		}

	}

}
