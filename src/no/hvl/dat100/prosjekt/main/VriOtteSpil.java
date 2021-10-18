package no.hvl.dat100.prosjekt.main;

import javax.swing.SwingUtilities;

import no.hvl.dat100.prosjekt.kontroll.spill.Kontroll;
import no.hvl.dat100.prosjekt.modell.*;
import no.hvl.dat100.prosjekt.utsyn.*;
import no.hvl.dat100.prosjekt.kontroll.*;

public class VriOtteSpil {

	public static void main(String[] args) {
		
		
		//testConstructor();
		
		runGame();
	}
	private static void runGame() {
		//opprett kontroll delen
		final Kontroll kontroll = new Kontroll();
	
		// start utsyn (Swing grensesnitt)
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				new Utsyn(kontroll);
			}
		});
	}
	private static void testConstructor() {
		Kort tmp = new Kort(Kortfarge.Hjerter, 4);
		KortSamling gamle = new KortSamling();
		gamle.leggTil(tmp);
		gamle.printAlle();
		System.out.println(" __ ");
		KortSamling nye = new KortSamling(gamle);
		nye.printAlle();
		System.out.printf("Nye har %d element \n", nye.getAntalKort());
		
		nye.leggTil(gamle.taSiste());
		System.out.printf("Nye har %d element \n", nye.getAntalKort());
		System.out.printf("gamle har %d element \n", gamle.getAntalKort());
	}
}



















