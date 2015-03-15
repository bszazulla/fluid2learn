package pt.c02classes.s01knowledge.s02app.app;

import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;

public class OrchestratorInit 
{
	public static void main(String[] args)
	{
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		
		
		System.out.println("Escolha um desafio: ");
		
		Scanner console = new Scanner(System.in);
		String desafio = console.nextLine();
		
		if(desafio.equalsIgnoreCase("maze"))
		{
			System.out.println("----------------------------------- Selecionado: Maze -----------------------------------");
			System.out.println("Qual Maze você deseja resolver?");
			String maze = console.nextLine();
			
			stat = new Statistics();
			resp = new ResponderMaze(stat, maze);
			enq = new EnquirerMaze();
			enq.connect(resp);
			enq.discover();
			System.out.println("----------------------------------------------------------------------------------------\n");
		}
		else if(desafio.equalsIgnoreCase("animals"))
		{
			System.out.println("--------------------------------- Selecionado: Animals ---------------------------------");
			System.out.println("Qual animal voce deseja que o Animals adivinhe?");
			String animal = console.nextLine();
			
			
		}
		else
			System.out.println("Desafio não encontrado");
		
		console.close();
	}
}
