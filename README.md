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

<!-- Dodaj tutaj zrzuty ekranu swojej aplikacji -->
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
      <td><img src="#" alt="ekran_ladowania" width="200"></td>
    </tr>
    <tr>
      <td>Ekran logowania</td>
      <td><img src="#" alt="ekran_logowania" width="200"></td>
    </tr>
    <tr>
      <td>Błąd logowania</td>
      <td><img src="#" alt="ekran_logowania" width="200"></td>
    </tr>
    <tr>
      <td>Panel czujników</td>
      <td><img src="#" alt="panel_urzadzen" width="200"></td>
    </tr>
    <tr>
      <td>Panel urządzeń</td>
      <td><img src="#" alt="sterowanie_urzadzeniem" width="200"></td>
    </tr>
    <tr>
      <td>Profil użytkownika</td>
      <td><img src="#" alt="sterowanie_urzadzeniem" width="200"></td>
    </tr>
    <!-- Dodaj więcej zrzutów ekranu według potrzeb -->
  </tbody>
</table>

---

## Autor

- Łukasz Sieradzki

---
