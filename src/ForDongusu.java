import java.util.Scanner;
import java.util.Random;

public class ForDongusu {
    /*
     * Mülakat Sorusu: "Şifreli Basamak Matrisi ve Kademeli Filtreleme"Bir finans
     * veya güvenlik şirketinin teknik mülakatında sorulan bu senaryoda, sana
     * rastgele pozitif tam sayılardan oluşan iki boyutlu bir dizi (matris)
     * veriliyor.Senaryo ve Kurallar:Veri Yapısı: Elinde $M \times N$ boyutlarında
     * iki boyutlu bir tamsayı dizisi var.Adım 1 - Basamak Analizi (for / while ve
     * switch-case):Matrisin içindeki her bir sayının basamaklarını tek tek
     * incelemen gerekiyor. Her sayının basamak değerlerini kendi içinde toplayarak
     * yeni bir ara değer elde etmelisin. Elde ettiğin bu toplamın sonucuna göre
     * (örneğin 4'e bölümünden kalana göre) bir
     * switch-case yapısından geçirerek o sayıya yeni bir "puan" atamalısın.Adım 2 -
     * Özyinelemeli (Recursive) Eleme:Elde ettiğin bu yeni puan matrisi üzerinden,
     * belirleyeceğin bir kurala göre (örneğin belirli bir eşik değerinden küçük
     * olanları veya asal sayı olanları) recursive (özyinelemeli) bir fonksiyon
     * yazarak filtrelemeli ve bu sayıları matristen "ayıklamalısın" (yerine örneğin
     * 0 yazarak etkisiz kılabilirsin).Adım 3 - Döngü Kontrolü ve
     * Raporlama (break, continue):Aranan gizli bir "hedef sayı" veya "desene"
     * ulaşıldığında döngüyü anında kırmak (break) ya da istenmeyen şartları
     * sağlayan turu es geçmek (continue) için akış kontrol komutlarını stratejik
     * olarak kullanmalısın.
     */
    public static void main(String[] args) {
        int[][] tempDizi = matrisDoldurma(matrisOlusturma());
        int[][] tempDizi2 = matrisElemanlarinaBakma(tempDizi);
        int satir = 0, sutun = 0;
        elemanEleme(satir, sutun, tempDizi2);
        arananSayi(tempDizi);
        elemanlariYazdirma(tempDizi, tempDizi2);
    }

    static int x, y;

    static int[][] matrisOlusturma() {
        Scanner girdi = new Scanner(System.in);
        System.out.println("Iki boyutlu matrisin ilk boyutunu giriniz:");
        x = girdi.nextInt();
        System.out.println("Iki boyutlu matrisin ikinci boyutunu giriniz:");
        y = girdi.nextInt();
        int[][] matris = new int[x][y];
        System.out.println("Matris olusturuldu");
        return matris;
    }

    static int[][] matrisDoldurma(int[][] matris) {
        Random rand = new Random();
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                matris[i][j] = rand.nextInt(999) + 1;
            }
        }
        System.out.println("Matrise elemanlar atandi");
        return matris;
    }

    static int[][] matrisElemanlarinaBakma(int[][] matris) {
        int temp, basamakToplami = 0, deger;

        int[][] matris2 = new int[x][y];
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                temp = matris[i][j];
                basamakToplami = 0;
                while (temp > 0) {
                    basamakToplami += (temp % 10);
                    temp /= 10;
                }
                deger = basamakToplami % 4;
                switch (deger) {
                    case 0 -> {
                        matris2[i][j] = 10;
                    }
                    case 1 -> {
                        matris2[i][j] = 20;
                    }
                    case 2 -> {
                        matris2[i][j] = 30;
                    }
                    case 3 -> {
                        matris2[i][j] = -10;
                    }
                    default -> {
                        matris2[i][j] = 50;
                    }
                }
            }
        }
        return matris2;
    }

    static void elemanEleme(int satir, int sutun, int[][] matris2) {
        if (satir >= matris2.length) {
            return;
        }
        if (matris2[satir][sutun] < 30) {
            matris2[satir][sutun] = 0;
        }
        int sonrakiSutun = sutun + 1;
        int sonrakiSatir = satir;

        if (sonrakiSutun >= matris2[satir].length) {
            sonrakiSutun = 0;
            sonrakiSatir = satir + 1;
        }

        elemanEleme(sonrakiSatir, sonrakiSutun, matris2);
    }

    static void arananSayi(int[][] matris) {
        Scanner girdi = new Scanner(System.in);
        System.out.println("Hangi sayiyi ariyorsunuz?");
        int sayi = girdi.nextInt();
        boolean kontrol = false;

        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                if (sayi == matris[i][j]) {
                    kontrol = true;
                    break;
                }
            }
        }
        if (kontrol) {
            System.out.println("Sayiniz bulundu!");
        } else {
            System.out.println("Sayiniz bulunamadi!");
        }
    }

    static void elemanlariYazdirma(int[][] matris, int[][] matris2) {
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[i].length; j++) {
                System.out.println(matris[i][j] + " sayisinin puani: " + matris2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
