package no.hvl.dat100.prosjekt.kontroll;

import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortSamling;

/**
 * Klasse som for å representere en vriåtter nord-spiller. Strategien er å lete
 * gjennom kortene man har på hand og spille et tilfeldig som lovlig.
 *
 */
public class NordSpiller extends Spiller {

	public NordSpiller(Spillere spiller) {
		super(spiller);
	}

	@Override
	public Handling nesteHandling(Kort topp) {
		System.out.print("\n Spelar Nord Prøvar å gjere noko \n");
		System.out.printf("Toppen er %d , Farge er %s \n", topp.getVerdi(), topp.fargeTilStreng());
		System.out.printf("Nord Har : \n"); 
		
		for(Kort i : hand) 
			{
			System.out.printf("%d , \tFarge er %s \n ", i.getVerdi(), i.fargeTilStreng());
			}
		
		Kort mogleg = hand.GetGyldige(topp);
		
		if(mogleg != null) {
			System.out.printf("Kan legge ned kort : \tFarge = %s\tVerdi=\t%d\n",mogleg.fargeTilStreng(),mogleg.getVerdi());
			return new Handling(HandlingsType.LEGGNED, mogleg);
		}
		if(getAntallTrekk() < Regler.maksTrekk()) {
			System.out.printf("Prøvar å trekke, Antal trekk er : %d \n", getAntallTrekk());
			return new Handling(HandlingsType.TREKK, topp);
		}
		System.out.println("Må melde Pass");
		return new Handling(HandlingsType.FORBI, topp);
	}
		
		
		/*// ArrayLister for de kort vi har og kan spille
		KortSamling hand2 = new KortSamling(hand);
		KortSamling lovlige = new KortSamling();
		KortSamling attere = new KortSamling();

		// Gå igjennom kort å finn ut hvilke som kan spilles
		for (Kort k : hand2) {
			if (Regler.kanLeggeNed(k, topp)) {
				if (Regler.atter(k)) {
					attere.leggTil(k);
				} else {
					lovlige.leggTil(k);
				}
			}
		}

		Kort spill = null;
		Kort[] spillFra = null;

		if (!lovlige.erTom()) {
			spillFra = lovlige.getAllekort();
		} else if (!attere.erTom())  {
			spillFra = attere.getAllekort();
		}

		Handling handling = null;
		
		if (spillFra != null) {
			
			Random r = new Random();
			int p = r.nextInt(spillFra.length);
			spill = spillFra[p];
			handling = new Handling(HandlingsType.LEGGNED, spill);
			// setAntallTrekk(0);
			
		} else if (getAntallTrekk() < Regler.maksTrekk()) {
			handling = new Handling(HandlingsType.TREKK, null);
		} else {
			handling = new Handling(HandlingsType.FORBI, null);
			// setAntallTrekk(0);
		}

		return handling;
	}*/
		

}
