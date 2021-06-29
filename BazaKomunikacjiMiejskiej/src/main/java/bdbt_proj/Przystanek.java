package bdbt_proj;

public class Przystanek {
	private int id_przystanku;
	private String nazwa;
	private float szerokosc_geograficzna;
	private float dlugosc_geograficzna;
	private String na_zadanie;

	public Przystanek() {
	}

	public Przystanek(int id_przystanku, String nazwa, float szerokosc_geograficzna, float dlugosc_geograficzna,
			String na_zadanie) {
		super();
		this.id_przystanku = id_przystanku;
		this.nazwa = nazwa;
		this.szerokosc_geograficzna = szerokosc_geograficzna;
		this.dlugosc_geograficzna = dlugosc_geograficzna;
		this.na_zadanie = na_zadanie;
	}

	/* Getters and setters */
	public int getID_przystanku() {
		return id_przystanku;
	}

	public void setID_przystanku(int iD_przystanku) {
		id_przystanku = iD_przystanku;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public float getSzerokosc_geograficzna() {
		return szerokosc_geograficzna;
	}

	public void setSzerokosc_geograficzna(float szerokosc_geograficzna) {
		this.szerokosc_geograficzna = szerokosc_geograficzna;
	}

	public float getDlugosc_geograficzna() {
		return dlugosc_geograficzna;
	}

	public void setDlugosc_geograficzna(float dlugosc_geograficzna) {
		this.dlugosc_geograficzna = dlugosc_geograficzna;
	}

	public String getNa_zadanie() {
		return na_zadanie;
	}

	public void setNa_zadanie(String na_zadanie) {
		this.na_zadanie = na_zadanie;
	}

	@Override
	public String toString() {
		return "Przystanek [ID_przystanku=" + id_przystanku + ", nazwa=" + nazwa + ", szerokosc_geograficzna="
				+ szerokosc_geograficzna + ", dlugosc_geograficzna=" + dlugosc_geograficzna + ", na_zadanie="
				+ na_zadanie + "]";
	}
	
}
