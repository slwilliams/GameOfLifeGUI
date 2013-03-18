package uk.ac.cam.slw69;

public class PackedLong
{
  public static boolean get(long packed, int pos)
  {
    long mask = 1L;
    mask <<= pos;
    return ((mask&packed) != 0) ? true : false;
  }

  public static long set(long packed, int pos, boolean value)
  {
    long mask = 1L;
    mask <<= pos;
    return value ? (packed |= mask) : (packed &= ~mask);
  }
}

