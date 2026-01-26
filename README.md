# LPK Smart Center

**LPK Smart Center** to aplikacja mobilna na platformę Android do zarządzania systemem Smart Home opartym na mikrokontrolerze ESP32. Aplikacja umożliwia użytkownikowi podgląd stanu czujników oraz urządzeń, ich zdalne sterowanie, a także odbiór powiadomień.Wykorzystuje Firebase do uwierzytelniania użytkowników, synchronizacji danych oraz obsługi powiadomień push, oferując nowoczesny interfejs użytkownika zbudowany w Jetpack Compose. 


---

## Funkcjonalności

- **Logowanie i rejestracja** – każdy użytkownik ma własne konto z integracją Firebase Authentication. 
- **Sterowanie urządzeniami** – zdalna kontrola urządzeń Smart Home w czasie rzeczywistym.
- **System powiadomień push** – powiadomienia FCM (Firebase Cloud Messaging) o zdarzeniach w systemie Smart Home.
- **Synchronizacja w czasie rzeczywistym** – natychmiastowa aktualizacja stanów urządzeń dzięki Firebase Realtime Database.
- **Automatyzacja** – konfiguracja i uruchamianie automatycznych scenariuszy dla urządzeń. 

---

## Stos technologiczny

- **Język:** Kotlin
- **Framework:** Jetpack Compose (UI)
- **Backend:** Firebase (Authentication, Realtime Database, Cloud Messaging, Functions)
- **Hardware:** ESP32 (mikrokontroler IoT)
- **Architektura:** MVVM (Model-View-ViewModel)
- **Nawigacja:** Navigation Compose
- **Zarządzanie stanem:** ViewModel, StateFlow

---

## Instalacja

1. Sklonuj repozytorium: 
   ```bash
   git clone https://github.com/sieluka/LPK_Smart_Center.git
   ```
2. Otwórz projekt w Android Studio. 
3. Skonfiguruj Firebase (zobacz sekcję "Konfiguracja Firebase" poniżej).
4. Zbuduj i uruchom aplikację na emulatorze lub urządzeniu fizycznym.

Wymagania:
- Android Studio Flamingo lub nowszy
- JDK 11+
- Emulator lub urządzenie z Androidem 9.0+ (API 28+)

---

## Dokumentacja Firebase:
```
https://firebase.google.com/docs
```

---

## Uprawnienia

Aplikacja wymaga następujących uprawnień:

- **POST_NOTIFICATIONS** – do wyświetlania powiadomień push (Android 13+)

---

## Zrzuty ekranu

<table>
  <thead>
    <tr>
      <th>Widok</th>
      <th>Zrzut ekranu</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Ekran ładowania</td>
      <td><img src="https://github.com/user-attachments/assets/18416f55-1fbf-440f-898c-db972921ea3b" alt="ekran_ladowania" width="200"></td>
    </tr>
    <tr>
      <td>Ekran logowania</td>
      <td><img src= "https://github.com/user-attachments/assets/59d4bb4a-5b3b-410b-8392-9064192a26f9" alt=E_Log" width="200"></td>
    </tr>
    <tr>
      <td>Błąd logowania</td>
      <td><img src="https://github.com/user-attachments/assets/18416f55-1fbf-440f-898c-db972921ea3b" alt="blad_logowania" width="200"></td>
    </tr>
    <tr>
      <td>Panel czujników</td>
      <td><img src="https://github.com/user-attachments/assets/18416f55-1fbf-440f-898c-db972921ea3b" alt="E_czuj" width="200"></td>
    </tr>
    <tr>
      <td>Panel urządzeń</td>
      <td><img src="https://github.com/user-attachments/assets/99ccae4f-34eb-43ad-9364-62f8c0221ec4" alt="E_urz" width="200"></td>
    </tr>
    <tr>
      <td>Profil użytkownika</td>
      <td><img src="https://github.com/user-attachments/assets/8fcb6cdb-562d-4f61-82e0-01bea2487908" alt="E_kont" width="200></td>
    </tr>
  </tbody>
</table>

---

## Autor

Łukasz Sieradzki

---

