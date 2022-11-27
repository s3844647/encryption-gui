import java.math.BigInteger;

public class ElGamal {

	private BigInteger m; // message
	private BigInteger p; // prime
	private BigInteger g; // generator
	private BigInteger x; // private key
	private BigInteger r; // random

	public void setValues(BigInteger m, BigInteger p, BigInteger g, BigInteger x) {
		this.m = m;
		this.p = p;
		this.g = g;
		this.x = x;
	}

	public void setR(BigInteger r) {
		this.r = r;
	}

	public BigInteger getM() {
		return m;
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getG() {
		return g;
	}

	public BigInteger getX() {
		return x;
	}

	public BigInteger getY() {
		return g.modPow(x, p);
	}

	public BigInteger getR() {
		return r;
	}

	public BigInteger getK() {
		return getY().modPow(r, p);
	}

	public BigInteger getC1() {
		return g.modPow(r, p);
	}

	public BigInteger getC2() {
		return (m.multiply(getK())).mod(p);
	}
	
	public BigInteger getKInverse() {
		return getK().modInverse(p);
	}
	
	public BigInteger decryptM() {
		return (getKInverse().multiply(getC2())).mod(p);
	}

}
