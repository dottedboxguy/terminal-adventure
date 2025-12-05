package terminal.adventure.game.spells;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import terminal.adventure.game.actors.Actor;

public class SpellTest {

    private static class TestSpell extends Spell {
        public TestSpell(String name, String description) {
            super(name, description);
        }

        @Override
        public String cast(String[] args, Actor caster) {
            return "Spell cast successfully";
        }

        @Override
        public List<Object> getTargets(String[] args, Actor caster) {
        	List<Object> res = new ArrayList<>();
        	res.add(caster);
            return res;
        }
    }

    @Test
    public void testSpellConstructor() {
        Spell spell = new TestSpell("Fireball", "A powerful fireball");
        assertEquals("Fireball", spell.getName());
        assertEquals("A powerful fireball", spell.getDescription());
    }

    @Test
    public void testCast() {
        Spell spell = new TestSpell("Fireball", "A powerful fireball");
        Actor caster = new Actor("TestActor", "A test actor") {};
        String result = spell.cast(new String[0], caster);
        assertEquals("Spell cast successfully", result);
    }

    @Test
    public void testGetTargets() {
        Spell spell = new TestSpell("Fireball", "A powerful fireball");
        Actor caster = new Actor("TestActor", "A test actor") {};
        List<Object> targets = spell.getTargets(new String[0], caster);
        assertEquals(1, targets.size());
        assertEquals(caster, targets.get(0));
    }
}
