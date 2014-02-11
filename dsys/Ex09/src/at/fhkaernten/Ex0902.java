package at.fhkaernten;

import java.util.Scanner;

import com.reportingsales.BigZip;
import com.reportingsales.DeFactoSF1;
import com.reportingsales.DeFactoSF1Soap;
import com.reportingsales.DefactoZip2;

public class Ex0902 {

	private final static String KEY = "7c6e61b8-9749-4ee9-be9b-3e51b3c8fe7c";

	public static void main(String[] args) {

		/**
		 * Der Webservice liefert Informationen über die Anzahl der Einwohner
		 * anhand Eines ZIP-Codes. Außerdem können Informationen über Anzahl der
		 * Häuser und die Verteilung der Einwohnerzahl auf Alter und ethnische
		 * Gruppen abgerufen werden. Damit der Webservice funktioniert, muss bei
		 * jeder Abfrage ein Key mitgeschickt werden. ein Key für 1000 Anfragen
		 * ist kostenlos erhältlich.
		 * 
		 * Für das Abrufen der Informationen gibt es 4 Funktionen:
		 * 
		 * DeFactoSF1Part1ByNameState(String place, String state, String, key)
		 * DeFactoSF1Part1ByZip(String zip, String key)
		 * DeFactoSF1Part2ByNameState(String place, String state, String key)
		 * DeFactoSF1Part2ByZip(String zip, String key)
		 * 
		 * Manueller SOAP Request
		 * 
				POST /DeFactoSF1.asmx HTTP/1.1
				Host: www.reportingsales.com
				Content-Type: application/soap+xml; charset=utf-8
				Content-Length: length

				<?xml version="1.0" encoding="utf-8"?>
				<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
  				<soap12:Body>
    			<DeFactoSF1Part1ByZip xmlns="http://reportingsales.com/">
      			<zip>string</zip>
      			<key>string</key>
    			</DeFactoSF1Part1ByZip>
  				</soap12:Body>
				</soap12:Envelope>
		 */
		DeFactoSF1Soap defactoservice = new DeFactoSF1().getDeFactoSF1Soap();
		Scanner sc = new Scanner(System.in);

		System.out.println("Please enter ZIP code: ");
		String zip = sc.next();
		System.out.println("What information do you want?");
		System.out
				.println("1) Population by ethnic group and number of houses");
		System.out.println("2) Information about age groups");
		int information = sc.nextInt();

		switch (information) {
		case 1:
			BigZip zipinfo1 = defactoservice.deFactoSF1Part1ByZip(zip, KEY);
			System.out.println("Name: " + zipinfo1.getNAME());
			System.out.println("Lat: " + zipinfo1.getLAT() + " Lon: "
					+ zipinfo1.getLON());
			System.out.println("Population total: " + zipinfo1.getTotal());
			System.out.println("White: " + zipinfo1.getWhiteOnly() + " Black: "
					+ zipinfo1.getBlackOnly() + " Native: "
					+ zipinfo1.getNativeOnly() + " Asian: "
					+ zipinfo1.getAsianOnly());
			System.out.println("Houses total: " + zipinfo1.getHousTot()
					+ " Houses occupied total: " + zipinfo1.getHousOccTot());

			break;
		case 2:
			DefactoZip2 zipinfo2 = defactoservice
					.deFactoSF1Part2ByZip(zip, KEY);
			System.out.println("Name: " + zipinfo2.getNAME());
			System.out.println("Lat: " + zipinfo2.getLAT() + " Lon: "
					+ zipinfo2.getLON());
			System.out.println("Median age female: " + zipinfo2.getMedAgeFem()
					+ " Median age male: " + zipinfo2.getMedAgeMale());
			int female20to29 = zipinfo2.getFem20() + zipinfo2.getFem21()
					+ zipinfo2.getFem22To24() + zipinfo2.getFem25To29();
			int male20to29 = zipinfo2.getMale20() + zipinfo2.getMale21()
					+ zipinfo2.getMale22To24() + zipinfo2.getMale25To29();
			System.out.println("Female in twenties: " + female20to29
					+ " Male in twenties: " + male20to29);
			break;
		default:
			System.out.println("Unknown option");
			break;
		}
		sc.close();
	}
}
