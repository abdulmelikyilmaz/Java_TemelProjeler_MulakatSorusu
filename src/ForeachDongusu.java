import java.util.Scanner;
import java.util.Random;

public class ForeachDongusu {
    /*
     * 🧩 Mülakat Sorusu: "Kayıp Sinyal Desifre Edici"
     * Bir uzay gözlem istasyonu, uzaydan iki boyutlu bir matris şeklinde
     * şifrelenmiş veri paketleri alıyor. Bu paketleri analiz edip anlamlı bir
     * sonuca ulaşman gerekiyor. Ancak uzay boşluğundaki parazitler yüzünden veriler
     * bozulabiliyor.
     * 
     * Kurallar ve Kısıtlar:
     * Veri Yapısı: Şifrelenmiş veriler iki boyutlu bir tamsayı dizisinde (int[][])
     * saklanıyor. Her bir hücre bir sinyal şiddetini temsil ediyor.
     * 
     * Hata Yönetimi (try-catch):
     * 
     * Veri matrisi işlenirken dışarıdan gelen bozuk referanslar (null) veya sınır
     * dışı erişim ihtimallerine karşı kodun güvenli olmalı. Hata fırlatan
     * durumlarda program çökmemeli, try-catch ile yakalanıp loglanmalı (ekrana
     * yazdırılmalı) ve işlem güvenli şekilde sürdürülmelidir.
     * 
     * Akış Kontrolü (break / continue / switch-case):
     * 
     * Sinyal değerleri taranırken eğer 999 değeri görülürse, bu acil durum kodu
     * demektir; sinyal analizi anında sonlandırılmalıdır (break).
     * 
     * Eğer sinyal değeri çift sayı ise, bu gürültüdür ve bu değer atlanmalıdır
     * (continue).
     * 
     * Tek sayı olan sinyallerin türüne göre (örneğin son rakamına veya büyüklüğüne
     * göre) switch-case kullanarak farklı katsayılarla çarpıp puanlama yapmalısın.
     * 
     * Döngüler ve İleri Yapılar:
     * 
     * Matrisin satırlarında veya sütunlarında gezinirken mutlaka foreach döngüsünü
     * ana gezinti mekanizması olarak kullanmalısın.
     * 
     * Toplama veya karmaşık katsayı hesaplama adımlarından birini recursive
     * (özyinelemeli) bir fonksiyon yazarak çözmelisin.
     */
    public static void main(String[] args) {

        recursiveSum(diziElemanlariKontrolu(diziyeElemanGirme(diziOlusturma())), 0, 0);
    }

    static Integer[][] diziOlusturma() {
        Scanner girdi = new Scanner(System.in);
        System.out.println("Dizinin x boyutunu giriniz:");
        int x = girdi.nextInt();
        System.out.println("Dizinin y boyutunu giriniz:");
        int y = girdi.nextInt();
        Integer[][] dizi = new Integer[x][y];
        return dizi;
    }

    static Integer[][] diziyeElemanGirme(Integer[][] dizi) {
        Random rand = new Random();
        try {
            for (int i = 0; i < dizi.length; i++) {
                for (int j = 0; j < dizi[i].length; j++) {
                    int olusturulanSayi = rand.nextInt(999) + 1;
                    if (olusturulanSayi == 100) {
                        dizi[i][j] = null;
                    } else {
                        dizi[i][j] = olusturulanSayi;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return dizi;
    }

    static Integer[][] diziElemanlariKontrolu(Integer[][] dizi) {
        boolean kontrol = false;
        int puanToplam = 0;
        for (Integer[] satir : dizi) {
            for (Integer sutun : satir) {
                if (sutun == 999) {
                    System.out.println("Acil kod. Sohbet sonlandirildi");
                    kontrol = true;
                }
                if (kontrol == true) {
                    break;
                }
                if (sutun == null) {
                    continue;
                }
                if (sutun % 2 == 0) {
                    continue;
                } else {
                    int sonRakam = sutun % 10;
                    switch (sonRakam) {
                        case 1 -> {
                            System.out.println(sutun + " sayisinin puani: " + (sonRakam * 1));
                            puanToplam += sonRakam * 1;
                        }
                        case 3 -> {
                            System.out.println(sutun + " sayisinin puani: " + (sonRakam * 3));
                            puanToplam += sonRakam * 3;
                        }
                        case 5 -> {
                            System.out.println(sutun + " sayisinin puani: " + (sonRakam * 5));
                            puanToplam += sonRakam * 5;
                        }
                        case 7 -> {
                            System.out.println(sutun + " sayisinin puani: " + (sonRakam * 7));
                            puanToplam += sonRakam * 7;
                        }
                        case 9 -> {
                            System.out.println(sutun + " sayisinin puani: " + (sonRakam * 9));
                            puanToplam += sonRakam * 9;
                        }
                    }
                    System.out.println("Toplanan puan: " + puanToplam);
                }
            }
            if (kontrol == true) {
                break;
            }
        }
        return dizi;
    }

    public static int recursiveSum(Integer[][] matrix, int row, int col) {
        // 1. Durum: Tüm satırlar bittiyse 0 döndür

        if (row >= matrix.length) {
            return 0;
        }

        // 2. Durum: Mevcut satırdaki sütunlar bittiyse bir sonraki satıra geç
        if (col >= matrix[row].length) {
            return recursiveSum(matrix, row + 1, 0);
        }
        if (matrix[row][col] == null) {
            return recursiveSum(matrix, row, col + 1);
        }
        // 3. Adım: Mevcut elemanı toplaya ekle ve sütunu 1 artırarak devam et
        return matrix[row][col] + recursiveSum(matrix, row, col + 1);
    }

}