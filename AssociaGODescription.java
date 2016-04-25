import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class AssociaGODescription {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	private void associaDescrizione() throws IOException{
		String line2;
		Set<String> GOTermsHuman = new TreeSet<String>();
		BufferedReader GOTerms = new BufferedReader(
				new FileReader(
				"C:\\Documents and Settings\\Administrator\\My Documents\\miRNA\\goTerms15-04-2011_human.txt"));
while ((line2 = GOTerms.readLine()) != null) {
			
			GOTermsHuman.add(line2);
		}
		GOTerms.close();
		System.out.println("numero GO terms umani: "+GOTermsHuman.size());
		//System.out.println(GOTermsHuman);
	
		Set<String> GOTermsFromFisher = new TreeSet<String>();
		String line;
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(
				"C:\\Documents and Settings\\Administrator\\My Documents\\nefropatia diabetica array Affy 2005\\FisherTestResultsWithGODescription.txt"));

		
		BufferedReader FisherTestResults = new BufferedReader(
				new FileReader(
				"C:\\Documents and Settings\\Administrator\\My Documents\\nefropatia diabetica array Affy 2005\\FisherTestResults.txt"));
		FisherTestResults.readLine();
		while ((line = FisherTestResults.readLine()) != null) {
			
			GOTermsFromFisher.add(line);
		}
		FisherTestResults.close();
		Iterator<String> it = GOTermsHuman.iterator();
		while (it.hasNext()) {
			
			String string = (String) it.next();
			String[] field1 = string.split("\t");
			for (String line1 : GOTermsFromFisher) {
				String[] field = line1.split("\t");
				if (field[0].equalsIgnoreCase(field1[0])){
				bw.write(line1+"\t"+field1[1]+"\r\n");
				bw.flush();
			}
			}
			
		}bw.close();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
AssociaGODescription descrizione = new AssociaGODescription();
descrizione.associaDescrizione();
	}

}
