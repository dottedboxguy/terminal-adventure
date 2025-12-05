package terminal.adventure.game.spells;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.HiddenExit;

public class VisionSpell extends Spell {

    public VisionSpell() {
        super("Greater Vision", "Lets you see the unseen");
    }

    @Override
    /**
     * see {@link Spell}
     */
    public String cast(String[] args, Actor caster) {
    	
    	List<Object> targets = this.getTargets(args, caster);
    	
    	if (targets.isEmpty()) {
    		return "Magic diffuse in the area, but nothing seems to happen.";
    	}
    	
    	String res = "Mana swirls in the room, you feel the air vibrate for a moment, then slowly fades away.\n";
    	for ( Object e : targets) {
    		HiddenExit exit = ((HiddenExit) e);
    		exit.unveil();
    		res+= exit.getName() + " appeared !\n";
    	}
    	return res;

    }


	@Override
	public List<Object> getTargets(String[] args, Actor caster) {
		List<Object> res = new ArrayList<>();
		
		res.addAll(caster.getCurrentLocation().getInvisibleExits().values());
		
		
		return res;
	}
}
