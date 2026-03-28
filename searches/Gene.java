import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gene {

	public static void main(String[] args) {
		String geneStr = "ACGTGGCTCTCTAACGTACGTACTGCGTACGTACGGGTTTATATATACCCTAGGACTCCCTTT";
		Gene myGene = new Gene(geneStr);
		Codon acg = new Codon("ACG");
		Codon gat = new Codon("GAT");
		System.out.println(myGene.linearContains(acg));
	}

	enum Nucleotide { A, C, G, T }

	public static class Codon implements Comparable<Codon> {
		public final Nucleotide first, second, third;

		final Comparator<Codon> comparator = Comparator.comparing((Codon c) -> c.first)
			.thenComparing((Codon c) -> c.second)
			.thenComparing((Codon c) -> c.third);

		public Codon(String codonStr) {
			first = Nucleotide.valueOf(codonStr.substring(0, 1));
			second = Nucleotide.valueOf(codonStr.substring(1, 2));
			third = Nucleotide.valueOf(codonStr.substring(2, 3));
		}

		@Override
		public int compareTo(Codon other) {
			return comparator.compare(this, other);
		}
	}

	ArrayList<Codon> codons = new ArrayList<>();
	
	public Gene(String geneStr) {
		for (int i = 0; i < geneStr.length() - 3; i += 3) {
			codons.add(new Codon(geneStr.substring(i, i + 3)));
		}
	}
	public boolean linearContains(Codon key) {
		return codons.stream().anyMatch(c -> c.compareTo(key) == 0);
	}

	public boolean binaryContains(Codon key) {
		ArrayList<Codon> sorted = new ArrayList<>(codons);
		Collections.sort(sorted);
		int low = 0, high = sorted.size() - 1;
		while (low <= high) {
			int middle = low + (high - low) / 2;
			int comparison = sorted.get(middle).compareTo(key);
			if (comparison == 0) {
				return true;
			} else if (comparison < 0) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		return false;
	}
}
