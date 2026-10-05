import java.util.Random;
import java.util.Scanner;

import javax.xml.parsers.FactoryConfigurationError;

public class WhileDongusu {
    /*
     * Mülakat Sorusu: "Matris Labirentinde Güvenli Geçiş"Elinde sabit boyutlu bir
     * 2D dizi (matris) olduğunu hayranlıkla varsayalım (örneğin $5 \times 5$
     * boyutunda bir oyun alanı veya harita). Bu matrisin içinde bazı hücreler
     * güvenli, bazıları ise engelli/tuzaklı (örneğin özel bir sayıyla temsil
     * ediliyor).Senaryo ve Kurallar:Dinamik Girdi Alımı (While + Try-Catch +
     * Break/Continue):Kullanıcıdan sürekli olarak koordinat veya hamle bilgileri
     * (satır ve sütun değerleri) bir while döngüsü içinde istenir.Kullanıcı harf
     * girerse, matris sınırlarının dışına çıkarsa veya mantıksız bir değer girerse
     * program çökmemeli (try-catch), hata ekrana yazdırılıp continue ile yeni hamle
     * istenmelidir.Kullanıcı çıkış tuşuna bastığında veya özel bir durdurma değeri
     * girdiğinde break ile döngü sonlandırılmalıdır.Asıl Zorluk (2D Dizi +
     * Recursive Algoritma):Giriş aşaması bittikten sonra ve matrisin son hali
     * netleştiğinde, başlangıç noktasından (örneğin [0][0]) hedef noktaya ([4][4])
     * engellere takılmadan ulaşıp ulaşılamayacağını kontrol eden bir rekürsif
     * (recursive) fonksiyon yazman gerekiyor.Bu rekürsif fonksiyon, matris içinde
     * yukarı, aşağı, sağa, sola adım atarken daha önce gezdiği yerleri tekrar
     * dolaşmamak (sonsuz döngüye girmemek) için yine diziler veya mantıksal durum
     * takipleri kullanmalı.Fonksiyon, hedefe varılabiliyorsa true, varılamıyorsa
     * false dönmeli.
     */

    public static void main(String[] args) {
        int[][] dizi = new int[5][5];
        System.out.println("Dizi olusturuldu");
        int engelSayisi = 3;
        engeller(dizi, engelSayisi);
        System.out.println("Engeller olusturuldu");

        System.out.println("Oyun basladi");
        System.out.println("Cikis yapmak icin -1 giriniz:");

        hamle(dizi);

        boolean[][] gezildi = new boolean[5][5];

        boolean sonuc = hedefeUlasildiMi(dizi, 0, 0, gezildi);

        if (sonuc) {
            System.out.println("Tebrikler, hedefe guvenli bir yol bulundu!");
        } else {
            System.out.println("Maalesef hedefe giden guvenli bir yol bulunamadi (Engel veya cikmaz sokak var).");
        }

    }

    static void engeller(int[][] dizi, int engelSayisi) {
        Random rand = new Random();
        while (engelSayisi != 0) {
            int xEkseni = rand.nextInt(5);
            int yEkseni = rand.nextInt(5);

            if (dizi[xEkseni][yEkseni] != 1) {
                dizi[xEkseni][yEkseni] = 1;
                engelSayisi--;
            }
        }
    }

    static void hamle(int[][] dizi) {
        Scanner girdi = new Scanner(System.in);
        int xEkseni, yEkseni;
        while (true) {
            try {
                System.out.println("Hamlenizi giriniz:");
                xEkseni = girdi.nextInt();
                yEkseni = girdi.nextInt();
                if (xEkseni == -1 || yEkseni == -1) {
                    System.out.println("Cikis yapildi");
                    break;
                }

                if (xEkseni < 0 || xEkseni > 4) {
                    throw new IllegalArgumentException("Index disina ciktiniz!");
                }
                if (yEkseni < 0 || yEkseni > 4) {
                    throw new IllegalArgumentException("Index disina ciktiniz!");
                }
                dizi[xEkseni][yEkseni] = 1;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        girdi.close();
    }

    static boolean hedefeUlasildiMi(int[][] dizi, int x, int y, boolean[][] gezildi) {
        if (x < 0 || x > 4) {
            return false;
        }
        if (y < 0 || y > 4) {
            return false;
        }
        if (dizi[x][y] == 1) {
            return false;
        }
        if (gezildi[x][y] == true) {
            return false;
        }
        if (x == 4 && y == 4) {
            return true;
        }

        gezildi[x][y] = true;

        boolean sonuc = hedefeUlasildiMi(dizi, x + 1, y, gezildi) ||
                hedefeUlasildiMi(dizi, x, y + 1, gezildi) ||
                hedefeUlasildiMi(dizi, x - 1, y, gezildi) ||
                hedefeUlasildiMi(dizi, x, y - 1, gezildi);

        return sonuc;
    }
}
