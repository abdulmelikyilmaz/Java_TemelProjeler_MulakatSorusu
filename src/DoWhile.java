import java.util.Scanner;

public class DoWhile {
    /*
     * 🎯 Mülakat Sorusu: "Hatalı Sensör Verisi Simülatörü"
     * Bir IoT (Nesnelerin İnterneti) cihazından gelen ham sıcaklık verilerini
     * işleyen bir sistem tasarlıyorsun. Sistem, kullanıcıdan sürekli veri alacak ve
     * belirli kurallara göre istatistik çıkaracak.
     * 
     * Senaryo ve Kurallar:
     * Giriş Döngüsü (do-while şartı):
     * 
     * Program, kullanıcıdan sürekli olarak tam sayı sıcaklık değerleri girmesini
     * isteyecek.
     * 
     * Kullanıcı özel bir bitiş komutu (-999) girdiği anda döngü sona erecek.
     * 
     * Hata ve İstisna Yönetimi (try-catch):
     * 
     * Kullanıcı sayı yerine harf veya geçersiz bir metin girerse, program
     * çökmemeli; try-catch bloğu ile bu hata yakalanmalı, ekrana uygun bir uyarı
     * yazdırılmalı ve kullanıcıdan tekrar veri istenmelidir (burada do-while
     * mantığı hayat kurtaracak).
     * 
     * Akış Kontrolü (break & continue):
     * 
     * Eğer girilen sıcaklık değeri mutlak sıfırın altında (< -273) ve bitiş komutu
     * (-999) değilse, bu veri "imkansız ölçüm" olarak kabul edilmeli,
     * istatistiklere dahil edilmemeli ve continue ile döngünün başına dönülmelidir.
     * 
     * Eğer sistem üst üste 3 defa hatalı (geçersiz metin veya imkansız sıcaklık)
     * veri girdisi alırsa, güvenlik protokolü devreye girmeli ve break komutu ile
     * döngü zorla sonlandırılmalıdır.
     * 
     * Veri Saklama (Diziler):
     * 
     * Geçerli olarak kabul edilen tüm sıcaklık değerleri, tek boyutlu bir dizide
     * (veya hafıza sınırını simüle eden bir yapıda) saklanmalıdır.
     * 
     * (İpucu: Sabit boyutlu dizi kullanacaksan maksimum kapasiteyi baştan
     * belirleyebilir veya kullanıcıyı sınırlandırabilirsin).
     * 
     * Çok Boyutlu Dizi Analizi (Matris Raporlama):
     * 
     * Döngü bittikten sonra, toplanan tüm geçerli sıcaklık değerleri, satır ve
     * sütun mantığıyla iki boyutlu bir diziye (matrise) aktarılmalıdır (Örneğin;
     * her satırda 5 ölçüm olacak şekilde, kalanlar son satıra yerleştirilebilir).
     * 
     * for veya foreach döngüleri ve switch-case yapısı kullanarak; bu matris
     * üzerindeki her bir satırın ortalamasını hesaplamalı ve switch-case ile
     * sıcaklık kategorisini (Dondurucu, Normal, Sıcak) belirleyip ekrana
     * yazdırmalısın.
     * 
     * Rekürsif (Özyineli) Fonksiyon:
     * 
     * Matris içindeki en yüksek sıcaklık değerini bulma işlemini normal bir
     * döngüyle değil, yazdığın rekürsif bir metot aracılığıyla gerçekleştirmelisin.
     */
    public static void main(String[] args) {
        enYuksekSicaklik(elemanGirdisi(), 0);
    }

    static int[] elemanGirdisi() {
        Scanner girdi = new Scanner(System.in);
        System.out.println("Sicaklik degerini giriniz\n" +
                "Cikis yapmak icin -999 giriniz!");
        int sayi = 0, hata = 0, i = 0;
        int[] dizi = new int[15];
        do {
            try {
                if (i == 15) {
                    System.out.println("Maksimum elemans sayisina ulastiniz");
                    break;
                }
                sayi = girdi.nextInt();
                if (sayi == -999) {
                    break;
                }

                if (sayi < -273) {
                    System.out.println("Imkansiz olcum");
                    hata++;
                    if (hata >= 3) {
                        System.out.println("Ard arda 3 yanlis girim yaptiginizdan hesabiniz bloke oldu");
                        break;
                    }
                    continue;
                } else {
                    hata = 0;
                }

                dizi[i] = sayi;
                i++;

            } catch (Exception ex) {
                System.out.println("Yanlis deger atadiniz!");
                ++hata;
                girdi.next();
                if (hata >= 3) {
                    System.out.println("Çok fazla hatalı giriş! Güvenlik protokolü devreye girdi.");
                    break;
                }
            }
        } while (sayi != -999);

        int[][] dizi2 = new int[3][5];
        int l = 0;
        for (int k = 0; k < dizi2.length; k++) {
            for (int j = 0; j < dizi2[k].length; j++) {
                dizi2[k][j] = dizi[l];
                l++;
            }
        }

        for (int k = 0; k < dizi2.length; k++) {
            for (int j = 0; j < dizi2[k].length; j++) {
                System.out.print(dizi2[k][j] + " ");
            }
            System.out.println();
        }
        int ortalama = 0;
        for (int[] j : dizi2) {
            for (int k : j) {
                ortalama += k;
            }
            ortalama /= 5;
            if (ortalama <= 0) {
                ortalama = 1;
            } else if (ortalama > 0 && ortalama < 25) {
                ortalama = 2;
            } else {
                ortalama = 3;
            }
            switch (ortalama) {
                case 1 -> {

                    System.out.println("Dondurucu soguk");

                }
                case 2 -> {

                    System.out.println("Normal");

                }
                case 3 -> {

                    System.out.println("Sıcak");

                }
            }
            ortalama = 0;
        }
        return dizi;
    }

    static int enYuksek = -999;

    static int enYuksekSicaklik(int[] dizi, int i) {
        if (i == dizi.length) {
            return enYuksek;
        }
        if (dizi[i] > enYuksek) {
            enYuksek = dizi[i];
        }
        return enYuksekSicaklik(dizi, i + 1);
    }

}
