import java.util.Scanner;

public class TryCatch {
    /*
     * Mülakat Sorusu: "Güvenli Kümülatif Veri İşleme ve Çevrim Motoru"
     * Bir finans/veri analiz uygulaması geliştirdiğini hayal et. Sistem, iki
     * boyutlu tam sayı dizilerinden (int[][]) oluşan bir veri matrisini alıp
     * belirli kurallara göre işlemek zorunda.
     * 
     * Senaryo ve Kurallar:
     * 1. Girdi (Input):
     * 
     * Metot, iki boyutlu bir int[][] matris alacak.
     * 
     * 2. İşlem Adımları (Döngü ve Akış Kontrolü):
     * 
     * Matrisin satırları ve sütunları üzerinde gezineceksin.
     * 
     * Her bir elemanı işlerken şu mantığı izlemelisin:
     * 
     * Eğer eleman tek bir sayı ise, bu sayının faktöriyelini hesaplayıp toplam
     * değere eklemelisin. (Faktöriyel hesaplamasını recursive (özyinelemeli) bir
     * metotla yapmalısın).
     * 
     * Eğer eleman çift bir sayı ise, sayının kendisini doğrudan toplam değere
     * eklemelisin.
     * 
     * Eğer eleman sıfır (0) ise, döngüyü tamamen kırmak yerine o anki satırı
     * atlayıp sonraki satırdan devam etmeni sağlayacak bir akış kontrolü (continue
     * mantığı) çalıştırmalısın.
     * 
     * 3. Hata Yönetimi ve Sınırlar (try-catch):
     * 
     * Matris işlenirken dışarıdan gelebilecek veya mantıksal olarak oluşabilecek
     * bazı hataları try-catch bloğu ile yakalayıp güvenli bir şekilde yönetmelisin:
     * 
     * Aritmetik/Matematiksel Hata: Örneğin, negatif bir sayının faktöriyelini
     * almaya çalışmak veya çok büyük sayılarda taşma (overflow) olması durumunda
     * uygun bir istisnayı yakalayıp ekrana kullanıcı dostu bir hata mesajı
     * yazdırmalı ve işlem sonucunu sıfırlamadan o ana kadar hesaplanan değeri
     * koruyarak ya da güvenli bir akışla bitirmelisin.
     * 
     * Null veya Boyut Hatası: Matrisin kendisinin veya satırlarından birinin null
     * olması ya da beklenen boyutların dışına çıkılması
     * (ArrayIndexOutOfBoundsException) durumunda sistemin çökmesini engellemek için
     * gerekli yakalamaları yapmalısın.
     */
    public static void main(String[] args) {
        diziKontrol(diziOlusturma());
    }

    static int[][] diziOlusturma() {
        Scanner girdi = new Scanner(System.in);
        System.out.println("Dizinin x koordinatini giriniz:");
        int x = girdi.nextInt();
        System.out.println("Dizinin y koordinatini giriniz:");
        int y = girdi.nextInt();
        int[][] dizi = new int[x][y];
        System.out.println("Dizi olusturuldu");
        return dizi;
    }

    static void diziKontrol(int[][] dizi) {
        int toplam = 0;
        donguAtlama: for (int[] i : dizi) {
            for (int j : i) {
                try {
                    if (j < 0) {
                        throw new NegatifKontrol();
                    }
                    if (j > 2147483647) {
                        throw new BuyuklukAsma();
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }

                if (j == 0) {
                    continue donguAtlama;
                } else if (j % 2 == 0) {
                    toplam += j;
                } else {
                    toplam += faktoriyel(j);
                }
            }
        }
    }

    static int faktoriyel(int sayi) {
        if (sayi < 0) {
            return 0;
        }
        if (sayi == 1) {
            return 1;
        }

        return faktoriyel(sayi - 1) * sayi;
    }

}