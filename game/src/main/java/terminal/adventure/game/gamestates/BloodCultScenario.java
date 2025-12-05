package terminal.adventure.game.gamestates;

import java.util.ArrayList;
import java.util.LinkedList;

import terminal.adventure.game.Console;
import terminal.adventure.game.Location;
import terminal.adventure.game.Stats;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.actors.Cultist;
import terminal.adventure.game.actors.Human;
import terminal.adventure.game.controllers.AIController;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.controllers.PlayerController;
import terminal.adventure.game.exits.HiddenExit;
import terminal.adventure.game.exits.OpenExit;
import terminal.adventure.game.interactables.Chest;
import terminal.adventure.game.inventory.items.Backpack;
import terminal.adventure.game.inventory.items.HeadEquipment;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.items.WinGem;
import terminal.adventure.game.spells.UnlockSpell;
import terminal.adventure.game.spells.VisionSpell;


public class BloodCultScenario extends GameState{
    public BloodCultScenario(){
        
        super(new LinkedList<>(), new ArrayList<>());

		Console console = new Console();

		Actor tempActor;
		Controller tempController;
		
		this.map.add( new Location("Dark Room", "A dark cellar with basic furniture scattered across the floor. A thin ray of light is piercing through the roof.") );
		this.map.add( new Location("Chest Room", "A small circular room, in the center lies an old chest."));
		this.map.add( new Location("Hole of Shame", "A simple hole, just too deep to climb back up.\nYou have a hunch there's a way to get out, but you're on your own"));
		this.map.add( new Location("Ritual Altar", "A large room circled by large marble pillars.\nThe floor is covered by several layers of all shades of red. It seems to be coming from a majestuous altar in the center."));
		
		this.map.get(0).addExit(new OpenExit(this.map.get(1), "Ruined Door", "A small wooden door, barely holding."));
		this.map.get(0).addExit(new OpenExit(this.map.get(2), "Floor Hole", "Just a hole in the ground. You think you could slip in."));
		this.map.get(2).addExit(new HiddenExit(this.map.get(3), "Wall crack", "A crack barely visible in the Wall. You think you could crawl through."));

		this.map.get(0).addItem(new Item("Rock", "Just a rock. What did you expect ?", new Stats()));
		// Adding a chest 
		Chest chest = new Chest("A rusty old chest, in pretty good condition nevertheless.", "Old Chest");

		Stats crownStats = new Stats();
		crownStats.setArmor(2);
		chest.addItem(new HeadEquipment("King Crown", "Very dusty, with rust traces here and there. Still very impressive.", crownStats ));
		
		this.map.get(1).addInteractable(chest);
		
		// Adding one Actor + Controller to the games
		tempActor = new Cultist("Small cultist");
		tempController = new AIController(Faction.badGuys);
		tempController.bindActor(tempActor);
		
		this.map.get(3).addActor(tempActor);
		this.controllers.add(tempController);
		//
		
		// Adding one Actor + Controller to the game
		tempActor = new Cultist("Tall cultist");
		tempController = new AIController(Faction.badGuys);
		tempController.bindActor(tempActor);
		Backpack satchel = new Backpack("Satchel", "A tiny bag");
		satchel.addItem(new WinGem("Waterstone", "A deep Blue stone, feels wet to the touch", new Stats()));
		tempActor.takeItem(satchel, null);
		tempActor.equip(satchel, null);
		
		this.map.get(3).addActor(tempActor);
		this.controllers.add(tempController);
		//
		
		// Adding Player Actor + Controller to the game
		tempActor = new Human("You", "Hero of the kingdom, you're determined to kill the blood cultists");
		tempController = new PlayerController(Faction.goodGuys, console);
		tempController.bindActor(tempActor);
		
		this.map.get(0).addActor(tempActor);
		this.controllers.add(tempController);
		
		tempActor.equip(new Backpack("Backpack", "Your Backpack your mama gave you."), tempController);
		
		tempActor.learnSpell(new UnlockSpell());
		tempActor.learnSpell(new VisionSpell());
    }
}