# Board Game Engine

Board Game Engine project contains the code to run board game logic written in [Board Game Language](https://github.com/gergelyszaz/board-game-langauge).

## Getting Started

### Prerequisites

To build the project you need the following:

* JDK8+ installed
* Maven installed
* allow snapshot dependencies in your `$HOME/.m2/settings.xml`:

```xml
<settings>
  <profiles>
<!-- Allow snapshots -->
    <profile>
     <id>allow-snapshots</id>
        <activation><activeByDefault>true</activeByDefault></activation>
     <repositories>
        <repository>
          <id>snapshots-repo</id>
          <url>https://oss.sonatype.org/content/repositories/snapshots</url>
          <releases><enabled>false</enabled></releases>
          <snapshots><enabled>true</enabled></snapshots>
        </repository>
      </repositories>
    </profile>
<!-- -->
  </profiles>
</settings>
```

### Building

```sh
mvn clean install
```

### Running Tests

```sh
mvn test
```
