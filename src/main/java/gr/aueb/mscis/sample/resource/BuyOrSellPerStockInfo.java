package gr.aueb.mscis.sample.resource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BuyOrSellPerStockInfo {
	 private String BuyOrSellPerStock;

	 public String getBuyOrSellPerStock() {
	 	return BuyOrSellPerStock;
	 }

	 public void setBuyOrSellPerStock(String info) {
		 BuyOrSellPerStock = info;
	 }

	 public BuyOrSellPerStockInfo(String info) {
	 	super();
	 	BuyOrSellPerStock = info;
	 }

	 public static BuyOrSellPerStockInfo  wrap(String Info) {
	 	return new BuyOrSellPerStockInfo(Info);
	 }
	 
}
