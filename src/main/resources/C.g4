grammar C;

prog:   stat+ ;

stat:   block
      | declaration
      | assignment
      | ifStat
      | forStat
      | whileStat
      | functionCall
      ;

block:  '{' stat* '}' ;

declaration: 'int' ID ';' ;

assignment: ID '=' expr ';' ;

expr:   expr ('+'|'-') expr
      | expr ('*'|'/') expr
      | INT
      | ID
      ;

ifStat: 'if' '(' expr ')' stat ('else' stat)? ;

forStat: 'for' '(' assignment expr ';' assignment ')' stat ;

whileStat: 'while' '(' expr ')' stat ;

functionCall: ID '(' (expr (',' expr)*)? ')' ';' ;

ID  :   [a-zA-Z_][a-zA-Z_0-9]* ;
INT :   [0-9]+ ;
WS  :   [ \t\r\n]+ -> skip ;
