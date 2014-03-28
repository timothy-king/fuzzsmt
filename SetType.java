/*  FuzzSMT: Fuzzing tool for Satisfiablity Modulo Theories (SMT) benchmarks.
 *  Copyright (C) 2009, 2014  Robert Daniel Brummayer, Kshitij Bansal
 *
 *  This file is part of FuzzSMT.
 *
 *  See the file COPYING in the top-level source directory for
 *  licensing information.
 */

public class SetType extends SMTType {

  public static final SetType setType = new SetType ();

  public SetType () {}

  public String toString() {
    return "(Set Element)";
  }

}
