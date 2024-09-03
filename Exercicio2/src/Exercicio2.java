import java.util.Scanner;
import java.util.ArrayList;

public class Exercicio2 {
	private static int anterior = 0, atual = 1;
	private static ArrayList<Integer> Fibonaci = new ArrayList<>();
	
	private static void initLista(){
		Fibonaci.add(anterior);
		Fibonaci.add(atual);
	} 
	
	public static boolean calculaFibonaci(int valor) {
		boolean pertence = false, flag = false;
		int aux = 0;

		if (Fibonaci.contains(valor)) {
			// Número pertence à sequencia e já foi pesquisado.
			flag = true;
			pertence = true;

		} else if (Fibonaci.get(Fibonaci.size() - 1) > valor) {
			// Ultimo elemento salvo na lista é maior que o valor procurado ( que não esta salvo )
			// isto é: valor não pertence à sequencia.
			flag = true;
			pertence = false;
			
		}else {
			atual = Fibonaci.get(Fibonaci.size() - 1);
			anterior = Fibonaci.get(Fibonaci.size() - 2);
		}

		while (atual < valor && flag == false) {
			aux = anterior;
			anterior = atual;
			atual = atual + aux;
			Fibonaci.add(atual);
			if (atual == valor) { // Pertence à sequencia
				 flag = true;
				 pertence = true;
			}else if (atual > valor) { // Não pertence à sequencia
				flag = true;
				pertence = false;
			}
		}
	
		return pertence;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada;
		int numero;
		boolean ativo = true, resultado = false;
		initLista();

		do {
			System.out.println("\n"
					+ "Digite um número que deseja verificar\n"
					+ "Ou digite 'Sair' para encerrar");

			if (sc.hasNextInt()) { // Digitou um número para verificar
				numero = sc.nextInt();
				resultado = calculaFibonaci(numero);
				if (resultado) {
					System.out.printf("\nValor %d PERTENCE à sequência de Fibonaci\n", numero);
				}else {
					System.out.printf("\nValor %d NÃO PERTENCE à sequência de Fibonaci\n", numero);
				}
			} else if (sc.hasNextLine()) { // Digitou um texto
				entrada = sc.next();
				if (entrada.trim().equalsIgnoreCase("sair"))
					ativo = false; // saindo do sistema
			}

		} while (ativo);

		sc.close();
	}
}
