// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:30
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg;

import srpg.RangeType;

public class Range {

	static final Range SIMPLE = new Range(RangeType.ARCH, 0, 0, 0, 0);
	final RangeType at;
	final int min;
	public final int max;
	final int minH;
	final int maxH;

	Range(RangeType at, int min, int max, int minH, int maxH) {
		this.at = at;
		this.min = min;
		this.max = max;
		this.minH = minH;
		this.maxH = maxH;
	}
}
