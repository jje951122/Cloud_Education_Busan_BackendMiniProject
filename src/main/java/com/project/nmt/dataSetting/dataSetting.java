package com.project.nmt.dataSetting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nmt.model.StockInfo;
import com.project.nmt.repository.StockInfoRepository;
import com.project.nmt.repository.stockRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

@RequiredArgsConstructor
@RestController
public class dataSetting {
	String API = "ef02eb73-7007-4884-8401-e1bee7361066";

	private final StockInfoRepository stockInfoRepository;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/setData")
	public List<Map<String, Object>> getString() throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date now = new Date(); 
		String end_dt = format.format(now);
		String start_dt="2018-01-01";
		//고구마, 감자, 대파, 배추, 양파, 무, 고추, 마늘, 부추, 파프리카
		String categoryCode[]= {"100","100","200","200","200","200","200","200","200","200"};
		String item[]= {"고구마","감자","대파","배추","양파","무","고추","마늘","부추","파프리카"};
		String itemCode[]= {"151", "152","246","211","245","231","243","258","254","256"};
		String kindCode[]= {"00", "01","00","01","00","01","00","01","00","00"};

		JsonParser jsonParser = JsonParserFactory.getJsonParser();

		// 요청 URL
		URL url = null;
		try {
			url = new URL(
					"https://www.kamis.or.kr/service/price/xml.do?action=periodProductList&p_productclscode=02&"
					+ "p_startday="+start_dt+"+&p_endday="+end_dt+"&"
					+ "p_itemcategorycode=200&p_itemcode=211&p_kindcode=01&"
//					+ "p_itemcategorycode="+categoryCode[0]+"&p_itemcode="+itemCode[0]+"&p_kindcode="+kindCode[0]+"&"
					+ "p_productrankcode=04&p_countrycode=1101&p_convert_kg_yn=Y&"
					+ "p_cert_key="+API+"&p_cert_id=222&p_returntype=json");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// url.openStream()으로 요청 결과를 stream으로 받음.
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

		// JsonParser를 사용하기 위해 stream을 하나의 String 객체로 묶음.
		String line = br.readLine();
		StringBuilder json = new StringBuilder();
		while (line != null && !line.equals("")) {
			json.append(line);

			line = br.readLine();
		}

		// JsonParser를 통해 요청 결과를 Map<String, Object>로 변환하여 반환.
		Map<String, Object> map= jsonParser.parseMap(json.toString());
		map = (Map<String, Object>) map.get("data");
		List<Map<String, Object>> prices=(List<Map<String, Object>>) map.get("item");
		
		
		
		for(int i=0;i<prices.size();i++) {
			StockInfo info=new StockInfo(); 
			Map<String, Object> nowPrice=prices.get(i);
			info.setPrice(Integer.parseInt(((String) (nowPrice.get("price"))).replace(",", "")));
			String[] date=nowPrice.get("regday").toString().split("/");
			info.setInfoDate(LocalDate.of(Integer.parseInt((String)nowPrice.get("yyyy")) , Integer.parseInt(date[0]), Integer.parseInt(date[1])));
			stockInfoRepository.save(info);
		}
		
		return prices;
	}
	
	@GetMapping("/chart")
	public String chart() {
		
		return "chart";
	}

}