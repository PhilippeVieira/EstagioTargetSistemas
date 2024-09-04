import java.util.Scanner;

public class Exercicio5 {
	public static String reverteString(String entrada) {
		String invertida = "";
		String[] dividida;

		dividida = entrada.split("");
		for (int i = dividida.length - 1; i >= 0; i--) {
			invertida = invertida + dividida[i];
		}
		return invertida;
	}

	public static void main(String[] args) {
		boolean ativo = true;
		Scanner sc = new Scanner(System.in);
		String entrada, saida;

		//         vvvv frase para testar vvvv
		// >> "socorram me subi no onibus em marrocos" <<
		
		do {
			System.out.println("\n" 
					+ "Digite uma frase que deseja inverter\n" 
					+ "Ou digite 'SAIR' para encerrar");

			entrada = sc.nextLine();
			saida = reverteString(entrada);
			if (entrada.trim().equalsIgnoreCase("sair")) {
				ativo = false; // saindo do sistema
				System.out.printf(">> Aplicação encerrada <<\nPS->%s", saida);
			} else {
				System.out.printf("String invertida: %s\n", saida);
			}

		} while (ativo);

		sc.close();
	}
}
