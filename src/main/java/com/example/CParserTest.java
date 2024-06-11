package com.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

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

            // Iniciar el parsing a partir de la regla de inicio 'prog'
            ParseTree tree = parser.prog();

            // Imprimir el árbol de parsing
            System.out.println(tree.toStringTree(parser));
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de entrada: " + e.getMessage());
        }
    }
}
