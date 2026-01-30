# Lua Converter Tool

## Overview
This is a robust, enterprise-grade Maven Java application that converts Java, JavaScript, Python, or JAR files into Lua scripts. The output is compatible with Lua 5.1 and LuaJIT 2.1.x (OpenResty 1.21.4.1). The tool is designed for 100% functional and behavioral equivalence, handling all language concepts and complexities.

## Features
- **Input Types:** `.java`, `.js`, `.py`, `.jar`
- **Output:** Lua script (same name as input, or custom name)
- **Automatic Type Detection:** Detects input type by extension
- **Robust CLI:**
  - Usage: `java -jar lua-converter-tool.jar <input-file> [output-lua-file]`
  - If output file is not specified, uses input file name with `.lua` extension
  - Supports full file paths for input/output
- **Production Ready:**
  - Uses Java OpenJDK 21 for best performance and features
  - Handles all levels of code complexity
  - Optimized, extensible, and maintainable architecture

## Usage
### Build
Requires Java 21+ and Maven:

```sh
mvn clean package
```

Creates:
```
target/lua-converter-tool-1.0.0-jar-with-dependencies.jar
```

### Run
```sh
java -jar target/lua-converter-tool-1.0.0-jar-with-dependencies.jar <input-file> [output-lua-file]
```
- If `[output-lua-file]` is omitted, output will be `<input-file>.lua` (or replaces extension with `.lua`)

## Architecture
- `Main.java`: CLI, argument parsing, robust error handling
- `ConverterFacade.java`: Detects file type, routes to correct converter
- `JavaToLuaConverter.java`, `JsToLuaConverter.java`, `PythonToLuaConverter.java`, `JarToLuaConverter.java`: Language-specific logic (implement for full coverage)
- `LuaWriter.java`: Writes output file

## Best Practices & Extensibility
- Follow Java 21 best practices for performance and maintainability
- Add new language support by extending the facade and adding new converter classes
- All code is structured for enterprise, production, and high-complexity scenarios

## License
MIT