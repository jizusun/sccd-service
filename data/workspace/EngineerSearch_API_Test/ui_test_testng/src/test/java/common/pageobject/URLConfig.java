package common.pageobject;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class URLConfig {

	private String LP_DEV = "https://supportshell-dgq5qdz5dm.dispatcher.int.sap.eu2.hana.ondemand.com/";
	private String LP_QUAL = "https://supportshell-q28pq0r93n.dispatcher.int.sap.eu2.hana.ondemand.com/";
	private String LP_TEST = "https://supportshell-tnxd3nxr8c.dispatcher.int.sap.eu2.hana.ondemand.com/";
	private String LP_PILOT = "https://pilot.support.sap.com/";

	private String BC_DEV = "https://bcdaci.wdf.sap.corp/sap(bD1lbiZjPTAwMSZkPW1pbg==)/bc/bsp/sap/crm_ui_start/default.htm?saprole=ZCSSNEXTPROC";
	private String BC_QUAL = "https://bcvaci.wdf.sap.corp/sap(bD1lbiZjPTAwMSZkPW1pbg==)/bc/bsp/sap/crm_ui_start/default.htm?saprole=ZCSSNEXTPROC/";
	private String BC_TEST = "https://bctaci.wdf.sap.corp/sap(bD1lbiZjPTAwMSZkPW1pbg==)/bc/bsp/sap/crm_ui_start/default.htm?saprole=ZCSSNEXTPROC";

	private String SNOW_DEV = "https://dev.itsm.services.sap/login";
	private String SNOW_TEST = "https://test.itsm.services.sap/login";

	private String ES_DEV = "https://go-dgq5qdz5dm.dispatcher.int.sap.eu2.hana.ondemand.com/xsearch/";
	private String ES_TEST = "https://go-tnxd3nxr8c.dispatcher.int.sap.eu2.hana.ondemand.com/xsearch/";
	private String ES_PILOT = "https://go-a8f41cb0e.dispatcher.hana.ondemand.com/xsearch/";
	private String ES_PRODUCT = "https://go.support.sap.com/xsearch/";

	private Map<String, String> urlMap = new HashMap<String, String>();

	public URLConfig() {
		urlMap.put("LP_DEV", this.LP_DEV);
		urlMap.put("LP_QUAL", this.LP_QUAL);
		urlMap.put("LP_TEST", this.LP_TEST);
		urlMap.put("LP_PILOT", this.LP_PILOT);
		urlMap.put("BC_DEV", this.BC_DEV);
		urlMap.put("BC_QUAL", this.BC_QUAL);
		urlMap.put("BC_TEST", this.BC_TEST);
		urlMap.put("SNOW_DEV", this.SNOW_DEV);
		urlMap.put("SNOW_TEST", this.SNOW_TEST);
		urlMap.put("ES_DEV", this.ES_DEV);
		urlMap.put("ES_TEST", this.ES_TEST);
		urlMap.put("ES_PILOT", this.ES_PILOT);
		urlMap.put("ES_PRODUCT", this.ES_PRODUCT);
	}

	public String getURLConfig(String landscape,String application) {
		if(landscape==null||landscape=="") {
			landscape = "DEV";
		}
		if(application==null||application=="") {
			application = "LP";
		}
		String urlValue = application + "_" +landscape;
		return urlMap.get(urlValue);
	}
    
}
