package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.entity.Damageable;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Citizen Max Health")
@Description({"Modify citizens max health by:",
        "* ID",
        "* Number",
        "Will allow you to modify the maximum amount of health an NPC can have. 0.5 = half a heart, 1 = a heart, etc..."})
@RequiredPlugins("Citizens")
public class EffCitizenSetMaxHealth extends Effect {

    private Expression<Number> id;
    private Expression<Number> maxHealth;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<Number>) exp[0];
        maxHealth = (Expression<Number>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC npcH = registry.getById(id.getSingle(evt).intValue());
        Damageable npc = (Damageable) npcH.getEntity();
        npc.setMaxHealth(maxHealth.getSingle(evt).doubleValue());
    }

}
