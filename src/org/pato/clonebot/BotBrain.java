package org.pato.clonebot;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import org.pato.clonebot.WorldState.Action;
import org.pato.clonebot.actions.MovAction;

public class BotBrain
{
  public static class BrainInstruction
  {
    byte opCode;
    byte arg1;
    byte arg2;
    byte arg3;
  }

  public static final int MAX_NUMBER_INST = 128;
  public static final int INSTRUCTION_NO_OP = 0;
  public static final int INSTRUCTION_HALT = 1;
  public static final int INSTRUCTION_LOAD_N = 2;
  public static final int INSTRUCTION_LOAD_R = 3;
  public static final int INSTRUCTION_JUMP = 4;
  public static final int INSTRUCTION_JUMP_EQ = 5;
  public static final int INSTRUCTION_JUMP_GT = 6;
  public static final int INSTRUCTION_ADD = 7;
  public static final int INSTRUCTION_SUB = 8;
  public static final int INSTRUCTION_MUL = 9;
  public static final int INSTRUCTION_DIV = 10;
  public static final int INSTRUCTION_MOD = 11;
  public static final int INSTRUCTION_ACTION_MOV = -1;
  public static final int INSTRUCTION_ACTION_CLONE = -2;
  public static final int REGISTER_LIFE = -1;
  public static final int REGISTER_SPECIES = -2;
  public static final int REGISTER_CELL0_USED = -10;
  public static final int REGISTER_CELL0_SPECIES = -18;
  public static final int REGISTER_CELL0_AGE = -26;
  public WorldState.Action actionToRun;
  public final BrainInstruction instructions[] = new BrainInstruction[MAX_NUMBER_INST];
  public final byte registers[] = new byte[128];
  public byte nextInst = 0;

  private byte readRegister(WorldState.Cell cell, byte reg)
  {
    if (reg >= 0)
      return registers[reg];
    else if (reg >= REGISTER_CELL0_USED && reg < (REGISTER_CELL0_USED + 8))
      return (byte) (cell.isNeighboorInUse(reg - REGISTER_CELL0_USED) ? 1 : 0);
    else if (reg >= REGISTER_CELL0_SPECIES && reg < (REGISTER_CELL0_SPECIES + 8))
      return cell.getNeighboorSpecies(reg - REGISTER_CELL0_SPECIES);
    else if (reg >= REGISTER_CELL0_AGE && reg < (REGISTER_CELL0_AGE + 8))
      return cell.getNeighboorAge(reg - REGISTER_CELL0_AGE);
    return 0;
  }

  private void writeRegister(byte reg, byte value)
  {
    if (reg < 0)
      registers[reg] = value;
  }

  private void jump(int value)
  {
    nextInst = (byte) (value % MAX_NUMBER_INST);
    if (nextInst < 0)
      nextInst += MAX_NUMBER_INST;
  }

  private Action runInstruction(WorldState.Cell cell, BrainInstruction inst)
  {
    jump(nextInst + 1);
    if (inst == null)
      return null;
    switch (inst.opCode) {
      case INSTRUCTION_HALT:
        break;
      case INSTRUCTION_LOAD_N:
        writeRegister(inst.arg1, inst.arg2);
        break;
      case INSTRUCTION_LOAD_R:
        writeRegister(inst.arg1, readRegister(cell, inst.arg2));
        break;
      case INSTRUCTION_JUMP:
        jump(inst.arg1);
        break;
      case INSTRUCTION_JUMP_EQ:
        if (inst.arg2 == inst.arg3)
          jump(inst.arg1);
        break;
      case INSTRUCTION_JUMP_GT:
        if (inst.arg2 > inst.arg3)
          jump(inst.arg1);
        break;
      case INSTRUCTION_ADD:
        writeRegister(inst.arg1, (byte) (readRegister(cell, inst.arg2) + readRegister(cell, inst.arg3)));
        break;
      case INSTRUCTION_SUB:
        writeRegister(inst.arg1, (byte) (readRegister(cell, inst.arg2) - readRegister(cell, inst.arg3)));
        break;
      case INSTRUCTION_MUL:
        writeRegister(inst.arg1, (byte) (readRegister(cell, inst.arg2) * readRegister(cell, inst.arg3)));
        break;
      case INSTRUCTION_DIV:
        writeRegister(inst.arg1, (byte) (readRegister(cell, inst.arg2) / readRegister(cell, inst.arg3)));
        break;
      case INSTRUCTION_MOD:
        writeRegister(inst.arg1, (byte) (readRegister(cell, inst.arg2) % readRegister(cell, inst.arg3)));
        break;
      case INSTRUCTION_ACTION_MOV:
        return new MovAction(readRegister(cell, inst.arg1), readRegister(cell, inst.arg2));
    }
    return null;
  }
}
