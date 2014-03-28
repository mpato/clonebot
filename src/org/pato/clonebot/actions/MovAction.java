package org.pato.clonebot.actions;

import org.pato.clonebot.CloneBot;
import org.pato.clonebot.WorldState;
import org.pato.clonebot.WorldState.Action;


public class MovAction implements Action
{
  private int x, y;

  public MovAction(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  public void run(WorldState state, WorldState.Cell cell, CloneBot bot)
  {
    state.getSpeciesStrength(bot.getSpecies());
  }
}
