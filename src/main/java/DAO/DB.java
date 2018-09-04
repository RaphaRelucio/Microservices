package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import random.Dice;

//hello
public class DB {

	/*
	 * private String url =
	 * "jdbc:mysql://localhost:3306/country?autoReconnect=true&useSSL=false";
	 * private String username = "root"; private String password = "root";
	 */

	public void addDice(List<Dice> dice) throws SQLException {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config/application.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Connection myConn = DriverManager.getConnection(prop.getProperty("db.mysql.url"),
				prop.getProperty("db.mysql.username"), prop.getProperty("db.mysql.password"));
		String sql = "INSERT INTO `random_number`.`random_number` (`dice1`, `dice2`, `dice3`, `timeStamp`) VALUES (?, ?, ?, ?);";
		PreparedStatement prep = myConn.prepareStatement(sql);

		try {
			if (prop.getProperty("use.database").equals("true")) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				prep.setInt(1, dice.get(0).getNumber());
				prep.setInt(2, dice.get(1).getNumber());
				prep.setInt(3, dice.get(2).getNumber());
				prep.setString(4, sdf.format(date));
				prep.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			myConn.close();
			prep.close();
		}

	}

}
