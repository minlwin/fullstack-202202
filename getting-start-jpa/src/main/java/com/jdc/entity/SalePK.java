package com.jdc.entity;

import java.io.Serializable;
import java.util.Objects;

public class SalePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int seqNumber;
	private int transactionType;

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seqNumber, transactionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalePK other = (SalePK) obj;
		return seqNumber == other.seqNumber && transactionType == other.transactionType;
	}

}
