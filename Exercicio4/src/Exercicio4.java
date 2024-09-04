import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Exercicio4 {
	private static ArrayList<Estado> ListaEstados = new ArrayList<>();
	private static double total;
	private static void init(String path) {
		String[] dados;
		String UF;
		double fat;

		try {
			List<String> linhas = Files.readAllLines(Paths.get(path));
			for (String atual : linhas) {
				dados = atual.split("–");
				UF = dados[0].trim();
				fat = Float.parseFloat(dados[1].trim()); // arredondando para 2 casas decimais.
				ListaEstados.add(new Estado(UF, fat));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void calcPercentual() {
		double porcent = 0;
		// soma todos os faturamentos, mantendo 2 casas decimais.
		total = Math.floor(ListaEstados.stream().mapToDouble(Estado::getFaturamento).sum() * 100.0) / 100.0;
		for (Estado e : ListaEstados) {
			// calcula a porcentagem de representação, mantendo 2 casas decimais.
			porcent = Math.ceil(((e.getFaturamento() / total) * 100) * 100.0) / 100.0;
			e.setPercentual(porcent);
		}
	}

	public static void main(String[] args) {
		init("../Exercicio4/entrada.txt");
		calcPercentual();
		System.out.printf("Valor total arrecadado foi R$%.2f\n",total);
		ListaEstados.stream()
				.forEach(e -> System.out.printf("%s, o que corresponde a %.2f%% do faturamento total da empresa\n",
						e.toString(), e.getPercentual()));
	}
}

class Estado {
	private String sigla;
	private double faturamento, percentual;

	public Estado(String sigla, double faturamento) {
		this.sigla = sigla;
		this.faturamento = faturamento;
	}

	public String getSigla() {
		return sigla;
	}

	public double getFaturamento() {
		return faturamento;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public void setFaturamento(double faturamento) {
		this.faturamento = faturamento;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

	@Override
	public String toString() {
		return String.format("O estado %s teve um faturamento de R$%.2f", getSigla(), getFaturamento());
	}

}