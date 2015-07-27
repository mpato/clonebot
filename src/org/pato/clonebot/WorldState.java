package org.pato.clonebot;

public class WorldState
{

  public interface Action
  {
    public void run(WorldState state, WorldState.Cell cell, CloneBot bot);
  }

  public class Cell
  {
    public byte age;
    public byte species;
    public CloneBot bot;
    private int x, y;

    public WorldState getWorld()
    {
      return WorldState.this;
    }

    public Cell getNeighboor(int neighboor)
    {
      return getWorld().getNeighboor(this, neighboor);
    }

    public boolean isNeighboorInUse(int neighboor)
    {
      Cell cell;
      cell = getNeighboor(neighboor);
      return cell != null && cell.bot != null;
    }

    public byte getNeighboorSpecies(int neighboor)
    {
      Cell cell;
      cell = getNeighboor(neighboor);
      if (cell == null)
        return 0;
      return cell.species;
    }

    public byte getNeighboorAge(int neighboor)
    {
      Cell cell;
      cell = getNeighboor(neighboor);
      if (cell == null)
        return 0;
      return cell.age;
    }
  }

  public Cell map[][];

  public Cell getNeighboor(Cell cell, int neighboor)
  {
    int x, y;
    x = cell.x - 1 + neighboor % 3;
    y = cell.y - 1 + neighboor / 3;
    return getCellAt(x, y);
  }

  public Cell getCellAt(int x, int y)
  {
    return null;
  }
}
