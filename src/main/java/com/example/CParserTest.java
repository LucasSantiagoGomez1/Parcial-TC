package com.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
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
            parser.addErrorListener(new ErrorListener());

            // Iniciar el parsing a partir de la regla de inicio 'prog'
            ParseTree tree = parser.prog();

            // Realizar el análisis semántico
            SemanticAnalyzer analyzer = new SemanticAnalyzer();
            analyzer.visit(tree);

            // Mostrar la tabla de símbolos
            analyzer.printSymbolTable();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de entrada: " + e.getMessage());
        }
    }
}

class ErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg,
                            RecognitionException e) {
        System.err.println("Error sintáctico en línea " + line + ":" + charPositionInLine + " - " + msg);
    }
}

class SemanticAnalyzer extends CBaseVisitor<Void> {
    private SymbolTable symbolTable = new SymbolTable();
    private List<String> errors = new ArrayList<>();

    @Override
    public Void visitVarDecl(CParser.VarDeclContext ctx) {
        String type = ctx.type().getText();
        String id = ctx.ID().getText();
        if (symbolTable.contains(id)) {
            errors.add("Error semántico: Doble declaración de '" + id + "'");
        } else {
            symbolTable.add(id, type);
        }
        return null;
    }

    // Agregar métodos visit para otras reglas de la gramática, como assignment, functionDecl, etc.

    public void printSymbolTable() {
        System.out.println("Tabla de Símbolos:");
        System.out.println(symbolTable);
    }

    public void printErrors() {
        System.out.println("Errores encontrados:");
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : symbols.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}

