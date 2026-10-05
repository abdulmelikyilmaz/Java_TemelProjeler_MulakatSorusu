import java.util.Scanner;

public class SwitchCase1 {

    /*
     * Mülakat Senaryosu: E-Ticaret Kargo Ücreti ve Teslimat Hesaplayıcı
     * 
     * Bir e-ticaret sistemi için kargo ve teslimat süresi hesaplayan bir metot
     * yazmanız isteniyor.
     * 
     * Kurallar:
     * Metot parametre olarak bir String kargoTipi ve bir double urunAgirligi (kg
     * cinsinden) alacak. switch-case yapısını kullanarak şu kurallara göre toplam
     * kargo ücretini (double olarak) hesaplayıp döndürmelisiniz:
     * 
     * "STANDART":
     * 
     * Ürün ağırlığı 2 kg veya daha azsa: 30.0 TL
     * 
     * Ürün ağırlığı 2 kg'dan fazla ve 5 kg'a (dahil) kadarsa: 50.0 TL
     * 
     * Ürün ağırlığı 5 kg'dan fazlaysa: 80.0 TL
     * 
     * "EXPRESS":
     * 
     * Ürün ağırlığı 3 kg veya daha azsa: 80.0 TL
     * 
     * Ürün ağırlığı 3 kg'dan fazlaysa: 120.0 TL
     * 
     * "AYNI_GUN":
     * 
     * Sabit 150.0 TL (Ağırlık fark etmez).
     * 
     * "ULUSLARARASI":
     * 
     * Sabit 300.0 TL (Ağırlık fark etmez).
     * 
     * Geçersiz Durumlar:
     * 
     * Tanımlı olmayan bir kargo tipi girilirse (default), IllegalArgumentException
     * fırlatılmalı ve hata mesajı olarak "Geçersiz kargo tipi: " + kargoTipi
     * verilmelidir.
     * 
     * Beklentiler:
     * Kodunuzu yazarken ister geleneksel switch-case (break içeren) yapısını
     * kullanın, ister Java'nın modern switch expressions (-> ve yield) yapısını
     * tercih edin.
     */
    public static void main(String[] args) {
        Scanner girdi = new Scanner(System.in);
        System.out.println("Kargo tipleri;\n" +
                "STANDART\n" +
                "EXPRESS\n" +
                "AYNIGUN\n" +
                "ULUSLARARASI");

        try {
            System.out.println("Kargo tipi giriniz: ");
            String kargoTipi = girdi.nextLine();
            System.out.println("Kargo agirligi giriniz: ");
            double urunAgirligi = girdi.nextDouble();
            System.out.println(kargoVeTeslimatSuresi(kargoTipi, urunAgirligi) + "TL");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        girdi.close();

    }

    static double kargoVeTeslimatSuresi(String kargoTipi, double urunAgirligi) {
        // Urun agirligi kilogram cinsinden alinmistir!

        switch (kargoTipi) {
            case "STANDART" -> {
                if (urunAgirligi <= 2) {
                    return 30.00;
                } else if (urunAgirligi > 2 && urunAgirligi <= 5) {
                    return 50.00;
                } else {
                    return 80.00;
                }
            }
            case "EXPRESS" -> {
                if (urunAgirligi <= 3) {
                    return 80.00;
                } else {
                    return 120.00;
                }
            }
            case "AYNIGUN" -> {
                return 150.00;
            }
            case "ULUSLARARASI" -> {
                return 300.00;
            }
            default -> throw new IllegalArgumentException("Gecersiz kargo tipi: " + kargoTipi);

        }

    }

}
