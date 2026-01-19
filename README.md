<div align="center">

# 🎨 KnglPlot v2 - "Ne Çizersen Satın Alıyorum!"

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Platform](https://img.shields.io/badge/Platform-Paper%2FSpigot-lightgrey?style=for-the-badge&logo=spigotmc)
![License](https://img.shields.io/badge/License-Private-red?style=for-the-badge)
![Owner](https://img.shields.io/badge/Owner-ErayCan_Özkenar-blue?style=for-the-badge)

<p align="center">
  <b>ErayCan Özkenar</b>'ın efsanevi <i>"100 Kişiyle Minecraft"</i> ve <i>"Çizim Yarışması"</i> serileri için<br>
  <b>AhmetJR</b> tarafından özel olarak geliştirilmiş gelişmiş arsa yönetim sistemi.
</p>

[YouTube Kanalı](https://www.youtube.com/@ErayCanOzkenar) • [Discord Sunucusu](https://discord.gg/seninlinkin)

</div>

---

## 📺 Video Vitrini (Proje Kullanım Alanları)

Bu eklenti, aşağıdaki video konseptlerinin teknik altyüsını oluşturmaktadır. Tıklayarak izleyebilirsiniz:

<div align="center">
  <table>
    <tr>
      <td align="center">
        <h3>💰 250₺ vs 25.000₺ Çizim!</h3>
        <a href="https://www.youtube.com/watch?v=VIDEO_ID_1">
          <img src="https://img.youtube.com/vi/VIDEO_ID_1/mqdefault.jpg" width="300" alt="Video 1 Kapak">
        </a>
        <br>
        <i>Arsalar fiyatlandırılıyor...</i>
      </td>
      <td align="center">
        <h3>🏗️ 100 Kişiyle İnşaat!</h3>
        <a href="https://www.youtube.com/watch?v=VIDEO_ID_2">
          <img src="https://img.youtube.com/vi/VIDEO_ID_2/mqdefault.jpg" width="300" alt="Video 2 Kapak">
        </a>
        <br>
        <i>Kaos ve eğlence bir arada!</i>
      </td>
    </tr>
  </table>
</div>

---

## 🔥 Temel Özellikler

Bu proje, standart plot pluginlerinden farklı olarak **içerik üretimi** odaklıdır:

* **🪄 Otomatik Arsa Dağıtımı (`/arsadagit`):**
    * Tek komutla sunucudaki 100+ oyuncuyu saniyeler içinde boş arsalara ışınlar.
    * Adminleri (ErayCan, AhmetJR) listeden hariç tutar.
* **💸 Dinamik Bütçe Sistemi (Renk Kodları):**
    * Haritada belirlenen renkli bölgelere (Lime, Magenta, Red vb.) giren oyunculara otomatik Title mesajı gider.
    * *Örn: Yeşil alana giren oyuncu ekranda "Bütçen: 20.000 TL" görür.*
* **🧨 Eleme ve Yok Etme:**
    * **Eleme Çubuğu:** Özel model (CustomModelData: 3131).
    * Bir oyuncu elendiğinde arsası **animasyonla havaya uçar (TNT/Air)** ve oyuncuya veda mesajı gösterilir.
* **⏳ Yarışma Modu:**
    * Süre başlatıldığında herkesin envanteri temizlenir ve Creative moda alınır.

---

## 🛠️ Komut Listesi

### 👑 Yönetici (ErayCan) Komutları

| Komut | Açıklama |
| :--- | :--- |
| `/knglplot başlat` | Geri sayımı başlatır, herkesi Creative yapar, kaosu tetikler. |
| `/knglplot arsadagit` | Oyuncuları karıştırır ve rastgele boş arsalara dağıtır. |
| `/knglplot ele <isim>` | Oyuncuyu eler, arsasını **SİLER** ve sunucudan atar/mesaj atar. |
| `/knglplot yoket <isim>` | Oyuncunun arsasını manuel olarak sıfırlar. |
| `/knglplot elemeçubuk` | Özel dokulu eleme çubuğunu verir. |
| `/admin puanla <tutar>` | Bakılan arsaya fiyat etiketi yapıştırır. |

### 🎨 Bütçe Alanı Test Komutları
*Bu komutlar config'deki koordinatları test etmek içindir.*

| Renk Kodu | Bütçe Değeri | Komut |
| :--- | :--- | :--- |
| 🟤 Kahverengi | **50 TL** | `/test brown` |
| 🌸 Pembe | **100 TL** | `/test pink` |
| 🟡 Sarı | **250 TL** | `/test yellow` |
| 🔵 Açık Mavi | **750 TL** | `/test lightblue` |
| 🔴 Kırmızı | **10.000 TL** | `/test red` |
| 🟢 Yeşil (Lime) | **20.000 TL** | `/test lime` |

---

## ⚙️ Yapılandırma (Config)

`config.yml` dosyasından renkli alanların koordinatları ve yasaklı bloklar ayarlanabilir.

```yaml
Main:
  prefix: '&6Sunucu&bIsmi &c| '
  admins:
    - 'AhmetJR'
    - 'Eray'

# Renkli alan koordinatları (Bütçe sistemi için)
Messageteam:
  limecords:
    pos1: {x: 351, y: -4, z: 273}
    pos2: {x: -272, y: 255, z: 319}