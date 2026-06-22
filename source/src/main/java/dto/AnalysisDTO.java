package dto;

import java.io.Serializable;

public class AnalysisDTO implements Serializable {
	private int typeId;
	private double negativeRate, positiveRate, activeIndex;
	private int emoBalance;
	
	public AnalysisDTO(int typeId, double negativeRate, double positiveRate, double activeIndex, int emoBalance) {
		super();
		this.typeId = typeId;
		this.negativeRate = negativeRate;
		this.positiveRate = positiveRate;
		this.activeIndex = activeIndex;
		this.emoBalance = emoBalance;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public double getNegativeRate() {
		return negativeRate;
	}

	public void setNegativeRate(double negativeRate) {
		this.negativeRate = negativeRate;
	}

	public double getPositiveRate() {
		return positiveRate;
	}

	public void setPositiveRate(double positiveRate) {
		this.positiveRate = positiveRate;
	}

	public double getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(double activeIndex) {
		this.activeIndex = activeIndex;
	}

	public int getEmoBalance() {
		return emoBalance;
	}

	public void setEmoBalance(int emoBalance) {
		this.emoBalance = emoBalance;
	}
	
}
