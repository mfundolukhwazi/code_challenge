Challenges Faced: 
**Dependency Conflict: **
Version 5.0.0 + Android fails with: Cannot inline bytecode built with JVM target 11
mockito version 5 had an issue with jvm target 1.8 and wanted jvm 11 so I had to downgrade it to version 4 which seemed to work and compile
https://github.com/mockito/mockito-kotlin/issues/488
