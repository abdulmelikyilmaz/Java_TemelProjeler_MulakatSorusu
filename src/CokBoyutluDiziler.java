import java.util.Random;
import java.util.Scanner;

public class CokBoyutluDiziler {

    public static void main(String[] args) {
        // Test için örnek akış
        Integer[][] matris = diziOlusturma();
        if (matris != null) {
            diziyeElemanGirme(matris);

            // Güvenli işleme ve try-catch ile dışarıdan hata yakalama
            try {
                int sonuc = elemanKontrol(matris);
                System.out.println("İşlem Başarılı! Toplam Sonuç: " + sonuc);
            } catch (Exception e) {
                System.out.println("[Kritik Hata Yakalandı]: " + e.getMessage());
            }
        }
    }

    static Integer[][] diziOlusturma() {
        try {
            Scanner girdi = new Scanner(System.in);
            System.out.println("Dizinin x (satır) boyutunu giriniz:");
            int x = girdi.nextInt();
            System.out.println("Dizinin y (sütun) boyutunu giriniz:");
            int y = girdi.nextInt();

            if (x <= 0 || y <= 0) {
                throw new IllegalArgumentException("Boyutlar sıfır veya negatif olamaz!");
            }

            return new Integer[x][y];
        } catch (Exception e) {
            System.out.println("Dizi oluşturulurken hata: " + e.getMessage());
            return null;
        }
    }

    static Integer[][] diziyeElemanGirme(Integer[][] dizi) {
        Random rand = new Random();
        try {
            for (int i = 0; i < dizi.length; i++) {
                for (int j = 0; j < dizi[i].length; j++) {
                    // Test amaçlı -50 ile +15 arası değerler (faktöriyel taşması görebilmek için)
                    int olusturulanSayi = rand.nextInt(65) - 50;
                    dizi[i][j] = olusturulanSayi;
                }
            }
        } catch (Exception ex) {
            System.out.println("Eleman girilirken hata: " + ex.getMessage());
        }
        return dizi;
    }

    static int elemanKontrol(Integer[][] dizi) throws Exception {
        int toplam = 0;

        // Null kontrolü (Soru kuralı: Null veya Boyut Hatası)
        if (dizi == null) {
            throw new NullPointerException("Matris null olamaz!");
        }

        // Dıştaki döngüye etiket (label) veriyoruz ki sıfır (0) çıkınca satırı
        // atlayabilelim.
        satirDongusu: for (Integer[] satir : dizi) {
            if (satir == null) {
                continue; // Satır null ise o satırı geç
            }

            for (Integer sutun : satir) {
                // Null eleman kontrolü
                if (sutun == null) {
                    sutun = 0;
                }

                // Kural: Eğer eleman sıfır (0) ise, o anki satırı atlayıp sonraki satırdan
                // devam et
                if (sutun == 0) {
                    continue satirDongusu;
                }

                // Negatif sayı veya çok büyük sayılar için kontrol (Aritmetik/Matematiksel
                // Hata)
                if (sutun < 0) {
                    throw new BoyutGecersiz();
                }
                if (sutun >= 13) {
                    throw new BoyutAsma();
                }

                int anahtarDeger = sutun % 10;

                // Kural: Tek sayı ise faktöriyelini ekle, çift sayı ise kendisini ekle
                if (anahtarDeger % 2 != 0) {
                    toplam += faktoriyel(sutun);
                } else {
                    toplam += sutun;
                }
            }
        }
        return toplam;
    }

    // Recursive (Özyinelemeli) Faktöriyel Metodu
    static int faktoriyel(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return faktoriyel(n - 1) * n;
    }
}