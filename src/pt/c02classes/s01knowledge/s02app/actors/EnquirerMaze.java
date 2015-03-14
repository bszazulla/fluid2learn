package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Stack;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.impl.DirecaoMaze;

public class EnquirerMaze implements IEnquirer 
{

	IResponder responder;
	
	public void connect(IResponder responder) 
	{
		this.responder = responder;
	}
	
	public boolean discover() 
	{
		// inicia com pergunta
		String acao = "P";
		// de onde veio
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
				resposta = responder.ask(atual.getDirecao());
				
				if (resposta == "passagem" || resposta == "saida") 
				{
					// verifica se nao esta voltando sem necessidade
					if(!atual.voltando())
					{
						responder.move(atual.getDirecao());
						
						pilha.push(atual); // coloca na pilha
						System.out.println("Movimento para " + atual.getDirecao());
						
						DirecaoMaze aux = pilha.peek(); // ultimo local passado
						dirUltimo = aux.getDirecao(); // captura a direcao do ultimo local passado
					
						atual = new DirecaoMaze(dirUltimo); // novo objeto	
					}
				} 
				else
				{
					atual.mudaDirecao(); // muda a tentativa de movimento
					
					// se nao ha caminho aqui e tem que voltar para um ponto anterior
					if(atual.getDirecao().equalsIgnoreCase("nao ha caminho aqui"))
					{
						pilha.push(atual);						
						atual = pilha.peek(); 
					}
				}
			} while (resposta != "saida");
			
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
