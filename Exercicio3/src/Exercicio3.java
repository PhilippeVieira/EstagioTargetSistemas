import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Exercicio3 {
	private static ArrayList<diaMes> ListaDias = new ArrayList<>();
	private static List<diaMes> ListaDiaUteis = null;
	private static double mediaMes = 0;

	private static void init(String path) {
		String[] atts, dado;
		int dia = 0, mes = 0;
		float fat = 0;
		boolean DU = false;

		try { // Lendo Json e criando a lista de dias
			String texto = new String(Files.readAllBytes(Paths.get(path))).trim();
			texto = texto.substring(1, texto.length() - 1).trim();
			String[] objetos = texto.split("},");
			for (String obj : objetos) {
				obj = obj.replace("{", "").replace("}", "").trim();
				atts = obj.split(",");
				for (int i = 0; i < atts.length; i++) {
					dado = atts[i].trim().split(":");
					if (i == 0) {
						dia = Integer.parseInt(dado[1].trim());
					} else if (i == 1) {
						mes = Integer.parseInt(dado[1].trim());
					} else if (i == 2) {
						fat = Float.parseFloat(dado[1].trim());
					} else if (i == 3) {
						DU = Boolean.parseBoolean(dado[1].trim());
					}
				}
				diaMes dm = new diaMes(dia, mes, fat, DU);
				ListaDias.add(dm);
			}
			// filtrando a lista para obter os dias uteis.
			ListaDiaUteis = ListaDias.stream().filter(d -> d.ehDiaUtil()).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static diaMes menorFaturamento() {
		diaMes menorDia = null;
		float fat = 0;

		for (int i = 0; i < ListaDiaUteis.size(); i++) {
			if (i == 0) { // primeira iteração
				fat = ListaDiaUteis.get(i).getFaturamento();
				menorDia = ListaDiaUteis.get(i);
				
			} else if (ListaDiaUteis.get(i).getFaturamento() < fat) { // faturamento menor que o selecionado
				fat = ListaDiaUteis.get(i).getFaturamento();
				menorDia = ListaDiaUteis.get(i);

			}
		}
		return menorDia;
	}

	public static diaMes maiorFaturamento() {
		diaMes maiorDia = null;
		float fat = 0;

		for (int i = 0; i < ListaDiaUteis.size(); i++) {
			if (i == 0) { // primeira iteração
				fat = ListaDiaUteis.get(i).getFaturamento();
				maiorDia = ListaDiaUteis.get(i);

			} else if (ListaDiaUteis.get(i).getFaturamento() > fat) { // faturamento maior que o selecionado
				fat = ListaDiaUteis.get(i).getFaturamento();
				maiorDia = ListaDiaUteis.get(i);

			}
		}
		return maiorDia;
	}

	public static List<diaMes> mediaFaturamento() {
		List<diaMes> ListaAcimaMedia = null;
		float vrtot= 0;

		for (diaMes d : ListaDiaUteis) {
			vrtot += d.getFaturamento();
		}
		
		mediaMes = vrtot / ListaDiaUteis.size(); // valor total / total de dias uteis
		mediaMes = Math.ceil(mediaMes * 100.0) / 100.0; // arredondando o valor para manter 2 casas.
		ListaAcimaMedia = ListaDias.stream().filter(d -> d.getFaturamento() > mediaMes).collect(Collectors.toList());
		return ListaAcimaMedia;
	}

	public static void main(String[] args) {
		diaMes melhorDia = null, piorDia = null;
		List<diaMes> diasMedia = null;

		init("../Exercicio3/entrada.json");

		melhorDia = maiorFaturamento(); 
		piorDia = menorFaturamento(); 
		diasMedia = mediaFaturamento();
		
		System.out.printf("O PIOR faturamento ocorreu no dia %s, e foi de R$%.2f \n",piorDia.getDiaMes(),piorDia.getFaturamento());
		System.out.printf("O MELHOR faturamento ocorreu no dia %s, e foi de R$%.2f \n",melhorDia.getDiaMes(),melhorDia.getFaturamento());
		System.out.printf("\n%d dias tiveram um faturamento acima da média(R$%.2f)\n",diasMedia.size(),mediaMes);
		System.out.printf("Lista de dias:\nDia\t||   Faturamento\n");
		diasMedia.stream().forEach(d -> System.out.printf("%s\t||   %.2f\n",d.getDiaMes(),d.getFaturamento()));
	}
}

class diaMes {
	private int dia, mes;
	private float faturamento;
	private boolean DiaUtil;

	public diaMes(int dia, int mes, float faturamento, boolean diaUtil) {
		this.dia = dia;
		this.mes = mes;
		this.faturamento = faturamento;
		this.DiaUtil = diaUtil;
	}

	public String getDiaMes() {
		return String.format("%02d", dia) + "/" + String.format("%02d", mes);
	}

	public float getFaturamento() {
		return faturamento;
	}

	public boolean ehDiaUtil() {
		return DiaUtil;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public void setFaturamento(float valor) {
		this.faturamento = valor;
	}

	public void setDiaUtil(boolean diaUtil) {
		DiaUtil = diaUtil;
	}

	private String foiDiaUtil() {
		String str = null;
		if (DiaUtil) {
			str = "que era um dia util";
		} else {
			str = "que não era um dia util";
		}
		return str;
	}

	@Override
	public String toString() {
		return String.format("O dia %s, %s, teve um faturamento de R$%.2f\n", getDiaMes(), foiDiaUtil(),
				getFaturamento());
	}
}