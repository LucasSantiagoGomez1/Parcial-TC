package com.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CParserTest {
    public static void main(String[] args) throws Exception {
        // Ruta al archivo de entrada
        String inputFile = "input/example.c";
        String input = new String(Files.readAllBytes(Paths.get(inputFile)));

        // Crear el lexer y el parser
        CLexer lexer = new CLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CParser parser = new CParser(tokens);

        // Iniciar el análisis sintáctico en la regla principal
        ParseTree tree = parser.prog();

        // Imprimir el árbol sintáctico
        System.out.println(tree.toStringTree(parser));
    }
}
