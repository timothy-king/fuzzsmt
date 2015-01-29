/*  FuzzSMT: Fuzzing tool for Satisfiablity Modulo Theories (SMT) benchmarks.
 *  Copyright (C) 2015  Tim King
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

import java.util.Random;

public class Range {
  private int min;
  private int max;

  /** Creates a new empty range. */
  public Range(){
    this.min = 1;
    this.max = 0;
  }
  
  public Range(int min, int max) {
    this.min = min;
    this.max = max;
  }

  public String toString() {
    return "["+min+", "+max+"]";
  }

  public void setMin(int newMin) { this.min = newMin; }
  public void setMax(int newMax) { this.max = newMax; }

  public void setMinMax(int newMin, int newMax){
    this.min = newMin;
    this.max = newMax;
  }
  
  public int getMin() { return this.min; }
  public int getMax() { return this.max; }

  public boolean isEmpty(){ return min > max; }

  public int size(){
    if (isEmpty()){
      return 0;
    }else{
      return max - min + 1;
    }
  }

  public boolean smallRange(){
    return isEmpty() || ((long)max) - ((long) min) + (long)1 <= Integer.MAX_VALUE;
  }
  
  public int selectRandom(Random r){
    int result;

    assert (r != null);
    assert (!isEmpty());
    assert (smallRange());
    
    result = r.nextInt(size()) + min;

    assert (contains(result));
    return result;
  }

  public boolean equals (Object o){
    assert (o != null);

    if (! (o instanceof Range))
      return false;

    Range other = (Range) o;
    return this.min == other.min && this.max == other.max;
  }

  public boolean above(int l){
    return isEmpty() || l <= this.min;
  }

  public boolean contains(int i){
    return !isEmpty() || this.min <= i && i <= this.max;
  }
  
  
  public boolean subRange(int l, int h){
    return isEmpty() || l <= this.min && this.max <= h;
  }

  public boolean subRange(Range r){
    return subRange(r.min, r.max);
  }
  
  public boolean smallNonemptySubrange(int l, int h){
    return !isEmpty() && subRange(l,h) && smallRange();
  }

  public boolean smallNonemptyAbove(int l){
    return !isEmpty() && above(l) && smallRange();
  }
  
  public boolean smallNonempty(){
    return !isEmpty() && smallRange();
  }
}
