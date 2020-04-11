package model;

public enum DINO {
	CDINO("dinochooser/cDino.png"),
	TIGER("dinochooser/tiger.png"),
	HDINO("dinochooser/hDino.png"),
	MAMMOTH("dinochooser/mammoth.png");
	
	private String urlDino;
	
	private DINO(String urlDino) {
		this.urlDino = urlDino;
	}
	
	public String getUrlDino() {
		return urlDino;
	}

}
