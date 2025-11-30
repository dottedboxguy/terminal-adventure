package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.game.Console;
import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;

public class CommandLook implements Command {

    @Override
    public void execute(String[] args, Console console) {
          if (args.length == 0) {
            // Regarder la localisation actuelle si aucun argument
            Location currentLocation = console.getPlayer().getActor().getCurrentLocation();
            System.out.println(currentLocation.getDescription());
            return;
        }
        
        for (String arg : args) {
            // Chercher les personnages correspondant au nom
            List<Actor> actors = console.getPlayer().getActor().getCurrentLocation().getActorByName(arg);
            for (Actor actor : actors) {
                System.out.println(actor.getDescription());
            }
            
            // Chercher les sorties correspondant au nom
            List<Exit> exits = console.getPlayer().getActor().getCurrentLocation().getExitByName(arg);
            for (Exit exit : exits) {
                System.out.println(exit.getDescription());
            }
            
            // Si rien n'est trouvé pour cet argument
            if (actors.isEmpty() && exits.isEmpty()) {
                System.out.println("Aucun '" + arg + "' n'est visible ici.");
            }
        }
    }
    
    @Override
    public String help() {
        return "\n Usage : \n LOOK \t ";
    }
}