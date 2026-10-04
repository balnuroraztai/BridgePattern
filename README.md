# Bridge Pattern – Shape Renderer

## Description

This project demonstrates the Bridge Design Pattern in Java.

The project separates shapes from their rendering implementations.  
The abstraction hierarchy contains `Shape`, `Circle`, and `Square`.  
The implementation hierarchy contains `Renderer`, `VectorRenderer`, `RasterRenderer`, and `AsciiRenderer`.

The Bridge pattern allows shapes and renderers to vary independently.

## Bridge Structure

### Abstraction
- `Shape` – abstract base class
- `Circle` – refined abstraction
- `Square` – refined abstraction

### Implementor
- `Renderer` – implementor interface
- `VectorRenderer` – concrete implementation
- `RasterRenderer` – concrete implementation
- `AsciiRenderer` – third implementation added after the base commit

`Shape` stores a reference to the `Renderer` interface:

```java
protected Renderer renderer;
```

This composition creates the bridge between the two hierarchies.

## Runtime Switching

The renderer can be changed at runtime using:

```java
setImplementation(Renderer renderer)
```

The same shape object is kept while only its renderer changes.

Test T5 verifies that:
- the object reference stays the same;
- the ID stays unchanged;
- the domain data stays unchanged;
- the output changes because a different renderer is used.

## Demo Tests

The project contains seven deterministic demo checks:

- T1 – Circle + VectorRenderer
- T2 – Circle + RasterRenderer
- T3 – Square + VectorRenderer
- T4 – Square + RasterRenderer
- T5 – Runtime implementation switching
- T6 – Circle + AsciiRenderer
- T7 – Square + AsciiRenderer

Expected final result:

```text
SUMMARY: 7/7 PASS
```

The complete demo output is stored in `demo-output.txt`.

## Build and Run

Java 17 is required.

Compile the project from the repository root:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
```

Run the demo:

```bash
java -cp out Main --demo
```

## Extension Proof

The initial version of the project contained:

- `VectorRenderer`
- `RasterRenderer`

After the initial version was working, the following base commit was created:

```text
1be9ce2e529bc4fe43bb7eee088dbcdcb56389b0
```

After this commit, `AsciiRenderer` was added as the third implementation.

The existing abstraction and implementation classes did not need to be modified to add the new renderer.

The extension proof is stored in:

```text
extension.diff
```

It was generated using:

```bash
git diff 1be9ce2e529bc4fe43bb7eee088dbcdcb56389b0..HEAD > extension.diff
```

## Project Files

```text
src/
├── Main.java
├── Shape.java
├── Circle.java
├── Square.java
├── Renderer.java
├── VectorRenderer.java
├── RasterRenderer.java
└── AsciiRenderer.java

sources.txt
demo-output.txt
extension.diff
README.md
report.pdf
```

## Pattern Roles

| Bridge Role | Project Class |
|---|---|
| Abstraction | Shape |
| Refined Abstraction | Circle, Square |
| Implementor | Renderer |
| Concrete Implementor | VectorRenderer, RasterRenderer, AsciiRenderer |