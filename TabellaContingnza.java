import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;




public class TabellaContingenza {
	
	public static Set<String> SetGene2goHuman() throws IOException {
		String line2;
		Set<String> gene2goHuman = new TreeSet<String>();
		BufferedReader br2 = new BufferedReader(
				new FileReader(
				"C:\\Documents and Settings\\Administrator\\Desktop\\Rigetto cronico umorale\\gene2go19-12-2010_human.txt"));
		br2.readLine();
		while ((line2 = br2.readLine()) != null) {
				gene2goHuman.add(line2);			
		}br2.close();
		return gene2goHuman;
	}
	
	public static Set<String> SetGoTermsDEGenes() throws IOException {
		String line3;
		Set<String> GOtermsDEGenes = new TreeSet<String>();
		Iterator<String> it = SetGene2goHuman().iterator();
		Set<String> tmp = SetDEGenes();
		while (it.hasNext()) {
			line3 = it.next();
			String[] field = line3.split("\t");
			//for (String string : ListaCandidatiTot()) {
			if (tmp.contains(field[1])) {
				//System.out.println("ciao");
				GOtermsDEGenes.add(field[2]);
				//System.out.println("numero GO term: "+GOtermsCandidati.size());
			}			
		}
		return GOtermsDEGenes;
	}
	
	public static Set<String> SetDEGenes() throws IOException {
		String line;
		Set<String> DEGenes = new TreeSet<String>();//geni differenzialmente espressi
		BufferedReader geniDE = new BufferedReader(
				new FileReader(
				"C:\\Documents and Settings\\Administrator\\Desktop\\Rigetto cronico umorale\\All entities\\Mann_Asymptotic_Westfall.txt"));
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		geniDE.readLine();
		while ((line = geniDE.readLine()) != null) {
			String[] field = line.split("\t");
			System.out.println(field.length);
			if ((field.length>19)) {
				DEGenes.add(field[19]);
			}
			
			
			
		}
		geniDE.close();
		return DEGenes;		
	}
	
	private void Tabella() throws IOException{
		Set<String> DEGenes = new TreeSet<String>();//geni differenzialmente espressi
		List<String> gene2go_DE_nonDE= new ArrayList<String>();//file gene2go dei DE e non DE
		Set<String> nonDEGenes = new TreeSet<String>();//geni non DE
		Set<String> goTerms = new TreeSet<String>();//go terms dei DE
		String line;
		String line1;
		String line2;
				
		DEGenes = TabellaContingenza.SetDEGenes();
		System.out.println("numero geni differenzialmente espressi: "+DEGenes.size());
		
		
		BufferedReader geniNonDE = new BufferedReader(
				new FileReader(
				"C:\\Documents and Settings\\Administrator\\Desktop\\Rigetto cronico umorale\\Test di Fisher\\AllEntities.txt"));
		geniNonDE.readLine();
		geniNonDE.readLine();
		geniNonDE.readLine();
		geniNonDE.readLine();
		geniNonDE.readLine();
		geniNonDE.readLine();
		geniNonDE.readLine();
		while ((line = geniNonDE.readLine()) != null) {
			String[] field = line.split("\t");
			if ((field.length>13)) {
			if (!DEGenes.contains(field[13])) {
				nonDEGenes.add(field[13]);
			}
			}
			
			
		}
		geniNonDE.close();
		System.out.println("numero geni non DE: "+nonDEGenes.size());
//		BufferedReader seq = new BufferedReader(
//				new FileReader(
//				"C:\\Documents and Settings\\Matteo\\Documenti\\Ontologie\\gene2go_diseaseGenes"));
//		while ((line = seq.readLine()) != null) {
//
//			String[] field = line.split("\t");
//			goTerms.add(field[2]);
//		}
		goTerms=TabellaContingenza.SetGoTermsDEGenes();
		System.out.println("numero go terms DE genes: "+goTerms.size());
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Rigetto cronico umorale\\Test di Fisher\\TabellaContingenzaPerFisherTest.txt"));
//		BufferedReader seq1 = new BufferedReader(
//				new FileReader(
//				"C:\\Documents and Settings\\Matteo\\Documenti\\Ontologie\\gene2go_reduced"));
//		seq1.readLine();
		
		Set<String>SetGene2goHuman= new TreeSet<String>();
		SetGene2goHuman=TabellaContingenza.SetGene2goHuman();
		Iterator<String> it1 = SetGene2goHuman.iterator();
		while (it1.hasNext()) {
			line1 = it1.next();
			String[] field = line1.split("\t");
			if (DEGenes.contains(field[1])) {
				gene2go_DE_nonDE.add(line1);
			} else if(nonDEGenes.contains(field[1])){
				gene2go_DE_nonDE.add(line1);
			}				
		}
		System.out.println("numero geni DE+non DE con go annotations: "+gene2go_DE_nonDE.size());
		System.out.println(gene2go_DE_nonDE);
		for (String goTerm : goTerms) {
			int N11 = 0;
			int N12 = 0;
			int N21 = 0;
			int N22 = 0;
			Iterator<String>it = gene2go_DE_nonDE.iterator();
			while (it.hasNext()) {
				line2 = it.next();
				String[] field2 = line2.split("\t");
				if (field2[2].equalsIgnoreCase(goTerm)) {
					if (DEGenes.contains(field2[1])) {
						N11++;						
					}else{
						N12++;
					}
				}
			}N21=(DEGenes.size()-N11);
			N22=(nonDEGenes.size()-N12);
//			ContingencyTable2x2 tabella = new ContingencyTable2x2(N11,N12,N21,N22);
//			FishersExactTest test = new FishersExactTest(tabella, H1.GREATER_THAN);
//			double p_value=test.getOneTailedSP();
			bw.write(goTerm+"\t"+N11+"\t"+N21+"\t"+N12+"\t"+N22/*+"\t"+p_value*/+"\r\n");
			bw.flush();
			
			
		}bw.close();System.out.println("ho finito");
	}
	
	


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
TabellaContingenza tabella = new TabellaContingenza();
tabella.Tabella();

	}
}
