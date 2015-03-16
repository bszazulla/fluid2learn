package pt.c02classes.s01knowledge.s01base.impl;

public class DirecaoMaze
{
	private String direcao, veioDe, dirUltimo;
	
	// construtor inicializando a sequencia de direções testadas e de onde veio
	public DirecaoMaze(String dirUltimo)
	{
		
		switch (dirUltimo) 
		{
		case "leste":
			this.direcao = "sul";
			break;
		case "oeste":
			this.direcao = "norte";
			break;
		case "norte":
			this.direcao = "leste";
			break;
		case "sul":
			this.direcao = "oeste";
			break;
		case "null":
			this.direcao = "leste";
			break;
		}	
		
		
		this.dirUltimo = dirUltimo;
		
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
	
	// capta a direção do objeto
	public String getDirecao()
	{
		return direcao;
	}
	
	// capta o ultimo movimento
	public String getVeioDe()
	{
		return veioDe;
	}
	
	// capta a direção para qual o ultimo foi
	public String getDirUltimo()
	{
		return dirUltimo;
	}
	
	// muda a direção em ordem
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
		}
	}
	
	// testa se estah voltando de onde veio
	public boolean voltando() 
	{
		if(this.direcao == this.veioDe)
			return true;
		
		return false;
	}
	

}
