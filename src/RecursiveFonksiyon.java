public class RecursiveFonksiyon {
    /*
     * Mülakat Sorusu: "Derinlemesine Dizi Bölme ve Eşleştirme"
     * Bir yazılım şirketinin mülakatındasın ve mülakatçı senden şu kurallara uyan
     * bir Java metodu yazmanı istiyor:
     * 
     * Senaryo ve Kuralları:
     * Sana tek boyutlu bir tam sayı dizisi (int[] arr) ve bir hedef sayı (int
     * target) veriliyor.
     * 
     * Bu diziyi recursive (öz yinelemeli) bir fonksiyon kullanarak işlemelisin.
     * Döngü (for, while, do-while, foreach) kullanmak yasak; tüm yinelemeyi ve
     * kontrolü recursive fonksiyon ve if-else yapılarıyla çözmelisin.
     * 
     * Fonksiyonun görevi: Dizinin içindeki elemanları kullanarak, öyle bir alt küme
     * (veya ardışık/ardışık olmayan kombinasyon) aramak değil; dizinin başından ve
     * sonundan başlayarak recursive mantıkla şu mantıksal eşleştirmeyi yapmak:
     * 
     * Fonksiyon, dizinin iki ucundan (en sol ve en sağ) elemanları karşılaştırmalı.
     * 
     * Eğer sol ve sağ elemanın toplamı target değerine eşitse, bu indeksteki
     * durumları bir şekilde işaretlemeli veya saymalıdır.
     * 
     * Ancak bunu yaparken, recursive fonksiyon her adımda diziyi ikiye bölmeli
     * (Divide and Conquer mantığıyla, örneğin sol indeksi 1 artırıp sağ indeksi 1
     * azaltarak iç içe veya ardışık alt problemlere inilmeli).
     * 
     * İşin içine biraz mülakat zoru katmak için: Eğer sol eleman ile sağ eleman
     * birbirine eşitse ve aynı zamanda bu değer target'ın tam yarısına eşitse, bu
     * durumu özel olarak yakalayıp try-catch bloğu içinde kontrol etmeni
     * istiyorlar. (Yani burada mantıksal bir hata fırlatabilir veya kendi yazacağın
     * özel bir Exception durumunu try-catch ile yakalayıp akışı değiştirebilirsin).
     */
    public static void main(String[] args) {
        int[] dizi = diziOlusturma();
        int target = 4;
        recursiveFonksiyon(dizi, target, 0);
    }

    static int[] diziOlusturma() {
        int[] dizi = { 3, 1, 2, 3, 4, 2, 1, 1 };
        return dizi;
    }

    static void recursiveFonksiyon(int[] dizi, int target, int i) {

        if (i == ((dizi.length - 1) / 2)) {
            return;
        }

        if (dizi[dizi.length - i - 1] + dizi[i] == target) {
            System.out.println(dizi[dizi.length - i - 1] + "-" + dizi[i]);
        }

        try {
            if ((dizi[dizi.length - i - 1] == dizi[i]) && ((dizi[dizi.length - i - 1] + dizi[i]) * 2) == target) {
                throw new HataSinifAdi();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " " + dizi[dizi.length - i - 1] + "-" + dizi[i]);
        }

        recursiveFonksiyon(dizi, target, i + 1);
    }
}
