package resource;

import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Metoxh;

@XmlRootElement
public class InformationOfStockInfo {
 private String InformationOfStock;

public String getInformationOfStock() {
	return InformationOfStock;
}

public void setInformationOfStock(String informationOfStock) {
	InformationOfStock = informationOfStock;
}

public InformationOfStockInfo(String info) {
	super();
	InformationOfStock = info;
}

public static InformationOfStockInfo  wrap(String Info) {
	return new InformationOfStockInfo(Info);
}

}
