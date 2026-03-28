import java.util.BitSet;

public class CompressedGene {
	public static void main(String[] args) {

		final String GENE = "ATCATACCGATGAGTG";
		CompressedGene cGene = new CompressedGene(GENE);
		System.out.println(cGene.decompress());
	}

	private BitSet bitSet;
	private int length;

	public CompressedGene(String gene) {
		compress(gene);
	}

	public String decompress() {
		StringBuilder sb = new StringBuilder(this.length);

		for (int i = 0; i < 2 * this.length; i += 2) {
			final int firstBit = this.bitSet.get(i) ? 1 : 0;
			final int secondBit = this.bitSet.get(i + 1) ? 1 : 0;

			final int lastBit = firstBit << 1 | secondBit;

			switch (lastBit) {
				case 0b00:
					sb.append('A');
					break;
				case 0b01:
					sb.append('C');
					break;
				case 0b10:
					sb.append('G');
					break;
				case 0b11:
					sb.append('T');
					break;
			}
		}
		return sb.toString();
	}

	public void compress(String gene) {

		this.length = gene.length();
		bitSet = new BitSet(2 * this.length);

		for (int i = 0; i < this.length; i++) {
			int firstPos = 2 * i;
			int secondPos = 2 * i + 1;

			final String upperGene = gene.toUpperCase();
			switch(upperGene.charAt(i)) {
				case 'A':
					bitSet.set(firstPos, false);
					bitSet.set(secondPos, false);
					break;
				case 'C':
					bitSet.set(firstPos, false);
					bitSet.set(secondPos, true);
					break;
				case 'G':
					bitSet.set(firstPos, true);
					bitSet.set(secondPos, false);
					break;
				case 'T':

					bitSet.set(firstPos, true);
					bitSet.set(secondPos, true);
					break;

				default:
					throw new IllegalArgumentException("The provided gene String contains characters other than AGCT");
			}
		}
	}
}
