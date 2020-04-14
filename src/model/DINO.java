package model;

public enum DINO {
	CDINO("dinochooser/cDinoL.png", "CARNIVORE", "dinochooser/cDino.png"),
	TIGER("dinochooser/tigerL.png", "CARNIVORE", "dinochooser/tiger.png"),
	HDINO("dinochooser/hDinoL.png", "HERBIVORE", "dinochooser/hDino.png"),
	MAMMOTH("dinochooser/mammothL.png", "HERBIVORE", "dinochooser/mammoth.png");
	
	private String urlDino;
	private String typeDino;
	private String urlLife;
	
	private DINO(String urlDino, String typeDino, String urlLife) {
		this.urlDino = urlDino;
		this.typeDino = typeDino;
		this.urlLife = urlLife;
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

	public void setUrlDino(String urlDino) {
		this.urlDino = urlDino;
	}

}
