/*  FuzzSMT: Fuzzing tool for Satisfiablity Modulo Theories (SMT) benchmarks.
 *  Copyright (C) 2009, 2014  Robert Daniel Brummayer, Tim King
 *
 *  This file is part of FuzzSMT.
 *
 *  See the file COPYING in the top-level source directory for
 *  licensing information.
 */

public class FloatingPointType extends SMTType {

	protected String name;

	protected int eb; // Width of the exponent
  protected int sb; // Width of the significand


	public static final FloatingPointType Float16 = new FloatingPointType (5, 11, "Float16");
	public static final FloatingPointType Float32   = new FloatingPointType (8, 24, "Float32");
	public static final FloatingPointType Float64   = new FloatingPointType (11, 53, "Float64");
	public static final FloatingPointType Float128  = new FloatingPointType (15, 113, "Float128");

	protected void initialize(int eb, int sb, String name){
		assert(eb >= 2);
		assert(sb >= 2);
		this.eb = eb;
		this.sb = sb;
		this.name = name;		
	}

	protected FloatingPointType(int eb, int sb, String name){ initialize(eb,sb,name); }

  public FloatingPointType (int eb, int sb) {
		initialize(eb, sb, "(_ FloatingPoint "+eb+" "+ sb+")");
	}

  public String toString() {
		return this.name;
  }

	public int getExponentWidth() { return this.eb; }
	public int getSignificandWidth() { return this.sb; }

  public boolean dominates(FloatingPointType other){
    return this.eb <= other.eb && this.sb <= other.sb;
  }

  public FloatingPointType dominantType(FloatingPointType other){
    if ( this.dominates(other) ){
      return this;
    } else if (other.dominates(this)){
      return other;
    } else {
      int maxeb = Math.max(this.eb, other.eb);
      int maxsb = Math.max(this.sb, other.sb);
      return new FloatingPointType( maxeb, maxsb); 
    }
  }


	/* Two floating point types are equal if the widths are equal.
	 * The names are ignored right now.
	 */
	public boolean equals (Object o){
		assert( o != null );

		if ( !(o instanceof FloatingPointType) ){ return false; }

		FloatingPointType fpo = (FloatingPointType) o;
		return this.eb == fpo.eb && this.sb == fpo.sb;
	}

}
