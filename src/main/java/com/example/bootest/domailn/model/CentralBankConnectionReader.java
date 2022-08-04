package com.example.bootest.domailn.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CentralBankConnectionReader {
  public static Map<String, Double> read() throws Exception {
    Map<String, Double> readedBoardFromCentralBak = new HashMap<>();
    DateTimeFormatter dateString = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    URL centrobank =
        new URL(
            "http://www.cbr.ru/scripts/XML_daily.asp?date_req="
                + dateString.format(LocalDate.now()));
    URLConnection connection = centrobank.openConnection();

    Document document =
        DocumentBuilderFactory.newInstance()
            .newDocumentBuilder()
            .parse(connection.getInputStream());
    document.getDocumentElement().normalize();

    Element root = document.getDocumentElement();
    NodeList valutes = root.getElementsByTagName("Valute");
    for (int i = 0; i < valutes.getLength(); i++) {
      Node node = valutes.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
        String charcode = element.getElementsByTagName("CharCode").item(0).getTextContent();
        String nominal = element.getElementsByTagName("Nominal").item(0).getTextContent();
        String value = element.getElementsByTagName("Value").item(0).getTextContent();
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(value);
        double formatedValue = number.doubleValue();
        readedBoardFromCentralBak.put(charcode, formatedValue / Double.parseDouble(nominal));
      }
    }

    return readedBoardFromCentralBak;
  }
}
