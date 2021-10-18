package no.hvl.dat100.prosjekt.kontroll.dommer;

import no.hvl.dat100.prosjekt.kontroll.ISpiller;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;

public abstract class Regler {
	
	// juster på disse for å spille med alle kort 
	public static final int MAKS_KORT_FARGE = 13;
	public final static int ANTALL_KORT_START = 6;
	
	private final static int MAKS_TREKK = 1;
	
	public static int maksTrekk() {
		
		return MAKS_TREKK;
		
	}
	
	public static boolean kanLeggeNed(Kort kort, Kort topp) {
		System.out.printf("kanLeggeNed kalla med :\n type: %s\t%d\ntopp :%s\t%d\n", 
				kort.fargeTilStreng(), kort.getVerdi(),
				topp.fargeTilStreng(), topp.getVerdi()
				);
		return kort.sammeFarge(topp) || kort.sammeVerdi(topp) || atter(kort);
	}

	public static boolean atter(Kort kort) {
		
		return kort.getVerdi() == 8;
		
	}

	public static Spillere vinner(ISpiller spiller1, ISpiller spiller2) {

		Spillere vinner = Spillere.INGEN;

		if (spiller1.erFerdig()) {
			vinner = spiller1.hvem();
		} else if (spiller2.erFerdig()) {
			vinner = spiller2.hvem();
		}

		return vinner;
	}
}
