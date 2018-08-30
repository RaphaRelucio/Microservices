package random;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DAO.DB;

@CrossOrigin
@RestController
public class RandomController {

	@RequestMapping("/randomNumber")
	public List<Dice> getRandomNumber() {

		List<Dice> dice = new ArrayList<Dice>();
		DB db = new DB();
		Random r = new Random();
		

		for (int i = 0; i < 3; i++) {
			Dice dc = new Dice();
			dc.setName("Dice " + (i+1) + ": ");
			dc.setNumber(r.nextInt(6) + 1);
			dice.add(dc);
		}

		try {
			db.addDice(dice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dice;
	}

}
