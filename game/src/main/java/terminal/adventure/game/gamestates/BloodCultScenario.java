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
import terminal.adventure.game.spells.UnlockSpell;
import terminal.adventure.game.spells.VisionSpell;

/**
 * Game scenario featuring blood cultists in a dungeon-like environment.
 * This scenario includes multiple rooms, enemies, and a player character.
 */
public class BloodCultScenario extends GameState {
    
    /**
     * Constructs a new Blood Cult scenario with:
     * - 4 interconnected locations
     * - 2 cultist enemies
     * - 1 player character
     * - Items and interactables (chest with crown)
     * - Spells for the player
     */
    public BloodCultScenario() {
        super(new LinkedList<>(), new ArrayList<>());

        Console console = new Console();
        
        // Initialize locations
        initializeLocations();
        
        // Initialize exits between locations
        initializeExits();
        
        // Add items to locations
        initializeItems();
        
        // Add chest with crown to room 1
        initializeChest();
        
        // Add cultist enemies
        initializeEnemies();
        
        // Add player character
        initializePlayer(console);
    }
    
    /**
     * Initializes the four locations in this scenario.
     */
    private void initializeLocations() {
        this.map.add(new Location(
            "Dark Room", 
            "A dark cellar with basic furniture scattered across the floor. " +
            "A thin ray of light is piercing through the roof."
        ));
        
        this.map.add(new Location(
            "Chest Room", 
            "A small circular room, in the center lies an old chest."
        ));
        
        this.map.add(new Location(
            "Hole of Shame", 
            "A simple hole, just too deep to climb back up.\n" +
            "You have a hunch there's a way to get out, but you're on your own"
        ));
        
        this.map.add(new Location(
            "Ritual Altar", 
            "A large room circled by large marble pillars.\n" +
            "The floor is covered by several layers of all shades of red. " +
            "It seems to be coming from a majestuous altar in the center."
        ));
    }
    
    /**
     * Initializes the exits between locations.
     */
    private void initializeExits() {
        // Dark Room <-> Chest Room
        this.map.get(0).addExit(new OpenExit(
            this.map.get(1), 
            "Ruined Door", 
            "A small wooden door, barely holding."
        ));
        
        // Dark Room -> Hole of Shame
        this.map.get(0).addExit(new OpenExit(
            this.map.get(2), 
            "Floor Hole", 
            "Just a hole in the ground. You think you could slip in."
        ));
        
        // Chest Room -> Dark Room
        this.map.get(1).addExit(new OpenExit(
            this.map.get(0), 
            "Ruined Door", 
            "A small wooden door, barely holding."
        ));
        
        // Hole of Shame -> Ritual Altar (hidden exit)
        this.map.get(2).addExit(new HiddenExit(
            this.map.get(3), 
            "Wall crack", 
            "A crack barely visible in the Wall. You think you could crawl through."
        ));
    }
    
    /**
     * Initializes items placed in the world.
     */
    private void initializeItems() {
        // Add a rock to the Dark Room
        this.map.get(0).addItem(new Item(
            "Rock", 
            "Just a rock. What did you expect ?", 
            new Stats()
        ));
    }
    
    /**
     * Initializes the chest in the Chest Room with a crown inside.
     */
    private void initializeChest() {
        Chest chest = new Chest(
            "A rusty old chest, in pretty good condition nevertheless.", 
            "Old Chest"
        );

        Stats crownStats = new Stats();
        crownStats.setArmor(2);
        
        chest.addItem(new HeadEquipment(
            "King Crown", 
            "Very dusty, with rust traces here and there. Still very impressive.", 
            crownStats
        ));
        
        this.map.get(1).addInteractable(chest);
    }
    
    /**
     * Initializes the cultist enemies in the scenario.
     */
    private void initializeEnemies() {
        // First cultist in Dark Room
        Actor cultist1 = new Cultist("Small cultist");
        Controller controller1 = new AIController(Faction.badGuys);
        controller1.bindActor(cultist1);
        this.map.get(0).addActor(cultist1);
        this.controllers.add(controller1);
        
        // Second cultist in Dark Room
        Actor cultist2 = new Cultist("Tall cultist");
        Controller controller2 = new AIController(Faction.badGuys);
        controller2.bindActor(cultist2);
        this.map.get(0).addActor(cultist2);
        this.controllers.add(controller2);
    }
    
    /**
     * Initializes the player character with equipment and spells.
     * 
     * @param console The console for player input/output
     */
    private void initializePlayer(Console console) {
        Actor playerActor = new Human(
            "You", 
            "Hero of the kingdom, you're determined to kill the blood cultists"
        );
        
        Controller playerController = new PlayerController(Faction.goodGuys, console);
        playerController.bindActor(playerActor);
        
        this.map.get(0).addActor(playerActor);
        this.controllers.add(playerController);
        
        // Equip the player with a backpack
        playerActor.equip(
            new Backpack("Backpack", "Your Backpack your mama gave you."), 
            playerController
        );
        
        // Teach spells to the player
        playerActor.learnSpell(new UnlockSpell());
        playerActor.learnSpell(new VisionSpell());
    }
}