package pt.c02classes.s01knowledge.s01base.impl;

public class DirecaoMaze
{
	private String direcao, veioDe;
	
	// construtor inicializando a sequencia de direções testadas e de onde veio
	public DirecaoMaze(String dirUltimo)
	{
		this.direcao = "leste";
		
		switch (dirUltimo) 
		{
		case "leste":
			this.veioDe = "oeste";
			break;
		case "oeste":
			this.veioDe = "leste";
			break;
		case "norte":
			this.veioDe = "sul";
			break;
		case "sul":
			this.veioDe = "norte";
			break;
		case "null":
			this.veioDe = null;
			break;
		}		
	}
	
	
	public String getDirecao()
	{
		return direcao;
	}
	
	public String getVeioDe()
	{
		return veioDe;
	}
	
	public void mudaDirecao()
	{
		switch (direcao) 
		{
		case "leste":
			this.direcao = "norte";
			break;
		case "norte":
			this.direcao = "oeste";
			break;
		case "oeste":
			this.direcao = "sul";
			break;
		case "sul":
			this.direcao = "leste";
			break;
		default:
			this.direcao = "nao ha caminho aqui";
			break;
		}
	}
	
	public boolean voltando() 
	{
		if(this.direcao == this.veioDe)
			return true;
		
		return false;
	}
		
}
