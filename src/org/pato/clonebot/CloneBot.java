package org.pato.clonebot;

public class CloneBot
{
  public BotBrain brain;
  public BotDNA dna;

  public CloneBot replicate()
  {
    CloneBot child;
    child = new CloneBot();
    return child;
  }
}
