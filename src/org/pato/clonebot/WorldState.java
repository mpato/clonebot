package org.pato.clonebot;

public class WorldState
{
  public interface Action {
    public void run(WorldState state, WorldState.Cell cell, CloneBot bot);
  }

  public class Cell {
    public byte age;
    public byte species;
    public CloneBot bot;
  }

  public Cell map[][];

}
