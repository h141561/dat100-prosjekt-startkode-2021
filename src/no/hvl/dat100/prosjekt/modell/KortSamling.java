package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import static java.lang.System.out;

import java.lang.Iterable;
import java.util.*;
/**
 * Struktur for å lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * en konstant i klassen Regler som angir antall kort i hver av de 4 fargene. Når
 * programmet er ferdig settes denne til 13, men under utvikling / testing kan
 * det være praktisk å ha denne mindre.
 * 
 */
public class KortSamling implements Iterable<Kort>{

	private final int MAKS_KORT = 52;
	private Kort[] samling;
	private int antall;
	private Vector<Kort> kortVector;

	/**
	 * Oppretter en tom Kortsamling med plass til MAKS_KORT (hele kortstokken).
	 */
	public KortSamling() {
		kortVector = new Vector<Kort>(MAKS_KORT);
	}
	public KortSamling(int antall) {
		kortVector = new Vector<Kort>(antall);
	}
	
	public KortSamling(KortSamling t)
	{
		kortVector = new Vector<Kort>();
		for(Kort i : t)
		{
			kortVector.add(i);
		}
	}


	/**
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke være
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan få
	 * tilgang til antallet ved å bruke metoden getAntallKort(). Metoden er
	 * først og fremst ment å brukes i testklasser. Om man trenger
	 * kortene utenfor, anbefales metoden getAlleKort().
	 * 
	 * @return tabell av kort.
	 */
	public Kort[] getSamling() {
		Kort[] ret = new Kort[kortVector.size()];
		return kortVector.toArray(ret);
	}
	
	/**
	 * Antall kort i samlingen.
	 * 
	 * @return antall kort i samlinga.
	 */
	public int getAntalKort() {
		return kortVector.size();
	}
	
	/**
	 * Sjekker om samlinga er tom.
	 * 
	 * @return true om samlinga er tom, false ellers.
	 */
	public boolean erTom() {
		return(kortVector.isEmpty());
	}

	/**
	 * Legg et kort til samlinga.
	 * 
	 * @param kort
	 *            er kortet som skal leggast til.
	 */
	public void leggTil(Kort kort) {
		System.out.printf("legger til her %s %d\n", kort.fargeTilStreng(), kort.getVerdi());
		kortVector.add(kort);
	}
	
	/**
	 * Legger alle korta (hele kortstokken) til samlinga. Korta vil være sortert
	 * slik at de normalt må stokkes før bruk.
	 */
	public void leggTilAlle() {
		for(int i = 0; i <= 3; i++)
		{
			for(int k = 1; k <= 13 ; k++)
			{
				kortVector.add(new Kort(Kortfarge.getFarge(i),k));
			}
		}
	}

	/**
	 * Fjerner alle korta fra samlinga slik at den blir tom.
	 */
	public void fjernAlle() {
		kortVector.clear();
	}
	
	/**
	 * Ser på siste kortet i samlinga.
	 * 
	 * @return siste kortet i samlinga, men det blir ikke fjernet. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort seSiste() {
		return kortVector.lastElement();
	}

	/**
	 * Tek ut siste kort fra samlinga.
	 * 
	 * @return siste kortet i samlinga. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort taSiste() {
		if(kortVector.isEmpty())
			return null;
		Kort tmp = kortVector.lastElement();
		Kort ret = new Kort(tmp.getFarge(), tmp.getVerdi());
		kortVector.remove(kortVector.size() - 1);
		return ret;
	}
	
	/**
	 * Undersøker om et kort finst i samlinga.
	 * 
	 * @param kort.
	 * 
	 * @return true om kortet finst i samlinga, false ellers.
	 */
	public boolean har(Kort kort) {
		return kortVector.contains(kort);
	}

	/**
	 * Fjernar et kort frå samlinga. Dersom kortet ikke finnest i samlinga,
	 * skjer ingenting med samilingen
	 * 
	 * @param kort
	 *            kortet som skal fjernast. Dersom kortet ikke finnes, skjer
	 *            ingenting.
	 * @return true om kortet blev fjernet fra samlinga, false ellers.
	 */
			 
	public boolean fjern(Kort kort) {
		
		ArrayList<Integer> fjernbare = new ArrayList<Integer>();
		
		return kortVector.remove(kort);
		/*
		int fjernast = 0;
		for(int i = 0; i < samling.length; i++)
		{
			if(samling[i].equals(kort)) 
			{
				++fjernast;
				fjernbare.add(i);
			}
		}
		if(fjernast == 0)
			return false;

		Kort[] ret = new Kort[samling.length - fjernast];
		int antall = 0;
		
		try {
			for(int i = 0 ; i < samling.length; i++)
			{
				if(fjernbare.contains(i))
					continue;
				if(samling[i].equals(kort));
				{
					samling[i].printinfo();
					ret[antall++] = samling[i];
				}
			}
		}catch(Exception e){
			System.out.printf("Noko gjekk til dunders ret len = %d \n", samling.length);
		}
		samling = ret;
		return true;*/
	}

	/**
	 * Gir kortene som en tabell av samme lengde som antall kort i samlingen
	 * 
	 * @return tabell av kort som er i samlingen, der kort skal ha samme rekkefølge
	 *         som i kortsamlinga.
	 */
	public Kort[] getAllekort() {
		return getSamling();
	}
	
	public void printAlle() {
		String outp = new String();
		int tabs = 0;
		Kortfarge gamle = Kortfarge.Hjerter;
		for(Kort i : kortVector)
		{
			i.printinfo();
		}
	}
	
	/**
	 * Gjer Tilbake alle Korta Som kan Leggast på eit spesifikt Kort
	 * 
	 * @param kort
	 * 		Kva kort som det skal Leggast på
	 * @return
	 * 		Fyrste Kortet som er Gyldig
	 * 
	 */
	
	public Kort GetGyldige(Kort maal) {
		
		for(Kort i : kortVector)
		{
			if(i.getFarge() == maal.getFarge())
			{
				return i;
			}
			if(i.getVerdi() == maal.getVerdi())
			{
				return i;
			}
		}
		
		return null;
	}
	

	
	public void stokkKort()
	{
		Collections.shuffle(kortVector);
	}
	public void sorter()
	{
		Collections.sort(kortVector);
	}
	
	@Override
	public Iterator<Kort> iterator() {
		return kortVector.iterator();
	}
	
	public ArrayList<Kort> tilArrayList(){
		ArrayList<Kort> ret = new ArrayList<Kort>();
		for(Kort i : kortVector)
			ret.add(i);
		return ret;
	}
}








