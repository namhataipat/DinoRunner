package model;

public class LeaderBoardEntry implements Comparable {
String name;
int score;
	
	
	public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public int getScore() {
	return score;
}


public void setScore(int score) {
	this.score = score;
}


	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((LeaderBoardEntry)o).score - this.score;
	}

}
