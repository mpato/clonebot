package org.pato.clonebot;

public class BotBrain
{
  public static class BrainInstruction
  {
    byte opCode;
    byte arg1;
    byte arg2;
    byte arg3;
  }
  public static final int INSTRUCTION_NO_OP = 0;
  public static final int INSTRUCTION_LOAD_N = 1;
  public static final int INSTRUCTION_LOAD_R = 2;
  public static final int INSTRUCTION_JUMP = 3;
  public static final int INSTRUCTION_JUMP_EQ = 4;
  public static final int INSTRUCTION_JUMP_GT = 5;
  public static final int INSTRUCTION_ADD = 6;
  public static final int INSTRUCTION_SUB = 7;
  public static final int INSTRUCTION_MUL = 8;
  public static final int INSTRUCTION_DIV = 9;
  public static final int INSTRUCTION_ACTION_MOV = -1;
  public static final int INSTRUCTION_ACTION_CLONE = -2;
  public static final int REGISTER_LIFE = -1;
  public static final int REGISTER_SPECIES = -2;
  public static final int REGISTER_CELL7_USED = -3;
  public static final int REGISTER_CELL7_SPECIES = -11;
  public static final int REGISTER_CELL7_AGE = -19;
  public WorldState.Action actionToRun;
  public final BrainInstruction instructions[] = new BrainInstruction[128];
  public final short registers[] = new short[128];

  private byte readRegister(WorldState.Cell cell, short reg)
  {
    if (reg > 0)
      return registers[reg];
    else if (cell >= REGISTER_CELL7_USED && cell <=){

    }
  }

  private byte runInstruction(BrainInstruction inst)
  {

  }
}
