package org.pato.clonebot;

public class CloneSpecies
{
  int cells;
  int specimens;
  int acumAge;

  public int getStrength()
  {
    return acumAge / specimens;
  }
  
  public int getDecay(CloneBot bot)
  {
    double age;
    age = bot.getAge();
    return (int) (cells / (double) specimens - (age * age) / (acumAge / (double) specimens));
  }

}
