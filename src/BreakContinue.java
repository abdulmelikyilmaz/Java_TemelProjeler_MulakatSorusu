import java.util.Scanner;
import java.util.Random;

public class BreakContinue {
    /*
     * 🧩 Senaryo: Akıllı Veri Akışı ve Filtreleme Motoru
     * Bir veri işleme sisteminde, sana int türünde iki boyutlu bir matris (örneğin
     * 2D dizi) veriliyor. Bu matris, sensörlerden gelen ham sıcaklık ölçümlerini
     * temsil ediyor. Sistem, bu veriler üzerinde belirli kurallara göre temizleme
     * ve analiz yapmanı istiyor.
     * 
     * Kurallar ve Kısıtlar:
     * 
     * Döngüler ve Kontrol: Matrisi satır satır ve sütun sütun gezerken, iç içe
     * döngüler (for veya while) kullanmalısın.
     * 
     * continue Şartı: Eğer okunan değer belirli bir eşik değerinin altındaysa
     * (örneğin negatif bir değerse veya sistemin belirleyeceği gürültü sınırının
     * altındaysa), o anki ölçümü işleme almadan direkt bir sonraki adıma geçmelisin
     * (continue).
     * 
     * break Şartı: Eğer satır veya genel akış boyunca kritik bir hata kodu (örneğin
     * önceden belirlenmiş bir durdurma değeri) ile karşılaşırsan, o döngüyü veya
     * tüm taramayı anında sonlandırmalısın (break).
     * 
     * Özyineleme (Recursive) Entegrasyonu: Elde edilen filtrelenmiş verilerin
     * toplamını veya belirli bir kurala göre katlanarak artan özet değerini
     * hesaplamak için normal bir toplama döngüsü yerine özyinelemeli (recursive)
     * bir metod yazıp kullanmalısın.
     * 
     * Hata Yönetimi (try-catch): İki boyutlu dizinin boyutlarında veya indeks
     * sınırlarında yaşanabilecek olası taşma/hata durumlarını (örneğin boş veya
     * düzensiz matrisler) try-catch bloğu ile yakalayıp güvenli bir varsayılan
     * değer döndürmelisin.
     */
    public static void main(String[] args) {
        System.out.println("=== Akıllı Veri İşleme Sistemi Başlatıldı ===");

        // 1. Adım: Dizi oluşturuluyor
        int[][] olusturulanDizi = diziOlusturma();

        // 2. Adım: Rastgele değerler atanıyor
        int[][] doluDizi = degerAtama(olusturulanDizi);

        System.out.println("\n--- Rastgele Üretilen Ham Matris ---");
        matrisiYazdir(doluDizi); // Matrisi ekranda görelim

        // 3. Adım: Kontroller yapılıyor (break / continue)
        System.out.println("\nVeriler kurallara göre filtreleniyor...");
        int[][] filtrelenmisDizi = degerKontrol(doluDizi);

        // 4. Adım: Özyinelemeli toplama/tarama yapılıyor
        System.out.println("\nÖzyinelemeli tarama başlatılıyor...");
        int[][] sonucDizi = degerToplam(filtrelenmisDizi, 0, 0);

        System.out.println("\n=== İşlem Başarıyla Tamamlandı! ===");
    }

    static int[][] diziOlusturma() {
        Scanner girdi = new Scanner(System.in);

        System.out.println("Dizinin sutun sayisini giriniz:");
        int sutun = girdi.nextInt();
        System.out.println("Dizinin satir sayisini giriniz:");
        int satir = girdi.nextInt();
        int[][] dizi = new int[sutun][satir];
        System.out.println("Dizi olusturuldu");

        return dizi;
    }

    static int[][] degerAtama(int[][] dizi) {
        Random rand = new Random();

        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                dizi[i][j] = rand.nextInt(100) + 1;
            }
        }
        return dizi;
    }

    static int[][] degerKontrol(int[][] dizi) {
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                if (dizi[i][j] == 50) {
                    break;
                }
                if (dizi[i][j] < 50) {
                    continue;
                }
            }
        }
        return dizi;
    }

    static int[][] degerToplam(int[][] dizi, int sutun, int satir) {

        try {
            if ((sutun * satir) == (dizi.length * dizi[0].length)) {
                return dizi;
            }
            if (satir >= dizi.length) {
                return dizi;
            }
            if (sutun >= dizi[satir].length) {
                return degerToplam(dizi, 0, satir + 1);
            }
            return degerToplam(dizi, sutun + 1, satir);
        } catch (Exception ex) {
            System.out.println("Hata yakalandı: " + ex.getMessage());
            return new int[0][0]; // Güvenli boş matris
        }

    }

    static void matrisiYazdir(int[][] dizi) {
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                System.out.print(dizi[i][j] + "\t");
            }
            System.out.println(); // Alt satıra geç
        }
    }

}
