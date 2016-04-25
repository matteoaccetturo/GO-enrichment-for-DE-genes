import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;


public class GSEA {

	
	private void geniPerGSEA() throws IOException{
		//crea la lista dei geni pre-ranked da analizzare con GSEA sommando i geni DE con tutti gli altri probesets
		String line;
		String line1;
		Set<String> geniDE = new TreeSet<String>();
		Set<String> allEntities = new TreeSet<String>();
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(
						"C:\\Documents and Settings\\Administrator\\My Documents\\Rigetto cronico umorale\\GSEA\\allEntitiesSenzaDEgenes.txt"));
		
		BufferedReader DEgenes = new BufferedReader(
				new FileReader(
				"C:\\Documents and Settings\\Administrator\\My Documents\\Rigetto cronico umorale\\GSEA\\DEgenesRanked.txt"));
		DEgenes.readLine();
		while ((line = DEgenes.readLine()) != null) {
			
				geniDE.add(line);
			
						
		}System.out.println("numero geni DE: "+geniDE.size());
		DEgenes.close();
		BufferedReader Allentities = new BufferedReader(
				new FileReader(
				"C:\\Documents and Settings\\Administrator\\My Documents\\Rigetto cronico umorale\\GSEA\\allEntitiesTot.txt"));
		Allentities.readLine();
		while ((line1 = Allentities.readLine()) != null) {
			String[] field = line1.split("\t");
			if (!geniDE.contains(field[1])) {
				bw.write(line1+"\r\n");
				bw.flush();
				allEntities.add(line1);		
				
			}
						
		}System.out.println("resto dei geni umani: "+allEntities.size());
		Allentities.close();
		bw.close();
		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
GSEA creaLista = new GSEA();
creaLista.geniPerGSEA();
	}

}
