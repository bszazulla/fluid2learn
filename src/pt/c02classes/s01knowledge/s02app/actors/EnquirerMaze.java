package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Stack;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.impl.DirecaoMaze;

public class EnquirerMaze implements IEnquirer 
{

	IResponder responder;
	
	// conexão com o responder
	public void connect(IResponder responder) 
	{
		this.responder = responder;
	}
	
	public boolean discover() 
	{
		// inicia com pergunta
		String acao = "P";

		String dirUltimo;
		
		// pilha 
		Stack<DirecaoMaze> pilha = new Stack<DirecaoMaze>();
		
		while (!acao.equalsIgnoreCase("F")) 
		{
			// objeto que guarda as informacoes do estado atual
			DirecaoMaze atual = new DirecaoMaze("null");
			
			// ateh encontrar a saida
			String resposta;
			
			do
			{
				resposta = responder.ask(atual.getDirecao()); // pergunta ao responder
				
				// passagem ou saida
				if (resposta.equalsIgnoreCase("passagem")|| resposta.equalsIgnoreCase("saida")) 
				{
					if(atual.voltando()) 
					{
						responder.move(atual.getVeioDe()); // volta
						System.out.println("Movimento para " + atual.getVeioDe());
						
						atual = pilha.pop();
						atual.mudaDirecao();
					}
					else 
					{
						responder.move(atual.getDirecao()); // responder move
						System.out.println("Movimento para " + atual.getDirecao());
						
						pilha.push(atual); // coloca na pilha
						
						if (!pilha.empty())
							dirUltimo = pilha.peek().getDirecao(); // ultima direção escolhida
						else
							dirUltimo = "null"; // estah na entrada do labirinto e pilha vazia
						
						atual = new DirecaoMaze(dirUltimo); // novo objeto apontado por atual = nova tentativa de movimento
					}
				} 
				// parede
				else
					atual.mudaDirecao(); // muda a tentativa de movimento
				
			} while (!resposta.equalsIgnoreCase("saida"));
			
			// pergunta se estah na saida e pode terminar o programa
			if (responder.ask("aqui") == "saida") 
			{
				acao = "F";
			}
		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");
		
		return true;
	}
	
}
