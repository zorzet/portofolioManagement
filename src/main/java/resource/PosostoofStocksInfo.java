package resource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PosostoofStocksInfo {
	double showPosostoofStocks;

	public double getshowPosostoofStocks() {
		return showPosostoofStocks;
	}

	public void setshowPosostoofStocks(double showPosostoofStocks) {
		this.showPosostoofStocks = showPosostoofStocks;
	}
	public PosostoofStocksInfo(double info) {
	 	super();
	 	showPosostoofStocks = info;
	 }

	 public static  PosostoofStocksInfo  wrap(double Info) {
	 	return new  PosostoofStocksInfo(Info);
	 }
}
