grammar C;

// Regla de inicio
prog: (statement | functionDecl)* EOF;

// Bloques de código
block: '{' statement* '}';

// Declaraciones y asignaciones
statement: 
      varDecl
    | assignment
    | ifStatement
    | forStatement
    | whileStatement
    | functionCall
    | block
    | ';'  // statement vacío
    ;

varDecl: type ID ('=' expr)? ';';
assignment: ID '=' expr ';';

// Expresiones
expr: logicalExpr;

logicalExpr: equalityExpr ('&&' equalityExpr | '||' equalityExpr)*;
equalityExpr: relationalExpr ('==' relationalExpr | '!=' relationalExpr)*;
relationalExpr: additiveExpr ('<' additiveExpr | '>' additiveExpr | '<=' additiveExpr | '>=' additiveExpr)*;
additiveExpr: multiplicativeExpr ('+' multiplicativeExpr | '-' multiplicativeExpr)*;
multiplicativeExpr: unaryExpr ('*' unaryExpr | '/' unaryExpr | '%' unaryExpr)*;
unaryExpr: primaryExpr;
primaryExpr: 
      '(' expr ')'
    | ID
    | INT
    | FLOAT
    ;

// Declaración y llamada a función
functionDecl: type ID '(' params? ')' block;
functionCall: ID '(' args? ')' ';';

params: param (',' param)*;
param: type ID;

args: expr (',' expr)*;

// Estructuras de control
ifStatement: 'if' '(' expr ')' statement ('else' statement)?;
forStatement: 'for' '(' forControl ')' statement;
forControl: (varDecl | assignment | ';')? expr? ';'? assignment?;  // Corrección aquí
whileStatement: 'while' '(' expr ')' statement;

// Tipos de datos
type: 'int' | 'float' | 'void' | 'char';

// Tokens
ID: [a-zA-Z_][a-zA-Z_0-9]*;
INT: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]* | '.' [0-9]+;  // Reconocer números de punto flotante
WS: [ \t\r\n]+ -> skip;
COMMENT: '//' ~[\r\n]* -> skip;
