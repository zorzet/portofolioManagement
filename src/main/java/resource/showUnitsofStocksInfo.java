package resource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class showUnitsofStocksInfo {
 private int UnitsofStock;

public int getUnitsofStock() {
	return UnitsofStock;
}

public void setUnitsofStock(int unitsofStock) {
	UnitsofStock = unitsofStock;
}
 
public showUnitsofStocksInfo(int info) {
 	super();
 	UnitsofStock = info;
 }

 public static showUnitsofStocksInfo  wrap(int Info) {
 	return new showUnitsofStocksInfo(Info);
 }
}
