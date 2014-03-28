package org.pato.clonebot;

public class CloneBot
{
  public BotBrain brain;
  public BotDNA dna;
  private CloneSpecies species;

  public CloneBot replicate()
  {
    CloneBot child;
    child = new CloneBot();
    return child;
  }

  public CloneSpecies getSpecies()
  {
    return species;
  }
}
