package com.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class CParserTest {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, proporciona el nombre del archivo de entrada.");
            return;
        }

        String inputFile = args[0];

        try {
            // Crear un CharStream que lee de un archivo
            CharStream input = CharStreams.fromFileName(inputFile);

            // Crear un lexer que consume el CharStream
            CLexer lexer = new CLexer(input);

            // Crear un buffer de tokens a partir del lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Crear un parser que consume los tokens
            CParser parser = new CParser(tokens);

            // Agregar un manejador de errores personalizado
            ErrorListener errorListener = new ErrorListener();
            parser.addErrorListener(errorListener);

            // Iniciar el parsing a partir de la regla de inicio 'prog'
            ParseTree tree = parser.prog();

            // Si hay errores sintácticos, reportarlos y salir
            if (errorListener.hasErrors()) {
                errorListener.printErrors();
                return;
            }

            // Mostrar el árbol sintáctico
            System.out.println("Árbol Sintáctico:");
            System.out.println(tree.toStringTree(parser));

            // Realizar el análisis semántico
            SemanticAnalyzer analyzer = new SemanticAnalyzer();
            analyzer.visit(tree);

            // Mostrar la tabla de símbolos
            analyzer.printSymbolTable();

            // Mostrar errores semánticos
            if (analyzer.hasErrors()) {
                analyzer.printErrors();
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de entrada: " + e.getMessage());
        }
    }
}

class ErrorListener extends BaseErrorListener {
    private List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg,
                            RecognitionException e) {
        errors.add("Error sintáctico en línea " + line + ":" + charPositionInLine + " - " + msg);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printErrors() {
        System.out.println("Errores sintácticos encontrados:");
        for (String error : errors) {
            System.out.println(error);
        }
    }
}

class SemanticAnalyzer extends CBaseVisitor<Void> {
    private SymbolTable symbolTable = new SymbolTable();
    private List<String> errors = new ArrayList<>();
    private Set<String> usedSymbols = new HashSet<>();
    private Set<String> initializedSymbols = new HashSet<>();

    @Override
    public Void visitVarDecl(CParser.VarDeclContext ctx) {
        String type = ctx.type().getText();
        String id = ctx.ID().getText();
        if (symbolTable.contains(id)) {
            errors.add("Error semántico: Doble declaración de '" + id + "'");
        } else {
            symbolTable.add(id, type);
            if (ctx.expr() != null) {
                initializedSymbols.add(id);
            }
        }
        return null;
    }

    @Override
    public Void visitAssignment(CParser.AssignmentContext ctx) {
        String id = ctx.ID().getText();
        if (!symbolTable.contains(id)) {
            errors.add("Error semántico: Uso de un identificador no declarado '" + id + "'");
        } else {
            initializedSymbols.add(id);
        }
        return null;
    }

    @Override
    public Void visitFunctionDecl(CParser.FunctionDeclContext ctx) {
        String id = ctx.ID().getText();
        if (symbolTable.contains(id)) {
            errors.add("Error semántico: Doble declaración de la función '" + id + "'");
        } else {
            symbolTable.add(id, "function");
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitPrimaryExpr(CParser.PrimaryExprContext ctx) {
        if (ctx.ID() != null) {
            String id = ctx.ID().getText();
            if (!symbolTable.contains(id)) {
                errors.add("Error semántico: Uso de un identificador no declarado '" + id + "'");
            } else {
                usedSymbols.add(id);
            }
        }
        return null;
    }

    @Override
    public Void visitProg(CParser.ProgContext ctx) {
        Void result = visitChildren(ctx);

        for (String id : symbolTable.getSymbols().keySet()) {
            if (!usedSymbols.contains(id)) {
                errors.add("Error semántico: Identificador declarado pero no usado '" + id + "'");
            }
        }

        return result;
    }

    public void printSymbolTable() {
        System.out.println("Tabla de Símbolos:");
        System.out.println(symbolTable);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printErrors() {
        System.out.println("Errores semánticos encontrados:");
        for (String error : errors) {
            System.out.println(error);
        }
    }
}

class SymbolTable {
    private Map<String, String> symbols = new HashMap<>();

    public void add(String id, String type) {
        symbols.put(id, type);
    }

    public boolean contains(String id) {
        return symbols.containsKey(id);
    }

    public Map<String, String> getSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : symbols.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
