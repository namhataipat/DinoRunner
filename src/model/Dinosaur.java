package model;

public enum Dinosaur {
	CDINO(ClassLoader.getSystemResource("dinochooser/cDinoL.png").toString(), ClassLoader.getSystemResource("dinochooser/cDino.png").toString(), ClassLoader.getSystemResource("cFood.png").toString(), ClassLoader.getSystemResource("hFood.png").toString()),
	TIGER(ClassLoader.getSystemResource("dinochooser/tigerL.png").toString(), ClassLoader.getSystemResource("dinochooser/tiger.png").toString(), ClassLoader.getSystemResource("cFood.png").toString(),  ClassLoader.getSystemResource("hFood.png").toString()),
	HDINO(ClassLoader.getSystemResource("dinochooser/hDinoL.png").toString(), ClassLoader.getSystemResource("dinochooser/hDino.png").toString(),  ClassLoader.getSystemResource("hFood.png").toString(),  ClassLoader.getSystemResource("cFood.png").toString()),
	MAMMOTH(ClassLoader.getSystemResource("dinochooser/mammothL.png").toString(), ClassLoader.getSystemResource("dinochooser/mammoth.png").toString(),  ClassLoader.getSystemResource("hFood.png").toString(),  ClassLoader.getSystemResource("cFood.png").toString());
	
	private String urlDino;
	private String urlLife;
	private String urlFood;
	private String urlNotYourFood;
	
	private Dinosaur(String urlDino, String urlLife, String urlFood, String urlNotYourFood) {
		this.urlDino = urlDino;
		this.urlLife = urlLife;
		this.urlFood = urlFood;
		this.urlNotYourFood = urlNotYourFood;
	}
	
	public String getUrlLife() {
		return urlLife;
	}

	public String getUrlDino() {
		return urlDino;
	}

	public String getUrlFood() {
		return urlFood;
	}

	public String getUrlNotYourFood() {
		return urlNotYourFood;
	}
	

}
