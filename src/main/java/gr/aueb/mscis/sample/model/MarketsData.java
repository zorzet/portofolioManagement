package gr.aueb.mscis.sample.model;
 

import javax.persistence.*;

@Entity
@Table(name = "MarketsData",uniqueConstraints = {
        @UniqueConstraint(columnNames = "MDId")})
public class MarketsData{
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MDId", unique = true, nullable = false)
	private int MDId;
	

	@Column(name = "date", nullable = false)
	private String date;

	@Column(name="Opening", nullable=false)
	private double Opening;
	
	@Column(name="Closing", nullable=false)
	private double Closing;
	
	@Column(name="Max", nullable=false)
	private double Max;
	
	@Column(name="Min", nullable=false)
	private double Min;
	
	public MarketsData(String date, double Opening, double Closing, double Max, double Min) {
		//this.MDId=MDID;
		this.date=date;
		this.Opening=Opening;
		this.Closing=Closing;
		this.Max=Max;
		this.Min=Min;
	}
	public MarketsData() {
	
	}
	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public double getOpening() {
		return this.Opening;
	}

	public void setOpening(double Opening) {
		this.Opening = Opening;
	}
	public double getClosing() {
		return this.Closing;
	}

	public void setClosing(double Closing) {
		this.Closing = Closing;
	}
	public double getMax() {
		return this.Max;
	}

	public void setMax(double Max) {
		this.Max = Max;
	}	
	public double getMin() {
		return this.Min;
	}

	public void setMin(double Min) {
		this.Min = Min;
	}
	
	public int getMDId() {
		return MDId;
	}
	public void setMDId(int mDId) {
		MDId = mDId;
	}
	@Override
	public String toString() {
		return "MarketsData [Date=" + date + ", Opening=" + Opening + ", Closing=" + Closing
				+ ", Max=" + Max + ", Min=" + Min + "]";
	}
}
