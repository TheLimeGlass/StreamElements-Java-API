# Unofficial StreamElements Java API (SEJA)
This is a Java ported library of StreamElements API

This project is under heavy development.

Pull requests welcome.

## Adding SEJA as a dependency
SEJA currently uses Jitpack https://jitpack.io/#TheLimeGlass/StreamElements-Java-API/ALPHA
### Maven
In your `pom.xml` add:
```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
    <groupId>com.github.TheLimeGlass</groupId>
    <artifactId>StreamElements-Java-API</artifactId>
    <version>ALPHA</version>
</dependency>

```
### Gradle
In your `build.gradle` add: 
```groovy
repositories {
  jcenter()
  maven {
    url 'https://jitpack.io'
  }
}

dependencies {
  compile 'com.github.TheLimeGlass:StreamElements-Java-API:ALPHA'
}
```
Check the link above for SBT and leiningen support.
