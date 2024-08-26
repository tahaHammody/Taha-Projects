package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import model.SysData;
import model.Player;

public class UpdatePlayerTest {
	
	@Test
	public void test() {
		ArrayList<Player> A = new ArrayList<>();
		Player P = new Player("Taha");
		Player P1 = new Player("Taha2");
        A.add(P1); 
        A.add(P);
		Boolean Status=	SysData.getInstance().setPlayersT(A);
		//checking if the set player method return True when Updating the player in Sysdata
		assertTrue(Status);
	}
}
