# ChatResponseAPI

A simple and lightweight API to await player chat responses asynchronously in Spigot plugins.

[![JitPack](https://jitpack.io/v/Fameless9/ChatResponseAPI.svg)](https://jitpack.io/#Fameless9/ChatResponseAPI)

---

## ✨ Features

- Await player chat input asynchronously
- Works with timeouts
- Perfect for menus, prompts, or simple chat-based forms

---

## 📦 Installation

### Add JitPack to your repositories

<details>
<summary><code>build.gradle.kts</code> (Kotlin DSL)</summary>

```kotlin
repositories {
    maven("https://jitpack.io")
}
```
</details>

<details>
<summary><code>build.gradle</code> (Groovy DSL)</summary>

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
</details>

<details>
  <summary><code>pom.xml</code> (Maven)</summary>

  ```xml
  <repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
  </repositories>
  ```
</details>

---

### Add the dependency

<details>
<summary><code>build.gradle.kts</code></summary>

```kotlin
dependencies {
    compileOnly("com.github.Fameless9:ChatResponseAPI:1.0.0")
}
```
</details>

<details>
<summary><code>build.gradle</code></summary>

```groovy
dependencies {
    compileOnly 'com.github.Fameless9:ChatResponseAPI:1.0.0'
}
```
</details>

<details>
  <summary><code>pom.xml</code></summary>
  
  ```xml
  <dependency>
    <groupId>com.github.Fameless9</groupId>
    <artifactId>ChatResponseAPI</artifactId>
    <version>1.0.0</version>
    <scope>provided</scope>
  </dependency>
  ```
</details>

---

### Register the API listener

Register the ChatResponseListener in your onEnable method:
```java
getServer().getPluginManager().registerEvents(ChatResponseListener.INSTANCE, this);
```

---

## 🛠️ Usage Example

```java
player.sendMessage("What does rule 4 say?");
ChatResponseAPI.awaitResponse(player, 10_000).thenAccept(response -> {
    player.sendMessage("You answered: " + response);
});
```
---

## 📄 License

This project is licensed under the MIT License – see [LICENSE](LICENSE) for details.
