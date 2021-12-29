package hastane_otomasyonu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Methodlar extends VeriBankasi {
	static int hastaNo = 0;
	static int doktorNo = 0;
	static Scanner scanner = new Scanner(System.in);
	static VeriBankasi doktor1 = new VeriBankasi("Hasan", "Kocaman", "Allergist");
	static VeriBankasi doktor2 = new VeriBankasi("Mustafa", "Donat", "Norolog");
	static VeriBankasi doktor3 = new VeriBankasi("Fatih", "Yıldız", "Genel cerrah");
	static VeriBankasi doktor4 = new VeriBankasi("Yıldız", "Aydogan", "Cocuk doktoru");
	static VeriBankasi doktor5 = new VeriBankasi("Emine", "Ceyran", "Dahiliye");
	static VeriBankasi doktor6 = new VeriBankasi("Kader", "Sağlam", "Kardiolog");
	static List<VeriBankasi> doktorList = new ArrayList<>(
			Arrays.asList(doktor1, doktor2, doktor3, doktor4, doktor5, doktor6));
	static List<String> durumlar = new ArrayList<String>(
			Arrays.asList("Allerji", "Bas agrisi", "Diabet", "Sogukalginligi", "Migren", "Kalphastaliklari"));

	public static void menu() {

		System.out.println("====HASTANE OTOMASYONU====\n" + "1-HASTA GIRISI\n" + "2-ÇIKIŞ");
		int secim = scanner.nextInt();
		switch (secim) {
		case 1:
			hastaGırısı();
			menu();
			break;
		case 2:
			cıkıs();
			break;
		default:
		}
	}

	private static void cıkıs() {
		System.out.println("İşlemleriniz yapıldı!!!");
	}

	private static void hastaGırısı() {
		System.out.println("Hastanın adını giriniz : ");
		String hastaAdi = scanner.nextLine();
		scanner.nextLine();
		System.out.println("Hastanın soyadını giriniz : ");
		String hastaSadi = scanner.nextLine();
		System.out.println("Hasta durumunu seçiniz : ");
		hastaDurumu(durumlar);
		int durum = scanner.nextInt() - 1;
		String hastaDurumu = durumlar.get(durum);
		System.out.println("Aciliyet durumunu belirtiniz : \n1-Acil\n2-Acil Değil");
		boolean aciliyet = false;
		int aciliyetSecim = scanner.nextInt();
		switch (aciliyetSecim) {
		case 1:
			aciliyet = true;
			break;
		case 2:
			aciliyet = false;
			break;
		}
		Hasta hasta = new Hasta(++hastaNo, hastaAdi, hastaSadi, hastaDurumu, aciliyet);
		System.out.println(hasta);
		doktorAta(hastaDurumu, doktorList, hasta);

	}

	private static void doktorAta(String hastaDurumu, List<VeriBankasi> doktorList, Hasta hasta) {
		if (hastaDurumu.contains("Allerji")) {
			System.out.println(hasta.getHastaAdi() + " " + hasta.getHastaSadi() + " doktoru : " + doktorList.stream()
					.filter(t -> t.getDoktorUnvan().equals("Allergist")).collect(Collectors.toList()));
		} else if (hastaDurumu.contains("Bas agrisi")) {
			System.out.println(hasta.getHastaAdi() + " " + hasta.getHastaSadi() + " doktoru : " + doktorList.stream()
					.filter(t -> t.getDoktorUnvan().equals("Norolog")).collect(Collectors.toList()));
		} else if (hastaDurumu.contains("Diabet")) {
			System.out.println(hasta.getHastaAdi() + " " + hasta.getHastaSadi() + " doktoru : " + doktorList.stream()
					.filter(t -> t.getDoktorUnvan().equals("Genel cerrah")).collect(Collectors.toList()));
		} else if (hastaDurumu.contains("Sogukalginligi")) {
			System.out.println(hasta.getHastaAdi() + " " + hasta.getHastaSadi() + " doktoru : " + doktorList.stream()
					.filter(t -> t.getDoktorUnvan().equals("Cocuk doktoru")).collect(Collectors.toList()));
		} else if (hastaDurumu.contains("Migren")) {
			System.out.println(hasta.getHastaAdi() + " " + hasta.getHastaSadi() + " doktoru : " + doktorList.stream()
					.filter(t -> t.getDoktorUnvan().equals("Dahiliye")).collect(Collectors.toList()));
		} else if (hastaDurumu.contains("Kalphastaliklari")) {
			System.out.println(hasta.getHastaAdi() + " " + hasta.getHastaSadi() + " doktoru : " + doktorList.stream()
					.filter(t -> t.getDoktorUnvan().equals("Kardiolog")).collect(Collectors.toList()));
		}

	}

	public static void hastaDurumu(List<String> durumlar) {
		AtomicInteger i = new AtomicInteger();
		durumlar.stream().forEach(t -> System.out.println(i.incrementAndGet() + "-" + t));

	}
}