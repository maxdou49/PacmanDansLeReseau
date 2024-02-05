/*
 * Le vecteur contenant la position(et direction) d'un agent
 */

package model;

import java.io.Serializable;

public class PositionAgent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	private int dir;

	public PositionAgent(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public boolean equals(PositionAgent other) {
		return (x == other.x) && (y == other.y);
	}

	public int squaredDistance(PositionAgent b)
	{
		PositionAgent dist = new PositionAgent(x - b.x, y - b.y, dir);
		return (dist.x * dist.x) + (dist.y * dist.y);
	}

}
