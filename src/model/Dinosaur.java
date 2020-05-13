package model;

public enum Dinosaur {
	CDINO(ClassLoader.getSystemResource("dinochooser/CDinoL.png").toString(), "CARNIVORE", ClassLoader.getSystemResource("dinochooser/cDino.png").toString(), ClassLoader.getSystemResource("cFood.png").toString()),
	TIGER(ClassLoader.getSystemResource("dinochooser/tigerL.png").toString() , "CARNIVORE", ClassLoader.getSystemResource("dinochooser/tiger.png").toString(), ClassLoader.getSystemResource("cFood.png").toString()),
	HDINO(ClassLoader.getSystemResource("dinochooser/hDinoL.png").toString(), "HERBIVORE", ClassLoader.getSystemResource("dinochooser/hDino.png").toString(),  ClassLoader.getSystemResource("hFood.png").toString()),
	MAMMOTH(ClassLoader.getSystemResource("dinochooser/mammothL.png").toString(), "HERBIVORE", ClassLoader.getSystemResource("dinochooser/mammoth.png").toString(),  ClassLoader.getSystemResource("hFood.png").toString());
	
	private String urlDino;
	private String typeDino;
	private String urlLife;
	private String urlFood;
	
	private Dinosaur(String urlDino, String typeDino, String urlLife, String urlFood) {
		this.urlDino = urlDino;
		this.typeDino = typeDino;
		this.urlLife = urlLife;
		this.urlFood = urlFood;
	}
	
	public String getUrlLife() {
		return urlLife;
	}

	public String getTypeDino() {
		return typeDino;
	}

	public String getUrlDino() {
		return urlDino;
	}

	public String getUrlFood() {
		return urlFood;
	}
	


}
