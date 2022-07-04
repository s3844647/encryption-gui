import java.math.BigInteger;

/**
 * RSA logic implementation (both encryption and decryption). Note that
 * BigIntegers are used as this program may need to handle much larger numbers
 * than the standard int.
 * 
 * @author Aditya Kerhalkar
 *
 */
public class RSA {

	private BigInteger m; // message
	private BigInteger p, q; // primes

	private BigInteger e;
	private BigInteger[] eCandidates;

	public RSA() {
	} // blank constructor

	public void setValues(BigInteger m, BigInteger p, BigInteger q) {
		this.m = m;
		this.p = p;
		this.q = q;
	}

	public void setE(BigInteger e) {
		this.e = e;
	}

	public BigInteger getN() { // Calculate n
		return p.multiply(q);
	}

	public BigInteger getPhi() { // Calculate phi(n)
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);
		BigInteger qMinusOne = q.subtract(BigInteger.ONE);
		return pMinusOne.multiply(qMinusOne);
	}

	public BigInteger[] getECandidates() { // Calculate prime numbers that can be used for e

		eCandidates = new BigInteger[10]; // No more than ten numbers to save space
		int numCandidates = 0;
		for (int i = 3; i < 100; i += 2) {
			if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0)
				continue; // not easy to figure out primes, this should do

			if (isCoprime(getPhi(), BigInteger.valueOf(i))) {
				eCandidates[numCandidates] = BigInteger.valueOf(i);
				numCandidates++;
			}
			if (numCandidates == 10)
				break;
		}

		return eCandidates;
	}

	private boolean isCoprime(BigInteger a, BigInteger b) { // coprime helper function

		return a.gcd(b).equals(BigInteger.ONE);
	}

}
