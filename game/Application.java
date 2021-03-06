
package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		WorldEndGame world = new WorldEndGame(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree(), new VehiclePark(), 
				new MamboMariePortal(),new Herb());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########...................i..............",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		"......i..................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		"...........i.............#...............................##..........i..........",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#...i.X...#..........................i....",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"......................i.........................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		"......................................................i..................++++...",
		"....................i.....................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		Actor player = new Player("Player", '@', 10000);
		world.addPlayer(player, gameMap.at(42,13));
		
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		
		// Place some farmers
		gameMap.at(40, 8).addActor(new Farmer("Tri"));
		gameMap.at(42, 8).addActor(new Farmer("Jian"));
	
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
		
		gameMap.at(42, 12).addItem(new Plank());
		
		player.addItemToInventory(new Sniper(10,10,10));
		player.addItemToInventory(new Shotgun(10,10));

		player.addItemToInventory(new ShotgunShells(2));
		player.addItemToInventory(new SniperBullets(2));
		
		gameMap.at(44, 10).addItem(new ShotgunShells(5));
		gameMap.at(43, 10).addItem(new SniperBullets(5));


		
		// FIXME: Add more zombies!
		gameMap.at(74, 3).addActor(new Zombie("Groan"));
		gameMap.at(25,  18).addActor(new Zombie("Boo"));
		gameMap.at(42, 22).addActor(new Zombie("Uuuurgh"));
		gameMap.at(42,21).addActor(new Zombie("Mortalis"));

		gameMap.at(2, 3).addActor(new Zombie("Bloated"));
		gameMap.at(4, 5).addActor(new Zombie("Aaargh"));	
		
		gameMap.at(70, 22).addActor(new Zombie("Monroe"));
		gameMap.at(69, 23).addActor(new Zombie("Marilyn"));	

		gameMap.at(74, 2).addActor(new Zombie("Reece"));	

		
		//Town
		
		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Fence(),
				new Tree(), new Wall(), new Door(), new Floor());
		
		List<String> townMapString = Arrays.asList(
		"................................................................................",
		"..........#.#.......................................#.#.........................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		".......WDDW....................WWWW.............................................",
		"......W____W..................D____D.........................WWWW...............",
		".......WDDW....................WWWW.........................D____D..............",
		"............................................................D____D..............",
		".............................................................WWWW...............",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		".......WWWW............................WWWW.....................................",
		"......D____D..........................W____W....................WWWW............",
		".......WWWW...........................W____W...................D____W...........",
		".......................................WDDW....................D____W...........",
		"....................................................WWWW........WWWW............",
		"...................................................D____D.......................",
		"....................................................WWWW........................",
		"................WWWW............................................................",
		"...............D____D.................................WWWW......................",
		"...............D____D.......WWWW.....................D____D.....................",
		"................WWWW.......D____D....................D____D.....................",
		"...........................D____D.....................WWWW......................",
		"............................WWWW................................................",
		"................................................................................");
		GameMap townMap = new GameMap(groundFactory2, townMapString);
		world.addGameMap(townMap);
		String[] humans2 = {"Carlton2", "May2", "Vicente2", "Andrea2", "Wendy2",
				"Elina2", "Winter2", "Clem2", "Jacob2", "Jaquelyn2"};
		
		townMap.at(42, 16).addItem(new ShotgunShells(5));
		townMap.at(64, 17).addItem(new SniperBullets(5));
		townMap.at(64, 9).addItem(new SniperBullets(5));
		
		townMap.at(57, 25).addItem(new SniperBullets(5));

		townMap.at(17, 23).addItem(new ShotgunShells(5));
		
		townMap.at(30, 25).addItem(new ShotgunShells(5));

		
		
		// Place some farmers
		townMap.at(40, 8).addActor(new Farmer("Tri"));
		townMap.at(42, 8).addActor(new Farmer("Jian"));
		int x2, y2;
		for (String name : humans2) {
			do {
				x2 = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y2 = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (townMap.at(x2, y2).containsAnActor() && townMap.at(x2, y2).getGround().canActorEnter(new Human(name)));
			townMap.at(x2,  y2).addActor(new Human(name));	
		}
		Vehicle newVehicle = new Vehicle("Bike",townMap.at(9, 1),gameMap.at(42,11));
		Vehicle newVehicle1 = new Vehicle("Car",townMap.at(11, 1),gameMap.at(0,11));
		Vehicle newVehicle2 = new Vehicle("Ute",townMap.at(13, 1),gameMap.at(1,4));
		Vehicle newVehicle3 = new Vehicle("F1 car",townMap.at(51, 1),gameMap.at(40,24));
		Vehicle newVehicle4 = new Vehicle("Tank",townMap.at(53, 1),gameMap.at(42,24));
		Vehicle newVehicle5 = new Vehicle("Coles trolley",townMap.at(55, 1),gameMap.at(42,10));


		gameMap.at(42, 11).addItem(newVehicle);
		gameMap.at(0,11).addItem(newVehicle1);
		gameMap.at(1,4).addItem(newVehicle2);
		gameMap.at(40,24).addItem(newVehicle3);
		gameMap.at(42,24).addItem(newVehicle4);
		gameMap.at(42,10).addItem(newVehicle5);

		
		world.run();
	}
}
