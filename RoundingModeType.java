/*  FuzzSMT: Fuzzing tool for Satisfiablity Modulo Theories (SMT) benchmarks.
 *  Copyright (C) 2009, 2014  Robert Daniel Brummayer, Tim King
 *
 *  This file is part of FuzzSMT.
 *
 *  See the file COPYING in the top-level source directory for
 *  licensing information.
 */

public class RoundingModeType extends SMTType {

  public static final RoundingModeType roundModeType = new RoundingModeType ();

  public RoundingModeType () {}

  public String toString() {
    return "RoundingMode";
  }
}
