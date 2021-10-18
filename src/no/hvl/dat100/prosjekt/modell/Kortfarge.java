package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.TODO;

public enum Kortfarge {
	
	Hjerter, Ruter, Klover, Spar;
	
	public static Kortfarge getFarge(int i) {
		switch(i) {
		case 0: return Hjerter;
		case 1: return Ruter;
		case 2: return Klover;
		case 3: return Spar;
		default:
			throw new UnsupportedOperationException("Feil Tal til Kortfarge::getFarge");
		}
	}
}
