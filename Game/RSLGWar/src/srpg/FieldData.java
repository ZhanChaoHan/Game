// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg;

public class FieldData {

	public static FieldData heichi = new FieldData(new String[] { "heichi",
			"heichi_kabe1", "sougen_kabe2" }, 1.0D, 1.0D);
	public static FieldData sougen = new FieldData(new String[] { "sougen",
			"sougen_kabe1", "sougen_kabe2" }, 1.2D, 0.9D);
	public static FieldData arechi = new FieldData(new String[] { "arechi",
			"arechi_kabe1", "sougen_kabe2" }, 2.0D, 0.8D);
	public static FieldData obstacle = new FieldData(new String[] { "sougen",
			"sougen_kabe1", "sougen_kabe2" }, -10.0D, 1.0D);
	final String[] graphicName;
	public final double cost;
	public final double damageEffect;

	FieldData(String[] graphicName, double cost, double damageEffect) {
		this.graphicName = graphicName;
		this.cost = cost;
		this.damageEffect = damageEffect;
	}
}
