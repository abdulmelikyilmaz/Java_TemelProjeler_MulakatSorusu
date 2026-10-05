import java.util.Scanner;

public class SwitchCase2 {
    /*
     * Mülakat Senaryosu: "Akıllı Fiyatlandırma ve Risk Motoru"
     * Bir e-ticaret ve fintech uygulaması için sipariş risk analizi ve dinamik
     * indirim hesaplayan bir modül tasarlıyorsunuz. Gelen bir Order nesnesinin
     * durumuna ve içeriğine göre hem işlem sonucunu belirlemeniz hem de kural
     * motorundan dönen değere göre toplam tutarı hesaplamanız gerekiyor.
     * 
     * Kurallar ve İstediklerim:
     * Girdi (Input):
     * Siparişin türünü (Type: DIGITAL, PHYSICAL, SUBSCRIPTION, B2B), müşteri
     * kategorisini (CustomerTier: STANDARD, VIP, CORP) ve sepet tutarını (double
     * amount) içeren bir yapı olduğunu hayal edin.
     * 
     * Koşullar:
     * 
     * DIGITAL siparişler: Eğer müşteri VIP veya CORP ise sepete %20 indirim
     * uygulanmalı; STANDARD ise indirim yapılmamalı. Ancak tutar 1000 TL'nin
     * üzerindeyse, müşteri tipine bakılmaksızın ekstra %10 "yüksek hacim" indirimi
     * eklenmeli (indirimler birikebilir).
     * 
     * PHYSICAL siparişler: Kargo durumuna göre değerlendirilmeli. Eğer sepet tutarı
     * 500 TL'den azsa sabit 50 TL kargo ücreti eklenmeli, 500 TL ve üzerindeyse
     * kargo ücretsiz olmalı. Ek olarak, CORP müşteriler için kargo ücretinden
     * bağımsız olarak ekstra %5 kurumsal indirim uygulanmalı.
     * 
     * SUBSCRIPTION siparişler: VIP müşteriler için yıllık alımda %30, aylık alımda
     * %15 indirim yapılmalı (bunu ayırt etmek için içeride bir alt kontrol
     * gerekebilir). STANDARD müşteriler için standart fiyat geçerli.
     * 
     * B2B siparişler: CORP dışındaki müşteri tipleri bu siparişi veremez; verilirse
     * sistem IllegalArgumentException fırlatmalı. CORP için ise tutar üzerinden
     * kademeli iskonto uygulanmalı (örneğin 5000 TL üstü %25, altı %10).
     * 
     * Mülakat Tuzakları ve Beklentiler:
     * 
     * Bu yapıyı klasik switch-case yerine Java'nın modern Switch Expressions (->
     * operatörü ve yield) yapılarını kullanarak tek bir ifade (expression) şeklinde
     * yazmalısınız.
     * 
     * case blokları içerisinde Pattern Matching (Tür eşleme / Guard koşulları when
     * gibi) kullanmanız gerekebilir.
     * 
     * break unutma riskini nasıl ortadan kaldırdığınızı ve kodun okunabilirliğini
     * nasıl maksimumda tuttuğunuzu göstermelisiniz.
     */

    public static void main(String[] args) {
        Scanner girdi = new Scanner(System.in);
        System.out.println("Siparis turleri\n " +
                "1-SUBSCRIPTION\n" +
                "2-DIGITAL\n" +
                "3-PHYSICAL\n" +
                "4-B2B");
        System.out.println("Siparis turunu giriniz:");
        String siparisTuru = girdi.nextLine();
        // STANDARD, VIP, CORP
        System.out.println("Musteri kategorisi\n" +
                "STANDART\n" +
                "VIP\n" +
                "CORP");
        System.out.println("Musteri kategorisini giriniz:");
        String kategori = girdi.nextLine();
        System.out.println("Sepet tutarini giriniz:");
        double sepetTutari = girdi.nextDouble();

        int abonelikDurumu = -1;

        if (siparisTuru.equals("SUBSCRIPTION")) {
            System.out.println("Abonelik durumunuz nedir?");
            System.out.println("1-Yillik\n" +
                    "2-Aylik");
            abonelikDurumu = girdi.nextInt();
        }

        double indirimliTutar = akilliFiyatlandirma(siparisTuru, kategori, sepetTutari, abonelikDurumu);
        System.out.println("Hesaplanan Yeni Sepet Tutari: " + indirimliTutar);

        girdi.close();

    }

    static double akilliFiyatlandirma(String siparisTuru, String kategori, double sepetTutari, int abonelikDurumu) {
        return switch (siparisTuru) {
            case "DIGITAL" -> {
                if (sepetTutari > 1000) {
                    sepetTutari -= (sepetTutari * 10) / 100;
                }
                if (kategori.equals("VIP") || kategori.equals("CORP")) {
                    sepetTutari -= (sepetTutari * 20) / 100;
                }
                yield sepetTutari;
            }
            case "PHYSICAL" -> {
                if (sepetTutari < 500) {
                    sepetTutari += 50;
                }
                if (kategori.equals("CORP")) {
                    sepetTutari -= (sepetTutari * 5) / 100;
                }
                yield sepetTutari;
            }
            case "SUBSCRIPTION" -> {
                if (kategori.equals("VIP")) {
                    if (abonelikDurumu == 1) {
                        sepetTutari -= (sepetTutari * 30) / 100;
                    } else if (abonelikDurumu == 2) {
                        sepetTutari -= (sepetTutari * 15) / 100;
                    }
                }
                yield sepetTutari;
            }
            case "B2B" -> {
                if (kategori.equals("CORP")) {
                    if (sepetTutari >= 5000) {
                        sepetTutari -= (sepetTutari * 25) / 100;
                    } else {
                        sepetTutari -= (sepetTutari * 10) / 100;
                    }
                } else {
                    throw new IllegalArgumentException("Bu kategori siparis veremez!");
                }
                yield sepetTutari;
            }
            default -> throw new IllegalArgumentException("Gecersiz siparis turu!");
        };

    }

}