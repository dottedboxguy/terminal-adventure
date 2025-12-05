package terminal.adventure.game.gamestates;

import java.util.ArrayList;
import java.util.LinkedList;

import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.actors.Cultist;
import terminal.adventure.game.actors.Human;
import terminal.adventure.game.controllers.AIController;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.controllers.PlayerController;
import terminal.adventure.game.exits.HiddenExit;
import terminal.adventure.game.exits.OpenExit;
import terminal.adventure.game.spells.UnlockSpell;
import terminal.adventure.game.spells.VisionSpell;
import terminal.adventure.game.Console;

public class BloodCultScenario extends GameState{
    public BloodCultScenario(){
        
        super(new LinkedList<Controller>(), new ArrayList<Location>());

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
		
		// Adding one Actor + Controller to the game
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
		
		this.map.get(3).addActor(tempActor);
		this.controllers.add(tempController);
		//
		
		// Adding Player Actor + Controller to the game
		tempActor = new Human("You", "Hero of the kingdom, you're determined to kill the blood cultists");
		tempController = new PlayerController(Faction.goodGuys, console);
		tempController.bindActor(tempActor);
		
		this.map.get(0).addActor(tempActor);
		this.controllers.add(tempController);
		
		tempActor.learnSpell(new UnlockSpell());
		tempActor.learnSpell(new VisionSpell());
    }
}