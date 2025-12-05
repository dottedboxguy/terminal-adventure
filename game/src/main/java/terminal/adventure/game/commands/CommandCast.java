package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.actors.Actor;

import terminal.adventure.game.spells.Spell;

public class CommandCast extends Command{

	public CommandCast(String[] args) {
		super(args, false);
	}

	@Override
	public String execute(Actor actor) throws invalidInputException {
			
		Spell spell = null;
		
		List<Spell> allSpells = actor.getSpells();
		
		
		if (args.length == 0) {
			return this.help()+this.spellList(allSpells);
		}
		
		//System.out.println("DEBUG execute : got spell name "+args[0]);
		
		for( Spell s : allSpells) {
			if(s.getName().equals(args[0])) {
				spell = s;
			}
		}
		
		if (spell == null) {			
			return "This spell seems to be unknown to you...\n"+spellList(allSpells);
		}
		
		
		return spell.cast(args, actor);
	}
	
	public String spellList(List<Spell> spells) {
		String res = "Known Spells :\n";
		for (Spell s : spells) {
			res += s.getName() + " : " + s.getDescription()+"\n";
		}
		return res;
	}
	

	@Override
	public String help() {
		return "which spell do you want to CAST ?\n usage : <spell name> OPTIONAL <target name>\n";
	}

	
}
