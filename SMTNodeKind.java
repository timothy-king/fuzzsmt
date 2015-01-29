/*  FuzzSMT: Fuzzing tool for Satisfiablity Modulo Theories (SMT) benchmarks.
 *  Copyright (C) 2009  Robert Daniel Brummayer
 *
 *  This file is part of FuzzSMT.
 *
 *  FuzzSMT is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  FuzzSMT is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public enum SMTNodeKind { 

  /* input */
  CONST("constant", 0),
  VAR("variable", 0), 
  /* boolean */
  TRUE("true", 0),
  FALSE("false", 0),
  NOT("not", 1), 
  AND("and", 2),
  OR("or", 2),
  IMPLIES("=>", 2),
  XOR("xor", 2),
  IFF("=", 2),
  IF_THEN_ELSE("ite", 3), // if-then-else on boolean operands 
  /* bit-vector operators: */
  /* unary operators */
  BVNOT("bvnot", 1),
  BVNEG("bvneg", 1),
  /* unary operators with index */
  EXTRACT("(_ extract", 1),
  REPEAT("(_ repeat", 1),
  ZERO_EXTEND("(_ zero_extend", 1),
  SIGN_EXTEND("(_ sign_extend", 1),
  ROTATE_LEFT("(_ rotate_left", 1),
  ROTATE_RIGHT("(_ rotate_right", 1),
  /* commutative binary operators */
  BVAND("bvand", 2),
  BVNAND("bvnand", 2),
  BVOR("bvor", 2),
  BVNOR("bvnor", 2),
  BVXOR("bvxor", 2),
  BVXNOR("bvxnor", 2),
  BVADD("bvadd", 2),
  BVMUL("bvmul", 2),
  BVCOMP("bvcomp", 2),
  /* non-commutative binary operators */
  BVULT("bvult", 2),
  BVULE("bvule", 2),
  BVUGT("bvugt", 2),
  BVUGE("bvuge", 2),
  BVSLT("bvslt", 2),
  BVSLE("bvsle", 2),
  BVSGT("bvsgt", 2),
  BVSGE("bvsge", 2),
  BVSHL("bvshl", 2),
  BVLSHR("bvlshr", 2),
  BVASHR("bvashr", 2),
  BVSUB("bvsub", 2),
  BVUDIV("bvudiv", 2),
  BVUREM("bvurem", 2),
  BVSDIV("bvsdiv", 2),
  BVSREM("bvsrem", 2),
  BVSMOD("bvsmod", 2),
  CONCAT("concat", 2),
  /* interpreted predicates */
  LT("<", 2),
  GT(">", 2),
  LE("<=", 2),
  GE(">=", 2),
  EQ("=", 2),
  DISTINCT("distinct", -1),
  /* interpreted functions */
  PLUS("+", 2),
  UNMINUS("-", 1),
  BINMINUS("-", 2),
  MUL("*", 2),
  DIV("/", 2),
  /* array operators */
  SELECT("select", 2),
  STORE("store", 3),
  /* if-then-else on terms */
  ITE("ite", 3),
  UFUNC("extrafun", -1),
  UPRED("extrapred", -1),
  /* set operators */
  UNION("union", 2),
  INTERSECTION("intersection", 2),
  SETMINUS("setminus", 2),

	/* floating point operators
	* Follows roughly the order given in:
	*   http://smtlib.cs.uiowa.edu/theories/FloatingPoint.smt2 */

	/* Rounding modes */
	ROUNDNEARESTTIESTOEVEN("roundNearestTiesToEven", 0),
	ROUNDNEARESTTIESTOAWAY("roundNearestTiesToAway", 0),
	ROUNDTOWARDSPOSITIVE("roundTowardPositive", 0),
	ROUNDTOWARDNEGATIVE("roundTowardNegative", 0),
	ROUNDTOWARDSZERO("roundTowardZero", 0),
	/* Short names for rounding modes */
	RNE("RNE", 0),
	RNA("RNA", 0),
	RTP("RTP", 0),
	RTN("RTN", 0),
	RTZ("RTZ", 0),

	/* Floating Point Sort */
	/* FP: Constant constructors */
	FPCONST("fp", 3),
	FPPOSINFINITY("(_ +oo", 0),
	FPNEGINFINITY("(_ -oo", 0),
	FPPOSZERO("(_ +zero", 0),
	FPNEGZERO("(_ -zero", 0),
	FPNAN("(_ NaN", 0),
	
	/* FP: Operators */
	FPABS("fp.abs", 1),
	FPNEGATION("fp.neg", 1),
	FPADD("fp.add", 3),
	FPSUB("fp.sub", 3),
	FPMUL("fp.mul", 3),
	FPDIV("fp.div", 3),
	FPFMA("fp.fma", 4),
	FPSQRT("fp.sqrt", 2),
	FPREM("fp.rem", 2),
	FPROUNDTOINTEGRAL("fp.roundToIntegral", 2),
	FPMIN("fp.min", 2),
	FPMAX("fp.max", 2),

	/* FP : Predicates */
	FPLEQ("fp.leq", 2),
	FPLT("fp.lt", 2),
	FPGEQ("fp.geq", 2),
	FPGT("fp.gt", 2),
	FPEQ("fp.eq", 2),
	FPISNORMAL("fp.isNormal", 1),
	FPISSUBNORMAL("fp.isSubnormal", 1),
	FPISZERO("fp.isZero", 1),
	FPISINFINITE("fp.isInfinite", 1),
	FPISNAN("fp.isNaN", 1),
	FPISNEGATIVE("fp.isNegative", 1),
	FPISPOSITIVE("fp.isPositive", 1),

	/* FP: Conversions from other sorts */
	BVTOFP("(_ to_fp", 1),   /* ((_ to_fp eb sb) (_ BitVec m) (_ FloatingPoint eb sb)) */
	FPTOFP("(_ to_fp", 2),   /* ((_ to_fp eb sb) RoundingMode (_ FloatingPoint mb nb) (_ FloatingPoint eb sb))*/
	REALTOFP("(_ to_fp", 2), /* ((_ to_fp eb sb) RoundingMode Real (_ FloatingPoint eb sb))*/
	SMITOFP("(_ to_fp", 2),  /* ((_ to_fp eb sb) RoundingMode (_ BitVec m) (_ FloatingPoint eb sb)) */
	UMITOFP("(_ to_fp_unsigned", 2) /*((_ to_fp_unsigned eb sb) RoundingMode (_ BitVec m) (_ FloatingPoint eb sb)) */
	;

  public static final int NARY = -1;
  
  protected String string;

  protected int arity; /* -1 is used to indicate n-ary operators */

  SMTNodeKind (String string, int arity){
    this.string = string;
    this.arity = arity;
  }

  public String getString(){
    return this.string;
  }

  public int getArity() {
    return this.arity;
  }

}
