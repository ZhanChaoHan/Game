// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:30
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg;

import java.util.ArrayList;
import java.util.Iterator;
import myutil.Coord;
import srpg.Field;
import srpg.Range;
import srpg.RangeType;

public class MapSearch {

	private double[][] map;
	private double[][] nakedMap;
	private int[][] height;
	private boolean[][] zoc;
	private Coord current;
	private MapSearch.Tile[][] tileMap;
	private Coord[][][] wayMap;
	private ArrayList openList;
	private ArrayList closedList;
	private final int OBSTACLE = -10;
	private static int obstacleHeight = 4;
	private static int unitHeight = 2;
	// $FF: synthetic field
	private static int[] $SWITCH_TABLE$srpg$RangeType;

	public MapSearch(Field[][] map) {
		this.map = new double[map.length][map[0].length];
		this.nakedMap = new double[map.length][map[0].length];

		int y;
		int x;
		for (y = 0; y < map.length; ++y) {
			for (x = 0; x < map[y].length; ++x) {
				this.map[y][x] = this.nakedMap[y][x] = map[y][x].fd.cost;
			}
		}

		this.height = new int[map.length][map[0].length];

		for (y = 0; y < map.length; ++y) {
			for (x = 0; x < map[y].length; ++x) {
				this.height[y][x] = map[y][x].height;
			}
		}

		this.zoc = new boolean[map.length][map[0].length];
		this.tileMap = new MapSearch.Tile[map[0].length][map.length];
		this.wayMap = new Coord[map[0].length][map.length][];
		this.openList = new ArrayList();
		this.closedList = new ArrayList();
		this.init();
	}

	public void plotObject(int x, int y) {
		this.map[y][x] = -10.0D;
	}

	public void plotZOC(Coord c) {
		this.plotZOC(((Integer) c.x).intValue(), ((Integer) c.y).intValue());
	}

	public void plotZOC(int x, int y) {
		this.zoc[y][x] = true;
	}

	public void setCurrentPos(Coord current) {
		this.current = current;
	}

	public boolean search(Coord target, int range, int jump) {
		if (this.insideMap(((Integer) target.x).intValue(),
				((Integer) target.y).intValue())
				&& this.map[((Integer) target.y).intValue()][((Integer) target.x)
						.intValue()] > 0.0D) {
			this.init();
			this.openList.add(this.tileMap[((Integer) this.current.x)
					.intValue()][((Integer) this.current.y).intValue()]);

			while (!this.openList.isEmpty()) {
				MapSearch.Tile currentT = this.getLeastScoreTile(this.openList);
				if (currentT.cost > (double) range) {
					break;
				}

				if (currentT.x == ((Integer) target.x).intValue()
						&& currentT.y == ((Integer) target.y).intValue()) {
					Coord[] var12 = new Coord[currentT.count];
					MapSearch.Tile var13 = currentT;

					for (int var14 = var12.length - 1; var14 >= 0; --var14) {
						var12[var14] = new Coord(Integer.valueOf(var13.x),
								Integer.valueOf(var13.y));
						var13 = var13.parent;
					}

					this.wayMap[currentT.x][currentT.y] = var12;
					return true;
				}

				this.closedList.add(currentT);
				if (((Integer) this.current.x).intValue() == currentT.x
						&& ((Integer) this.current.y).intValue() == currentT.y
						|| !this.zoc[currentT.y][currentT.x]) {
					int neighborX = currentT.y % 2 == 0 ? currentT.x - 1
							: currentT.x + 1;
					MapSearch.Tile[] neighbor = new MapSearch.Tile[] {
							this.pickTile(currentT.x + 1, currentT.y),
							this.pickTile(currentT.x - 1, currentT.y),
							this.pickTile(currentT.x, currentT.y + 1),
							this.pickTile(currentT.x, currentT.y - 1),
							this.pickTile(neighborX, currentT.y + 1),
							this.pickTile(neighborX, currentT.y - 1) };
					MapSearch.Tile[] var10 = neighbor;
					int var9 = neighbor.length;

					for (int var8 = 0; var8 < var9; ++var8) {
						MapSearch.Tile t = var10[var8];
						if (t != null) {
							int difH = this.height[t.y][t.x]
									- this.height[currentT.y][currentT.x];
							if (!this.openList.contains(t)
									&& !this.closedList.contains(t)
									&& this.map[t.y][t.x] > 0.0D
									&& jump >= difH && difH >= -(jump + 1)) {
								this.openList.add(t);
								t.parent = currentT;
								t.count = t.parent.count + 1;
								t.cost = t.parent.cost
										+ this.map[t.y][t.x]
										+ (double) (difH > 0 ? (float) difH / 2.0F
												: 0.0F);
								t.score = (int) t.cost
										+ distanse(
												currentT.x,
												currentT.y,
												((Integer) target.x).intValue(),
												((Integer) target.y).intValue());
							}
						}
					}
				}
			}

			return false;
		} else {
			return false;
		}
	}

	public boolean[][] search(Range range, Coord target) {
		boolean[][] availables = new boolean[this.map[0].length][this.map.length];
		int y;
		int x;
		switch ($SWITCH_TABLE$srpg$RangeType()[range.at.ordinal()]) {
		case 1:
			for (y = 0; y < this.map.length; ++y) {
				for (x = 0; x < this.map[y].length; ++x) {
					availables[x][y] = this.raySearch(
							new Coord(Integer.valueOf(x), Integer.valueOf(y)),
							range);
				}
			}

			return availables;
		case 2:
			for (y = 0; y < this.map.length; ++y) {
				for (x = 0; x < this.map[y].length; ++x) {
					availables[x][y] = this.searchFloat(x, y, range);
				}
			}

			return availables;
		case 3:
			for (y = 0; y < this.map.length; ++y) {
				for (x = 0; x < this.map[y].length; ++x) {
					availables[x][y] = this.searchPierce(
							new Coord(Integer.valueOf(x), Integer.valueOf(y)),
							range);
				}
			}

			return availables;
		case 4:
			if (target != null) {
				availables = this.searchIntervals(target, this.current, range,
						true);
			}
			break;
		default:
			availables = (boolean[][]) null;
			System.err.println("Illegal RanageType : " + range.at);
		}

		return availables;
	}

	public boolean searchFloat(int x, int y, Range range) {
		if (!this.insideMap(x, y)) {
			return false;
		} else {
			int d = distanse(((Integer) this.current.x).intValue(),
					((Integer) this.current.y).intValue(), x, y);
			int dh = Math
					.abs(this.height[y][x]
							- this.height[((Integer) this.current.y).intValue()][((Integer) this.current.x)
									.intValue()]);
			return d <= range.max && dh <= range.maxH
					&& (d >= range.min || dh >= range.minH);
		}
	}

	public boolean searchFloat(Coord target, Range range) {
		return this.searchFloat(((Integer) target.x).intValue(),
				((Integer) target.y).intValue(), range);
	}

	public boolean raySearch(Coord target, Range range) {
		return this.raySearch(target, range, this.map);
	}

	private boolean raySearch(Coord target, Range range, double[][] map) {
		if (!this.searchFloat(((Integer) target.x).intValue(),
				((Integer) target.y).intValue(), range)) {
			return false;
		} else {
			short hexSize = 128;
			double r = (double) (hexSize / 2) * 1.732D / 2.0D * 1.2D;
			Coord currentC = this.getCoordByHex(
					(double) ((Integer) this.current.x).intValue(),
					(double) ((Integer) this.current.y).intValue(), hexSize);
			Coord targetC = this.getCoordByHex(
					(double) ((Integer) target.x).intValue(),
					(double) ((Integer) target.y).intValue(), hexSize);
			double thetaL = Math.atan2(
					((Double) targetC.y).doubleValue()
							- ((Double) currentC.y).doubleValue(),
					((Double) targetC.x).doubleValue()
							- ((Double) currentC.x).doubleValue());

			for (int y = 0; y < map.length; ++y) {
				for (int x = 0; x < map[y].length; ++x) {
					if ((((Integer) target.x).intValue() != x || ((Integer) target.y)
							.intValue() != y)
							&& (((Integer) this.current.x).intValue() != x || ((Integer) this.current.y)
									.intValue() != y)
							&& distanse(((Integer) this.current.x).intValue(),
									((Integer) this.current.y).intValue(), x, y) <= range.max) {
						Coord barC = this.getCoordByHex((double) x, (double) y,
								hexSize);
						int offsetH = 0;
						if (map[y][x] <= -10.0D) {
							offsetH = obstacleHeight;
						}

						int difH = this.height[((Integer) target.y).intValue()][((Integer) target.x)
								.intValue()]
								- this.height[((Integer) this.current.y)
										.intValue()][((Integer) this.current.x)
										.intValue()];
						int h = this.height[y][x] + offsetH - unitHeight;
						if ((((Double) barC.x).doubleValue() - ((Double) currentC.x)
								.doubleValue())
								* (double) difH
								/ (((Double) targetC.x).doubleValue() - ((Double) currentC.x)
										.doubleValue())
								+ (double) this.height[((Integer) this.current.y)
										.intValue()][((Integer) this.current.x)
										.intValue()] <= (double) h
								&& (((Double) barC.y).doubleValue() - ((Double) currentC.y)
										.doubleValue())
										* (double) difH
										/ (((Double) targetC.y).doubleValue() - ((Double) currentC.y)
												.doubleValue())
										+ (double) this.height[((Integer) this.current.y)
												.intValue()][((Integer) this.current.x)
												.intValue()] <= (double) h) {
							double theta = Math.atan2(
									((Double) barC.y).doubleValue()
											- ((Double) currentC.y)
													.doubleValue(),
									((Double) barC.x).doubleValue()
											- ((Double) currentC.x)
													.doubleValue())
									- thetaL;
							if (Math.cos(theta) >= 0.0D
									&& Math.cos(thetaL
											- Math.atan2(
													((Double) targetC.y)
															.doubleValue()
															- ((Double) barC.y)
																	.doubleValue(),
													((Double) targetC.x)
															.doubleValue()
															- ((Double) barC.x)
																	.doubleValue())) >= 0.0D
									&& Math.hypot(
											((Double) barC.x).doubleValue()
													- ((Double) currentC.x)
															.doubleValue(),
											((Double) barC.y).doubleValue()
													- ((Double) currentC.y)
															.doubleValue())
											* Math.abs(Math.sin(theta)) < r) {
								return false;
							}
						}
					}
				}
			}

			return true;
		}
	}

	private boolean searchPierce(Coord target, Range range) {
		return this.raySearch(target, range, this.nakedMap);
	}

	private boolean[][] searchIntervals(Coord current, Coord target,
			Range range, boolean pierceUnit) {
		Range span = new Range(
				RangeType.ARCH,
				0,
				distanse(current, target),
				0,
				Math.abs(this.height[((Integer) current.y).intValue()][((Integer) current.x)
						.intValue()]
						- this.height[((Integer) target.y).intValue()][((Integer) target.x)
								.intValue()]));
		boolean[][] availables = new boolean[this.map[0].length][this.map.length];
		availables[((Integer) target.x).intValue()][((Integer) target.y)
				.intValue()] = true;
		if (!this.searchFloat(((Integer) target.x).intValue(),
				((Integer) target.y).intValue(), span)) {
			return availables;
		} else {
			short hexSize = 128;
			double r = (double) (hexSize / 2) * 1.732D / 2.0D
					* (1.2D + (double) range.max);
			Coord currentC = this.getCoordByHex(
					(double) ((Integer) current.x).intValue(),
					(double) ((Integer) current.y).intValue(), hexSize);
			Coord targetC = this.getCoordByHex(
					(double) ((Integer) target.x).intValue(),
					(double) ((Integer) target.y).intValue(), hexSize);
			double thetaL = Math.atan2(
					((Double) targetC.y).doubleValue()
							- ((Double) currentC.y).doubleValue(),
					((Double) targetC.x).doubleValue()
							- ((Double) currentC.x).doubleValue());

			for (int y = 0; y < this.map.length; ++y) {
				for (int x = 0; x < this.map[y].length; ++x) {
					if ((((Integer) target.x).intValue() != x || ((Integer) target.y)
							.intValue() != y)
							&& (((Integer) current.x).intValue() != x || ((Integer) current.y)
									.intValue() != y)
							&& distanse(((Integer) current.x).intValue(),
									((Integer) current.y).intValue(), x, y) <= span.max) {
						Coord barC = this.getCoordByHex((double) x, (double) y,
								hexSize);
						double theta = Math.atan2(
								((Double) barC.y).doubleValue()
										- ((Double) currentC.y).doubleValue(),
								((Double) barC.x).doubleValue()
										- ((Double) currentC.x).doubleValue())
								- thetaL;
						if (Math.cos(theta) >= 0.0D
								&& Math.cos(thetaL
										- Math.atan2(
												((Double) targetC.y)
														.doubleValue()
														- ((Double) barC.y)
																.doubleValue(),
												((Double) targetC.x)
														.doubleValue()
														- ((Double) barC.x)
																.doubleValue())) >= 0.0D
								&& Math.hypot(
										((Double) barC.x).doubleValue()
												- ((Double) currentC.x)
														.doubleValue(),
										((Double) barC.y).doubleValue()
												- ((Double) currentC.y)
														.doubleValue())
										* Math.abs(Math.sin(theta)) < r) {
							availables[x][y] = true;
						}
					}
				}
			}

			return availables;
		}
	}

	private boolean isOnRay(int x, int y, Coord current, Coord target,
			Coord currentC, Coord targetC, Coord barC, int offsetH) {
		int difH = this.height[((Integer) target.y).intValue()][((Integer) target.x)
				.intValue()]
				- this.height[((Integer) current.y).intValue()][((Integer) current.x)
						.intValue()];
		int h = this.height[y][x] + offsetH - unitHeight;
		return (((Double) barC.x).doubleValue() - ((Double) currentC.x)
				.doubleValue())
				* (double) difH
				/ (((Double) targetC.x).doubleValue() - ((Double) currentC.x)
						.doubleValue())
				+ (double) this.height[((Integer) current.y).intValue()][((Integer) current.x)
						.intValue()] <= (double) h
				&& (((Double) barC.y).doubleValue() - ((Double) currentC.y)
						.doubleValue())
						* (double) difH
						/ (((Double) targetC.y).doubleValue() - ((Double) currentC.y)
								.doubleValue())
						+ (double) this.height[((Integer) current.y).intValue()][((Integer) current.x)
								.intValue()] <= (double) h;
	}

	private Coord getCoordByHex(Coord c) {
		return this.getCoordByHex((double) ((Integer) c.x).intValue(),
				(double) ((Integer) c.y).intValue());
	}

	private Coord getCoordByHex(double x, double y) {
		return this.getCoordByHex(x, y, 128);
	}

	private Coord getCoordByHex(double x, double y, int hexSize) {
		return new Coord(Double.valueOf(x * (double) hexSize
				+ (double) (y % 2.0D == 0.0D ? 0 : hexSize / 2)),
				Double.valueOf(y * (double) hexSize * 3.0D / 4.0D));
	}

	public Coord[] getWay(int x, int y) {
		return this.wayMap[x][y];
	}

	public static int distanse(Coord c0, Coord c1) {
		return distanse(((Integer) c0.x).intValue(),
				((Integer) c0.y).intValue(), ((Integer) c1.x).intValue(),
				((Integer) c1.y).intValue());
	}

	public static int distanse(int x0, int y0, int x1, int y1) {
		x0 = toDoubleXCoord(x0, y0);
		x1 = toDoubleXCoord(x1, y1);
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);
		return dy > dx ? dy : (dx + dy) / 2;
	}

	private static Coord toVectorCoord(Coord c) {
		c.x = Integer.valueOf(toVectorCoord(((Integer) c.x).intValue(),
				((Integer) c.y).intValue()));
		return c;
	}

	private static int toVectorCoord(int x, int y) {
		return x - y / 2;
	}

	private static int toDoubleXCoord(int x, int y) {
		return x * 2 + (y % 2 == 0 ? -1 : 0);
	}

	private void init() {
		this.openList.clear();
		this.closedList.clear();

		for (int y = 0; y < this.map.length; ++y) {
			for (int x = 0; x < this.map[y].length; ++x) {
				this.tileMap[x][y] = new MapSearch.Tile(x, y);
			}
		}

	}

	private MapSearch.Tile getLeastScoreTile(ArrayList al) {
		MapSearch.Tile min = (MapSearch.Tile) al.get(0);
		Iterator var4 = al.iterator();

		while (var4.hasNext()) {
			MapSearch.Tile t = (MapSearch.Tile) var4.next();
			if (min.score > t.score) {
				min = t;
			}
		}

		al.remove(min);
		return min;
	}

	private MapSearch.Tile pickTile(int x, int y) {
		return !this.insideMap(x, y) ? null : this.tileMap[x][y];
	}

	private boolean insideMap(int x, int y) {
		return x >= 0 && x < this.map[0].length && y >= 0
				&& y < this.map.length;
	}

	// $FF: synthetic method
	static int[] $SWITCH_TABLE$srpg$RangeType() {
		if ($SWITCH_TABLE$srpg$RangeType != null) {
			return $SWITCH_TABLE$srpg$RangeType;
		} else {
			int[] var0 = new int[RangeType.values().length];

			try {
				var0[RangeType.ARCH.ordinal()] = 2;
			} catch (NoSuchFieldError var4) {
				;
			}

			try {
				var0[RangeType.INTERVAL.ordinal()] = 4;
			} catch (NoSuchFieldError var3) {
				;
			}

			try {
				var0[RangeType.PIERCE.ordinal()] = 3;
			} catch (NoSuchFieldError var2) {
				;
			}

			try {
				var0[RangeType.RAY.ordinal()] = 1;
			} catch (NoSuchFieldError var1) {
				;
			}

			$SWITCH_TABLE$srpg$RangeType = var0;
			return var0;
		}
	}

	private class Tile {

		MapSearch.Tile parent;
		int x;
		int y;
		double cost;
		int score;
		int count;

		Tile(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
