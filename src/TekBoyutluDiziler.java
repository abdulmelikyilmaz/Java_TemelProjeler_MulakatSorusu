public class TekBoyutluDiziler {
    /*
     * Mülakat Sorusu: "Kayıp Çifti Bulma ve Zincirleme Tamamlama"
     * Senaryo:
     * Bir teknoloji şirketinin mülakatındasın ve mülakatçı sana tam sayısal
     * değerlerden oluşan, boyutu en az 4 olan tek boyutlu bir dizi (int[]) veriyor.
     * Bu dizide bazı özel kurallar ve eksiklikler var. Görevin, diziyi manipüle
     * etmeden istenen mantıksal analizi yapan algoritmayı kurmak.
     * 
     * Kurallar ve İstihbarat:
     * 
     * Dizi Yapısı: Dizi rastgele pozitif tam sayılardan oluşur ve dizideki bazı
     * sayılar tam çiftler (iki tane aynı sayı) halinde bulunur, bazıları ise tek
     * kalmıştır.
     * 
     * Özel Durum (Kayıp ve Fazlalık): Dizide tek bir sayı hariç tüm sayılar ya çift
     * olarak bulunur ya da önceden belirlenmiş bir kurala uyar. Ancak bu soruda işi
     * biraz daha ilginç hale getiriyoruz:
     * 
     * Dizideki sayılardan tam olarak bir tanesi eksiktir (yani tek başına
     * kalmıştır, eşi yoktur).
     * 
     * Fakat dizinin boyutu tek mi çift mi belli değildir.
     * 
     * Senden İstenen Görev:
     * Parametre olarak bir int[] dizi alan ve geriye bir şey döndürmek yerine
     * ekrana veya mantıksal olarak şu çıktıları veren bir yapı kurgulaman
     * bekleniyor (Sadece bildiğin konuları kullanacaksın, ekstra veri yapıları
     * HashMap, List veya Set yasak, sadece tek boyutlu dizi ve bildiğin kontrol
     * yapıları kullanılacak):
     * 
     * Eşleşmeyen/Tek Kalan Elemanı Bulma: Dizide eşi (çifti) olmayan o tek elemanı
     * bul ve ekrana yazdır. (İpucu: Bunu yaparken dizinin eleman sayısını ve
     * döngüleri nasıl yönlendireceğini iyi düşünmelisin).
     * 
     * Döngü ve Kontrol: Eğer dizide aynı sayıdan 2'den fazla varsa (örneğin 3 tane
     * aynı sayı), bunu "Geçersiz Durum" olarak yakala ve try-catch mekanizması
     * fırlatarak (veya uygun bir hata yakalama kurgusuyla) yönet.
     * 
     * Recursive (Özyineleme) Dokunuşu: Dizinin elemanları üzerinde belirli bir
     * kurala göre arama yaparken veya toplam/kontrol işlemlerini gerçekleştirirken
     * recursive bir fonksiyon da işin içine dahil edilebilir. Örneğin, dizideki
     * elemanların toplamını veya belirli bir koşul sağlanıp sağlanmadığını kontrol
     * eden yardımcı bir recursive metod yazabilirsin.
     */
    public static void main(String[] args) {
        int[] dizi = { 1, 2, 3, 4, 5, 6, 4, 3, 2, 1 };
        tekSayi(dizi);
        recursiveToplam(dizi, 0, 0);
    }

    static void tekSayi(int[] dizi) {
        int sayac = 0, temp = -1;
        for (int i = 0; i < dizi.length; i++) {
            sayac = 0;
            for (int j = 0; j < dizi.length; j++) {
                if (dizi[i] == dizi[j]) {
                    sayac++;
                }
                temp = dizi[i];
            }
            try {
                if (sayac > 2) {
                    throw new GecersizDurum();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (sayac < 2) {
                System.out.println("Tek olan sayi: " + temp);
            }
        }
    }

    static void recursiveToplam(int[] dizi, int i, int toplam) {
        if (i == dizi.length) {
            System.out.println("Toplam: " + toplam);
            return;
        }
        toplam += dizi[i];
        recursiveToplam(dizi, i + 1, toplam);
    }
}
