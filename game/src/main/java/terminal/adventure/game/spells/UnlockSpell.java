package terminal.adventure.game.spells;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.exits.LockedExit;

public class UnlockSpell extends Spell {

    public UnlockSpell() {
        super("Unlock", "Opens a magically sealed exit.");
    }

    @Override
    /**
     * see {@link Spell}
     */
    public String cast(String[] args, Actor caster) {
    	List<Object> targets = this.getTargets(args, caster);
    	
    	if (targets.isEmpty()) {
    		return "You've cast such a great spell... But nothing happened...\n";
    	}
    	
    	LockedExit target = (LockedExit)targets.get(0);
    	
    	target.forcedUnlock();
    	
    	return "Mana enters the lock of the "+target.getName()+", and you hear a faint mechanical sound.\n";

    }


	@Override
	public List<Object> getTargets(String[] args, Actor caster) {

		List<Object> res = new ArrayList<>();
		for (Object exit : caster.getCurrentLocation().getVisibleExits().values()) {
			// pre-filter with Exit.canCross() before to limit the number of times instanceof is called. 
			if ( !((Exit) exit).canCross() 
					&& exit instanceof LockedExit 
					&& ((LockedExit)exit).getName().equals(args[1])) {
				res.add(exit);
				return res;
			}
		}

		return res;
	}
}
