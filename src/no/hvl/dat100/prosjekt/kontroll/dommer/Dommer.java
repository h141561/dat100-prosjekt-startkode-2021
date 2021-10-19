package no.hvl.dat100.prosjekt.kontroll.dommer;

import java.util.ArrayList;
import java.util.logging.Logger;

import no.hvl.dat100.prosjekt.kontroll.ISpiller;
import no.hvl.dat100.prosjekt.kontroll.Spill;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.modell.KortUtils;

public class Dommer {

	// gjøre dommer uavhengig av bunke og hand implementasjon som er oppgaven
	private ArrayList<Kort> nordHand;
	private ArrayList<Kort> sydHand;

	private int antalltrekk;
	private Kort overste;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private Spill spill;

	public Dommer(Spill spill) {
		this.spill = spill;
	}

	public boolean sjekkStart() {
		antalltrekk = 0;

		return (sjekkUtdeling() && sjekkOverste() && sjekkAntall());
	}

	private boolean sjekkAntall() {
		return true; // TODO: sjekke der er nok kort jvf. config i regler og
						// samling
	}

	/**
	 * Gir kortene som en ArrayList.
	 * 
	 * @return samlinga av kort som en ArrayList. Korta skal ha samme rekkefølge
	 *         som i kortsamlinga.
	 */
	
	private static ArrayList<Kort> toArrayList(KortSamling samling) {
		return samling.tilArrayList();
	}
	
	private boolean sjekkUtdeling() {
		boolean korrekt = false;

		nordHand = toArrayList(spill.getNord().getHand());
		sydHand = toArrayList(spill.getSyd().getHand());

		LOGGER.info("Nord har " + nordHand.toString() + " ved start.");
		LOGGER.info("Syd har " + sydHand.toString() + " ved start.");

		korrekt = (sydHand.size() == Spill.ANTALL_KORT_START) && (nordHand.size() == Spill.ANTALL_KORT_START);

		if (!korrekt) {
			LOGGER.warning(": kort ikke korrekt delt ut");
		}

		return korrekt;
	}

	private boolean sjekkOverste() {
		boolean korrekt = false;

		korrekt = (spill.getBord().antallBunkeTil() == 1);

		if (korrekt) {
			overste = spill.getBord().seOversteBunkeTil();
		} else {
			LOGGER.warning(": ikke korrekt antall kort i tilbunke ved start");
		}

		return korrekt;
	}

	private void trekkFraBunke(ISpiller spiller, Kort kort) {
		
		spiller.leggTilKort(kort);
		LOGGER.info(spiller + " trekker " + kort + " fra bunken med " + antalltrekk + "trekk");
	}

	private ArrayList<Kort> getHand(ISpiller spiller) {
		ArrayList<Kort> hand = null;

		if (spiller.hvem() == Spillere.SYD) {
			hand = sydHand;
		} else if (spiller.hvem() == Spillere.NORD) {
			hand = nordHand;
		}

		return hand;
	}

	private boolean harKort(ISpiller spiller, Kort kort) {
		return spiller.getHand().har(kort);

	}

	private void fjernKort(ISpiller spiller, Kort kort) {
		ArrayList<Kort> hand = getHand(spiller);

		if (hand != null) {
			hand.remove(kort);
		}

	}

	public boolean sjekkHandling(ISpiller spiller, Handling handling) {

		boolean ok = false;
		String handlingstr = "[ " + spiller.hvem() + " " + handling + " med " + antalltrekk + " trekk ] ";

		LOGGER.info("================================================");
		LOGGER.info("Sjekker " + handlingstr);

		switch (handling.getType()) {
		case TREKK:
			ok = antalltrekk < Regler.maksTrekk();
			break;
		case FORBI:
			ok = antalltrekk > 0;
			break;
		case LEGGNED:
			Kort kort = handling.getKort();
			ok = Regler.kanLeggeNed(kort, spill.getBord().seOversteBunkeTil());
			if(!harKort(spiller, kort))
				System.out.println("Spelar har ikkje kort");
		}

		if (!ok) {
			LOGGER.severe("Ulovlig " + handlingstr);
		}

		return ok;
	}

	public void utforHandling(ISpiller spiller, Handling handling, Kort kort) {

		String handlingstr = "[ " + spiller.hvem() + " " + handling + " med " + antalltrekk + " trekk ] ";

		switch (handling.getType()) {
		case TREKK:
			if (kort != null) 
			{
				if(spill.getBord().bunkefraTom())
					spill.getBord().snuTilBunken();
				trekkFraBunke(spiller, spill.getBord().taOversteFraBunke());
				LOGGER.info("Utfører " + "[ " + spiller.hvem() + " " + handling + " " + kort + " med " + antalltrekk
						+ " trekk ] ");
				antalltrekk++;
				
			} else {
				LOGGER.severe("Utfører " + "[ " + spiller.hvem() + " " + handling + " (null) med " + antalltrekk
						+ " trekk ] ");
			}
			
			break;
		case FORBI:
			LOGGER.info("Utfører " + handlingstr);
			antalltrekk = 0;
			break;
		case LEGGNED:
			LOGGER.info("Utfører " + handlingstr);
			Kort kortned = handling.getKort();
			if(kortned == null)
			{
				LOGGER.severe("\nKortned er pullptr\n");
			}else
				System.out.printf("komt gjennom utforhandling med %s %d", kortned.fargeTilStreng(), kortned.getVerdi());
			this.spill.leggnedKort(spiller, kort);
			antalltrekk = 0;
			break;
		}

		LOGGER.info("Nord har " + nordHand.toString());
		LOGGER.info("Syd har " + sydHand.toString());
		LOGGER.info("Toppen er " + overste); // TODO: kunne her sjekke at
												// overste var lig det som er i
												// spill sin tilbunke.
		spiller.setAntallTrekk(antalltrekk);
	}

}


















